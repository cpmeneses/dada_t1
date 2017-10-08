package dadat1;

public abstract class Node implements InterfaceNode{
  private Rectangle mbr;
  //debe tener elementos
  //debe tener un padre
  //seguramente tendra hijos
  public double findExpansion(Rectangle rect) {
    Rectangle surr_rect = mbr.surroundRect(rect);
    double expand_area = surr_rect.giveArea() - this.mbr.giveArea();
    return expand_area;
  }
}
