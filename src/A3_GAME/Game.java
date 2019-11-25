package A3_GAME;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game extends JPanel implements KeyListener, Runnable {
//    ImageIcon ic = new ImageIcon(
//            new ImageIcon(getClass().getResource("black22.png"))
//                    .getImage()
//                    .getScaledInstance(frameWidth, frameHeight, java.awt.Image.SCALE_SMOOTH)
//    );

    //private ImageIcon tankIcon = new ImageIcon("image2/tank.png")

    //    private ImageIcon tankIcon = new ImageIcon(
//            new ImageIcon("image2/tank.png")
//                    .getImage()
//                    .getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)
//    );
//
//    private JLabel tank = new JLabel(tankIcon);


    private Explosion ex;
    private Tank tank;
    private House house;
    private Mountain mountain;
    private JLabel endMenu;
    private boolean gameOver = false;
    private Mine mine, mine2;
    private boolean gameStatus = false;

    public Game() {
        addKeyListener(this);
        setFocusable(true);
        setSize(1000, 800);
        //setLayout(null);

        //tank = new Tank();
        //add(tank);

        setVisible(true);
        Random rand = new Random();
        int upperBoundY = this.getHeight();
        int upperBoundX = this.getWidth();
        mine = new Mine(rand.nextInt(upperBoundX), rand.nextInt(upperBoundY), 100, 100);
        mine2 = new Mine(rand.nextInt(upperBoundX), rand.nextInt(upperBoundY), 100, 100);
        endMenu = new JLabel("Game Over", JLabel.CENTER);
        endMenu.setVisible(false);
        this.add(endMenu);
        tank = new Tank(rand.nextInt(upperBoundX), rand.nextInt(upperBoundY), 100, 100);
        house = new House(600, 400, 100, 100);
        ex = new Explosion(300, 300, 300, 300, false);
        mountain = new Mountain(rand.nextInt(upperBoundX), rand.nextInt(upperBoundY), 200, 200);
        new Thread(this).start();
        new Thread(tank).start();
        new Thread(house).start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            System.out.println("UP#########");
            tank.run(0, false);
        } else if (code == KeyEvent.VK_RIGHT) {
            System.out.println("RIGHT#########");
            tank.run(1, false);
        } else if (code == KeyEvent.VK_DOWN) {
            System.out.println("DOWN#########");
            tank.run(2, false);
        } else if (code == KeyEvent.VK_LEFT) {
            System.out.println("LEFT#########");
            tank.run(3, false);
        } else if (code == KeyEvent.VK_SPACE) {
            System.out.println("SPACE#########");
            tank.run(0, true);
        }

        gameStatus = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        if (!gameOver) {

            if (ex.isVisible()) {
                g.drawImage(ex.getImage(), (int) ex.getX(), (int) ex.getY(), (int) 100, (int) 100, null);
            }
            if (mine.isVisible())
                g.drawImage(mine.getImage(), (int) mine.getX(), (int) mine.getY(), (int) mine.getWidth(), (int) mine.getHeight(), null);
            if (mine2.isVisible())
                g.drawImage(mine2.getImage(), (int) mine2.getX(), (int) mine2.getY(), (int) mine2.getWidth(), (int) mine2.getHeight(), null);
            if (mountain.isVisible())
                g.drawImage(mountain.getImage(), (int) mountain.getX(), (int) mountain.getY(), (int) mountain.getWidth(), (int) mountain.getHeight(), null);
            if (house.isVisible())
                g.drawImage(house.getImage(), (int) house.getX(), (int) house.getY(), (int) house.getWidth(), (int) house.getHeight(), null);

            Missile m = tank.missile;
            if (m.isVisible() && (((int) m.getX() <= 0) || ((int) m.getX() >= 1500) || m.y <= 0 || m.y >= 900))
                m.setVisible(false);
            if (m.isVisible()) {
                g.drawImage(m.getImage(), (int) m.x, (int) m.y, (int) m.getWidth(), (int) m.getHeight(), this);
            }

            Missile x = house.missile;
            if (x.isVisible() && (((int) x.getX() <= 0) || ((int) x.getX() >= 1500) || x.y <= 0 || x.y >= 900))
                x.setVisible(false);
            if (x.isVisible()) {
                g.drawImage(x.getImage(), (int) x.x, (int) x.y, (int) x.getWidth(), (int) x.getHeight(), this);
            }

            if (tank.isVisible()) {
                g2d.rotate(Math.toRadians(tank.orientation * 45), tank.getX() + tank.getWidth() / 2, tank.getY() + tank.getHeight() / 2);
                g2d.drawImage(tank.getImage(), (int) tank.getX(), (int) tank.getY(), (int) tank.getWidth(), (int) tank.getHeight(), null);
            }
        } else {
            g2d.drawString(endMenu.getText(), endMenu.getX(), endMenu.getY());
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("GUI Session Fall 2019");
        f.setSize(1500, 900);    // Set the frame size
        f.setLocationRelativeTo(null);         //center the frame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //f.setLayout(null);         //No layout manager

        //tank = new Tank();
        //f.add(new Game(), BorderLayout.CENTER);
        f.add(new Game());
        f.setVisible(true);        //Make your frame visible
    }

    @Override
    public void run() {
        while (true) {
            if (gameStatus) {
                repaint();
                // gameStatus = false;
            }
            if (tank_intersects_mine(tank, mine)) {
                System.out.println("Mine Collision!!");
                ex.setX(mine.getX());
                ex.setY(mine.getY());
                ex.setVisible(true);
                tank.setVisible(false);
                mine.setVisible(false);
                gameOver();
                repaint();
            }
            if (tank_intersects_mine(tank, mine2)) {
                System.out.println("mine2 Collision!!");
                ex.setX(mine2.getX());
                ex.setY(mine2.getY());
                ex.setVisible(true);
                tank.setVisible(false);
                mine2.setVisible(false);
                gameOver();
                repaint();
            }
            if (tank_intersects_house(tank, house)) {
                System.out.println("house Impassable!");
                ex.setX(tank.getX());
                ex.setY(tank.getY());
                ex.setVisible(true);
                tank.setVisible(false);
                gameOver();
                repaint();
                //break;
            }
            if (tank_intersects_mountain(tank, mountain)) {
                System.out.println("Mountain Impassable!");
                ex.setX(tank.getX());
                ex.setY(tank.getY());
                ex.setVisible(true);
                tank.setVisible(false);
                gameOver();
                repaint();
                //break;
            }
            if (tank_intersects_missile(tank, house.missile)) {
                System.out.println("House hit tank!");
                house.missile.setVisible(false);
                house.missile.setLocation(0, 0);
                tank.tankLives -= 1;
                if (tank.tankLives == 0) {
                    tank.setVisible(false);
                    ex.setLocation(tank.getX(), tank.getY());
                    ex.setVisible(true);
                    gameOver();
                    repaint();
                }
            }
            if (house_intersects_missile(house, tank.missile)) {
                System.out.println("tank hit house!");
                tank.missile.setVisible(false);
                tank.missile.setLocation(0, 0);
                house.houseLives -= 1;
                if (house.houseLives == 0) {
                    house.setVisible(false);
                    ex.setLocation(house.getX(), house.getY());
                    ex.setVisible(true);
                    gameOver();
                    repaint();
                }
            }
            int dir = (int) Math.toDegrees(Math.atan2((house.getY() + (house.getHeight() / 2)) - (tank.getY() + (tank.getWidth() / 2)), (house.getX() + (house.getWidth() / 2)) - (tank.getX() + tank.getWidth() / 2))) - 90;
            house.run(dir);
            repaint();
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void gameOver() {
        endMenu.setVisible(true);
        this.gameOver = true;
    }

    public boolean tank_intersects_mine(Tank t, Mine m) {
        return t.intersects(m);
    }

    public boolean tank_intersects_missile(Tank t, Missile mi) {
        return t.intersects(mi);
    }

    public boolean house_intersects_missile(House h, Missile mi) {
        return h.intersects(mi);
    }

    public boolean tank_intersects_house(Tank t, House h) {
        return t.intersects(h);
    }

    public boolean tank_intersects_mountain(Tank t, Mountain mo) {
        return t.intersects(mo);
    }

}
