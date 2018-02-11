package dadat1;

public interface InterfaceNode{
  //retorna un arreglo con ArrayableVoid si es correcto
  //retorna un arreglo con elementos no void si hay overflow
  Arrayable[] addArrayable(Arrayable arbs);
  double findExpansion(Rectangle rect);
  Arrayable[] giveArrayables();
}
