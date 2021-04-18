package fuegoquasar.starwars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fuegoquasar.starwars.contracts.ISatellitesService;
import fuegoquasar.starwars.dtos.SatellitesDto;
import fuegoquasar.starwars.models.SatelliteResponse;

@RestController
@RequestMapping("topsecret")
public class TopSecretController {
    
    @Autowired
    private ISatellitesService service;

    @PostMapping("/")
    public ResponseEntity<SatelliteResponse> index(@RequestBody SatellitesDto satellitesDto) {
        if (satellitesDto.getSatellites().length != 3)
            return new ResponseEntity<SatelliteResponse>(new HttpHeaders(), HttpStatus.NOT_FOUND);
        
        SatelliteResponse response = service.getResponse(satellitesDto.getSatellites());

        if (response.equals(null))
            return new ResponseEntity<SatelliteResponse>(new HttpHeaders(), HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(response);
    }
}