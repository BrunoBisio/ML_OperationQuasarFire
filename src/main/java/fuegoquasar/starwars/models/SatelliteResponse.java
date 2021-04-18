package fuegoquasar.starwars.models;

public class SatelliteResponse {
    private Position position;
    private String message;
    
    /**
     * @return the position
     */
    public Position getPosition() {
        return position;
    }
    
    /**
     * @param position the position to set
     */
    public void setPosition(Position position) {
        this.position = position;
    }
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
