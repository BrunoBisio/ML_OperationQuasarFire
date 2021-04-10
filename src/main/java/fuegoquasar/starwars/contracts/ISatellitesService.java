package fuegoquasar.starwars.contracts;

import fuegoquasar.starwars.models.Position;
import fuegoquasar.starwars.models.Satellite;
import fuegoquasar.starwars.models.SatelliteResponse;

public interface ISatellitesService {
    
    public Position getLocation(double distance1, double distance2, double distance3);

    public String getMessage(String[] message1, String[] message2, String[] message3);

    public SatelliteResponse getResponse(Satellite[] satellites);
}
