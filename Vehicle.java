package OOPProject;

import java.awt.*;

public abstract class Vehicle {
    protected int x, y;     //coordinates
    protected final int WIDTH;
    protected final int HEIGHT;
    protected Image vehicleImage;
    protected boolean isVehicleImageLoaded;

    Vehicle(int width, int height, int x, int y) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.x = x;
        this.y = y;
        isVehicleImageLoaded = false;
    }

    public abstract void moveLeft();

    public abstract void moveRight();

    public abstract void moveUp();

    public abstract void moveDown();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics g) {
        if (vehicleImage != null && isVehicleImageLoaded) {
            g.drawImage(vehicleImage, x, y, WIDTH, HEIGHT, null);
        }
    }
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}

//    class MovingOutOfBound extends RuntimeException{
//        MovingOutOfBound(String direction){
//            super("The car can not move more to the "+direction);
//        }
//    }
