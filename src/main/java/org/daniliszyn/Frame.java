package org.daniliszyn;

import javax.swing.*;

public class Frame extends JFrame {

  public Frame(Panel panel) {
    setSize(1300, 800);
    setContentPane(panel);
    addKeyListener(panel);
    addMouseListener(panel.mouse);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }
}
