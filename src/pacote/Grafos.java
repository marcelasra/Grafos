package pacote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Grafos<TIPO> {

    private ArrayList<Vertice<TIPO>> vertices;
    private ArrayList<Aresta<TIPO>> arestas;

    public Grafos() { //metodo construtor
        this.vertices = new ArrayList<Vertice<TIPO>>();
        this.arestas = new ArrayList<Aresta<TIPO>>();
    }

    public void adcionarVertice(TIPO dado) {
        Vertice<TIPO> novoVertice = new Vertice<TIPO>(dado);
        this.vertices.add(novoVertice);
    }

    public void adcionarAresta(Double peso, TIPO dadoInicio, TIPO dadoFim) {
        Vertice<TIPO> inicio = this.getVertice(dadoInicio);
        Vertice<TIPO> fim = this.getVertice(dadoFim);
        Aresta<TIPO> aresta = new Aresta<TIPO>(peso, inicio, fim);
        inicio.adcionarArestaSaida(aresta);
        fim.adcionarArestaEntrada(aresta);
        this.arestas.add(aresta);
    }

    public Vertice<TIPO> getVertice(TIPO dado) {
        Vertice<TIPO> vertice = null;
        for (int i = 0; i < this.vertices.size(); i++) {
            if (this.vertices.get(i).getDado().equals(dado)) {
                vertice = this.vertices.get(i);
                break;
            }

        }
        return vertice;
    }

    public void buscaEmLargura() {
        ArrayList<Vertice<TIPO>> marcados = new ArrayList<Vertice<TIPO>>();
        ArrayList<Vertice<TIPO>> fila = new ArrayList<Vertice<TIPO>>();
        Vertice<TIPO> atual = this.vertices.get(0);
        marcados.add(atual);
        System.out.println(atual.getDado());
        fila.add(atual);
        while (fila.size() > 0) {
            Vertice<TIPO> visitado = fila.get(0);
            for (int i = 0; i < visitado.getArestaSaida().size(); i++) {
                Vertice<TIPO> proximo = visitado.getArestaSaida().get(i).getFim();
                if (!marcados.contains(proximo)) {
                    marcados.add(proximo);
                    System.out.println(proximo.getDado());
                    fila.add(proximo);
                }
            }
            fila.remove(0);
        }

    }

    public void buscaEmProfundidade() {
        ArrayList<Vertice<TIPO>> marcados = new ArrayList<Vertice<TIPO>>();
        Vertice<TIPO> atual = this.vertices.get(0);
        DFS(atual, marcados);
    }

    private void DFS(Vertice<TIPO> vertice, ArrayList<Vertice<TIPO>> marcados) {
        marcados.add(vertice);
        System.out.println(vertice.getDado());
        for (Aresta<TIPO> aresta : vertice.getArestaSaida()) {
            Vertice<TIPO> proximo = aresta.getFim();
            if (!marcados.contains(proximo)) {
                DFS(proximo, marcados);
            }
        }
    }

    public void imprimirListaAdjacencia() {
        for (Vertice<TIPO> vertice : this.vertices) {
            System.out.print(vertice.getDado() + ": ");
            for (Aresta<TIPO> aresta : vertice.getArestaSaida()) {
                System.out.print(aresta.getFim().getDado() + " ");

            }
            System.out.println();
        }
    }

    public void imprimirMatrizAdjacencia() {
        int tamanho = this.vertices.size();
        int[][] matrizAdjacencia = new int[tamanho][tamanho];
        Map<Vertice<TIPO>, Integer> mapaVertices = new HashMap<>();

        for (int i = 0; i < tamanho; i++) {
            mapaVertices.put(this.vertices.get(i), i);
        }
        for (Aresta<TIPO> aresta : this.arestas) {
            int indiceInicio = mapaVertices.get(aresta.getInicio());
            int indiceFim = mapaVertices.get(aresta.getFim());
            matrizAdjacencia[indiceInicio][indiceFim] = 1;
        }
        System.out.println("Matriz Adjacência: ");
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                System.out.print(matrizAdjacencia[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
