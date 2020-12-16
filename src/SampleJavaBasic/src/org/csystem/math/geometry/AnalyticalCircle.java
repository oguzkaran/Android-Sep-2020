/*----------------------------------------------------------------------------------------------------------------------
	Circle sınıfı
	-- AnalyticalCircle is Circle, has a Point
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.math.geometry;

public class AnalyticalCircle extends Circle {
    private final Point m_center;

    public AnalyticalCircle()
    {
        this(0);
    }

    public AnalyticalCircle(double radius)
    {
        this(radius, 0, 0);
    }

    public AnalyticalCircle(int x, int y)
    {
        this(0, x, y);
    }

    public AnalyticalCircle(Point center)
    {
        this(0, center);
    }

    public AnalyticalCircle(double radius, Point center)
    {
        this(radius, center.x, center.y);
    }

    public AnalyticalCircle(double radius, int x, int y) //primary (core) ctor
    {
        super(radius);
        m_center = new Point(x, y);
    }

    public void setCenter(Point center)
    {
        this.setCenter(center.x, center.y);
    }

    public void setCenter(int x, int y)
    {
        this.setX(x);
        this.setY(y);
    }

    public void setX(int x)
    {
        m_center.x = x;
    }

    public int getX()
    {
        return m_center.x;
    }

    public void setY(int y)
    {
        m_center.y = y;
    }

    public int getY()
    {
        return m_center.y;
    }

    public Point getCenter()
    {
        return new Point(m_center); // m_center doğrudan verilseydi. Composition delinmiş olurdu
    }

    public void offset(int dxy) //delegate method
    {
        this.offset(dxy, dxy);
    }

    public void offset(int dx, int dy) //delegate method
    {
        m_center.offset(dx, dy);
    }

    public double radiusDistance(AnalyticalCircle analyticalCircle)
    {
        return m_center.distance(analyticalCircle.m_center);
    }
}
