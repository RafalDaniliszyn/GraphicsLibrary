package org.daniliszyn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements KeyListener {

  Mouse mouse = new Mouse();

  World world = new World();

  Cube cube = new Cube();
  Sphere sun = new Sphere();

  public static double[] CAMERA = new double[]{0.0, 0.0, 0.0};

  double[][] rotationPoint = cube.cube.getCenter();

  public static double[] LIGHT = new double[] {0.0, 1.0, 0.0};

  public Panel() {
    sun.sphere.translate(0, 0, 5);
    cube.cube.translate(0, 0, 9);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    //world.start(g);

    cube.display(g);
    sun.display(g);
    lightTest();

    try {
      repaint();
      Thread.sleep(10);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

  }

  public void lightTest() {
//    Point point = mouse.getMousePosition();
//    double lightX = point.getX()/1300;
//    double lightY = point.getY()/800;
//    Panel.LIGHT = new double[] {lightX, -lightY, -1.0};
    sun.sphere.rotate(0, 1, 0, rotationPoint);
    double[][] sunCenter = sun.sphere.getCenter();
    Panel.LIGHT = new double[] {sunCenter[0][0] - rotationPoint[0][0], sunCenter[1][0] - rotationPoint[1][0], sunCenter[2][0] - rotationPoint[2][0]};

  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
//    if (e.getKeyChar() == 'q'){
//      world.mesh.rotate(1, 0, 0, rotationPoint);
//    }
//    if (e.getKeyChar() == 'w'){
//      world.mesh.rotate(0, 1, 0, rotationPoint);
//    }
//    if (e.getKeyChar() == 'e'){
//      world.mesh.rotate(0, 0, 1, rotationPoint);
//    }
//    if (key == KeyEvent.VK_UP){
//      world.mesh.translate(0.0, -1.0, 0.0);
//    }
//    if (key == KeyEvent.VK_DOWN){
//      world.mesh.translate(0, 1.0, 0);
//    }
//    if (key == KeyEvent.VK_LEFT){
//      world.mesh.translate(1.0, 0, 0);
//    }
//    if (key == KeyEvent.VK_RIGHT){
//      world.mesh.translate(-1.0, 0, 0);
//    }
//    if (key == KeyEvent.VK_Z){
//      world.mesh.translate(0, 0, -1.0);
//    }
//    if (key == KeyEvent.VK_X){
//      world.mesh.translate(0, 0, 1.0);
//    }


    rotationPoint = cube.cube.getCenter();
    if (e.getKeyChar() == 'q'){
      cube.cube.rotate(2, 0, 0, rotationPoint);
    }
    if (e.getKeyChar() == 'w'){
      cube.cube.rotate(0, 2, 0, rotationPoint);
    }
    if (e.getKeyChar() == 'e'){
      cube.cube.rotate(0, 0, 2, rotationPoint);
    }
    if (key == KeyEvent.VK_UP){
      cube.cube.translate(0.0, 1.0, 0.0);
    }
    if (key == KeyEvent.VK_DOWN){
      cube.cube.translate(0, -1.0, 0);
    }
    if (key == KeyEvent.VK_LEFT){
      cube.cube.translate(-1.0, 0, 0);
    }
    if (key == KeyEvent.VK_RIGHT){
      cube.cube.translate(1.0, 0, 0);
    }
    if (key == KeyEvent.VK_Z){
      cube.cube.translate(0, 0, -1.0);
    }
    if (key == KeyEvent.VK_X){
      cube.cube.translate(0, 0, 1.0);
    }

    if (key == KeyEvent.VK_P){
     this.rotationPoint[0][0] = 100;
    }

    if (key == KeyEvent.VK_I){
      Panel.CAMERA[0] = Panel.CAMERA[0]+0.01;
      System.out.println(Panel.CAMERA[0]);
    }
    if (key == KeyEvent.VK_K){
      Panel.CAMERA[0] = Panel.CAMERA[0]-0.01;
    }

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
