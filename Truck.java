package OOPProject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Truck extends Vehicle {

    Truck(int startX, int startY) {
        super(100, 200, startX, startY);

        try {
            vehicleImage = new ImageIcon("C:\\Users\\irfan\\Desktop\\FAST\\OOPLab\\src\\OOPProject\\Pictures\\truck1.jpg").getImage();
            isVehicleImageLoaded = true;
        } catch (Exception e) {
            System.out.println("Could not load car image. Using shape instead.");
        }
    }

    @Override
    public void moveLeft() {
        x-=5;
    }

    @Override
    public void moveRight() {
        x+=5;
    }

    @Override
    public void moveUp() {
        y-=5;
    }

    @Override
    public void moveDown() {
        y += 5;
    }


}
