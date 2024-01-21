package org.daniliszyn;

/**
 * Class MatrixMath provides methods for performing matrix operations.
 */
public class MatrixMath {
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

    public static double dotProduct(double[] a, double[] b) {
        return (a[0] * b[0]) + (a[1] * b[1]) + (a[2] * b[2]);
    }

    public static double[] normalize(double[] a) {
        double m = Math.sqrt((a[0] * a[0]) + (a[1] * a[1]) + (a[2] * a[2]));
        return new double[] {
                a[0]/m, a[1]/m, a[2]/m
        };
    }

    public static double[] crossProduct(double[] v1, double[] v2) {
        double[] n = new double[] {
                (v1[1]*v2[2]) - (v1[2]*v2[1]),
                (v1[2]*v2[0]) - (v1[0]*v2[2]),
                (v1[0]*v2[1]) - (v1[1]*v2[0])
        };
        return MatrixMath.normalize(n);
    }
}
