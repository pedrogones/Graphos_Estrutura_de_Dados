import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class Graph {
    int n;
    int[][] M;
    ArrayList<Integer>[] L;
    
    public Graph(int n) {
        this.n = n;
        M = new int[n][n];
        L = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            L[i] = new ArrayList<Integer>();
        }
    }
    public void dfs(int s, int t) {
        boolean[] visited = new boolean[n];
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(s);
        visited[s] = true;

        while (!stack.isEmpty()) {
            int u = stack.pop();
            System.out.print(u + " ");

            if (u == t) {
                System.out.println("\nCaminho encontrado!");
                int v = t;
                System.out.print(v);
                while (parent[v] != -1) {
                    System.out.print(" <- " + parent[v]);
                    v = parent[v];
                }
                return;
            }

            for (int v : L[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    parent[v] = u;
                    stack.push(v);
                }
            }
        }
        //System.out.println("\nNão há caminho entre os vértices!");
    }    
    // o vértice de origem s e o vértice de destino t
    public ArrayList<Integer> bfs(int s, int t) {
        boolean[] visitado = new boolean[n];
        int[] pai = new int[n];
        Queue<Integer> fila = new LinkedList<Integer>();
        ArrayList<Integer> caminho = new ArrayList<Integer>();

        fila.offer(s);
        visitado[s] = true;
        pai[s] = -1;

        while (!fila.isEmpty()) {
            int u = fila.poll();

            if (u == t) {
                // reconstrói o caminho a partir do vértice de destino
                int v = t;
                while (v != -1) {
                    caminho.add(v);
                    v = pai[v];
                }
                Collections.reverse(caminho);
                return caminho;
            }

            for (int i = 0; i < L[u].size(); i++) {
                int v = L[u].get(i);
                if (!visitado[v]) {
                    visitado[v] = true;
                    pai[v] = u;
                    fila.offer(v);
                }
            }
        }

        // não há caminho entre s e t
        return null;
    }
}
public class GrafoEd {
    
    public static Graph loadFrom(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        
        int n = Integer.parseInt(scanner.nextLine());
        Graph g = new Graph(n);
        
        int l = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            String[] numeros = line.split("\t");
            
           //System.out.println(line);
            
            int c = 0;
            for (int _id = 0; _id < g.n; _id++) {
                int i = Integer.parseInt(numeros[_id]);
                g.M[l][c] = i;
                if (g.M[l][c] > 0) {
                    g.L[l].add(c);
                 //   System.out.println(g.L[l]+"\n"+g.M[l][c]);
                }
                c++;
            }
            l++;
        }
        for (int i = 0; i < n; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < g.L[i].size(); j++) {
                int adjacente = g.L[i].get(j);
                int peso = g.M[i][adjacente];
                System.out.print(adjacente + "(" + peso + ") ");
            }
            System.out.println();
        }
        scanner.close();
        return g;
    }
    //L é uma lista de inteiros que contém os índices dos vértices que são adjacentes ao vértice i
    //M[][] é o peso da aresta que liga o vértice i ao vértice j, ou 0 caso não haja uma aresta que ligue os vértices
    public static void main(String[] args) throws FileNotFoundException {
        Graph gr = loadFrom("pcv4.txt");
        
    //Exibindo a lista de adjacências:
      System.out.println("//Exibindo a lista de adjacências:");
      for (int i = 0; i < gr.L.length; i++) {
          System.out.println("linha : "+i+" - "+ gr.L[i]);
      }
      System.out.println("//exibindo qual o arquivo está sendo lido:");
      //exibindo qual o arquivo está sendo lido:
      for (int i = 0; i < gr.M.length; i++) {
       for (int j = 0; j < gr.M.length; j++) {
        System.out.print(gr.M[i][j]+"\t");
       } 
       System.out.println();
      }
      
//usando a funcao de busca em profundidade:        
int s = 0; // vértice de origem
int t = 3; // vértice de destino
ArrayList<Integer> caminho = gr.bfs(s, t);
if (caminho == null) {
    System.out.println("Não há caminho entre os vértices");
} else {
    System.out.print("Caminho entre " + s + " e " + t + ": ");
    for (int i = 0; i < caminho.size(); i++) {
        System.out.print(caminho.get(i));
        if (i < caminho.size() - 1) {
            System.out.print(" -> ");
            
        }
    }
} 
System.out.print("\nBusca em profundidade com uso de pilha: \n");
int k=2, y=4;

System.out.println("Caminho entre " + k + " e " + y + ": ");
gr.dfs(k, y) ;
}
}
