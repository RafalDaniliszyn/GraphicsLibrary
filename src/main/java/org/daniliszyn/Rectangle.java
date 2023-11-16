package org.daniliszyn;

import java.awt.*;
import java.awt.geom.Point2D;

public class Rectangle {
  private Vector3D v1;
  private Vector3D v2;
  private Vector3D v3;
  private Vector3D v4;
  private final Color color;
  private String name;

  public Rectangle(double x, double y, double z, double width, double height, Color color, String name) {
    this.name = name;
    this.color = color;
    v1 = new Vector3D(new double[][]{
            {x, 0, 0, 0},
            {y, 0, 0, 0},
            {z, 0, 0, 0},
            {0, 0, 0, 0}
    });
    v2 = new Vector3D(new double[][]{
            {x+width, 0, 0, 0},
            {y, 0, 0, 0},
            {z, 0, 0, 0},
            {0, 0, 0, 1}
    });
    v3 = new Vector3D(new double[][]{
            {x+width, 0, 0, 0},
            {y+height, 0, 0, 0},
            {z, 0, 0, 0},
            {0, 0, 0, 1}
    });
    v4 = new Vector3D(new double[][]{
            {x, 0, 0, 0},
            {y+height, 0, 0, 0},
            {z, 0, 0, 0},
            {0, 0, 0, 1}
    });
  }

  public void display(Graphics g) {
    g.fillPolygon(getXPointsScale(), getYPointsScale(), 4);

    scale();
    g.drawLine(
            (int)v1.getX(),
            (int)v1.getY(),
            (int)v2.getX(),
            (int)v2.getY()
    );
    g.drawLine(
            (int)v2.getX(),
            (int)v2.getY(),
            (int)v3.getX(),
            (int)v3.getY()
    );
    g.drawLine(
            (int)v3.getX(),
            (int)v3.getY(),
            (int)v4.getX(),
            (int)v4.getY()
    );
    g.drawLine(
            (int)v4.getX(),
            (int)v4.getY(),
            (int)v1.getX(),
            (int)v1.getY()
    );
  }

  public void translate(double x, double y, double z) {
    v1 = new Vector3D(Transform.translation(x, y, z, v1.getPoint3D()));
    v2 = new Vector3D(Transform.translation(x, y, z, v2.getPoint3D()));
    v3 = new Vector3D(Transform.translation(x, y, z, v3.getPoint3D()));
    v4 = new Vector3D(Transform.translation(x, y, z, v4.getPoint3D()));
  }

  public void rotate(int angleX, int angleY, int angleZ, double[][] rotationPoint) {
    v1 = new Vector3D(Transform.rotateX(angleX, v1.getPoint3D(), rotationPoint));
    v1 = new Vector3D(Transform.rotateY(angleY, v1.getPoint3D(), rotationPoint));
    v1 = new Vector3D(Transform.rotateZ(angleZ, v1.getPoint3D(), rotationPoint));

    v2 = new Vector3D(Transform.rotateX(angleX, v2.getPoint3D(), rotationPoint));
    v2 = new Vector3D(Transform.rotateY(angleY, v2.getPoint3D(), rotationPoint));
    v2 = new Vector3D(Transform.rotateZ(angleZ, v2.getPoint3D(), rotationPoint));

    v3 = new Vector3D(Transform.rotateX(angleX, v3.getPoint3D(), rotationPoint));
    v3 = new Vector3D(Transform.rotateY(angleY, v3.getPoint3D(), rotationPoint));
    v3 = new Vector3D(Transform.rotateZ(angleZ, v3.getPoint3D(), rotationPoint));

    v4 = new Vector3D(Transform.rotateX(angleX, v4.getPoint3D(), rotationPoint));
    v4 = new Vector3D(Transform.rotateY(angleY, v4.getPoint3D(), rotationPoint));
    v4 = new Vector3D(Transform.rotateZ(angleZ, v4.getPoint3D(), rotationPoint));
  }

  public Vector3D getV1() {
    return v1;
  }

  public Vector3D getV2() {
    return v2;
  }

  public Vector3D getV3() {
    return v3;
  }

  public Vector3D getV4() {
    return v4;
  }

  public Color getColor() {
    return color;
  }

  public int[] getXPointsScale() {
    double[][] v1Scale = Scale.scale(v1.getPoint3D());
    double[][] v2Scale = Scale.scale(v2.getPoint3D());
    double[][] v3Scale = Scale.scale(v3.getPoint3D());
    double[][] v4Scale = Scale.scale(v4.getPoint3D());
    return new int[]{
            (int) v1Scale[0][0], (int) v2Scale[0][0], (int) v3Scale[0][0], (int) v4Scale[0][0]
    };
  }

  public int[] getYPointsScale() {
    double[][] v1Scale = Scale.scale(v1.getPoint3D());
    double[][] v2Scale = Scale.scale(v2.getPoint3D());
    double[][] v3Scale = Scale.scale(v3.getPoint3D());
    double[][] v4Scale = Scale.scale(v4.getPoint3D());
    return new int[]{
            (int) v1Scale[1][0], (int) v2Scale[1][0], (int) v3Scale[1][0], (int) v4Scale[1][0]
    };
  }

  private void scale() {
    double[][] v1scale = Scale.scale(v1.getPoint3D());
    double[][] v2scale = Scale.scale(v2.getPoint3D());
    double[][] v3scale = Scale.scale(v3.getPoint3D());
    double[][] v4scale = Scale.scale(v4.getPoint3D());
    v1.setPoint2D(new Point2D.Double(v1scale[0][0], v1scale[1][0]));
    v2.setPoint2D(new Point2D.Double(v2scale[0][0], v2scale[1][0]));
    v3.setPoint2D(new Point2D.Double(v3scale[0][0], v3scale[1][0]));
    v4.setPoint2D(new Point2D.Double(v4scale[0][0], v4scale[1][0]));
  }
}
