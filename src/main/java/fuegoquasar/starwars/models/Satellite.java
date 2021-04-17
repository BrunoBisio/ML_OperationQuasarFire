package fuegoquasar.starwars.models;

public class Satellite {
    
    private long id;
    private String name;
    private Position position;
    private float distance;
    private String[] message;
    
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public float getDistance() {
        return distance;
    }
    public void setDistance(float distance) {
        this.distance = distance;
    }
    public String[] getMessage() {
        return message;
    }
    public void setMessage(String[] message) {
        this.message = message;
    }
    
}
