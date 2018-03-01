package dadat1;
import java.lang.instrument.Instrumentation;
import java.util.Random;
import java.util.Scanner;

public class Main {
  //una clase para cada heuristica
  public static void main(String[] args) {
    int m = 4;
    int M = 10;
    Scanner scanner = new Scanner(System.in);
    //hasta cuantos rectagulos
    System.out.println("Elige la potencia n\n");
    int n_power = scanner.nextInt();
    
    HeuristicLinearSplit heurlin = new HeuristicLinearSplit();    
    //HeuristicQuadraticSplit heurlin = new HeuristicQuadraticSplit();
    
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
    
    long init_time;
    long final_time;
    
    Runtime.getRuntime().gc();
    //Runtime.getRuntime().gc();
    long init_mem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    init_time = System.currentTimeMillis();
    
    for (int i = 0; i < rect_num; i++) {
      node_root.addRectangle(rects[i]);
    }
    
    final_time = System.currentTimeMillis();
    Runtime.getRuntime().gc();
    //Runtime.getRuntime().gc();
    long final_mem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    
    System.out.println("Tiempo inicial:");
    System.out.println(Long.toString(init_time));
    System.out.println("Tiempo final:");
    System.out.println(Long.toString(final_time));
    System.out.println("Tiempo total en segundos:");
    System.out.println(Double.toString((final_time - init_time) / 1000.000));
    System.out.println("Memoria usada:");
    System.out.println(Long.toString(final_mem - init_mem));
    
    
    //Busquedas
    //Generar rect_num rectangulos
    int search_length = rect_num / 10;
    Rectangle[] search_rects = new Rectangle[search_length];
    for (int i = 0; i < search_length; i++) {
      x1 = 49500 * rng.nextDouble();
      x2 = x1 + 1 + (99 * rng.nextDouble());
      y1 = 49500 * rng.nextDouble();
      y2 = y1 + 1 + (99 * rng.nextDouble());
      search_rects[i] = new Rectangle(x1, x2, y1, y2);
    }
    
    //hacer una busqueda por rectangulo
    long search_init_time;
    long search_final_time;
    
    search_init_time = System.currentTimeMillis();
    for (int i = 0; i < search_length; i++) {
      node_root.search(search_rects[i]);
    }
    search_final_time = System.currentTimeMillis();
    
    System.out.println("Busqueda");
    System.out.println("Tiempo inicial:");
    System.out.println(Long.toString(search_init_time));
    System.out.println("Tiempo final:");
    System.out.println(Long.toString(search_final_time));
    System.out.println("Tiempo total en segundos:");
    System.out.println(Double.toString((search_final_time - search_init_time) / 1000.000));
    
    
    
  }
}
