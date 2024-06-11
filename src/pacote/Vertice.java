package pacote;

import java.util.ArrayList;

public class Vertice<TIPO> {
    private TIPO dado;
    private ArrayList<Aresta<TIPO>> arestaEntrada;
    private ArrayList<Aresta<TIPO>> arestaSaida;

    public Vertice(TIPO valor) {
        this.dado = valor;
        this.arestaEntrada = new ArrayList<>();
        this.arestaSaida = new ArrayList<>();
    }

    public void adicionarArestaEntrada(Aresta<TIPO> aresta) {
        this.arestaEntrada.add(aresta);
    }

    public void adicionarArestaSaida(Aresta<TIPO> aresta) {
        this.arestaSaida.add(aresta);
    }

    public TIPO getDado() {
        return dado;
    }

    public void setDado(TIPO dado) {
        this.dado = dado;
    }

    public ArrayList<Aresta<TIPO>> getArestaEntrada() {
        return arestaEntrada;
    }

    public void setArestaEntrada(ArrayList<Aresta<TIPO>> arestaEntrada) {
        this.arestaEntrada = arestaEntrada;
    }

    public ArrayList<Aresta<TIPO>> getArestaSaida() {
        return arestaSaida;
    }

    public void setArestaSaida(ArrayList<Aresta<TIPO>> arestaSaida) {
        this.arestaSaida = arestaSaida;
    }
}
