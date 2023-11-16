package org.daniliszyn;

import java.awt.geom.Point2D;

public class Vector3D {
  private Point2D point2D;
  private final double[][] point3D;

  public Vector3D(double[][] point3D) {
    this.point3D = point3D;
    this.point2D = new Point2D.Double(point3D[0][0], point3D[1][0]);
  }

  public Vector3D(double x, double y, double z) {
    this.point3D = new double[][]{
            {x, 0, 0, 0},
            {y, 0, 0, 0},
            {z, 0, 0, 0},
            {0, 0, 0, 1}
    };
    this.point2D = new Point2D.Double(x, y);
  }

  public double getX() {
    return point2D.getX();
  }

  public double getY() {
    return point2D.getY();
  }

  public double getZ() {
    return point3D[2][0];
  }

  public double[][] getPoint3D() {
    return point3D;
  }

  public void setPoint2D(Point2D point2D) {
    this.point2D = point2D;
  }
}
