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
import org.springframework.web.server.ResponseStatusException;

import fuegoquasar.starwars.contracts.ISatellitesService;
import fuegoquasar.starwars.models.Satellite;
import fuegoquasar.starwars.models.SatelliteResponse;

@RestController
@RequestMapping("topsecret_split")
public class TopSecretSplitController {
    
    @Autowired
    private ISatellitesService service;

    @PostMapping("/{satellite_name}")
    public ResponseEntity<Satellite> index(@RequestBody Satellite satellite, @RequestParam String satellite_name) {
        Satellite savedSatellite = service.save(satellite, satellite_name);
        if (savedSatellite != null)
            return ResponseEntity.ok(savedSatellite);
        return new ResponseEntity<Satellite>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/")
    public ResponseEntity<SatelliteResponse> index() {
        /*SatelliteResponse response = null;
        try {
            SatelliteResponse response = service.getResponse();
        } catch (Exception ex) {
            // Ver si puedo obtener cual es la informacion faltante o que informacion si tengo
        }*/
        SatelliteResponse response = service.getResponse();
        if (response == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No hay suficiente información");
        
        return ResponseEntity.ok(response);
    }
}
