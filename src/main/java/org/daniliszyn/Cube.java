package org.daniliszyn;

import java.awt.Graphics;

public class Cube implements Displayable {

   Mesh cube;

  public Cube() {
    this.cube = MeshLoader.load("src/main/java/resources/cube.txt");
  }


  @Override
  public void display(Graphics g) {
    this.cube.display(g);
  }
}
