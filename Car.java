package OOPProject;
import javax.swing.*;

public class Car extends Vehicle{

     Car(int startX, int startY) {
         super(50,100, startX, startY);
         vehicleImage = new ImageIcon("C:\\Users\\irfan\\Desktop\\FAST\\OOPLab\\src\\OOPProject\\Pictures\\sportscar.png").getImage();
         if (vehicleImage == null || vehicleImage.getWidth(null) == -1) {
             isVehicleImageLoaded = false;
         } else {
             isVehicleImageLoaded = true;
         }
    }

    public void moveLeft() {
        if (x > 200) {
            x -= 30;
        }
    }

    public void moveRight() {
        if (x < 493) {
            x += 30;
        }
    }

    public void moveUp() {
        if (y > 30) {
            y-=30;
        }
    }

    public void moveDown() {
        if (y < 450) {
            y+=30;
        }
    }

    public boolean didCarImageFailToLoad() {
        return !(isVehicleImageLoaded);
    }
}
