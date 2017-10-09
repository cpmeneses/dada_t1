package dadat1;
import java.util.Scanner;

public class Main {
  //una clase para cada heuristica
  public static void main(String[] args) {
    int m = 2;
    int M = 3;
    Scanner scanner = new Scanner(System.in);
    //cuantos rectagulos
    System.out.println("Cuantos rectangulos\n");
    int n_rects = scanner.nextInt();
    
    NodeInner node_root = new NodeInner(2,3);
    node_root.makeRoot();
    
    for (int i = 0; i < n_rects; i++) {
      System.out.println("coordenada x_1");
      System.out.println("coordenada x_2");
      System.out.println("coordenada y_1");
      System.out.println("coordenada y_2");
    }
  }
}
