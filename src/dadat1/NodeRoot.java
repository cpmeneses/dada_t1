package dadat1;

public abstract class NodeRoot extends NodeNotLeaf implements InterfaceNode{
  int max;
  public NodeRoot(int max) {
    this.max = max;
  }
}
