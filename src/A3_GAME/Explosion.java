package A3_GAME;

public class Explosion extends Sprite {
    public Explosion(double x, double y, int width, int height, boolean visible) {
        super(x, y, width, height, visible);
        this.setImage("collide.gif");
    }
}
