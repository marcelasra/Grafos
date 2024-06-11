package pacote;

public class VerticeDistancia<TIPO> {
    private Vertice<TIPO> vertice;
    private double distancia;

    public VerticeDistancia(Vertice<TIPO> vertice, double distancia) {
        this.vertice = vertice;
        this.distancia = distancia;
    }

    public Vertice<TIPO> getVertice() {
        return vertice;
    }

    public double getDistancia() {
        return distancia;
    }
}
