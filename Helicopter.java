package OOPProject;

import javax.swing.*;
import java.awt.*;

public class Helicopter extends Vehicle implements HeliBlueprint{

    Helicopter(int startX, int startY) {
        super(150, 200, startX, startY);

        try {
            vehicleImage = new ImageIcon("C:\\Users\\irfan\\Desktop\\FAST\\OOPLab\\src\\OOPProject\\Pictures\\heli-edited.png").getImage();
            isVehicleImageLoaded = true;
        } catch (Exception e) {
            System.out.println("Could not load helicopter image. Using shape instead.");
        }
    }

    public void flyDiagonally(boolean atRightCorner){
        if(x<0 || atRightCorner){
            flyDiagonallyEastward();
        }
        else{
            flyDiagonallyWestward();
        }
    }

    public void flyDiagonallyEastward() {
        moveRight();
        moveUp();
    }

    public void flyDiagonallyWestward() {
        moveLeft();
        moveUp();
    }
    @Override
    public void moveLeft() {
            x -= 10;
    }

    public void moveRight() {
            x += 10;
    }

    public void moveUp() {
        y -= 7;
    }

    public void moveDown() {
        if (y < 450) {
            y += 10;
        }
    }

}
