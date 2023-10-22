package org.daniliszyn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Panel extends JPanel implements KeyListener {
  public static final int OFFSET = 500;
  Cube cube = new Cube(-50, 50, 10, 100, 100, 100);


  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    cube.display(g, OFFSET);

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
    if (e.getKeyChar() == 'q'){
      //cube.rotate(3, 0, 0, rotationPoint);
      cube.rotate(3, 0, 0, null);
    }
    if (e.getKeyChar() == 'w'){
      cube.rotate(0, 3, 0, null);
    }
    if (e.getKeyChar() == 'e'){
      cube.rotate(0, 0, 3, null);
    }


    if (e.getKeyChar() == 'u'){
      cube.moveTest(5, 5, 5);
    }
    if (e.getKeyChar() == 'h'){
      cube.moveTest(1, 0, 0);
      //cube.rotate(0, 0, 3, null);
    }
    if (e.getKeyChar() == 'k'){
      cube.moveTest(1, 0, 0);
      //cube.rotate(0, 0, 3, null);
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}
