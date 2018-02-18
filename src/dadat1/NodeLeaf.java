package dadat1;

import java.util.ArrayList;
import java.util.Arrays;

public class NodeLeaf extends Node{
  private boolean isRoot;
  private Rectangle mbr;
  private Rectangle[] rectangle_array;
  int min; //minimo de rectangulos
  int max; //maximo de rectangulos
  int num_rects; //rectangulos en rectangle_array
  
  public NodeLeaf(int m, int M) {
    this.isRoot = false;
    this.min = m;
    this.max = M;
    num_rects = 0;
    rectangle_array = new Rectangle[this.max];
    this.mbr = new Rectangle();
  }
  
  public Arrayable[] addRectangle(Rectangle rect) {
    Arrayable[] ans = new Arrayable[1];
    if (num_rects < max) {
      //agregar rect. hacer crecer mbr
      this.mbr = mbr.surroundRect(rect);
      this.rectangle_array[num_rects] = rect;
      num_rects++;
      //respuesta: todo en orden
      ans[0] = new ArrayableVoid();
      return ans;
    } else {
      //respuesta: no puedo meter este rectangulo
      //entregarselo al superior
      ans[0] = rect;
      return ans;
    }
  }

  @Override
  public double findExpansion(Rectangle rect) {
    Rectangle surr_rect = mbr.surroundRect(rect);
    double expand_area = surr_rect.getArea() - this.mbr.getArea();
    return expand_area;
  }
  
  public Arrayable showRectangle(int i) {
    return rectangle_array[i];
  }
  
  @Override
  public Arrayable[] getArrayables() {
    return this.rectangle_array;
  }

//  @Override
//  public Rectangle surroundRect(Arrayable arbs) {
//    return this.mbr.surroundRect(arbs);
//  }

  @Override
  public Rectangle getRectangle() {
    return this.mbr;
  }
  
  public Rectangle[] getRectangles() {
    return Arrays.copyOfRange(rectangle_array, 0, num_rects);
  }

  @Override
  public Rectangle[] searchRectangle(Rectangle rect) {
    ArrayList<Rectangle> pre_res = new ArrayList<Rectangle>();
    for (int i = 0; i < num_rects; i++) {
      if (rect.checkOtherInside(rectangle_array[i])) {
        pre_res.add(rectangle_array[i]);
      }
    }
    Rectangle[] res = new Rectangle[pre_res.size()];
    for (int i = 0; i < pre_res.size(); i++) {
      res[i] = pre_res.get(i);
    }
    return res;
  }

  @Override
  public boolean addArrayable(Arrayable arbs) {
    if (num_rects < max) {
      this.rectangle_array[num_rects] = (Rectangle) arbs;
      mbr = mbr.surroundRect(arbs);
      num_rects++;
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean isLeaf() {
    return true;
  }
}
