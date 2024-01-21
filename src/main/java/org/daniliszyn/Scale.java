package org.daniliszyn;

public class Scale {

  public static final double l = -1;
  public static final double r = 1;
  public static final double t = 1;
  public static final double b = -1;
  public static final double n = 3;
  public static final double f = 5;

  public static double[][] scale(double[][] point) {
    double[][] scaleMatrix = {
            {(2* n)/(r - l), 0, (r + l) / (r - l), 0},
            {0, (2 * n) / (t - b), (t + b) / (t - b), 0},
            {0, 0, -(f + n) / (f - n), (-2 * f * n) / (f - n)},
            {0, 0, -1, 0}
    };
    double[][] m1 = {
            {point[0][0], 0, 0, 0},
            {point[1][0], 0, 0, 0},
            {point[2][0], 0, 0, 0},
            {1          , 0, 0, 0}
    };
    double[][] scale = MatrixMath.multiply(m1, scaleMatrix, 4);
    double xPerspective = scale[0][0]/scale[2][0];
    double yPerspective = scale[1][0]/scale[2][0];

    double screen_x = ((xPerspective + 1) - Panel.CAMERA[0]) * (1300.0 / 2);
    double screen_y = ((1 - yPerspective) - Panel.CAMERA[1]) * (800.0 / 2);
    return new double[][]{
            {screen_x, 0, 0, 0},
            {screen_y, 0, 0, 0},
            {scale[2][0], 0, 0, 0},
            {0, 0, 0, 1}
    };
  }
}
