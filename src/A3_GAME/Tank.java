package A3_GAME;

public class Tank extends Sprite implements Runnable {

    public int orientation;
    private int vel;
    private boolean moving;
    private int moveDir;
    private boolean turning;
    private boolean firing;
    private boolean tankStatus;
    public int tankLives;

    public Missile missile = new Missile(0, 0, 0, 0, false);

//    private ImageIcon tankIcon = new ImageIcon(
//            new ImageIcon("image2/tank0.png")
//                    .getImage()
//                    .getScaledInstance(tankWidth, tankHeight, java.awt.Image.SCALE_SMOOTH)
//    );


    public Tank(int x, int y, int width, int height) {
        super(x, y, width, height);
        orientation = 0;
        tankLives = 3;
        vel = 15;
        moving = false;
        turning = false;
        firing = false;
        moveDir = 0;
        tankStatus = false;
        setImage("tank.png");
    }

    public void updateTank() {
        if (firing) {
            if (!missile.isVisible()) {
                double dimX = (x - 10) + this.getWidth() / 2;
                double dimY = (y - 10) + this.getWidth() / 2;
                missile = new Missile(dimX, dimY, 60, orientation * 45);
                missile.setImage("missileTank.png");
                missile.setVisible(true);
            }

            firing = false;
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (moveDir == 0) {
            move(moveDir);
        } else if (moveDir == 2) {
            move(moveDir);
        } else if (moveDir == 1) {
            orientation = ((orientation + 1) % 8);
        } else if (moveDir == 3) {
            orientation = ((orientation + 7) % 8);
        }

    }

    //move:
    //0 -> forward
    //1 -> turn right
    //2 -> backward
    //3 -> turn left
    public void run(int move, boolean fire) {
        moveDir = move;
        if (fire) firing = true;
        else if (move == 0 || move == 2) moving = true;
        else turning = true;

        tankStatus = true;
    }

    public void move(int dir) {
        double w;
        int flag = (dir == 0) ? 1 : -1;
        switch (orientation) {
            case 0: {
                y = y - flag * vel;
            }
            ;
            break;
            case 1: {
                w = Math.sin(Math.toRadians(45)) * vel * (-flag);
                x = x - w;
                y = y + w;

            }
            ;
            break;
            case 2: {
                x = x + flag * vel;
            }
            ;
            break;
            case 3: {
                w = Math.sin(Math.toRadians(45)) * vel * (flag);
                x = x + w;
                y = y + w;
            }
            ;
            break;
            case 4: {
                y = y + flag * vel;
            }
            ;
            break;
            case 5: {
                w = Math.sin(Math.toRadians(45)) * vel * (flag);
                x = x - w;
                y = y + w;
            }
            ;
            break;
            case 6: {
                x = x - flag * vel;
            }
            ;
            break;
            case 7: {
                w = Math.sin(Math.toRadians(45)) * vel * (-flag);
                x = x + w;
                y = y + w;
            }
            ;
            break;
        }
    }

    @Override
    public void run() {
        while (true) {
            //System.out.println("inside run tank000");
            if (tankStatus) {
                updateTank();
                tankStatus = false;
            }
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


//    private void resetIcon(String name) {
//        ImageIcon tankIcon = new ImageIcon(
//                new ImageIcon("image2/" + name + ".png")
//                        .getImage()
//                        .getScaledInstance(tankWidth, tankHeight, java.awt.Image.SCALE_SMOOTH)
//        );
//        setIcon(tankIcon);
//    }

//    public Image getImage() {
//        ImageIcon tc = new ImageIcon("image2/tank0.png");
//        return tc.getImage();
//    }
}
