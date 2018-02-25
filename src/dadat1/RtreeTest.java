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
  
  @Test
  void test_search() {
    HeuristicQuadraticSplit heur = new HeuristicQuadraticSplit();
    NodeHead head = new NodeHead(2, 3, heur);
    head.addRectangle(1, 2, 1, 2);
    head.addRectangle(3, 4, 3, 4);
    head.addRectangle(2, 3, 2, 3);
    head.addRectangle(4, 5, 4, 5);
    assertEquals(head.search(1, 1.5, 1, 1.5).length, 0);
    assertEquals(head.search(1, 2, 1, 2).length, 1);
    assertEquals(head.search(1, 3, 1, 3).length, 2);
    assertEquals(head.search(1, 4, 1, 4).length, 3);
    assertEquals(head.search(1, 5, 1, 5).length, 4);
    //assertEquals(head.real_node.getArrayables().length, 3);
  }

  @Test
  void test_search_2() {
    HeuristicQuadraticSplit heur = new HeuristicQuadraticSplit();
    NodeHead head = new NodeHead(2, 3, heur);
    head.addRectangle(1, 2, 1, 2);
    head.addRectangle(3, 4, 3, 4);
    head.addRectangle(2, 3, 2, 3);
    head.addRectangle(4, 5, 4, 5);
    head.addRectangle(5, 6, 5, 6);
    head.addRectangle(6, 7, 6, 7);
    head.addRectangle(7, 8, 7, 8);
    head.addRectangle(8, 9, 8, 9);
    
    assertEquals(head.search(1, 1.5, 1, 1.5).length, 0);
    assertEquals(head.search(1, 2, 1, 2).length, 1);
    assertEquals(head.search(1, 3, 1, 3).length, 2);
    assertEquals(head.search(1, 4, 1, 4).length, 3);
    assertEquals(head.search(1, 5, 1, 5).length, 4);
    //assertEquals(head.real_node.getArrayables().length, 3);
  }
  
  @Test
  void test_search_3() {
    HeuristicQuadraticSplit heur = new HeuristicQuadraticSplit();
    NodeHead head = new NodeHead(2, 3, heur);
    head.addRectangle(0, 1, 0, 1);
    head.addRectangle(-1, 0, 0, 1);
    head.addRectangle(-1, 0, -1, 0);
    head.addRectangle(0, 1, -1, 0);
    
    head.addRectangle(1, 2, 0, 1);
    head.addRectangle(1, 2, 1, 2);
    head.addRectangle(0, 1, 1, 2);
    
    assertEquals(head.search(0, 2, -1, 2).length, 5);
    assertEquals(head.search(-4, 4, -4, 4).length, 7);
    
    head.addRectangle(-1, 0, 1, 2);
    head.addRectangle(-2, -1, 1, 2);
    head.addRectangle(-2, -1, 0, 1);
    
    assertEquals(head.search(-2, 2, 0, 2).length, 8);
    assertEquals(head.search(-4, 4, -4, 4).length, 10);
    
    head.addRectangle(-2, -1, -1, 0);
    head.addRectangle(-2, -1, -2, -1);
    head.addRectangle(-1, 0, -2, -1);
    
    assertEquals(head.search(-2, 1, -2, 2).length, 11);
    assertEquals(head.search(-4, 4, -4, 4).length, 13);
    
    //head.addRectangle(8, 9, 8, 9);
    
    assertEquals(head.search(-1, 1, -1, 1).length, 4);
    
    //assertEquals(head.search(1, 3, 1, 3).length, 2);
    //assertEquals(head.search(1, 4, 1, 4).length, 3);
    //assertEquals(head.search(1, 5, 1, 5).length, 4);
    //assertEquals(head.real_node.getArrayables().length, 3);
  }
  
  @Test
  void test_search_3_2() {
    HeuristicLinearSplit heur = new HeuristicLinearSplit();
    NodeHead head = new NodeHead(2, 3, heur);
    head.addRectangle(0, 1, 0, 1);
    head.addRectangle(-1, 0, 0, 1);
    head.addRectangle(-1, 0, -1, 0);
    head.addRectangle(0, 1, -1, 0);
    
    head.addRectangle(1, 2, 0, 1);
    head.addRectangle(1, 2, 1, 2);
    head.addRectangle(0, 1, 1, 2);
    
    assertEquals(head.search(0, 2, -1, 2).length, 5);
    assertEquals(head.search(-4, 4, -4, 4).length, 7);
    
    head.addRectangle(-1, 0, 1, 2);
    head.addRectangle(-2, -1, 1, 2);
    head.addRectangle(-2, -1, 0, 1);
    
    assertEquals(head.search(-2, 2, 0, 2).length, 8);
    assertEquals(head.search(-4, 4, -4, 4).length, 10);
    
    head.addRectangle(-2, -1, -1, 0);
    head.addRectangle(-2, -1, -2, -1);
    head.addRectangle(-1, 0, -2, -1);
    
    assertEquals(head.search(-2, 1, -2, 2).length, 11);
    assertEquals(head.search(-4, 4, -4, 4).length, 13);
    
    //head.addRectangle(8, 9, 8, 9);
    
    assertEquals(head.search(-1, 1, -1, 1).length, 4);
    
    //assertEquals(head.search(1, 3, 1, 3).length, 2);
    //assertEquals(head.search(1, 4, 1, 4).length, 3);
    //assertEquals(head.search(1, 5, 1, 5).length, 4);
    //assertEquals(head.real_node.getArrayables().length, 3);
  }
  
  @Test
  void test_insert_4() {
    HeuristicQuadraticSplit heur = new HeuristicQuadraticSplit();
    NodeHead head = new NodeHead(2, 3, heur);
    for (int i = 0; i < 100; i++) {
      head.addRectangle(-10, 10, -10, 10);
    }
    assertEquals(head.search(-11, 11, -11, 11).length, 100);
  }
  
  @Test
  void test_insert_4_2() {
    HeuristicLinearSplit heur = new HeuristicLinearSplit();
    NodeHead head = new NodeHead(2, 3, heur);
    for (int i = 0; i < 100; i++) {
      head.addRectangle(-10, 10, -10, 10);
    }
    assertEquals(head.search(-11, 11, -11, 11).length, 100);
  }
  
  @Test
  void test_insert_5() {
    HeuristicQuadraticSplit heur = new HeuristicQuadraticSplit();
    NodeHead head = new NodeHead(4, 10, heur);
    for (int i = 0; i < 300; i++) {
      head.addRectangle(-10, 10, -10, 10);
    }
    assertEquals(head.search(-11, 11, -11, 11).length, 300);
  }
  
  @Test
  void test_insert_5_2() {
    HeuristicLinearSplit heur = new HeuristicLinearSplit();
    NodeHead head = new NodeHead(4, 10, heur);
    for (int i = 0; i < 300; i++) {
      head.addRectangle(-10, 10, -10, 10);
    }
    assertEquals(head.search(-11, 11, -11, 11).length, 300);
  }
  
  @Test
  void test_power() {
    assertEquals(Math.pow(2,10), 1024);
  }
}
