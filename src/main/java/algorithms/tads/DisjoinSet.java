package algorithms.tads;

import java.util.HashMap;
import java.util.Set;

public class DisjoinSet<V> {
    HashMap<V, V> disjoinsets;

    public DisjoinSet(Set<Vertex<V>> elements){
        this.disjoinsets = new HashMap<V, V>();
        for(Vertex<V> elem: elements){
            this.disjoinsets.put(elem.getData(), elem.getData());
        }
    }

    public V find(V e){
        V elem = disjoinsets.get(e);
        if(e.equals(elem)){
            return elem;
        }
        return find(elem);
    }

    public void join(V e1, V e2){
        //V elem1 = find(e1);
        //V elem2 = find(e2);
        disjoinsets.put(elem1, elem2);
    }
}
