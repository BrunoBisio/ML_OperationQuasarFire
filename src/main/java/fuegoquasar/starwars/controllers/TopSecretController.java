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
import fuegoquasar.starwars.models.ErrorResponse;
import fuegoquasar.starwars.models.SatelliteResponse;

@RestController
@RequestMapping("topsecret")
public class TopSecretController {
    
    @Autowired
    private ISatellitesService service;

    @PostMapping("/")
    public ResponseEntity<Object> index(@RequestBody SatellitesDto satellitesDto) {
        try {
            SatelliteResponse response = service.getResponse(satellitesDto.getSatellites());
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
            return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
        }
    }
}