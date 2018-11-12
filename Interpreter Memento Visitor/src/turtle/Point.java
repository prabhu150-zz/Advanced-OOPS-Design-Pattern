package turtle;

import java.text.DecimalFormat;

public class Point {

    private double x, y;
    private DecimalFormat roundOff = new DecimalFormat("#.##");

    Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    void add(Point offset) {
        x += offset.getX();
        y += offset.getY();
    }

    double getY() {
        return Double.parseDouble(roundOff.format(y));
    }

    double getX() {
        return Double.parseDouble(roundOff.format(x));
    }

    public String to_String() {
        return this.getX() + " " + this.getY(); // returns x,y in string format for test validation
    }

}
