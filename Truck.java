package OOPProject;

import javax.swing.*;

public class Truck extends Vehicle {
    private final int downWardSpeed;

    Truck(int startX, int startY, int truckSpeed) {
        super(100, 200, startX, startY);
        this.downWardSpeed = truckSpeed;

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
        y += downWardSpeed;
    } //5


}
