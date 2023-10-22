package org.daniliszyn;

import java.awt.*;

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
            {0, 0, 0, 1}
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

  public Rectangle(Vector3D v1, Vector3D v2, Vector3D v3, Vector3D v4, Color color) {
    this.v1 = v1;
    this.v2 = v2;
    this.v3 = v3;
    this.v4 = v4;
    this.color = color;
  }

  public void display(Graphics g, int OFFSET) {
    g.fillPolygon(getXPointsScale(OFFSET), getYPointsScale(OFFSET), 4);
    g.drawLine(
            (int)v1.getX() + OFFSET,
            (int)v1.getY() + OFFSET,
            (int)v2.getX() + OFFSET,
            (int)v2.getY() + OFFSET
    );
    g.drawLine(
            (int)v2.getX() + OFFSET,
            (int)v2.getY() + OFFSET,
            (int)v3.getX() + OFFSET,
            (int)v3.getY() + OFFSET
    );
    g.drawLine(
            (int)v3.getX() + OFFSET,
            (int)v3.getY() + OFFSET,
            (int)v4.getX() + OFFSET,
            (int)v4.getY() + OFFSET
    );
    g.drawLine(
            (int)v4.getX() + OFFSET,
            (int)v4.getY() + OFFSET,
            (int)v1.getX() + OFFSET,
            (int)v1.getY() + OFFSET
    );
  }

  public void displayCoordinates(Graphics g, double OFFSET) {
    g.drawString("V1: " + getV1().getZ(), (int) (getV1().getX() + OFFSET), (int) (getV1().getY() + OFFSET));
    g.drawString("V2" + getV2().getZ(), (int) (getV2().getX() + OFFSET), (int) (getV2().getY() + OFFSET));
    g.drawString("V3" + getV3().getZ(), (int) (getV3().getX() + OFFSET), (int) (getV3().getY() + OFFSET));
    g.drawString("V4" + getV4().getZ(), (int) (getV4().getX() + OFFSET), (int) (getV4().getY() + OFFSET));
  }

  public void moveTest(double x, double y, double z) {
    double[][] v1Point = v1.getPoint3D();
    double[][] v2Point = v2.getPoint3D();
    double[][] v3Point = v3.getPoint3D();
    double[][] v4Point = v4.getPoint3D();
    v1 = new Vector3D(Rotation.moveTest(v1Point[0][0] + x, v1Point[1][0] + y, v1Point[2][0] + z, v1.getPoint3D()));
    v2 = new Vector3D(Rotation.moveTest(v2Point[0][0] + x, v2Point[1][0] + y, v2Point[2][0] + z, v2.getPoint3D()));
    v3 = new Vector3D(Rotation.moveTest(v3Point[0][0] + x, v3Point[1][0] + y, v3Point[2][0] + z, v3.getPoint3D()));
    v4 = new Vector3D(Rotation.moveTest(v4Point[0][0] + x, v4Point[1][0] + y, v4Point[2][0] + z, v4.getPoint3D()));
    System.out.println();
    System.out.println(v1.getX());
    System.out.println(v1.getY());
    System.out.println(v1.getZ());
  }

  public void rotate(int angleX, int angleY, int angleZ, double[][] rotationPoint) {
    v1 = new Vector3D(Rotation.rotateX(angleX, v1.getPoint3D(), rotationPoint));
    v1 = new Vector3D(Rotation.rotateY(angleY, v1.getPoint3D(), rotationPoint));
    v1 = new Vector3D(Rotation.rotateZ(angleZ, v1.getPoint3D(), rotationPoint));

    v2 = new Vector3D(Rotation.rotateX(angleX, v2.getPoint3D(), rotationPoint));
    v2 = new Vector3D(Rotation.rotateY(angleY, v2.getPoint3D(), rotationPoint));
    v2 = new Vector3D(Rotation.rotateZ(angleZ, v2.getPoint3D(), rotationPoint));

    v3 = new Vector3D(Rotation.rotateX(angleX, v3.getPoint3D(), rotationPoint));
    v3 = new Vector3D(Rotation.rotateY(angleY, v3.getPoint3D(), rotationPoint));
    v3 = new Vector3D(Rotation.rotateZ(angleZ, v3.getPoint3D(), rotationPoint));

    v4 = new Vector3D(Rotation.rotateX(angleX, v4.getPoint3D(), rotationPoint));
    v4 = new Vector3D(Rotation.rotateY(angleY, v4.getPoint3D(), rotationPoint));
    v4 = new Vector3D(Rotation.rotateZ(angleZ, v4.getPoint3D(), rotationPoint));
  }

  public void rotate(int angleX, int angleY, int angleZ) {
    v1 = new Vector3D(Rotation.rotateX(angleX, v1.getPoint3D()));
    v1 = new Vector3D(Rotation.rotateY(angleY, v1.getPoint3D()));
    v1 = new Vector3D(Rotation.rotateZ(angleZ, v1.getPoint3D()));

    v2 = new Vector3D(Rotation.rotateX(angleX, v2.getPoint3D()));
    v2 = new Vector3D(Rotation.rotateY(angleY, v2.getPoint3D()));
    v2 = new Vector3D(Rotation.rotateZ(angleZ, v2.getPoint3D()));

    v3 = new Vector3D(Rotation.rotateX(angleX, v3.getPoint3D()));
    v3 = new Vector3D(Rotation.rotateY(angleY, v3.getPoint3D()));
    v3 = new Vector3D(Rotation.rotateZ(angleZ, v3.getPoint3D()));

    v4 = new Vector3D(Rotation.rotateX(angleX, v4.getPoint3D()));
    v4 = new Vector3D(Rotation.rotateY(angleY, v4.getPoint3D()));
    v4 = new Vector3D(Rotation.rotateZ(angleZ, v4.getPoint3D()));
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

  public int[] getXPointsScale(int offset) {
    double[][] v1Scale = Scale.scale(0.3, 0.3, 0.7, v1.getPoint3D());
    double[][] v2Scale = Scale.scale(0.3, 0.3, 0.7, v2.getPoint3D());
    double[][] v3Scale = Scale.scale(0.3, 0.3, 0.7, v3.getPoint3D());
    double[][] v4Scale = Scale.scale(0.3, 0.3, 0.7, v4.getPoint3D());
    return new int[]{
            (int) v1Scale[0][0] + offset, (int) v2Scale[0][0] + offset, (int) v3Scale[0][0] + offset, (int) v4Scale[0][0] + offset
    };
  }

  public int[] getYPointsScale(int offset) {
    double[][] v1Scale = Scale.scale(0.3, 0.3, 0.7, v1.getPoint3D());
    double[][] v2Scale = Scale.scale(0.3, 0.3, 0.7, v2.getPoint3D());
    double[][] v3Scale = Scale.scale(0.3, 0.3, 0.7, v3.getPoint3D());
    double[][] v4Scale = Scale.scale(0.3, 0.3, 0.7, v4.getPoint3D());
    return new int[]{
            (int) v1Scale[1][0] + offset, (int) v2Scale[1][0] + offset, (int) v3Scale[1][0] + offset, (int) v4Scale[1][0] + offset
    };
  }

  public int[] getXPoints(int offset) {
    return new int[]{
            (int) v1.getX() + offset, (int) v2.getX() + offset, (int) v3.getX() + offset, (int) v4.getX() + offset
    };
  }

  public int[] getYPoints(int offset) {
    return new int[]{
            (int) v1.getY() + offset, (int) v2.getY() + offset, (int) v3.getY() + offset, (int) v4.getY() + offset
    };
  }

}
