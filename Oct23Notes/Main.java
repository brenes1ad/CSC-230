package Oct23Notes;

import java.util.Arrays;
import java.util.Comparator;

class Point implements Comparable<Point> {
    int x;
    int y;


    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point o){
        int xdiff = this.x - o.x;
        if (xdiff == 0){
            return this.y - o.y;
        } else{
            return xdiff;
        }
    }


}

class compareByX implements Comparator<Point> {
    public int compare(Point o1, Point o2){
        return o1.x - o2.x;
    }
}

class compareByY implements Comparator<Point> {
    public int compare(Point o1, Point o2){
        return o1.y - o2.y;
    }
}

public class Main {
    public static void main(String[] args) {
        Point []pts = new Point[20];
        for (int i = 0; i < pts.length; i++) {
            pts[i] = new Point(i, 100-i);
        }
        for(Point p : pts) {
            System.out.print(p + ", ");
        }
        System.out.println();

        Arrays.sort(pts, new compareByY());

        for(Point p : pts) {
            System.out.print(p + ", ");
        }
        System.out.println();

        //Arrays.sort(pts, new compareByX());
        Arrays.sort(pts, new Comparator<Point>(){
            public int compare(Point o1, Point o2){
                return o1.x - o2.x;
            }
        });

        for(Point p : pts) {
            System.out.print(p + ", ");
        }
        System.out.println();
    }

}


