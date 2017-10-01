package dadat1;

public abstract class NodeLeaf extends Node implements InterfaceNode{
  private Rectangle mbr;
  private Rectangle[] rectangle_array;
  int min; //minimo de rectangulos
  int max; //maximo de rectangulos
  int num_rects; //rectangulos en rectangle_array
  
  public NodeLeaf(int m, int M) {
    this.min = m;
    this.max = M;
    num_rects = 0;
    rectangle_array = new Rectangle[this.max];
    //falta el mbr
  }
  
  public boolean addRectangle(Rectangle rect) {
    if (num_rects < max) {
      this.rectangle_array[num_rects] = rect;
      return true;
    } else {
      return false;
    }
  }
}
