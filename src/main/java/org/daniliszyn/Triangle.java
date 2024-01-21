package org.daniliszyn;

import java.awt.*;
import java.awt.geom.Point2D;

public class Triangle {

    private Vector3D v1;
    private Vector3D v2;
    private Vector3D v3;

    public Triangle(Vector3D v1, Vector3D v2, Vector3D v3) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }

    public void display(Graphics g) {
        scale();

        //double[] lightDir = new double[] {0.0, 1.0, 0.0};
        double[] lightDir = Panel.LIGHT;
        double[] lightNormal = MatrixMath.normalize(lightDir);
        double[] normal = getNormal();

        //LIGHT
        float lightDot = (float) MatrixMath.dotProduct(lightNormal, normal);

        if (lightDot < 0.0) {
            g.setColor(Color.BLACK);
        } else if (lightDot >= 0.0) {
            g.setColor(new Color(lightDot, lightDot, lightDot));
        }

        double[][] tri = v1.getPoint3D();
        if (normal[0] * (tri[0][0] - 0) +
            normal[1] * (tri[1][0] - 0) +
            normal[2] * (tri[2][0] - 0) <= 0) {
            g.fillPolygon(
                    new int[]{
                            (int) v1.getX(),
                            (int) v2.getX(),
                            (int) v3.getX(),
                            (int) v2.getX(),
                            (int) v3.getX(),
                            (int) v1.getX()
                    },
                    new int[]{
                            (int) v1.getY(),
                            (int) v2.getY(),
                            (int) v3.getY(),
                            (int) v2.getY(),
                            (int) v3.getY(),
                            (int) v1.getY()
                    },
                    6
            );
            g.drawLine((int) v1.getX(), (int) v1.getY(), (int) v2.getX(), (int) v2.getY());
            g.drawLine((int) v2.getX(), (int) v2.getY(), (int) v3.getX(), (int) v3.getY());
            g.drawLine((int) v3.getX(), (int) v3.getY(), (int) v1.getX(), (int) v1.getY());
        }
    }

    private double[] getNormal() {
        double[][] m0 = v1.getPoint3D();
        double[][] m1 = v2.getPoint3D();
        double[][] m2 = v3.getPoint3D();
        double[] a = new double[] {
                m1[0][0]-m0[0][0], //x
                m1[1][0]-m0[1][0], //y
                m1[2][0]-m0[2][0]  //z
        };
        double[] b = new double[] {
                m2[0][0]-m0[0][0], //x
                m2[1][0]-m0[1][0], //y
                m2[2][0]-m0[2][0]  //z
        };
        return MatrixMath.crossProduct(a, b);
    }

    private void scale() {
        double[][] v1scale = Transformations.scale(v1.getPoint3D());
        double[][] v2scale = Transformations.scale(v2.getPoint3D());
        double[][] v3scale = Transformations.scale(v3.getPoint3D());
        v1.setPoint2D(new Point2D.Double(v1scale[0][0], v1scale[1][0]));
        v2.setPoint2D(new Point2D.Double(v2scale[0][0], v2scale[1][0]));
        v3.setPoint2D(new Point2D.Double(v3scale[0][0], v3scale[1][0]));
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

    public void setV1(Vector3D v1) {
        this.v1 = v1;
    }

    public void setV2(Vector3D v2) {
        this.v2 = v2;
    }

    public void setV3(Vector3D v3) {
        this.v3 = v3;
    }
}
