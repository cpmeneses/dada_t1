package dadat1;

import java.util.Random;

public class NodeInner extends Node implements InterfaceNode{
  private Node child_array[];
  int num_nodes;
  private boolean isRoot;
  int min;
  int max;
  Rectangle mbr;
  
  public NodeInner(int m, int M) {
    num_nodes = 0;
    isRoot = false;
    child_array = new Node[M];
    min = m;
    max = M;
  }
  
  public boolean addNode() {
    //si hay un overflow debajo, se hace crecer.
    return false;
  }
  
  public int findTightest(Rectangle rect) {
    //entrega la posicion del nodo/recrangulo en child_array
    //que debe crecer menos para agregar a rect
    //si hay empate se debe elegir al azar
    double array_expand[] = new double[num_nodes]; //cuanto debe expandirse cada uno
    double iter_expand;
    double min_expand = child_array[0].findExpansion(rect);
    array_expand[0] = min_expand;
    int tied = 1;
    for(int i = 1; i < num_nodes; i++) {
      iter_expand = child_array[i].findExpansion(rect);
      array_expand[i] = iter_expand;
      if (iter_expand < min_expand) {
        min_expand = iter_expand;
        tied = 1;
      }
      else if (iter_expand == min_expand) {
        tied++;
      }
    }
    int candidates_left = tied;
    Random random = new Random();
    int win = -1;
    for (int i = 0; i < num_nodes; i++) {
      if (array_expand[i] == min_expand) {
        if (random.nextInt(candidates_left) == 0) {
          win = i;
          break;
        }
      }
    }
    return win;
  }

  @Override
  public boolean addRectangle(Rectangle rect) {
	//ver cual en child_array tiene que crecer menos
	//y agregarlo ahí
    //retornar false si hay overflow
    return false;
  }
}
