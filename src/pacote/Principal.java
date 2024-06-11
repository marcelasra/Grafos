package pacote;

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

        System.out.println("Busca em Largura (BFS) a partir de Marcela: ");
        grafos.buscaEmLargura("Marcela");

        System.out.println("\nBusca em Profundidade (DFS) a partir de Marcela: ");
        grafos.buscaEmProfundidade("Marcela");

        System.out.println("\nLista de Adjacência: ");
        grafos.imprimirListaAdjacencia();

        System.out.println("\nMatriz de Adjacência: ");
        grafos.imprimirMatrizAdjacencia();

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
