package turtle;

public class TurtleDemo {

    private int currentOrientation;
    private Point currentPosition;
    private boolean isPenDown;

    public TurtleDemo() {
        isPenDown = false;
        currentPosition = new Point();
        currentOrientation = 0;
    }

    public TurtleDemo(TurtleDemo newTurtle) {
        // copy constructor for saving state within memento
        currentOrientation = newTurtle.currentOrientation;
        currentPosition = newTurtle.location();
        isPenDown = newTurtle.isPenDown;
    }

    public TurtleDemo(Point currentLocation) {
        isPenDown = false;
        currentPosition = currentLocation;
        currentOrientation = 0;
    }

    public void move(double distance) {
        getNextLocation(currentPosition, distance, currentOrientation);
    }

    public void turn(double degrees) {
        currentOrientation += degrees;
    }

    public void penUp() {
        isPenDown = false;
    }

    public void penDown() {
        isPenDown = true;
    }

    public boolean isPenUp() {
        return !(this.isPenDown);
    }

    public Point location() {
        return new Point(currentPosition.getX(), currentPosition.getY());
    }

    public int orientation() {
        return currentOrientation;
    }

    private void getNextLocation(Point currentPoint, double distance, double direction) {
        int MAX_ORIENTATION = 360; // max degrees
        currentOrientation = (int) direction % MAX_ORIENTATION;
        double radians = currentOrientation * Math.PI / 180.0;
        double deltaX = Math.cos(radians) * distance, deltaY = Math.sin(radians) * distance;
        currentPoint.add(new Point(deltaX, deltaY));
    }

}

