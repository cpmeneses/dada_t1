package dadat1;

public abstract class Node extends Arrayable implements InterfaceNode{
  private Rectangle mbr;
  //debe tener elementos
  //debe tener un padre
  //seguramente tendra hijos
  
  public boolean isVoid() {
    return false;
  }
  
  public double findExpansion(Rectangle rect) {
    Rectangle surr_rect = mbr.surroundRect(rect);
    double expand_area = surr_rect.getArea() - this.mbr.getArea();
    return expand_area;
  }
}
