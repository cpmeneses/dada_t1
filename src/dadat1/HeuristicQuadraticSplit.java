package dadat1;

import java.util.Random;

public class HeuristicQuadraticSplit implements InterfaceHeuristic {
  @Override
  public Node[] splitNodeLeaf(NodeLeaf node, Rectangle rect, int m, int M) {
    //Toma el nodo con overflow y el rectangulo que causa el overflow
    //Y retorna un arreglo con dos nodos correctos
    
    
    //implementar
    //inicializar un arreglo de todos los rectanguloss
    Rectangle[] oldrects = node.giveRectangles();
    int l = oldrects.length;
    Rectangle[] rects = new Rectangle[l + 1];
    for (int i = 0; i < l; i++) {
      rects[i] = oldrects[i];
    }
    rects[l] = rect;
    
    //se busca el par con la mayor area inutil
    double max_useless_area = 0;
    int[] max_par = new int[2];
    max_par[0] = -1; //los indices
    max_par[1] = -1;
    double it_area;
    double it_useless_area;
    Rectangle it_mbr;
    for (int i = 0; i < l; i++) {
      for (int j = i+1; j < l; j++) {
        it_mbr = oldrects[i].surroundRect(oldrects[j]);
        it_area = it_mbr.giveArea();
        it_useless_area = it_area - oldrects[i].giveArea() - oldrects[j].giveArea();
        if (it_useless_area > max_useless_area) {
          max_useless_area = it_useless_area;
          max_par[0] = i;
          max_par[1] = j;
        }
      }
    }
    
    //reordenar
    rects[max_par[1]] = rects[l-1];
    rects[max_par[0]] = rects[l-2];
    
    //agregar rectangulos
    Node node1 = new NodeLeaf(m, M);
    Node node2 = new NodeLeaf(m, M);
    int in1 = 0;
    int in2 = 0;
    int needed = M - m + 1;
    //iter de maximizacion de diferencia
    double iter_rect_incr_d1;
    double iter_rect_incr_d2;
    double iter_max_incr;
    int max_pos;
    
    for (int i = l-3; i >= 0; i--) {
      //revisar si ya tiene todos los necesarios
      if (in1 >= needed) {
        //poner en node2
        node2.addRectangle(rects[i]);
        continue;
      }
      //revisar si ya tiene todos los necesarios
      if (in2 >= needed) {
        //poner en node1
        node1.addRectangle(rects[i]);
        continue;
      }
      
      //ver cual rectangulo maximiza la diferencia de incremento
      iter_max_incr = 0;
      max_pos = 0;
      for (int j = 0; j <= i; j++) {
        iter_rect_incr_d1 = node1.findExpansion(rects[j]);
        iter_rect_incr_d2 = node2.findExpansion(rects[j]);
        //ver si es maximo
        if (Math.abs(iter_rect_incr_d1 - iter_rect_incr_d2) >= iter_max_incr) {
          iter_max_incr = Math.abs(iter_rect_incr_d1 - iter_rect_incr_d2);
          max_pos = j;
        }
      }
      
      //ver a cual nodo pertenece
      if (node1.findExpansion(rects[max_pos]) > node2.findExpansion(rects[max_pos])) {
        node2.addRectangle(rects[max_pos]);
      } else {
        node1.addRectangle(rects[max_pos]);
      }
      
      //ordenar
      rects[max_pos] = rects[i];
    }
    
    Node[] res = new Node[2];
    res[0] = node1;
    res[1] = node2;
    return res;
  }
}
