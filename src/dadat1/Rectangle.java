package dadat1;

public class Rectangle {
  int x1;
  int x2;
  int y1;
  int y2;
  
  public Rectangle(int x1, int x2, int y1, int y2) {
    this.x1 = x1;
    this.x2 = x2;
    this.y1 = y1;
    this.y2 = y2;
  }
  
  public Rectangle surroundRect(Rectangle rect) {
    //entrega el mbr de this y rect
    int rx1, rx2, ry1, ry2;
    rx1 = Math.min(this.x1, rect.x1);
    rx2 = Math.max(this.x2, rect.x2);
    ry1 = Math.min(this.y1, rect.y1);
    ry2 = Math.max(this.y2, rect.y2);
    Rectangle res = new Rectangle(rx1, rx2, ry1, ry2);
    return res;
  }
}
