package dadat1;

public class Rectangle extends Arrayable{
  boolean defined;
  double x1;
  double x2;
  double y1;
  double y2;
  
  public Rectangle() {
    this.defined = false;
  }
  
  public Rectangle(double x1, double x2, double y1, double y2) {
    this.defined = true;
    this.x1 = x1;
    this.x2 = x2;
    this.y1 = y1;
    this.y2 = y2;
  }
  
  public boolean isVoid() {
    return false;
  }
  
  public Rectangle surroundRect(Rectangle rect) {
    //entrega el mbr de this y rect
    if (!this.defined) {
      return rect;
    }
    double rx1, rx2, ry1, ry2;
    rx1 = Math.min(this.x1, rect.x1);
    rx2 = Math.max(this.x2, rect.x2);
    ry1 = Math.min(this.y1, rect.y1);
    ry2 = Math.max(this.y2, rect.y2);
    Rectangle res = new Rectangle(rx1, rx2, ry1, ry2);
    return res;
  }
  
  public double giveArea() {
    double res = (x2-x1)*(y2-y1);
    return res;
  }
}
