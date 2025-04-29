import javax.swing.*;
import java.awt.*;

public abstract class Vehicle {
    protected int x, y;     //coordinates
    protected final int WIDTH;
    protected final int HEIGHT;
    protected Image vehicleImage;


    Vehicle(int width, int height, int x, int y){
        this.WIDTH = width;
        this.HEIGHT = height;
        this.x = x;
        this.y = y;
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
