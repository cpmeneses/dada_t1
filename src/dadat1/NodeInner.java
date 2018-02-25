package dadat1;

import java.util.Random;

public class NodeInner extends Node{
  private Node child_array[];
  int num_nodes;
  private boolean isRoot;
  int min;
  int max;
  Rectangle mbr;
  InterfaceHeuristic heuristic;
  
  //public NodeInner(int m, int M) {
  //  num_nodes = 0;
  //  isRoot = false;
  //  child_array = new Node[M];
  //  min = m;
  //  max = M;
  //  mbr = new Rectangle();
  //}
  
  public NodeInner(int m, int M, InterfaceHeuristic heur) {
    num_nodes = 0;
    isRoot = false;
    child_array = new Node[M];
    min = m;
    max = M;
    mbr = new Rectangle();
    heuristic = heur;
  }
  
  public void makeRoot() {
    this.isRoot = true;
  }
  
  public boolean addNode(Node node) {
    if (num_nodes < max) {
      child_array[num_nodes] = node;
      num_nodes++;
      mbr = mbr.surroundRect(node);
      return true;
    } else {
      return false;
    }
  }
  
  public Rectangle[] getRectangles() {
    Rectangle[][] arrs = new Rectangle[num_nodes][];
    int elems = 0;
    for (int i = 0; i < num_nodes; i++) {
      arrs[i] = child_array[i].getRectangles();
      elems += arrs[i].length;
    }
    Rectangle[] ans = new Rectangle[elems];
    int rect_index = 0;
    for (int i = 0; i < num_nodes; i++) {
      for (int j = 0; j < arrs[i].length; j++) {
        ans[rect_index] = arrs[i][j];
        rect_index++;
      }
    }
    return ans;
  }
  
  public int findTightest(Rectangle rect) {
    //entrega la posicion del nodo/rectangulo en child_array
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
        } else { //areglo de bug?
          candidates_left--;
        } //fin arreglo de bug?
      }
    }
    return win;
  }

  @Override
  public Arrayable[] addRectangle(Rectangle rect) {
	//ver cual en child_array tiene que crecer menos
	//y agregarlo ahí
    //si le retornar algo distinto a un nodeVoid:
    //partir el con overflow en dos, junto a lo que me devuelva
    //retornar false si hay overflow propio
    ////Rectangle rect = arrb.getRectangle();
    int tightest = findTightest(rect);
    Arrayable[] ans = child_array[tightest].addRectangle(rect);
    if (ans[0].isVoid()) {
      mbr = mbr.surroundRect(child_array[tightest]);
      return ans;
    } else {
      //partir al de abajo en dos, junto a lo recibido
      //usando la heuristica
      Node[] split_nodes = heuristic.splitNode(child_array[tightest], ans[0], min, max);
      //ver si se pueden agregar?
      child_array[tightest] = split_nodes[0];
      if (num_nodes < max) {
        child_array[num_nodes] = split_nodes[1];
        num_nodes++;
        mbr = mbr.surroundRect(split_nodes[1]);
        ans[0] = new ArrayableVoid();
        return ans;
      } else {
        ans[0] = split_nodes[1];
        return ans;
      }
    }
  }
  
  public Rectangle surroundRect(Rectangle rect) {
    return this.mbr.surroundRect(rect);
  }

//  @Override
//  public Arrayable[] addRectangle(Node node) {
//    Arrayable[] ans;
//    if (num_nodes < max) {
//      //agregar rect. hacer crecer mbr
//      this.mbr = mbr.surroundRect(node);
//      this.child_array[num_nodes] = node;
//      //respuesta: todo en orden
//      ans = new Arrayable[1];
//      ans[0] = new ArrayableVoid();
//      return ans;
//    } else {
//      //respuesta: no puedo meter este rectangulo
//      ans = new Arrayable[1];
//      ans[0] = rect;
//      return ans;
//    }
//  }

  @Override
  public Arrayable[] getArrayables() {
    return this.child_array;
  }

  @Override
  public Rectangle getRectangle() {
    return this.mbr;
  }

  @Override
  public Rectangle[] searchRectangle(Rectangle rect) {
    Rectangle[][] arrs = new Rectangle[num_nodes][];
    Rectangle[] res;
    int elems = 0;
    
    if (rect.checkOverlap(mbr)) {
      if (rect.checkOtherInside(mbr)) {
        for (int i = 0; i < num_nodes; i++) {
          arrs[i] = child_array[i].getRectangles();
          elems += arrs[i].length;
        }
      } else {
        for (int i = 0; i < num_nodes; i++) {
          arrs[i] = child_array[i].searchRectangle(rect);
          elems += arrs[i].length;
        }
      }
      res = new Rectangle[elems];
      int rect_index = 0;
      for (int i = 0; i < num_nodes; i++) {
        for (int j = 0; j < arrs[i].length; j++) {
          res[rect_index] = arrs[i][j];
          rect_index++;
        }
      }
      return res;
    } else {
      res = new Rectangle[0];
      return res;
    }
    //else {
    //  if (rect.checkOverlap(mbr)) {
    //    //poner el otro caso aqui dentro
    //  } else {
    //    res = new Rectangle[0];
    //    return res;
    //  }
    //}
    //arrs[i] = child_array[i].searchRectangle(rect);
  
  }

@Override
public boolean addArrayable(Arrayable arbs) {
  if (num_nodes < max) {
    this.child_array[num_nodes] = (Node) arbs;
    num_nodes++;
    mbr = mbr.surroundRect(arbs);
    return true;
  } else {
    return false;
  }
}

  @Override
  public boolean isLeaf() {
    return false;
  }
}
