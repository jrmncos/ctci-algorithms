package algorithms.tads;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

class Vertex<V>{
    private V data;

    public Vertex(V data){
        this.data = data;
    }
}

public class Graph<V>{
    private HashMap<Vertex<V>, HashSet<Vertex<V>>> neighborhood;

    public Graph(){
        neighborhood = new HashMap<Vertex<V>, HashSet<Vertex<V>>>();
    }

    public void addVertex(Vertex<V> vertex){
        neighborhood.put(vertex, new HashSet<Vertex<V>>());
    }

    public void addEdge(Vertex<V> v1, Vertex<V> v2){
        neighborhood.get(v1).add(v2);
        neighborhood.get(v2).add(v1);
    }

    public Set<Vertex<V>> neighbors(Vertex<V> vertex){
        return neighborhood.get(vertex);
    }

    public boolean adjacent(Vertex<V> v1, Vertex<V> v2){
        return neighborhood.get(v1).contains(v2) && neighborhood.get(v2).contains(v1);
    }
    
    
}
