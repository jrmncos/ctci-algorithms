package algorithms.tads;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.Map.Entry;

class Vertex<V> {
    private V data;

    public Vertex(V data) {
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vertex<V> other = (Vertex<V>) obj;
        return this.data == other.data;
    }

    public V getData() {
        return this.data;
    }

}

class Edge{
    Vertex v1;
    Vertex v2;

    public Edge(Vertex v1, Vertex v2){
        this.v1 = v1;
        this.v2 = v2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(v1) + Objects.hash(v2);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Edge other = (Edge) obj;
        return this.v1.equals(other.v1) && this.v2.equals(other.v2)
        || this.v1.equals(other.v2) && this.v2.equals(other.v1);
    }

    public Vertex getV1(){
        return v1;
    }
    
    public Vertex getV2(){
        return v2;
    }
    
}

public class Graph<V> {
    private HashMap<Vertex<V>, HashSet<Vertex<V>>> neighborhood;

    public Graph() {
        neighborhood = new HashMap<Vertex<V>, HashSet<Vertex<V>>>();
    }

    public void addVertex(Vertex<V> vertex) {
        neighborhood.put(vertex, new HashSet<Vertex<V>>());
    }

    public void addEdge(Vertex<V> v1, Vertex<V> v2) {
        neighborhood.get(v1).add(v2);
        neighborhood.get(v2).add(v1);
    }

    public Set<Vertex<V>> neighbors(Vertex<V> vertex) {
        return neighborhood.get(vertex);
    }

    public Set<Edge> edges(){
        Set<Edge> edges = new HashSet<Edge>();

        for(Entry<Vertex<V>, HashSet<Vertex<V>>> entries : neighborhood.entrySet()){
            Vertex<V> v1 = entries.getKey();
            for(Vertex<V> v2: entries.getValue()){
                Edge e = new Edge(v1, v2);
                if(!edges.contains(e))
                    edges.add(e);
            }
        }
        return edges;
    }
    
    public boolean adjacent(Vertex<V> v1, Vertex<V> v2){
        return neighborhood.get(v1).contains(v2) && neighborhood.get(v2).contains(v1);
    }

    public void removeVertex(Vertex<V> vertex){
        for(Vertex<V> neighbor: neighborhood.get(vertex)){
            removeEdge(vertex, neighbor);
        }
        neighborhood.remove(vertex);
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
    
    /*4.2
    Given a Graph (No directed), design an algorithm to find out wheter there is a route between two nodes
    Because the traversal is bfs, the shortest path (unweighted) is find
    */
    public boolean existsRoute(Vertex<V> v1, Vertex<V> v2){
        Queue<Vertex<V>> queue = new LinkedList<Vertex<V>>();
        Set<Vertex<V>> visited = new HashSet<Vertex<V>>();
        visited.add(v1);
        queue.add(v1);

        while(!queue.isEmpty()){
            Vertex<V> r = queue.poll();
            for(Vertex<V> v : neighbors(r)){
                if(!visited.contains(v)){
                    visited.add(v);
                    queue.add(v);
                }
                else if(visited.contains(v2)){
                    return true;
                }
            }
        }
        return false;
    }
    //With BFS, if 'n' is in visited. The graph has Cycle
    public boolean hasCycle(){
        DisjoinSet<V> disjoinset = new DisjoinSet<V>(neighborhood.keySet());
        Set<Edge> edges = edges();

        for(Edge e: edges){
            V parentV1 = disjoinset.find((V) e.getV1().getData());
            V parentV2 = disjoinset.find((V) e.getV2().getData());
            if(parentV1.equals(parentV2))
                return true;
            else
                disjoinset.join(parentV1, parentV2);
        }
        return false;
    }

    public boolean isBipartite(){
        return twoColorable(null);
    }

    public boolean twoColorable(Vertex<V> vertex){
        //0 red, 1 blue, -1 no color
        HashMap<Vertex<V>, Integer> colors = new HashMap<Vertex<V>, Integer>();
        Queue<Vertex<V>> visited = new LinkedList<Vertex<V>>();
        colors.put(vertex, 0);
        visited.add(vertex);

        while(!visited.isEmpty()){
            Vertex<V> actual = visited.poll();

            for(Vertex<V> v: neighbors(actual)){
                
                if(colors.getOrDefault(v, -1) == -1){
                    //Inverse color to actual
                    colors.put(v, 1-colors.get(actual));
                    visited.add(v);
                }
                else if(colors.get(v) == colors.get(actual));
                    return false;                
            }
        }
        return true;
    }
/*
    public void kCores(int k){
        for(Vertex<V> v: neighborhood.keySet()){
            HashSet<Vertex<V>> neighbors = neighborhood.get(v);
            if(neighbors.size() < k){
                removeVertex(v);
            }
        }

        Queue<Vertex<V>> visited = new LinkedList<Vertex<V>>();
        visited.add(null);

        while(!visited.isEmpty()){
            Vertex<V> actual = visited.poll();

            for(Vertex<V> v: neighbors(actual)){
                if(!visited.contains(v)){
                    if()
                }
            }
        }

    }
    */
}
