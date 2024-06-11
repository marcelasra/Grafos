package pacote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
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
