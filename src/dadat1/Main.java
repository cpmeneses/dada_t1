package dadat1;
import java.util.Random;
import java.util.Scanner;

public class Main {
  //una clase para cada heuristica
  public static void main(String[] args) {
    int m = 4;
    int M = 10;
    Scanner scanner = new Scanner(System.in);
    //cuantos rectagulos
    System.out.println("Elige la potencia n\n");
    int n_power = scanner.nextInt();
    HeuristicLinearSplit heurlin = new HeuristicLinearSplit();
    
    int rect_num = (int) Math.pow(2, n_power);
    Rectangle[] rects = new Rectangle[rect_num];
    double x1;
    double x2;
    double y1;
    double y2;
    Random rng = new Random(System.currentTimeMillis());
    
    for (int i = 0; i < rect_num; i++) {
      x1 = 49500 * rng.nextDouble();
      x2 = x1 + 1 + (99 * rng.nextDouble());
      y1 = 49500 * rng.nextDouble();
      y2 = y1 + 1 + (99 * rng.nextDouble());
      rects[i] = new Rectangle(x1, x2, y1, y2);
    }
    
    
    NodeHead node_root = new NodeHead(m, M, heurlin);
    long init_time = System.currentTimeMillis();
    for (int i = 0; i < rect_num; i++) {
      node_root.addRectangle(rects[i]);
    }
    long final_time = System.currentTimeMillis();
    
    System.out.println(Long.toString(init_time));
    System.out.println(Long.toString(final_time));
    
    
    //for (int i = 0; i < n_rects; i++) {
    //  System.out.println("coordenada x_1");
    //  System.out.println("coordenada x_2");
    //  System.out.println("coordenada y_1");
    //  System.out.println("coordenada y_2");
    //}
    
    
    
  }
}
