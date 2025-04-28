import javax.swing.*;
import java.awt.*;

public abstract class Vehicle {
    protected int x, y;
    protected final int WIDTH;
    protected final int HEIGHT;
    protected Image carImage;


    Vehicle(int width, int height, int x, int y){
        this.WIDTH = width;
        this.HEIGHT = height;
        this.x = x;
        this.y = y;
        try {
            carImage = new ImageIcon("C:\\Users\\k243032\\Desktop\\untitled\\src\\car.jpg").getImage();
        } catch (Exception e) {
            System.out.println("Could not load car image. Using shape instead.");}
    }


    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract void moveUp();
    public abstract void moveDown();

}

    class MovingOutOfBound extends RuntimeException{
        MovingOutOfBound(String direction){
            super("The car can not move more to the "+direction);
        }
    }
