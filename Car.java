package OOPProject;
import javax.swing.*;

public class Car extends Vehicle{

    private int primaryCarSpeed;

     Car(int startX, int startY, String carManeuverability) {
         super(50,100, startX, startY);
         switch(carManeuverability){
             case "light":primaryCarSpeed = 40; break;
             case "intermediate":primaryCarSpeed = 30; break;
             case "expert":primaryCarSpeed = 25; break;
             default:primaryCarSpeed = 35;
         }

         vehicleImage = new ImageIcon("C:\\Users\\irfan\\Desktop\\FAST\\OOPLab\\src\\OOPProject\\Pictures\\sportscar.png").getImage();
         if (vehicleImage == null || vehicleImage.getWidth(null) == -1) {
             isVehicleImageLoaded = false;
         } else {
             isVehicleImageLoaded = true;
         }
    }

    public void moveLeft() {
        if (x > 215) { //220
            x -= primaryCarSpeed;
        }
    }

    public void moveRight() {
        if (x < 480) {  //490
            x += primaryCarSpeed;
        }
    }

    public void moveUp() {
        if (y > 30) {
            y-=30;
        }
    }

    public void moveDown() {
        if (y < 670) {
            y+=30;
        }
    }

    public boolean didCarImageFailToLoad() {
        return !(isVehicleImageLoaded);
    }
}
