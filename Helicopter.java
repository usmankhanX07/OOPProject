package OOPProject;

import javax.swing.*;

public class Helicopter extends Vehicle implements HeliBlueprint{

    Helicopter(int startX, int startY, boolean flipped) {
        super(220, 250, startX, startY);
        try{
            buildHelicopter(flipped);
        } catch (HelicopterBuildFailed e) {
            System.out.println(e.getMessage());
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
            x += 7;
    } //10

    public void moveUp() {
        y -= 5;
    } //5

    public void moveDown() {}

    public void buildHelicopter(boolean flipped) throws HelicopterBuildFailed{
        if(!flipped){
            try {
                vehicleImage = new ImageIcon(
                        "C:\\Users\\irfan\\Desktop\\FAST\\OOPLab\\src\\OOPProject\\Pictures\\heli-tilted.png").getImage();
                isVehicleImageLoaded = true;
            }finally{}
        }
        else{
            try {
                vehicleImage = new ImageIcon(
                        "C:\\Users\\irfan\\Desktop\\FAST\\OOPLab\\src\\OOPProject\\Pictures\\heli-camo-flipped.png").getImage();
                isVehicleImageLoaded = true;
            }finally{}
        }
    }
}

