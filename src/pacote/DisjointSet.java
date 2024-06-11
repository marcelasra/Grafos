package pacote;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet<TIPO> {
    private Map<TIPO, Node<TIPO>> map = new HashMap<>();

    private class Node<T> {
        T data;
        Node<T> parent;
        int rank;

        public Node(T data) {
            this.data = data;
            this.parent = this;
            this.rank = 0;
        }
    }

    public void makeSet(TIPO data) {
        if (!map.containsKey(data)) {
            map.put(data, new Node<>(data));
        }
    }

    public TIPO findSet(TIPO data) {
        Node<TIPO> node = findSetNode(data);
        return node.data;
    }

    private Node<TIPO> findSetNode(TIPO data) {
        Node<TIPO> node = map.get(data);
        if (node == null) {
            return null;
        }
        if (node != node.parent) {
            node.parent = findSetNode(node.parent.data);
        }
        return node.parent;
    }

    public void union(TIPO data1, TIPO data2) {
        Node<TIPO> parent1 = findSetNode(data1);
        Node<TIPO> parent2 = findSetNode(data2);

        if (parent1 == null || parent2 == null) {
            return;
        }

        if (parent1 == parent2) {
            return;
        }

        if (parent1.rank >= parent2.rank) {
            if (parent1.rank == parent2.rank) {
                parent1.rank++;
            }
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
    }
}
