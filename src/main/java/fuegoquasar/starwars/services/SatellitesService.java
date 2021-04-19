package fuegoquasar.starwars.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fuegoquasar.starwars.contracts.ISatellitesService;
import fuegoquasar.starwars.models.Position;
import fuegoquasar.starwars.models.Satellite;
import fuegoquasar.starwars.models.SatelliteResponse;
import fuegoquasar.starwars.repositories.SatelliteRepository;
import fuegoquasar.starwars.utils.MathUtils;
import fuegoquasar.starwars.utils.MessageUtils;

@Service
public class SatellitesService implements ISatellitesService {

    /*
    Glosario:
        [nombreDeSatelite]_p --> indica la posicion del satelite
        [nombreDeSatelite]_d --> indica el valor de la distancia del satelite
        [nombreDeSatelite]_m --> indica el mensaje del satelite
    */
    
    @Autowired
    private SatelliteRepository repository;

    Position kenobi_p = new Position(-500, -200);
    Position skywalker_p = new Position(100, -100);
    Position sato_p = new Position(500, 100);

    /***
     * Para el calculo de getLocation se uso el metodo de trilateración
     * al final de los calculos se realiza un redondeo de los valores de
     * x e y con el fin de evitar problemas de precision que puedan ocurrir
     * en los tipos de datos double y float de java.
     * Otra opcion para solucionar este problema seria pasar a usar el tipo
     * de dato BigDecimal.
     */
    @Override
    public Position getLocation(float kenobi_d, float skywalker_d, float sato_d) throws Exception {
        Position location;
        try {
            // P3 - P1
            Position difPos_31 = MathUtils.subPos(sato_p, kenobi_p);
            // P2 - P1
            Position difPos_21 = MathUtils.subPos(skywalker_p, kenobi_p);
            // d = ‖P2 - P1‖
            float d = MathUtils.magnitude(difPos_21);
            // ex = (P2 - P1) / ‖P2 - P1‖
            Position ex = MathUtils.divEscalar(difPos_21, d);
            // i = ex(P3 - P1)
            float i = MathUtils.multiPos(ex, difPos_31);
            // P3 - P1 - i · ex
            Position difPos_31i = MathUtils.subPos(difPos_31, MathUtils.multiEscalar(ex, i));
            // ey = (P3 - P1 - i · ex) / ‖P3 - P1 - i · ex‖
            Position ey = MathUtils.divEscalar(difPos_31i, MathUtils.magnitude(difPos_31i));
            // j = ey(P3 - P1)
            float j = MathUtils.multiPos(ey, difPos_31);
            // x = (r1^2 - r2^2 + d^2) / 2d
            float x = (MathUtils.pow2(kenobi_d) - MathUtils.pow2(skywalker_d) + MathUtils.pow2(d)) / (2*d);
            // y = (r1^2 - r3^2 + i^2 + j^2) / 2j - ix / j
            float y = ((MathUtils.pow2(kenobi_d) - MathUtils.pow2(sato_d) + MathUtils.pow2(i) + MathUtils.pow2(j)) / (2*j)) - ((i*x) /j);
            // p = P1 + x*ex + y*ey
            Position p = MathUtils.sumPos(kenobi_p, MathUtils.sumPos(MathUtils.multiEscalar(ex, x), MathUtils.multiEscalar(ey, y)));
            // se redondea para tratar de evitar problemas de redondeo generados por el tipo de dato float
            location = new Position(Math.round(p.getX()), Math.round(p.getY()));
        }
        catch(Exception ex) {
            throw new Exception("No se pudo calcular la posicion de la nave porta carga");
        }
        // return position
        return location;
    }

    @Override
    public String getMessage(String[] kenobi_m, String[] skywalker_m, String[] sato_m) throws Exception {
        String output;
        // busco el menor de los arrays
        int value = Math.min(Math.min(kenobi_m.length, skywalker_m.length), sato_m.length);
        if (value == kenobi_m.length) {
            output = getMessageFromArrays(kenobi_m, skywalker_m, sato_m);
        } else if (value == skywalker_m.length) {
            output = getMessageFromArrays(skywalker_m, kenobi_m, sato_m);
        } else {
            output = getMessageFromArrays(sato_m, kenobi_m, skywalker_m);
        }
        return output;
    }

    @Override
    public Satellite save(Satellite satellite, String satellite_name) {
        satellite.setName(satellite_name);
        Satellite savedSatellite = repository.save(satellite);
        return savedSatellite;
    }

    @Override
    public SatelliteResponse getResponse(Satellite[] satellites) throws Exception {
        HashMap<String, Satellite> satellitesMap = new HashMap<>();
        for (int i = 0; i < satellites.length; i++) {
            satellitesMap.put(satellites[i].getName().toLowerCase(), satellites[i]);
        }
        if (satellitesMap.values().size() != 3) {
            throw new Exception("Falta informacion de al menos 1 satelite");
        }
        return buildResponse(satellitesMap.get("kenobi"), satellitesMap.get("skywalker"), satellitesMap.get("sato"));
    }
    
    @Override
    public SatelliteResponse getResponse() throws Exception {
        // considero que en caso que se haya mas de una vez alguno de los satellites
        // siempre utilizo la ultima carga de cada uno
        Satellite kenobi_s = repository.findLastByName("kenobi");
        Satellite skywalker_s = repository.findLastByName("skywalker");
        Satellite sato_s = repository.findLastByName("sato");
        
        if (kenobi_s == null || skywalker_s == null || sato_s == null) {
            throw new Exception("No se tiene informacion sobre al menos 1 de los satelites");
        }

        return buildResponse(kenobi_s, skywalker_s, sato_s);
    }
    
    private static String getMessageFromArrays(String[] baseArray, String[] firstInjection, String[] secondInjection) throws Exception{
        MessageUtils.addMissingWords(baseArray, firstInjection);
        MessageUtils.addMissingWords(baseArray, secondInjection);
        if (MessageUtils.isComplete(baseArray)) {
            return String.join(" ", baseArray).trim();
        }
        throw new Exception("No se pudo decifrar el mensaje de ayuda");
    }

    private SatelliteResponse buildResponse(Satellite kenobi_s, Satellite skywalker_s, Satellite sato_s) throws Exception {
        SatelliteResponse response = new SatelliteResponse();
        Position location = getLocation(kenobi_s.getDistance(), skywalker_s.getDistance(), sato_s.getDistance());
        String message = getMessage(kenobi_s.getMessage(), skywalker_s.getMessage(), sato_s.getMessage());
        response.setPosition(location);
        response.setMessage(message);
        return response;
    }
}
