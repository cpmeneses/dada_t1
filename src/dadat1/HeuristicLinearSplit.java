package dadat1;

import java.util.Random;

public class HeuristicLinearSplit implements InterfaceHeuristic {
  @Override
  public Node[] splitNode(Node node, Arrayable rect, int m, int M) {
    //Toma el nodo con overflow y el rectangulo que causa el overflow
    //Y retorna un arreglo con dos nodos correctos
    Node node1;
    Node node2;
	if (node.isLeaf()) {
      node1 = new NodeLeaf(m, M);
      node2 = new NodeLeaf(m, M);
    }  else {
      node1 = new NodeInner(m, M, this);
      node2 = new NodeInner(m, M, this);
    }
    
    //inicializar un arreglo de todos los rectanguloss
    Arrayable[] oldrects = node.getArrayables();
    int l = oldrects.length;
    Arrayable[] rects = new Arrayable[l + 1];
    for (int i = 0; i < l; i++) {
      rects[i] = oldrects[i];
    }
    rects[l] = rect;
    
    //aleatorizarlo
    rects = this.randomizeArrayableArray(rects);
    
    double[] coor;
    coor = rects[0].getRectangle().getCoor();
    
    double left_range = coor[0]; //inicializar todos con rects[0]
    double right_range = coor[1];
    double rightmost_left = coor[0];
    double leftmost_right = coor[1];
    int leftside_winner = 0; //el indice del ganador
    int rightside_winner = 0;
    
    double down_range = coor[2];
    double up_range = coor[3];
    double highest_down = coor[2];
    double lowest_up = coor[3];
    int downside_winner = 0;
    int upside_winner = 0;
    
    //up_range
    //down_range
    
    //Horizontal y Vertical
    for (int i = 1; i < rects.length; i++) { //excluyo el rects[0]
      coor = rects[i].getRectangle().getCoor();
      if (coor[0] < left_range) {
        left_range = coor[0];
      }
      if (coor[1] > right_range) {
        right_range = coor[1];
      }
      if (coor[2] < down_range) {
        down_range = coor[2];
      }
      if (coor[3] > up_range) {
        up_range = coor[3];
      }
      if (coor[0] > rightmost_left) {
        rightmost_left = coor[0];
        leftside_winner = i;
      }
      if (coor[1] < leftmost_right) {
        leftmost_right = coor[1];
        rightside_winner = i;
      }
      if (coor[2] > highest_down) {
        highest_down = coor[2];
        downside_winner = i;
      }
      if (coor[3] < lowest_up) {
        lowest_up = coor[3];
        upside_winner = i;
      }
    }
    
    int winner1;
    int winner2;
    
    if ((rightmost_left - leftmost_right) / (right_range - left_range)
        > (highest_down - lowest_up) / (up_range - down_range)) {
      //separacion horizontal > separacion vertical
      if (leftside_winner == rightside_winner) {
        winner1 = leftside_winner;
        if (winner1 == 0) {//tomar otro al azar
          winner2 = 1;
        } else {
          winner2 = 0;
        }
      } else {
        //usar ambos como iniciales
        winner1 = leftside_winner;
        winner2 = rightside_winner;
      }
    } else {
      if (upside_winner == downside_winner) {
        winner1 = downside_winner;
        if (winner1 == 0) {
          winner2 = 1;
        } else {
          winner2 = 0;
        }
      } else {
        winner1 = downside_winner;
        winner2 = upside_winner;
      }
    }
    
    if (winner2 < winner1) {
      int aux = winner2;
      winner2 = winner1;
      winner1 = aux;
    }
    
    node1.addArrayable(rects[winner1]);
    node2.addArrayable(rects[winner2]);
    
    int in1 = 1;
    int in2 = 1;
    int needed = M - m + 1;
    
    rects[winner2] = rects[l]; //(l + 1) - 1
    rects[winner1] = rects[l - 1]; //(l + 1) - 2
    
    //agregar el resto
    for (int i = l - 2; i >= 0; i--) { // (l + 1) los elementos - 2 los ya puestos - 1 es indice
      //revisar si ya tiene todos los necesarios
      if (in1 >= needed) {
        //poner en node2
        node2.addArrayable(rects[i]);
        in2++;
        continue;
      }
      //revisar si ya tiene todos los necesarios
      if (in2 >= needed) {
        //poner en node1
        node1.addArrayable(rects[i]);
        in1++;
        continue;
      }
        
      //ver cual rectangulo maximiza la diferencia de incremento. No para linear
      //iter_max_incr = -1; //DEBUG
      //max_pos = 0;
      //for (int j = 0; j <= i; j++) {
      //  iter_rect_incr_d1 = node1.findExpansion(arbs[j].getRectangle());
      //  iter_rect_incr_d2 = node2.findExpansion(arbs[j].getRectangle());
      //  //ver si es maximo
      //  if (Math.abs(iter_rect_incr_d1 - iter_rect_incr_d2) >= iter_max_incr) {
      //    iter_max_incr = Math.abs(iter_rect_incr_d1 - iter_rect_incr_d2);
      //    max_pos = j;
      //  }
      //}
      
      //ver a cual nodo pertenece
      if (node1.findExpansion(rects[i].getRectangle()) > node2.findExpansion(rects[i].getRectangle())) {
        node2.addArrayable(rects[i]);
        in2++;
      } else {
        node1.addArrayable(rects[i]);
        in1++;
      }
        
      //ordenar. No es necesario en linear
      //arbs[max_pos] = arbs[i];
    }
    
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
      rects[pos] = rects[i];
    }
    return res;
  }
}
