package org.daniliszyn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements KeyListener {
  Cube cube = new Cube(0.3, 0.3, 3, 1.2, 1.2, 1.2);
  Rectangle rect = new Rectangle(-1, -1, 1, 1, 150, Color.BLACK, "");

  World world = new World();

  double[][] rotationPoint = new double[][] {
    {0},
    {0},
    {4}
  };

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    //cube.display(g);
    world.start(g);
    //world.mesh.rotate(3, 3, 0, rotationPoint);

    try {
      repaint();
      Thread.sleep(10);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    if (e.getKeyChar() == 'q'){
      cube.rotate(3, 0, 0, this.rotationPoint);
      rect.rotate(3, 0, 0, null);
      world.mesh.rotate(1, 0, 0, rotationPoint);
    }
    if (e.getKeyChar() == 'w'){
      cube.rotate(0, 3, 0, this.rotationPoint);
      rect.rotate(0, 3, 0, null);
      world.mesh.rotate(0, 1, 0, rotationPoint);
    }
    if (e.getKeyChar() == 'e'){
      cube.rotate(0, 0, 3, this.rotationPoint);
      rect.rotate(0, 0, 3, null);
      world.mesh.rotate(0, 0, 1, rotationPoint);
    }


    if (key == KeyEvent.VK_UP){
      world.mesh.translate(0.0, -1.0, 0.0);
    }
    if (key == KeyEvent.VK_DOWN){
      world.mesh.translate(0, 1.0, 0);
    }
    if (key == KeyEvent.VK_LEFT){
      world.mesh.translate(1.0, 0, 0);
    }
    if (key == KeyEvent.VK_RIGHT){
      world.mesh.translate(-1.0, 0, 0);
    }
    if (key == KeyEvent.VK_Z){
      world.mesh.translate(0, 0, -1.0);
    }
    if (key == KeyEvent.VK_X){
      world.mesh.translate(0, 0, 1.0);
    }

    if (key == KeyEvent.VK_P){
     this.rotationPoint[0][0] = 100;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
