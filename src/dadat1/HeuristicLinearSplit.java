package dadat1;

public class HeuristicLinearSplit implements InterfaceHeuristic {
  @Override
  public Node[] splitNodeLeaf(Node node, Rectangle rect, int m, int M) {
    //Toma el nodo con overflow y el rectangulo que causa el overflow
    //Y retorna un arreglo con dos nodos correctos
    Node node1 = new NodeLeaf(m, M); //falta m y M
    Node node2 = new NodeLeaf(m, M);
    
    //implementar
    
    
    Node[] res = new Node[2];
    res[0] = node1;
    res[1] = node2;
    return res;
  }
}
