package dadat1;

public interface InterfaceNode{
  //retorna un arreglo con ArrayableVoid si es correcto
  //retorna un arreglo con elementos no void si hay overflow
  //Arrayable[] addArrayable(Arrayable arbs);
  boolean addArrayable(Arrayable arbs);
  Arrayable[] getArrayables();
  boolean isLeaf();
  
  double findExpansion(Rectangle rect);
  
  Arrayable[] addRectangle(Rectangle rect);
  Rectangle[] searchRectangle(Rectangle rect);
  Rectangle[] getRectangles();
}
