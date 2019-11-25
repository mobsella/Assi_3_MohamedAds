package A3_GAME;

import java.awt.*;

import javax.swing.ImageIcon;

public class Sprite {

    protected double x, y;
    private double Width, Height;
    private boolean visible;
    private Image SpriteImage;

    public Sprite(double x, double y, int width, int height) {

        this.x = x;
        this.y = y;
        this.Width = width;
        this.Height = height;
        this.visible = true;
    }

    public Sprite(double x, double y, int width, int height, boolean visible) {

        this.x = x;
        this.y = y;
        this.Width = width;
        this.Height = height;
        this.visible = visible;
    }

    public void setImage(String ImageName) {

        ImageIcon source = new ImageIcon("image2/" + ImageName);
        SpriteImage = source.getImage();
    }

    public Image getImage() {
        return this.SpriteImage;
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setWidth(double width) {
        this.Width = width;
    }

    public double getWidth() {
        return Width;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public double getHeight() {
        return Height;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
    }

    public boolean intersects(Sprite other) {
        /*double diffX = Math.abs(this.x - other.x);
        double diffY = Math.abs(this.y - other.y);
        boolean intX, intY;
        intX = (this.x >= other.x? (diffX <= other.Width): (diffX <= this.Width));
        intY = (this.y >= other.y? (diffY <= other.Height): (diffY <= this.Height));
        return intX && intY;*/

        return this.getBounds().intersects(other.getBounds());
    }

    public Rectangle intersection(Sprite other) {
        return this.getBounds().intersection(other.getBounds());
    }
}