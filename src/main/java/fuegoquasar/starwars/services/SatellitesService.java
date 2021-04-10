package fuegoquasar.starwars.services;

import org.springframework.stereotype.Service;

import fuegoquasar.starwars.contracts.ISatellitesService;
import fuegoquasar.starwars.models.Position;
import fuegoquasar.starwars.models.Satellite;
import fuegoquasar.starwars.models.SatelliteResponse;

@Service
public class SatellitesService implements ISatellitesService {

    /*
        (x1, y1) = [-500, -200]
        (x2, y2) = [100,-100]
        (x3, y3) = [500, 100]
    */
    double x1 = -500;
    double y1 = -200;
    double x2 = 100;
    double y2 = -100;
    double x3 = 500;
    double y3 = 100;

    @Override
    public Position getLocation(double distance1, double distance2, double distance3) {
        // Busco el resultado de la resta entre las equaciones de las circunferencias 2 y 1
        // luego de despejar x y reemplazarlo en la equacion de de la circunferencia 1
        double[] y_roots = getRootsForYAxis(x1, y1, distance1, x2, y2, distance2);
        // reemplazo los valores 2 valores de y en la equacion de la circunferencia 1
        double[] x_roots_1 = new double[2];
        double[] x_roots_2 = new double[2];
        double x_root = 0;
        Position position = null;

        // aux_i = (di^2 - xi^2 - yi^2)
        double aux_1 = pow2(distance1) - pow2(x1) - pow2(y1);
        double aux_2 = pow2(distance2) - pow2(x2) - pow2(y2);

        for(double y_root: y_roots) {
            x_roots_1 = getRoots(0, -2 * x1, pow2(y_root) - 2 * y1 * y_root - aux_1);
            x_roots_2 = getRoots(0, -2 * x2, pow2(y_root) - 2 * y2 * y_root - aux_2);
            
            if (contains(x_roots_1[0], x_roots_2)) {
                x_root = x_roots_1[0];
            } else if (contains(x_roots_1[1], x_roots_2)) {
                x_root = x_roots_1[1];
            } else {
                // TODO: las distancias ingresadas no son correctas
            }
            
            // si la posicion existe en el diametro significa que encontre la posicion deseada.
            if (pow2(x_root - x3) + pow2(y_root - y3) == pow2(distance3)) {
                position = new Position(x_root, y_root);
                break;
            }
        }

        return position;
    }

    @Override
    public String getMessage(String[] message1, String[] message2, String[] message3) {
        String[] messageOutput;
        int outputCounter = 0;
        // busco el menor de los arrays
        int value = Math.min(Math.min(message1.length, message2.length), message3.length);
        if (value == message1.length) {
            messageOutput = message1;
            outputCounter = message1.length-1;
            for (int i = message2.length-1; i != 0; i--, outputCounter--) {
                if (message2[i] != "" && messageOutput[outputCounter] == "") {
                    messageOutput[outputCounter] = message2[i];
                }
            }
            outputCounter = message1.length-1;
            for (int j = message3.length-1; j != 0; j--, outputCounter--) {
                if (message3[j] != "" && messageOutput[outputCounter] == "") {
                    messageOutput[outputCounter] = message2[j];
                }
            }
        }
        return "";
    }

    @Override
    public SatelliteResponse getResponse(Satellite[] satellites) {
        // TODO Auto-generated method stub
        return null;
    }

    /// Siendo la equiacion de una circunferencia
    ///     (x - xi)^2 + (y-yi)^2 = di^2
    /// la funcion espera las posiciones de x e y de cada satelite
    /// y la distancia entre cada posicion y el punto buscado.
    private double[] getRootsForYAxis(double x1, double y1, double d1, double x2, double y2, double d2) {
        // acomodo los valores para agrupar x e y
        // x^2 - 2*xi*x + y^2 - 2*yi*y - (di^2 - xi^2 - yi^2) = 0
        // aux_i = di^2 - xi^2 - yi^2
        double aux_1 = pow2(d1) - pow2(x1) - pow2(y1);
        double aux_2 = pow2(d2) - pow2(x2) - pow2(y2);

        // calculo de A:
        double a = (aux_2-aux_1) * (y2 - y1) / (x1 - x2);
        a -= ((2 * (aux_2-aux_1)) / (x1 - x2));
        a -= (2 * y1);
        // calculo de B:
        double b = (pow2(y2 - y1) / pow2(x1 - x2)) + 1;
        // calculo de C:
        double c = (pow2(aux_2 - aux_1) / 4 * (x1 - x2)) - aux_1 - x1 * (aux_2 - aux_1) / (x1 - x2);
        
        return getRoots(a, b, c);
    }

    /// espera los valores para el calculo de las raices de una equacion cuadratrica.
    private double[] getRoots(double a, double  b, double  c) {
        double[] roots = new double[2];
        double sqrt = Math.sqrt(pow2(b) - 4 * a * c);
        roots[0] = (b + sqrt) / (2 * a);
        roots[1] = (b - sqrt) / (2 * a);
        return roots;
    }

    private double pow2(double value){
        return Math.pow(value, 2);
    }

    private boolean contains(double value, double[] array) {
        return value == array[0] || value == array[1];
    }
}
