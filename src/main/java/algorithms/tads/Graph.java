package algorithms.tads;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Vertex<V>{
    private V data;

    public Vertex(V data){
        this.data = data;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Vertex<V> other = (Vertex<V>) obj;
        return this.data == other.data;
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

    public void removeVertex(Vertex<V> vertex){
        neighborhood.remove(vertex);
        for(HashSet<Vertex<V>> neighbors : neighborhood.values()){
            if(neighbors.contains(vertex))
                neighbors.remove(vertex);
        }
    }
    
    public void removeEdge(Vertex<V> v1, Vertex<V> v2){
        neighborhood.get(v1).remove(v2);
        neighborhood.get(v2).remove(v1);
    }
    //Bfs : Queue (Cercanos primeros, busqueda en ancho, me expando desde el inicial)
    //Tambien sirve para distancia entre v1 y v2.
    //Dfs: Stack (Me alejo lo mas posible y luego empiezo a volver)
    public Set<Vertex<V>> bfs(Vertex<V> vertex){
        Queue<Vertex<V>> queue = new LinkedList<Vertex<V>>();
        Set<Vertex<V>> visited = new HashSet<Vertex<V>>();
        visited.add(vertex);
        queue.add(vertex);

        while(!queue.isEmpty()){
            Vertex<V> r = queue.poll();
            for(Vertex<V> v : neighbors(r)){
                if(!visited.contains(v)){
                    visited.add(v);
                    queue.add(v);
                }
            }
        }

        return visited;
    }

    public boolean isConnected(){
        return bfs(null).size() == neighborhood.keySet().size();
    }
    
}
