package pacote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Grafos<TIPO> {
    private ArrayList<Vertice<TIPO>> vertices;
    private ArrayList<Aresta<TIPO>> arestas;

    public Grafos() {
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
    }

    public void adicionarVertice(TIPO dado) {
        Vertice<TIPO> novoVertice = new Vertice<>(dado);
        this.vertices.add(novoVertice);
    }

    public void adicionarAresta(Double peso, TIPO dadoInicio, TIPO dadoFim) {
        Vertice<TIPO> inicio = getVertice(dadoInicio);
        Vertice<TIPO> fim = getVertice(dadoFim);
        Aresta<TIPO> aresta = new Aresta<>(peso, inicio, fim);
        inicio.adicionarArestaSaida(aresta);
        fim.adicionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }

    public Vertice<TIPO> getVertice(TIPO dado) {
        for (Vertice<TIPO> vertice : vertices) {
            if (vertice.getDado().equals(dado)) {
                return vertice;
            }
        }
        return null;
    }

    public void buscaEmLargura(TIPO dadoInicio) {
        Vertice<TIPO> inicio = getVertice(dadoInicio);
        if (inicio == null) {
            System.out.println("Vértice de início não encontrado.");
            return;
        }

        ArrayList<Vertice<TIPO>> marcados = new ArrayList<>();
        Queue<Vertice<TIPO>> fila = new LinkedList<>();

        marcados.add(inicio);
        System.out.println(inicio.getDado());
        fila.offer(inicio);

        while (!fila.isEmpty()) {
            Vertice<TIPO> visitado = fila.poll();
            for (Aresta<TIPO> aresta : visitado.getArestaSaida()) {
                Vertice<TIPO> proximo = aresta.getFim();
                if (!marcados.contains(proximo)) {
                    marcados.add(proximo);
                    System.out.println(proximo.getDado());
                    fila.offer(proximo);
                }
            }
        }
    }
    // Método Dijkstra
    public Map<Vertice<TIPO>, Double> dijkstra(TIPO dadoInicio) {
        Vertice<TIPO> inicio = getVertice(dadoInicio);
        if (inicio == null) {
            System.out.println("Vértice de início não encontrado.");
            return null;
        }

        Map<Vertice<TIPO>, Double> distancias = new HashMap<>();
        for (Vertice<TIPO> vertice : vertices) {
            distancias.put(vertice, Double.POSITIVE_INFINITY);
        }
        distancias.put(inicio, 0.0);

        PriorityQueue<VerticeDistancia<TIPO>> fila = new PriorityQueue<>((a, b) -> Double.compare(a.getDistancia(), b.getDistancia()));
        fila.offer(new VerticeDistancia<>(inicio, 0.0));

        while (!fila.isEmpty()) {
            VerticeDistancia<TIPO> verticeDistancia = fila.poll();
            Vertice<TIPO> vertice = verticeDistancia.getVertice();
            double distancia = verticeDistancia.getDistancia();

            if (distancia == distancias.get(vertice)) {
                for (Aresta<TIPO> aresta : vertice.getArestaSaida()) {
                    Vertice<TIPO> vizinho = aresta.getFim();
                    double novaPeso = distancia + aresta.getPeso();
                    if (novaPeso < distancias.get(vizinho)) {
                        distancias.put(vizinho, novaPeso);
                        fila.offer(new VerticeDistancia<>(vizinho, novaPeso));
                    }
                }
            }
        }

        return distancias;
    }

    // Método Remover Vértice
    public void removerVertice(TIPO dado) {
        Vertice<TIPO> verticeRemover = getVertice(dado);
        if (verticeRemover == null) {
            System.out.println("Vértice não encontrado.");
            return;
        }

        // Remover arestas de entrada
        for (Aresta<TIPO> aresta : verticeRemover.getArestaEntrada()) {
            Vertice<TIPO> inicio = aresta.getInicio();
            inicio.getArestaSaida().remove(aresta);
            arestas.remove(aresta);
        }

        // Remover arestas de saída
        for (Aresta<TIPO> aresta : verticeRemover.getArestaSaida()) {
            Vertice<TIPO> fim = aresta.getFim();
            fim.getArestaEntrada().remove(aresta);
            arestas.remove(aresta);
        }

        vertices.remove(verticeRemover);
    }

    // Método Remover Aresta
    public void removerAresta(TIPO dadoInicio, TIPO dadoFim) {
        Vertice<TIPO> inicio = getVertice(dadoInicio);
        Vertice<TIPO> fim = getVertice(dadoFim);
        if (inicio == null || fim == null) {
            System.out.println("Vértice(s) não encontrado(s).");
            return;
        }

        Aresta<TIPO> arestaRemover = null;
        for (Aresta<TIPO> aresta : arestas) {
            if (aresta.getInicio() == inicio && aresta.getFim() == fim) {
                arestaRemover = aresta;
                break;
            }
        }

        if (arestaRemover == null) {
            System.out.println("Aresta não encontrada.");
            return;
        }

        inicio.getArestaSaida().remove(arestaRemover);
        fim.getArestaEntrada().remove(arestaRemover);
        arestas.remove(arestaRemover);
    }

    // Método Kruskal
    public List<Aresta<TIPO>> kruskal() {
        List<Aresta<TIPO>> arvoreGeradora = new ArrayList<>();
        PriorityQueue<Aresta<TIPO>> filaArestas = new PriorityQueue<>((a, b) -> Double.compare(a.getPeso(), b.getPeso()));
        filaArestas.addAll(arestas);

        DisjointSet<Vertice<TIPO>> disjointSet = new DisjointSet<>();
        for (Vertice<TIPO> vertice : vertices) {
            disjointSet.makeSet(vertice);
        }

        while (!filaArestas.isEmpty() && arvoreGeradora.size() < vertices.size() - 1) {
            Aresta<TIPO> aresta = filaArestas.poll();
            Vertice<TIPO> inicio = aresta.getInicio();
            Vertice<TIPO> fim = aresta.getFim();

            if (disjointSet.findSet(inicio) != disjointSet.findSet(fim)) {
                arvoreGeradora.add(aresta);
                disjointSet.union(inicio, fim);
            }
        }

        return arvoreGeradora;
    }

    public void buscaEmProfundidade(TIPO dadoInicio) {
        Vertice<TIPO> inicio = getVertice(dadoInicio);
        if (inicio == null) {
            System.out.println("Vértice de início não encontrado.");
            return;
        }

        ArrayList<Vertice<TIPO>> marcados = new ArrayList<>();
        dfs(inicio, marcados);
    }

    private void dfs(Vertice<TIPO> vertice, ArrayList<Vertice<TIPO>> marcados) {
        marcados.add(vertice);
        System.out.println(vertice.getDado());

        for (Aresta<TIPO> aresta : vertice.getArestaSaida()) {
            Vertice<TIPO> proximo = aresta.getFim();
            if (!marcados.contains(proximo)) {
                dfs(proximo, marcados);
            }
        }
    }

    public void imprimirListaAdjacencia() {
        for (Vertice<TIPO> vertice : vertices) {
            System.out.print(vertice.getDado() + ": ");
            for (Aresta<TIPO> aresta : vertice.getArestaSaida()) {
                System.out.print(aresta.getFim().getDado() + " ");
            }
            System.out.println();
        }
    }

    public void imprimirMatrizAdjacencia() {
        int tamanho = vertices.size();
        int[][] matrizAdjacencia = new int[tamanho][tamanho];

        Map<Vertice<TIPO>, Integer> mapaVertices = new HashMap<>();
        for (int i = 0; i < tamanho; i++) {
            mapaVertices.put(vertices.get(i), i);
        }

        for (Aresta<TIPO> aresta : arestas) {
            int indiceInicio = mapaVertices.get(aresta.getInicio());
            int indiceFim = mapaVertices.get(aresta.getFim());
            matrizAdjacencia[indiceInicio][indiceFim] = 1;
        }

        System.out.println("Matriz Adjacência: ");
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                System.out.print(matrizAdjacencia[i][j] + " ");
            }
            System.out.println();
        }
    }
}
