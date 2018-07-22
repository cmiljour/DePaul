package view.gui;

import java.util.ArrayList;
import java.util.List;

public class Point {
    private int x;
    private int y;
    private List<Integer> pointList = new ArrayList<Integer>();


    public Point(int x, int y){
        this.x = x;
        this.y = y;
        pointList.add(x);
        pointList.add(y);

    }

    public List<Integer> getPointList(){
        return pointList;
    }

}
