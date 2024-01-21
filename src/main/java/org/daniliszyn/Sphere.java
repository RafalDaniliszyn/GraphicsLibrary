package org.daniliszyn;

import java.awt.*;

public class Sphere implements Displayable {
    Mesh sphere;

    public Sphere() {
        this.sphere = MeshLoader.load("src/main/java/resources/testsphere.txt");
    }

    @Override
    public void display(Graphics g) {
        g.setColor(Color.YELLOW);
        this.sphere.display(g);
    }
}
