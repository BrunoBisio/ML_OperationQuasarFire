package fuegoquasar.starwars.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import fuegoquasar.starwars.contracts.ISatellitesService;
import fuegoquasar.starwars.models.Satellite;
import fuegoquasar.starwars.models.SatelliteResponse;

@RestController
@RequestMapping("topsecret")
public class APIController {
    
    @Autowired
    private ISatellitesService service;

    @PostMapping("/")
    public ResponseEntity<SatelliteResponse> index(@RequestBody Satellite[] satellites) {
        if (satellites.length < 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You are missing at least one satellite");
        }
        if (satellites.length > 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sorry, but I am not prepared for more than 3 satellites");
        }
        SatelliteResponse body = service.getResponse(satellites);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/")
    public String index(){
        return "Hello from tomcat!";
    }
}