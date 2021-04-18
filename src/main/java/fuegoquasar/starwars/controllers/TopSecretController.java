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
        if (satellitesDto.getSatellites().length != 3) {
            ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, "Por favor, ingresar solo 3 satelites");
            return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
        }
        
        SatelliteResponse response = service.getResponse(satellitesDto.getSatellites());

        if (response.equals(null)) {
            ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, "Ocurrio un error al tratar de calcular la posicion o de obtener el mensaje");
            return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
        }
        return ResponseEntity.ok(response);
    }
}