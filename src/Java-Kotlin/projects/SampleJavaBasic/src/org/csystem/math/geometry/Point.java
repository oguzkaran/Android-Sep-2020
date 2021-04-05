/*----------------------------------------------------------------------------------------------------------------------
	Point sınıfı
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.math.geometry;

import static java.lang.Math.sqrt;

public class Point {
	public int x;
	public int y;

	Point(Point point)
	{
		x = point.x;
		y = point.y;
	}

	public Point()
	{		
	}
	
	public Point(int x)
	{
		this.x = x;
	}
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public double distance()
	{
		return distance(0);
	}

	public double distance(Point p)
	{
		return this.distance(p.x, p.y);
	}
	
	public double distance(int a)
	{
		return distance(a, 0);
	}

	public double distance(int a, int b)
	{
		return sqrt((x - a) * (x - a) + (y - b) * (y - b));								
	}
	
	public void offset(int dxy)
	{
		this.offset(dxy, dxy);
	}
	
	public void offset(int dx, int dy)
	{
		x += dx;
		y += dy;
	}
	
	public String toString()
	{
		return String.format("{x: %d, y: %d}", x, y);
	}	
}
