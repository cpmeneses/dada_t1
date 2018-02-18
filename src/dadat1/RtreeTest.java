package dadat1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RtreeTest {

  @Test
  void test_LinearSplit() {
    HeuristicLinearSplit heur = new HeuristicLinearSplit();
    Rectangle[] rects = new Rectangle[10];
    rects[0] = new Rectangle();
    rects[1] = new Rectangle();
    rects[2] = new Rectangle();
    rects[3] = new Rectangle();
    rects[4] = new Rectangle();
    rects[5] = new Rectangle();
    rects[6] = new Rectangle();
    rects[7] = new Rectangle();
    rects[8] = new Rectangle();
    rects[9] = new Rectangle();
    Arrayable[] ans = heur.randomizeArrayableArray(rects);
  }

  @Test
  void test_insert_1() {
    HeuristicLinearSplit heur = new HeuristicLinearSplit();
    NodeHead head = new NodeHead(2, 3, heur);
    head.addRectangle(1, 10, 1, 10);
    assertEquals(head.search(0, 11, 0, 11).length, 1);
    //assertEquals(head.real_node.getArrayables().length, 3);
  }
  
  @Test
  void test_insert_2() {
    HeuristicLinearSplit heur = new HeuristicLinearSplit();
    NodeHead head = new NodeHead(2, 3, heur);
    head.addRectangle(2, 10, 1, 10);
    head.addRectangle(3, 10, 1, 10);
    head.addRectangle(4, 10, 1, 10);
    assertEquals(head.search(0, 11, 0, 11).length, 3);
  }
  
  @Test
  void test_insert_3() {
    HeuristicLinearSplit heur = new HeuristicLinearSplit();
    NodeHead head = new NodeHead(2, 3, heur);
    head.addRectangle(3, 8, 3, 8);
    head.addRectangle(2, 9, 2, 9);
    head.addRectangle(0, 11, 0, 11);
    assertEquals(head.search(1, 10, 1, 10).length, 2);
  }
  
  @Test
  void test_insert() {
    HeuristicQuadraticSplit heur = new HeuristicQuadraticSplit();
    NodeHead head = new NodeHead(2, 3, heur);
    head.addRectangle(1, 10, 1, 10);
    head.addRectangle(2, 10, 2, 10);
    head.addRectangle(3, 10, 3, 10);
    head.addRectangle(4, 10, 4, 10);
    assertEquals(head.search(0, 11, 0, 11).length, 4);
    //assertEquals(head.real_node.getArrayables().length, 3);
  }

}
