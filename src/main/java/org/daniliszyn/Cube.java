package org.daniliszyn;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Cube {
  private Rectangle rec1;
  private Rectangle rec2;
  private Rectangle rec3;
  private Rectangle rec4;
  private Rectangle rec5;
  private Rectangle rec6;
  private String name;

  public Cube(double x, double y, double z, double width, double height, double depth) {
    rec1 = new Rectangle(x, y, z, width, height, Color.BLACK, "sciana przod");
    rec2 = new Rectangle(x, y, z + depth, width, height, Color.RED, "sciana tyl");

    rec3 = new Rectangle(x, y, z + depth, width, height, Color.BLUE, "dach gora");
    rec3.rotate(90, 0, 0, new double[][]{
            {x + (width/2)},
            {y + (height/2)},
            {z + (depth/2)}
    });

    rec4 = new Rectangle(x, y , z + depth, width, height, Color.GRAY, "dach dol");
    rec4.rotate(270, 0, 0, new double[][]{
            {x + (width/2)},
            {y + (height/2)},
            {z + (depth/2)}
    });
    rec5 = new Rectangle(x, y, z, width, height, Color.GREEN, "sciana lewa");
    rec5.rotate(0, 270, 0,  new double[][]{
            {x + (width/2)},
            {y + (height/2)},
            {z + (depth/2)}
    });
    rec6 = new Rectangle(x, y, z, width, height, Color.CYAN, "sciana prawa");
    rec6.rotate(0, 90, 0,  new double[][]{
            {x + (width/2)},
            {y + (height/2)},
            {z + (depth/2)}
    });
  }

  public void display(Graphics g) {
    for (Rectangle rectangle : getDisplayOrder(getRectangles())) {
      g.setColor(rectangle.getColor());
      rectangle.display(g);
    }
  }

  public void moveTest(double x, double y, double z) {
    rec1.translate(x, y, z);
    rec2.translate(x, y, z);
    rec3.translate(x, y, z);
    rec4.translate(x, y, z);
    rec5.translate(x, y, z);
    rec6.translate(x, y, z);
  }

  public void rotate(int angleX, int angleY, int angleZ, double[][] rotationPoint) {
    rec1.rotate(angleX, angleY, angleZ, rotationPoint);
    rec2.rotate(angleX, angleY, angleZ, rotationPoint);
    rec3.rotate(angleX, angleY, angleZ, rotationPoint);
    rec4.rotate(angleX, angleY, angleZ, rotationPoint);
    rec5.rotate(angleX, angleY, angleZ, rotationPoint);
    rec6.rotate(angleX, angleY, angleZ, rotationPoint);
  }

  private Rectangle[] getDisplayOrder(Rectangle[] rectangles) {
    Arrays.sort(rectangles, Comparator.comparingDouble(o -> {
        return o.getV1().getZ() + o.getV2().getZ() + o.getV3().getZ() + o.getV4().getZ();
      }
    ));
    Collections.reverse(Arrays.asList(rectangles));
    return rectangles;
  }

  private Rectangle[] getRectangles() {
    return new Rectangle[]{
            rec1, rec2, rec3, rec4, rec5, rec6
    };
  }

  private void drawLines(Graphics g, int OFFSET) {
    g.setColor(Color.BLACK);
    g.drawLine((int) rec1.getV1().getX()+ OFFSET, (int) rec1.getV1().getY()+ OFFSET, (int) rec2.getV1().getX()+ OFFSET, (int) rec2.getV1().getY()+ OFFSET);
    g.drawLine((int) rec1.getV2().getX()+ OFFSET, (int) rec1.getV2().getY()+ OFFSET, (int) rec2.getV2().getX()+ OFFSET, (int) rec2.getV2().getY()+ OFFSET);
    g.drawLine((int) rec1.getV3().getX()+ OFFSET, (int) rec1.getV3().getY()+ OFFSET, (int) rec2.getV3().getX()+ OFFSET, (int) rec2.getV3().getY()+ OFFSET);
    g.drawLine((int) rec1.getV4().getX()+ OFFSET, (int) rec1.getV4().getY()+ OFFSET, (int) rec2.getV4().getX()+ OFFSET, (int) rec2.getV4().getY()+ OFFSET);
  }

}
