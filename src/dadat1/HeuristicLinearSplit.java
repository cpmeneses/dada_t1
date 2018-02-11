package dadat1;

import java.util.Random;

public class HeuristicLinearSplit implements InterfaceHeuristic {
  @Override
  public Node[] splitNode(Node node, Arrayable rect, int m, int M) {
    //Toma el nodo con overflow y el rectangulo que causa el overflow
    //Y retorna un arreglo con dos nodos correctos
    Node node1 = new NodeLeaf(m, M);
    Node node2 = new NodeLeaf(m, M);
    
    //implementar
    int in1 = 0;
    int in2 = 0;
    
    //inicializar un arreglo de todos los rectanguloss
    Arrayable[] oldrects = node.giveArrayables();
    int l = oldrects.length;
    Arrayable[] rects = new Arrayable[l + 1];
    for (int i = 0; i < l; i++) {
      rects[i] = oldrects[i];
    }
    rects[l] = rect;
    
    //aleatorizarlo
    rects = this.randomizeArrayableArray(rects);
    
    Node[] res = new Node[2];
    res[0] = node1;
    res[1] = node2;
    return res;
  }
  
  public Arrayable[] randomizeArrayableArray(Arrayable[] rects) {  
    int l = rects.length;
    Arrayable[] res = new Arrayable[l];
    Random rand = new Random(System.currentTimeMillis());
    int pos;
    for (int i = l-1; i >= 0; i--) {
      pos = rand.nextInt(i+1);
      res[i] = rects[pos];
      if (pos != i) {
        rects[pos] = rects[i];
      }
    }
    return res;
  }
}
