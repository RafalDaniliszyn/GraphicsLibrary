package org.daniliszyn;

import java.awt.geom.Point2D;

public class Rotation {

  public static Point2D rotate(int angle, Point2D point) {
    double x = point.getX();
    double y = point.getY();
    double x1 =  (x * Math.cos(Math.toRadians(angle)) - y * Math.sin(Math.toRadians(angle)));
    double y1 =  (x * Math.sin(Math.toRadians(angle)) + y * Math.cos(Math.toRadians(angle)));
    return new Point2D.Double(x1, y1);
  }

  public static double[][] moveTest(double x, double y, double z, double[][] point) {
    double[][] matrix = {
            {1, 0, 0, x+5},
            {0, 1, 0, y+5},
            {0, 0, 1, z+5},
            {0, 0, 0, 1}
    };
    return multiply(matrix, point, 4);
  }

  public static double[][] rotateX(int angle, double[][] point, double[][] rotationPoint) {
    if (rotationPoint == null) {
      rotationPoint = new double[][] {
              {0},
              {0},
              {0}
      };
    }
    double sin = Math.sin(Math.toRadians(angle));
    double cos = Math.cos(Math.toRadians(angle));
    double[][] xMatrix = {
            {1.0, 0.0, 0.0, 0.0},
            {0.0, cos, -sin, 0.0},
            {0.0, sin, cos, 0.0},
            {0.0, 0.0, 0.0, 1}
    };
    double[][] m1 = {
            {point[0][0] - rotationPoint[0][0], 0.0, 0.0, 0.0},
            {point[1][0] - rotationPoint[1][0], 0.0, 0.0, 0.0},
            {point[2][0] - rotationPoint[2][0], 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 1.0}
    };
    double[][] result = multiply(xMatrix, m1, 4);
    result[0][0] += rotationPoint[0][0];
    result[1][0] += rotationPoint[1][0];
    result[2][0] += rotationPoint[2][0];
    return result;
  }

  public static double[][] rotateY(int angle, double[][] point, double[][] rotationPoint) {
    if (rotationPoint == null) {
      rotationPoint = new double[][] {
              {0},
              {0},
              {0}
      };
    }
    double sin = Math.sin(Math.toRadians(angle));
    double cos = Math.cos(Math.toRadians(angle));
    double[][] yMatrix = {
            {cos, 0, sin, 0},
            {0, 1, 0, 0},
            {sin*-1, 0, cos, 0},
            {0, 0, 0, 1}
    };
    double[][] m1 = {
            {point[0][0] - rotationPoint[0][0], 0.0, 0.0, 0.0},
            {point[1][0] - rotationPoint[1][0], 0.0, 0.0, 0.0},
            {point[2][0] - rotationPoint[2][0], 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 1}
    };
    double[][] result = multiply(yMatrix, m1, 4);
    result[0][0] += rotationPoint[0][0];
    result[1][0] += rotationPoint[1][0];
    result[2][0] += rotationPoint[2][0];
    return result;
  }

  public static double[][] rotateZ(int angle, double[][] point, double[][] rotationPoint) {
    if (rotationPoint == null) {
      rotationPoint = new double[][] {
              {0},
              {0},
              {0}
      };
    }
    double sin = Math.sin(Math.toRadians(angle));
    double cos = Math.cos(Math.toRadians(angle));
    double[][] zMatrix = {
            {cos, sin*-1, 0, 0},
            {sin, cos, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1}
    };
    double[][] m1 = {
            {point[0][0] - rotationPoint[0][0], 0.0, 0.0, 0.0},
            {point[1][0] - rotationPoint[1][0], 0.0, 0.0, 0.0},
            {point[2][0] - rotationPoint[2][0], 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 1.0}
    };
    double[][] result = multiply(zMatrix, m1, 4);
    result[0][0] += rotationPoint[0][0];
    result[1][0] += rotationPoint[1][0];
    result[2][0] += rotationPoint[2][0];
    return result;
  }

  @Deprecated
  public static double[][] rotateX(int angle, double[][] point) {
    double sin = Math.sin(Math.toRadians(angle));
    double cos = Math.cos(Math.toRadians(angle));
    double[][] xMatrix = {
            {1.0, 0.0, 0.0},
            {0.0, cos, -sin},
            {0.0, sin, cos}
    };
    double[][] m1 = {
            {point[0][0], 0.0, 0.0},
            {point[1][0], 0.0, 0.0},
            {point[2][0], 0.0, 0.0}
    };
    return multiply(xMatrix, m1);
  }

  @Deprecated
  public static double[][] rotateY(int angle, double[][] point) {
    double sin = Math.sin(Math.toRadians(angle));
    double cos = Math.cos(Math.toRadians(angle));
    double[][] yMatrix = {
            {cos, 0, sin},
            {0, 1, 0},
            {sin*-1, 0, cos}
    };
    double[][] m1 = {
            {point[0][0], 0.0, 0.0},
            {point[1][0], 0.0, 0.0},
            {point[2][0], 0.0, 0.0}
    };
    return multiply(yMatrix, m1);
  }

  @Deprecated
  public static double[][] rotateZ(int angle, double[][] point) {
    double sin = Math.sin(Math.toRadians(angle));
    double cos = Math.cos(Math.toRadians(angle));
    double[][] zMatrix = {
            {cos, sin*-1, 0},
            {sin, cos, 0},
            {0, 0, 1}
    };
    double[][] m1 = {
            {point[0][0], 0.0, 0.0},
            {point[1][0], 0.0, 0.0},
            {point[2][0], 0.0, 0.0}
    };
    return multiply(zMatrix, m1);
  }


  // TODO: 24.08.2023 Wydzielić do innej klasy
  public static double[][] multiply(double[][] m1, double[][] m2) {
    double[][] m3 = new double[3][3];
    for (int i = 0; i < m1[0].length; i++) {
      for (int i1 = 0; i1 < m1.length; i1++) {
        double sum = 0;
        for (int j = 0; j < m1[0].length; j++) {
          sum += m1[i][j] * m2[j][i1];
        }
        m3[i][i1] = sum;
      }
    }
    return m3;
  }

  public static double[][] multiply(double[][] m1, double[][] m2, int matrixSize) {
    double[][] m3 = new double[matrixSize][matrixSize];
    for (int i = 0; i < m1[0].length; i++) {
      for (int i1 = 0; i1 < m1.length; i1++) {
        double sum = 0;
        for (int j = 0; j < m1[0].length; j++) {
          sum += m1[i][j] * m2[j][i1];
        }
        m3[i][i1] = sum;
      }
    }
    return m3;
  }

}




















