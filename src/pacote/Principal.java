package pacote;

import java.util.List;
import java.util.Map;

public class Principal {
    public static void main(String[] args) {
        Grafos<String> grafos = new Grafos<>();

        grafos.adicionarVertice("Marcela");
        grafos.adicionarVertice("Caio");
        grafos.adicionarVertice("Victoria");
        grafos.adicionarVertice("Lucas");
        grafos.adicionarVertice("Freddie");

        grafos.adicionarAresta(3.0, "Marcela", "Caio");
        grafos.adicionarAresta(2.0, "Marcela", "Victoria");
        grafos.adicionarAresta(4.0, "Caio", "Lucas");
        grafos.adicionarAresta(1.0, "Victoria", "Freddie");
        grafos.adicionarAresta(1.0, "Lucas", "Freddie");

        // Dijkstra
        System.out.println("Distâncias usando Dijkstra a partir de Marcela:");
        Map<Vertice<String>, Double> distancias = grafos.dijkstra("Marcela");
        for (Map.Entry<Vertice<String>, Double> entry : distancias.entrySet()) {
            System.out.println(entry.getKey().getDado() + ": " + entry.getValue());
        }

        // Remover Vértice
        grafos.removerVertice("Freddie");
        System.out.println("\nApós remover o vértice Freddie:");
        grafos.imprimirListaAdjacencia();

        // Remover Aresta
        grafos.removerAresta("Marcela", "Victoria");
        System.out.println("\nApós remover a aresta Marcela-Victoria:");
        grafos.imprimirListaAdjacencia();

        // Kruskal
        System.out.println("\nÁrvore Geradora Mínima usando Kruskal:");
        List<Aresta<String>> arvoreGeradora = grafos.kruskal();
        for (Aresta<String> aresta : arvoreGeradora) {
            System.out.println(aresta.getInicio().getDado() + " - " + aresta.getFim().getDado() + ": " + aresta.getPeso());
        }
        
        // Exemplo de uso da classe DisjointSet
        DisjointSet<String> disjointSet = new DisjointSet<>();
        disjointSet.makeSet("Marcela");
        disjointSet.makeSet("Caio");
        disjointSet.makeSet("Victoria");
        disjointSet.makeSet("Lucas");
        disjointSet.makeSet("Freddie");

        disjointSet.union("Marcela", "Caio");
        disjointSet.union("Victoria", "Freddie");
        disjointSet.union("Caio", "Lucas");

        System.out.println("\nConjuntos disjuntos após as uniões:");
        System.out.println("Representante de Marcela: " + disjointSet.findSet("Marcela"));
        System.out.println("Representante de Caio: " + disjointSet.findSet("Caio"));
        System.out.println("Representante de Victoria: " + disjointSet.findSet("Victoria"));
        System.out.println("Representante de Lucas: " + disjointSet.findSet("Lucas"));
        System.out.println("Representante de Freddie: " + disjointSet.findSet("Freddie"));
    }
}
