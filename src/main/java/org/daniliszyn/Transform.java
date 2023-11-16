package org.daniliszyn;

public class Transform {

  public static double[][] translation(double x, double y, double z, double[][] point) {
    double[][] matrix = {
            {1.0, 0.0, 0.0, x},
            {0.0, 1.0, 0.0, y},
            {0.0, 0.0, 1.0, z},
            {0.0, 0.0, 0.0, 1.0}
    };
    double[][] m1 = {
            {point[0][0], 0, 0, 0},
            {point[1][0], 0, 0, 0},
            {point[2][0], 0, 0, 0},
            {1.0        , 0, 0, 0}
    };
    double[][] result = multiply(matrix, m1, 4);
    m1 = new double[][]{
            {result[0][0], 0, 0, 0},
            {result[1][0], 0, 0, 0},
            {result[2][0], 0, 0, 0},
            {0, 0, 0, 1}
    };
    return m1;
  }

  public static double[][] rotate(int angleX, int angleY, int angleZ, double[][] point, double[][] rotationPoint) {
    if (angleY != 0) {
      point = rotateY(angleY, point, rotationPoint);
    }
    if (angleX != 0) {
      point = rotateX(angleX, point, rotationPoint);
    }
    if (angleZ != 0) {
      point = rotateZ(angleZ, point, rotationPoint);
    }
    return point;
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

    double[][] origin = Transform.translation(-rotationPoint[0][0], -rotationPoint[1][0], -rotationPoint[2][0], point);

    double[][] m1 = {
            {origin[0][0], 0.0, 0.0, 0.0},
            {origin[1][0], 0.0, 0.0, 0.0},
            {origin[2][0], 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 1.0}
    };

    double[][] result = multiply(xMatrix, m1, 4);

    double[][] m2 = {
            {result[0][0], 0, 0, 0},
            {result[1][0], 0, 0, 0},
            {result[2][0], 0, 0, 0},
            {1.0         , 0, 0, 0}
    };

    return Transform.translation(rotationPoint[0][0], rotationPoint[1][0], rotationPoint[2][0], m2);
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
    double[][] origin = Transform.translation(-rotationPoint[0][0], -rotationPoint[1][0], -rotationPoint[2][0], point);

    double[][] m1 = {
            {origin[0][0], 0.0, 0.0, 0.0},
            {origin[1][0], 0.0, 0.0, 0.0},
            {origin[2][0], 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 1.0}
    };

    double[][] result = multiply(yMatrix, m1, 4);

    double[][] m2 = {
            {result[0][0], 0, 0, 0},
            {result[1][0], 0, 0, 0},
            {result[2][0], 0, 0, 0},
            {1.0         , 0, 0, 0}
    };

    return Transform.translation(rotationPoint[0][0], rotationPoint[1][0], rotationPoint[2][0], m2);
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
    double[][] origin = Transform.translation(-rotationPoint[0][0], -rotationPoint[1][0], -rotationPoint[2][0], point);

    double[][] m1 = {
            {origin[0][0], 0.0, 0.0, 0.0},
            {origin[1][0], 0.0, 0.0, 0.0},
            {origin[2][0], 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 1.0}
    };

    double[][] result = multiply(zMatrix, m1, 4);

    double[][] m2 = {
            {result[0][0], 0, 0, 0},
            {result[1][0], 0, 0, 0},
            {result[2][0], 0, 0, 0},
            {1.0         , 0, 0, 0}
    };

    return Transform.translation(rotationPoint[0][0], rotationPoint[1][0], rotationPoint[2][0], m2);
  }

  // TODO: 24.08.2023 Wydzielić do innej klasy
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