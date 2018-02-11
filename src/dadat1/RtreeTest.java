package dadat1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RtreeTest {

  @Test
  void test_insert() {
    NodeLeaf head = new NodeLeaf(2, 3);
    Rectangle rect = new Rectangle(0, 10, 0, 10);
    head.addRectangle(rect);
    assertEquals(head.getArrayables().length, 3);
  }

}
