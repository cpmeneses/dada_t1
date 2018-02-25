package dadat1;

public class NodeHead {
  Node real_node; //private
  private int m;
  private int M;
  private InterfaceHeuristic heuristic;

  public NodeHead(int m, int M, InterfaceHeuristic heuristic) {
    this.real_node = new NodeLeaf(m, M);
    this.m = m;
    this.M = M;
    this.heuristic = heuristic;
  }
  
  public void addRectangle(double x1, double x2, double y1, double y2) {
    Rectangle rect = new Rectangle(x1, x2, y1, y2);
    Arrayable[] arr = real_node.addRectangle(rect);
    if (!arr[0].isVoid()) {
      NodeInner new_real_node = new NodeInner(m, M, this.heuristic);
      Node old_node = real_node;
      Node[] split_nodes = heuristic.splitNode(old_node, arr[0], m, M);
      for (int i = 0; i < split_nodes.length; i++) {
        new_real_node.addArrayable(split_nodes[i]);
      }
      this.real_node = new_real_node;
    }
  }
  
  public void addRectangle(Rectangle rect) {
    Arrayable[] arr = real_node.addRectangle(rect);
    if (!arr[0].isVoid()) {
      NodeInner new_real_node = new NodeInner(m, M, this.heuristic);
      Node old_node = real_node;
      Node[] split_nodes = heuristic.splitNode(old_node, arr[0], m, M);
      for (int i = 0; i < split_nodes.length; i++) {
        new_real_node.addArrayable(split_nodes[i]);
      }
      this.real_node = new_real_node;
    }
  }
  
  public Rectangle[] search(double x1, double x2, double y1, double y2) {
    Rectangle rect = new Rectangle(x1, x2, y1, y2);
    Rectangle[] res = real_node.searchRectangle(rect);
    return res;
  }
}
