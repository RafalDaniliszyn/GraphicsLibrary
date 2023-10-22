package org.daniliszyn;

public class Scale {

  public static double[][] scale(double scaleX, double scaleY, double scaleZ, double[][] point) {
    double[][] scaleMatrix = {
            {scaleX, 0, 0, 0},
            {0, scaleY, 0, 0},
            {0, 0, scaleZ, 0},
            {0, 0, 0, 1}
    };
    return Rotation.multiply(scaleMatrix, point, 4);
  }
}
