package fuegoquasar.starwars.models;

public class Satellite {
    
    private long id;
    private String name;
    private Position position;
    private double distance;
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
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public String[] getMessage() {
        return message;
    }
    public void setMessage(String[] message) {
        this.message = message;
    }
    
}
