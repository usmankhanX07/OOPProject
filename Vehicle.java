package OOPProject;

public abstract class Vehicle {
    protected int x, y;     //coordinates
    protected final int WIDTH;
    protected final int HEIGHT;

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

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}

//    class MovingOutOfBound extends RuntimeException{
//        MovingOutOfBound(String direction){
//            super("The car can not move more to the "+direction);
//        }
//    }
