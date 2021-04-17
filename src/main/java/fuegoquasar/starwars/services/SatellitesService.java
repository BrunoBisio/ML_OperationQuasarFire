package fuegoquasar.starwars.services;

import org.springframework.stereotype.Service;

import fuegoquasar.starwars.contracts.ISatellitesService;
import fuegoquasar.starwars.models.Position;
import fuegoquasar.starwars.models.Satellite;
import fuegoquasar.starwars.models.SatelliteResponse;

@Service
public class SatellitesService implements ISatellitesService {

    /*
    Glosario:
        [nombreDeSatelite]_p --> indica la posicion del satelite
        [nombreDeSatelite]_d --> indica el valor de la distancia del satelite
        [nombreDeSatelite]_m --> indica el mensaje del satelite
    */

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
    public Position getLocation(float kenobi_d, float skywalker_d, float sato_d) {
        // P3 - P1
        Position difPos_31 = subPos(sato_p, kenobi_p);
        // P2 - P1
        Position difPos_21 = subPos(skywalker_p, kenobi_p);
        // d = ‖P2 - P1‖
        float d = magnitude(difPos_21);
        // ex = (P2 - P1) / ‖P2 - P1‖
        Position ex = divEscalar(difPos_21, d);
        // i = ex(P3 - P1)
        float i = multiPos(ex, difPos_31);
        // P3 - P1 - i · ex
        Position difPos_31i = subPos(difPos_31, multiEscalar(ex, i));
        // ey = (P3 - P1 - i · ex) / ‖P3 - P1 - i · ex‖
        Position ey = divEscalar(difPos_31i, magnitude(difPos_31i));
        // j = ey(P3 - P1)
        float j = multiPos(ey, difPos_31);
        // x = (r1^2 - r2^2 + d^2) / 2d
        float x = (pow2(kenobi_d) - pow2(skywalker_d) + pow2(d)) / (2*d);
        // y = (r1^2 - r3^2 + i^2 + j^2) / 2j - ix / j
        float y = ((pow2(kenobi_d) - pow2(sato_d) + pow2(i) + pow2(j)) / (2*j)) - ((i*x) /j);
        // p1,2 = P1 + x*ex + y*ey
        Position p = sumPos(kenobi_p, sumPos(multiEscalar(ex, x), multiEscalar(ey, y)));
        // return position
        return new Position(Math.round(p.getX()), Math.round(p.getY()));
    }

    @Override
    public String getMessage(String[] kenobi_m, String[] skywalker_m, String[] sato_m) {
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
    public SatelliteResponse getResponse(Satellite[] satellites) {
        SatelliteResponse response = new SatelliteResponse();
        response.setMessage(getMessage(satellites[0].getMessage(), satellites[1].getMessage(), satellites[2].getMessage()));
        response.setPosition(getLocation(satellites[0].getDistance(), satellites[1].getDistance(), satellites[2].getDistance()));
        return response;
    }
    
    // utils for getMessage
    private String getMessageFromArrays(String[] baseArray, String[] firstInjection, String[] secondInjection) {
        addMissingWords(baseArray, firstInjection);
        addMissingWords(baseArray, secondInjection);
        return String.join(" ", baseArray).trim();
    }

    private void addMissingWords(String[] baseArray, String[] injectedArray){
        for (int i = baseArray.length-1, j = injectedArray.length-1; i != 0; i--, j--) {
            if (baseArray[i] == "" && injectedArray[j] != "") {
                baseArray[i] = injectedArray[j];
            }
        }
    }

    // utils for getLocation
    public Position sumPos(Position pos1, Position pos2) {
        return new Position(pos1.getX() + pos2.getX(), pos1.getY() + pos2.getY());
    }

    public Position subPos(Position pos1, Position pos2) {
        return new Position(pos1.getX() - pos2.getX(), pos1.getY() - pos2.getY());
    }

    public float magnitude(Position pos) {
        return (float) Math.sqrt(pow2(pos.getX()) + pow2(pos.getY()));
    }

    private float pow2(float number) {
        return number * number;
    }

    private Position multiEscalar(Position pos, float number) {
        return new Position(number * pos.getX(), number * pos.getY());
    }

    private float multiPos(Position pos1, Position pos2) {
        return pos1.getX() * pos2.getX() + pos1.getY() * pos2.getY();
    }

    private Position divEscalar(Position pos, float number) {
        return new Position(pos.getX() / number, pos.getY() / number);
    }
}
