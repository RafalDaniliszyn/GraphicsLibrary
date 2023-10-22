package org.daniliszyn;

import java.awt.geom.Point2D;

public class Vector3D {
  private Point2D point2D;
  private double[][] point3D;

  public Vector3D(double[][] point3D) {
    this.point3D = point3D;
    this.point2D = new Point2D.Double(point3D[0][0], point3D[1][0]);
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
}
