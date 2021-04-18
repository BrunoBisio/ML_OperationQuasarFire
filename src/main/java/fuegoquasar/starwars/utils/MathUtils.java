package fuegoquasar.starwars.utils;

import fuegoquasar.starwars.models.Position;

public class MathUtils {
    
    public static Position sumPos(Position pos1, Position pos2) {
        return new Position(pos1.getX() + pos2.getX(), pos1.getY() + pos2.getY());
    }

    public static Position subPos(Position pos1, Position pos2) {
        return new Position(pos1.getX() - pos2.getX(), pos1.getY() - pos2.getY());
    }

    public static float magnitude(Position pos) {
        return (float) Math.sqrt(pow2(pos.getX()) + pow2(pos.getY()));
    }

    public static float pow2(float number) {
        return number * number;
    }

    public static Position multiEscalar(Position pos, float number) {
        return new Position(number * pos.getX(), number * pos.getY());
    }

    public static float multiPos(Position pos1, Position pos2) {
        return pos1.getX() * pos2.getX() + pos1.getY() * pos2.getY();
    }

    public static Position divEscalar(Position pos, float number) throws Exception {
        if (number == 0)
            throw new Exception("El divisor no puede ser 0");
        return new Position(pos.getX() / number, pos.getY() / number);
    }
}
