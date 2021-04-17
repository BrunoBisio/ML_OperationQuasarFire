package fuegoquasar.starwars.models;


public class Position {

    private long id;
    private float x;
    private float y;
    
    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public long getId() {
        return id;
    }
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }
    
}
