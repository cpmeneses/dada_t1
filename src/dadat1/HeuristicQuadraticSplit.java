package dadat1;

import java.util.Random;

public class HeuristicQuadraticSplit implements InterfaceHeuristic {
  @Override
  public Node[] splitNode(Node node_to_split, Arrayable arb_to_add, int m, int M) {
    //Toma el nodo con overflow y el rectangulo que causa el overflow
    //Y retorna un arreglo con dos nodos correctos
    
    
    //implementar
    //inicializar un arreglo de todos los arrayables
    Arrayable[] old_arbs = node_to_split.getArrayables();
    int l = old_arbs.length;
    Arrayable[] arbs = new Arrayable[l + 1];
    for (int i = 0; i < l; i++) {
      arbs[i] = old_arbs[i];
    }
    arbs[l] = arb_to_add;
    
    //se busca el par con la mayor area inutil
    double max_useless_area = -1;
    int[] max_par = new int[2];
    max_par[0] = -1; //los indices
    max_par[1] = -1;
    double it_area;
    double it_useless_area;
    Rectangle it_mbr;
    for (int i = 0; i < l; i++) {
      for (int j = i+1; j < l; j++) {
        it_mbr = old_arbs[i].getRectangle().surroundRect(old_arbs[j]);
        it_area = it_mbr.getArea();
        it_useless_area = it_area - old_arbs[i].getRectangle().getTotalArea(old_arbs[j].getRectangle());//old_arbs[i].getRectangle().getArea() - old_arbs[j].getRectangle().getArea(); //quitar el area comun
        if (it_useless_area > max_useless_area) {
          max_useless_area = it_useless_area;
          max_par[0] = i;
          max_par[1] = j;
        }
      }
    }
    
    
    
    //agregar rectangulos
    //if nodetosplit is leaf
    Node node1;
    Node node2;
    if (node_to_split.isLeaf()) {
      node1 = new NodeLeaf(m, M);
      node2 = new NodeLeaf(m, M);
    } else {
      node1 = new NodeInner(m, M);
      node2 = new NodeInner(m, M);
    }
    node1.addArrayable(arbs[max_par[0]]);
    node2.addArrayable(arbs[max_par[1]]);
    int in1 = 1;
    int in2 = 1;
    int needed = M - m + 1;
    
    //reordenar.
    arbs[max_par[1]] = arbs[l]; //(l + 1) - 1
    arbs[max_par[0]] = arbs[l - 1]; //(l + 1) - 2
    
    //iter de maximizacion de diferencia
    double iter_rect_incr_d1;
    double iter_rect_incr_d2;
    double iter_max_incr;
    int max_pos;
    
    for (int i = l - 2; i >= 0; i--) { // (l + 1) los elementos - 2 los ya puestos - 1 es indice
      //revisar si ya tiene todos los necesarios
      if (in1 >= needed) {
        //poner en node2
        node2.addArrayable(arbs[i]);
        in2++;
        continue;
      }
      //revisar si ya tiene todos los necesarios
      if (in2 >= needed) {
        //poner en node1
        node1.addArrayable(arbs[i]);
        in1++;
        continue;
      }
      
      //ver cual rectangulo maximiza la diferencia de incremento
      iter_max_incr = 0;
      max_pos = 0;
      for (int j = 0; j <= i; j++) {
        iter_rect_incr_d1 = node1.findExpansion(arbs[j].getRectangle());
        iter_rect_incr_d2 = node2.findExpansion(arbs[j].getRectangle());
        //ver si es maximo
        if (Math.abs(iter_rect_incr_d1 - iter_rect_incr_d2) >= iter_max_incr) {
          iter_max_incr = Math.abs(iter_rect_incr_d1 - iter_rect_incr_d2);
          max_pos = j;
        }
      }
      
      //ver a cual nodo pertenece
      if (node1.findExpansion(arbs[max_pos].getRectangle()) > node2.findExpansion(arbs[max_pos].getRectangle())) {
        node2.addArrayable(arbs[max_pos]);
        in2++;
      } else {
        node1.addArrayable(arbs[max_pos]);
        in1++;
      }
      
      //ordenar
      arbs[max_pos] = arbs[i];
    }
    
    Node[] res = new Node[2];
    res[0] = node1;
    res[1] = node2;
    return res;
  }
}
