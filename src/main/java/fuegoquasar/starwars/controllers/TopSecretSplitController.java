package fuegoquasar.starwars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fuegoquasar.starwars.contracts.ISatellitesService;
import fuegoquasar.starwars.models.ErrorResponse;
import fuegoquasar.starwars.models.Satellite;
import fuegoquasar.starwars.models.SatelliteResponse;

@RestController
@RequestMapping("topsecret_split")
public class TopSecretSplitController {
    
    @Autowired
    private ISatellitesService service;

    @PostMapping("/{satellite_name}")
    public ResponseEntity<Object> index(@RequestBody Satellite satellite, @RequestParam String satellite_name) {
        Satellite savedSatellite = service.save(satellite, satellite_name);
        if (savedSatellite == null){
            ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, "Ocurrio un error durante el salvado");
            return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
        }
        
        return ResponseEntity.ok(savedSatellite);
    }

    @GetMapping("/")
    public ResponseEntity<Object> index() {
        SatelliteResponse response = service.getResponse();
        if (response == null) {
            ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, "No hay suficiente información");
            return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
        }
            
        return ResponseEntity.ok(response);
    }
}
