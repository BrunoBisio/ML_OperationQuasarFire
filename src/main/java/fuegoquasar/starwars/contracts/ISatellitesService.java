package fuegoquasar.starwars.contracts;

import fuegoquasar.starwars.models.Position;
import fuegoquasar.starwars.models.Satellite;
import fuegoquasar.starwars.models.SatelliteResponse;

public interface ISatellitesService {
    
    public Position getLocation(float distance1, float distance2, float distance3) throws Exception;

    public String getMessage(String[] message1, String[] message2, String[] message3) throws Exception;

    public SatelliteResponse getResponse(Satellite[] satellites) throws Exception;

    public Satellite save(Satellite satellite, String satellite_name);

    public SatelliteResponse getResponse() throws Exception;
}
