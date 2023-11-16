package org.daniliszyn;

import java.awt.Graphics;

public class Mesh {

    private Triangle[] triangles;

    public Mesh(Triangle[] triangles) {
        this.triangles = triangles;
    }

    public void display(Graphics g) {
        for (Triangle triangle : triangles) {
            triangle.display(g);
        }
    }

    public void rotate(int angleX, int angleY, int angleZ, double[][] rotationPoint) {
        for (Triangle triangle : triangles) {
            triangle.setV1(new Vector3D(Transform.rotate(angleX, angleY, angleZ, triangle.getV1().getPoint3D(), rotationPoint)));
            triangle.setV2(new Vector3D(Transform.rotate(angleX, angleY, angleZ, triangle.getV2().getPoint3D(), rotationPoint)));
            triangle.setV3(new Vector3D(Transform.rotate(angleX, angleY, angleZ, triangle.getV3().getPoint3D(), rotationPoint)));
        }
    }

    public void translate(double x, double y, double z) {
        for (Triangle triangle : triangles) {
            triangle.setV1(new Vector3D(Transform.translation(x, y, z, triangle.getV1().getPoint3D())));
            triangle.setV2(new Vector3D(Transform.translation(x, y, z, triangle.getV2().getPoint3D())));
            triangle.setV3(new Vector3D(Transform.translation(x, y, z, triangle.getV3().getPoint3D())));
        }
    }
}
