package pacote;

public class Principal {
    public static void main(String[] args) {
        Grafos<String> grafos = new Grafos<String>();
        grafos.adcionarVertice("Marcela");
        grafos.adcionarVertice("Caio");
        grafos.adcionarVertice("Victoria");
        grafos.adcionarVertice("Lucas");
        grafos.adcionarVertice("Freddie");
        
        grafos.adcionarAresta(3.0, "Marcela", "Caio");
        grafos.adcionarAresta(2.0, "Marcela", "Victoria");
        grafos.adcionarAresta(4.0, "Caio", "Lucas");
        grafos.adcionarAresta(1.0, "Victoria", "Freddie");
        grafos.adcionarAresta(1.0, "Lucas", "Freddie");
        
        System.out.println("Busca em Largura (BFS): ");
        grafos.buscaEmLargura();
        
        System.out.println("Busca em Profundidade (DFS): ");
        grafos.buscaEmProfundidade();
    }
    
}
