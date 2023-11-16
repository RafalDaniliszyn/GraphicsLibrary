package org.daniliszyn;

import java.awt.*;

public class World {
    Mesh mesh;

    public World() {
        initSample();
    }

    public void start(Graphics g) {
        mesh.display(g);
    }

    private void initSample() {
        mesh = MeshLoader.load("src/main/java/resources/cow.txt");
        mesh.rotate(0, 0, 0, new double[][] {
                {0},
                {0},
                {4}
        });
    }
}
