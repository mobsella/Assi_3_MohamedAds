package A3_GAME;

public class House extends Sprite implements Runnable {
    private int fireDir;
    boolean houseStatus;
    public int houseLives;
    public Missile missile = new Missile(0, 0, 0, 0, false);

    public House(int x, int y, int width, int height) {
        super(x, y, width, height);
        houseStatus = false;
        houseLives = 3;
        fireDir = 0;
        setImage("house.png");
    }

    private void updateHouse() {
        if(!missile.isVisible()) {
            double dimX = (x - 10) + this.getWidth() / 2;
            double dimY = (y - 10) + this.getHeight() / 2;
            missile = new Missile(dimX, dimY, 15, fireDir);
            missile.setImage("missileTank.png");
            missile.setVisible(true);
        }
    }

    public void run(int dir) {
        fireDir = dir;
        houseStatus = true;
    }


    @Override
    public void run() {
        while (true) {
            if (houseStatus) {
                updateHouse();
                houseStatus = false;
            }
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
