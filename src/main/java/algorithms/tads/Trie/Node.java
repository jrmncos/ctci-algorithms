package algorithms.tads.Trie;

import java.util.ArrayList;

public class Node<V> {
    private V val;
    private ArrayList<Node<V>> nodes;

    public Node(int size){
        nodes = new ArrayList<Node<V>>(size);
    }

    public V getVal(){
        return val;
    }

    public void setVal(V value){
        this.val = value;
    }

    public Node<V> children(int i){
        return nodes.get(i);
    }

    public void setChildren(int i, Node<V> newChildren){
        nodes.set(i, newChildren);
    }
}


