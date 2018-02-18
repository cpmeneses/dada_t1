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
  
  public double[] getCoor() {
    double[] res = new double[4];
    res[0] = x1;
    res[1] = x2;
    res[2] = y1;
    res[3] = y2;
    return res;
  }
  
  public Rectangle surroundRect(Arrayable arrb) {
    //entrega el mbr de this y rect
    Rectangle rect = arrb.getRectangle();
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
  
  public double getArea() {
    double res = (x2-x1)*(y2-y1);
    return res;
  }

  @Override
  public Rectangle getRectangle() {
    return this;
  }
  
  public boolean checkOtherInside(Rectangle rect) {
    double[] coor = rect.getCoor();
    return (coor[0] >= x1 && coor[1] <= x2 && coor[2] >= y1 && coor[3] <= y2);
  }
  
  public boolean checkOverlap(Rectangle rect) {
    double[] coor = rect.getCoor();
    return (x1 < coor[1] && x2 > coor[0] && y1 < coor[3] && y2 > coor[2]);
  }
  
  public double getTotalArea(Rectangle rect) {
    double[] coor = rect.getCoor();
    double left = Math.max(x1, coor[0]);
    double right = Math.max(x2, coor[1]);
    double down = Math.max(y1, coor[2]);
    double up = Math.max(y2, coor[3]);
    if (left < right && down < up) {
      return this.getArea() + rect.getArea() - ((right - left) * (up - down));
    } else {
      return this.getArea() + rect.getArea();
    }
  }
}
