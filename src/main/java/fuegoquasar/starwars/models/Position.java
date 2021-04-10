package fuegoquasar.starwars.models;


public class Position {

    private long id;
    private double x;
    private double y;
    
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public long getId() {
        return id;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    
}
