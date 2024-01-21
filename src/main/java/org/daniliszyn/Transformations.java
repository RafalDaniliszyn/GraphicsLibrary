package org.daniliszyn;

import static org.daniliszyn.ViewProperties.*;

public class Transformations {

  public static double[][] translate(double x, double y, double z, double[][] point) {
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
    double[][] result = MatrixMath.multiply(matrix, m1, 4);
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

  private static double[][] rotateX(int angle, double[][] point, double[][] rotationPoint) {
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

    double[][] origin = Transformations.translate(-rotationPoint[0][0], -rotationPoint[1][0], -rotationPoint[2][0], point);

    double[][] m1 = {
            {origin[0][0], 0.0, 0.0, 0.0},
            {origin[1][0], 0.0, 0.0, 0.0},
            {origin[2][0], 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 1.0}
    };

    double[][] result = MatrixMath.multiply(xMatrix, m1, 4);

    double[][] m2 = {
            {result[0][0], 0, 0, 0},
            {result[1][0], 0, 0, 0},
            {result[2][0], 0, 0, 0},
            {1.0         , 0, 0, 0}
    };

    return Transformations.translate(rotationPoint[0][0], rotationPoint[1][0], rotationPoint[2][0], m2);
  }

  private static double[][] rotateY(int angle, double[][] point, double[][] rotationPoint) {
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
    double[][] origin = Transformations.translate(-rotationPoint[0][0], -rotationPoint[1][0], -rotationPoint[2][0], point);

    double[][] m1 = {
            {origin[0][0], 0.0, 0.0, 0.0},
            {origin[1][0], 0.0, 0.0, 0.0},
            {origin[2][0], 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 1.0}
    };

    double[][] result = MatrixMath.multiply(yMatrix, m1, 4);

    double[][] m2 = {
            {result[0][0], 0, 0, 0},
            {result[1][0], 0, 0, 0},
            {result[2][0], 0, 0, 0},
            {1.0         , 0, 0, 0}
    };

    return Transformations.translate(rotationPoint[0][0], rotationPoint[1][0], rotationPoint[2][0], m2);
  }

  private static double[][] rotateZ(int angle, double[][] point, double[][] rotationPoint) {
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
    double[][] origin = Transformations.translate(-rotationPoint[0][0], -rotationPoint[1][0], -rotationPoint[2][0], point);

    double[][] m1 = {
            {origin[0][0], 0.0, 0.0, 0.0},
            {origin[1][0], 0.0, 0.0, 0.0},
            {origin[2][0], 0.0, 0.0, 0.0},
            {0.0, 0.0, 0.0, 1.0}
    };

    double[][] result = MatrixMath.multiply(zMatrix, m1, 4);

    double[][] m2 = {
            {result[0][0], 0, 0, 0},
            {result[1][0], 0, 0, 0},
            {result[2][0], 0, 0, 0},
            {1.0         , 0, 0, 0}
    };

    return Transformations.translate(rotationPoint[0][0], rotationPoint[1][0], rotationPoint[2][0], m2);
  }

  public static double[][] scale(double[][] point) {
    double[][] scaleMatrix = {
            {(2* NEAR)/(RIGHT - LEFT), 0, (RIGHT + LEFT) / (RIGHT - LEFT), 0},
            {0, (2 * NEAR) / (TOP - BOTTOM), (TOP + BOTTOM) / (TOP - BOTTOM), 0},
            {0, 0, -(FAR + NEAR) / (FAR - NEAR), (-2 * FAR * NEAR) / (FAR - NEAR)},
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