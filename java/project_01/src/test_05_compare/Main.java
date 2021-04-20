package test_05_compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
	/*
	 * 1. Interface Comparable
	 * 정렬 수행 시 기본적으로 적용되는 정렬 기준이 되는 메서드를 정의하는 인터페이스
	 * Java에서 제공되는 정렬이 가능한 클래스들은 모두 Comparable 인터페이스를 구현하고 있으며, 정렬 시에 이에 맞게 정렬이 수행됨
	 * 정렬할 객체에 Comparable interface를 implements 후, compareTo() 메서드를 오버라이드하여 구현한다.
		compareTo() 메서드 작성법
		현재 객체 < 파라미터로 넘어온 객체: 음수 리턴
		현재 객체 == 파라미터로 넘어온 객체: 0 리턴
		현재 객체 > 파라미터로 넘어온 객체: 양수 리턴
		음수 또는 0이면 객체의 자리가 그대로 유지되며, 양수인 경우에는 두 객체의 자리가 바뀐다.
	 */
	
	/*
	 * 2. Interface Comparator
	 * 정렬 가능한 클래스(Comparable 인터페이스를 구현한 클래스)들의 기본 정렬 기준과 다르게 정렬 하고 싶을 때 사용하는 인터페이스
	 * Comparator interface를 implements 후 compare() 메서드를 오버라이드한 myComparator class를 작성한다.
		compare() 메서드 작성법
		첫 번째 파라미터로 넘어온 객체 < 두 번째 파라미터로 넘어온 객체: 음수 리턴
		첫 번째 파라미터로 넘어온 객체 == 두 번째 파라미터로 넘어온 객체: 0 리턴
		첫 번째 파라미터로 넘어온 객체 > 두 번째 파라미터로 넘어온 객체: 양수 리턴
		음수 또는 0이면 객체의 자리가 그대로 유지되며, 양수인 경우에는 두 객체의 자리가 변경된다.
	 */
	
	/*
	 * compareTo(), compare() 함수 구현 시...
	 * 사전순, 오름차순이 기본
	 * 원래 있던 것에서 새로 들어온 것을 뺐을 떄 양수라면 -> 기본 정렬
	 */
	
	static class Point1 implements Comparable<Point1> {
	    int x, y;
	    
	    Point1(int x, int y){
	    	this.x = x;
	    	this.y = y;
	    }

	    @Override
	    public int compareTo(Point1 p) {
	        if(this.x > p.x) {
	            return 1; // x에 대해서는 오름차순
	        }
	        else if(this.x == p.x) {
	            if(this.y < p.y) { // y에 대해서는 내림차순
	                return 1;
	            }
	        }
	        return -1;
	    }
	    
	    @Override
	    public String toString() {
	    	return "x: " + this.x + " y: " + this.y;
	    }
	}
	
	static class Point2 {
	    int x, y;
	    
	    Point2(int x, int y){
	    	this.x = x;
	    	this.y = y;
	    }
	    
	    @Override
	    public String toString() {
	    	return "x: " + this.x + " y: " + this.y;
	    }
	}
	
	static class MyComparator implements Comparator<Point2> {
		  @Override
		  public int compare(Point2 p1, Point2 p2) {
		    if (p1.x > p2.x) {
		      return 1; // x에 대해서는 오름차순
		    }
		    else if (p1.x == p2.x) {
		      if (p1.y < p2.y) { // y에 대해서는 내림차순
		        return 1;
		      }
		    }
		    return -1;
		  }
		}
	
	static void ex_comparable() {
		List<Point1> pointList = new ArrayList<>();
		pointList.add(new Point1(5, 1));
		pointList.add(new Point1(5, 7));
		pointList.add(new Point1(5, 3));
		pointList.add(new Point1(7, 3));
		pointList.add(new Point1(1, 3));
		Collections.sort(pointList);
		System.out.println("check pointList(use comparable) ... ");
		
		for(Point1 po1: pointList) {
			System.out.println(po1.toString());
		}
	}
	
	static void ex_comparator1() {
		List<Point2> pointList = new ArrayList<>();
		pointList.add(new Point2(5, 1));
		pointList.add(new Point2(5, 7));
		pointList.add(new Point2(5, 3));
		pointList.add(new Point2(7, 3));
		pointList.add(new Point2(1, 3));
		MyComparator myComparator = new MyComparator();
		Collections.sort(pointList, myComparator);
		
		System.out.println("check pointList(use comparator1) ... ");
		
		for(Point2 po2: pointList) {
			System.out.println(po2.toString());
		}
	}
	
	static void ex_comparator2() {
		// Comparator 익명 클래스 이용
		List<Point2> pointList = new ArrayList<>();
		Comparator<Point2> myComparator = new Comparator<Point2>() {
			  @Override
			  public int compare(Point2 p1, Point2 p2) {
			    if (p1.x > p2.x) {
				      return 1; // x에 대해서는 오름차순
				    }
				    else if (p1.x == p2.x) {
				      if (p1.y < p2.y) { // y에 대해서는 내림차순
				        return 1;
				      }
				    }
				    return -1;
				  }
			};
		pointList.add(new Point2(5, 1));
		pointList.add(new Point2(5, 7));
		pointList.add(new Point2(5, 3));
		pointList.add(new Point2(7, 3));
		pointList.add(new Point2(1, 3));
		Collections.sort(pointList, myComparator);
		
		System.out.println("check pointList(use comparator2) ... ");
		
		for(Point2 po2: pointList) {
			System.out.println(po2.toString());
		}
	}
	
	public static void main(String[] args) {
		ex_comparable();
		ex_comparator1();
		ex_comparator2();
	}
}
