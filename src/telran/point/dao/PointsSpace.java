package telran.point.dao;

import java.util.Arrays;
import java.util.Comparator;

import telran.point.model.Point;

public class PointsSpace {
	Point relPoint;
	Point[] points;
	Comparator<Point> comp = (Point p1, Point p2)->{
		double d1 = calcDistance(p1,relPoint);
		double d2 = calcDistance(p2,relPoint);
		return Double.compare(d1,d2);
	};

	public PointsSpace(Point relPoint, Point[] points) {
		this.relPoint = relPoint;
		this.points = Arrays.copyOf(points,points.length);//FIXME Fix problem
		//TODO sorting this.points by proximity to relPoint (ask to Pithagoras)
		//to apply method sort of class Arrays
		Arrays.sort(this.points,comp);
	}

	public Point[] getPoints() {
		return points;
	}
	
	public void addPoint(Point point) {
		//TODO keep sort of this.points
		//to apply method binarySearch of the class Arrays
		//to apply method arrayCopy of the class System
		//method arrayCopy will be called twice
		double d = calcDistance(point,relPoint);
		int ind = Math.abs(Arrays.binarySearch(points,point,comp));
		Point[] res = new Point[points.length + 1];
		System.arraycopy(points,0,res,0,ind - 1);
		res[ind - 1] = point;
		System.arraycopy(points,ind-1,res,ind,points.length-(ind-1));
		points = res;
	}
	private double calcDistance(Point p1, Point p2){
		return Math.sqrt(Math.pow(p1.getX() - p2.getX(),2) + Math.pow(p1.getY() - p2.getY(),2));
	}
}
