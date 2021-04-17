package fuegoquasar.starwars.dtos;

import fuegoquasar.starwars.models.Satellite;

public class SatellitesDto {
    private Satellite[] satellites;

    /**
     * @return the satellites
     */
    public Satellite[] getSatellites() {
        return satellites;
    }

    /**
     * @param satellites the satellites to set
     */
    public void setSatellites(Satellite[] satellites) {
        this.satellites = satellites;
    }
      
}
