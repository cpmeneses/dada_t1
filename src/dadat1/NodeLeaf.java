package dadat1;

public class NodeLeaf extends Node implements InterfaceNode{
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
    Arrayable[] ans;
    if (num_rects < max) {
      this.mbr = mbr.surroundRect(rect);
      this.rectangle_array[num_rects] = rect;
      ans = new Arrayable[1];
      ans[0] = new ArrayableVoid();
      return ans;
    } else {
      ans = new Arrayable[1];
      ans[0] = rect;
      return ans;
    }
  }

  @Override
  public double findExpansion(Rectangle rect) {
    Rectangle surr_rect = mbr.surroundRect(rect);
    double expand_area = surr_rect.giveArea() - this.mbr.giveArea();
    return expand_area;
  }
  
  public Rectangle showRectangle(int i) {
    return rectangle_array[i];
  }
  
  public Rectangle[] giveRectangles() {
    return rectangle_array;
  }
}
