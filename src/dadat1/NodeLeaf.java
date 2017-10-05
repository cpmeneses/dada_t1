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

  @Override
  public void addRectangle() {
    // TODO Auto-generated method stub
  
  }

  @Override
  public int findExpansion(Rectangle rect) {
    // TODO Auto-generated method stub
    return 0;
  }
  
  
}
