package pacote;

public class Principal {
    public static void main(String[] args) {
        Grafos<String> grafos = new Grafos<String>();
        grafos.adcionarVertice("Marcela");
        grafos.adcionarVertice("Caio");
        grafos.adcionarVertice("Victória");
        grafos.adcionarVertice("Lucas");
        
        grafos.adcionarAresta(3.0, "Marcela", "Victória");
        grafos.adcionarAresta(2.0, "Victória", "Lucas");
        grafos.adcionarAresta(4.0, "Lucas", "Caio");
        grafos.adcionarAresta(1.0, "Caio", "Marcela");
        
        grafo.buscaEmLargura();
    }
    
}
