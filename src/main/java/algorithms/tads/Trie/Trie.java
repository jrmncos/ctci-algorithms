package algorithms.tads.Trie;

public class Trie<V>{

    private Node<V> root;

    private Alphabet<Integer> alphabet;

    public Trie(Alphabet<Integer> alphabet){
        this.alphabet = alphabet;
    }

    public void add(String key, V value){
        if(key == null) return;
        if(this.root == null)
            this.root = new Node<V>(this.alphabet.size());
        add(this.root, key, value, 0);
    }

    private void add(Node<V> node, String key, V value, int i){
        if(i == key.length())
            node.setVal(value);
        else{
            int index = alphabet.index(Character.getNumericValue(key.charAt(i)));
            if(node.children(index) == null){
                Node<V> children = new Node<V>(this.alphabet.size());
                node.setChildren(index, children);
            }
            add(node.children(index), key, value, i+1);
        }
    }

    public V get(String key){
        if(key == null) return null;
        return get(this.root, key, 0);
    }

    private V get(Node<V> node, String key, int i) {
        if(node == null) return null;
        if(i == key.length()) return node.getVal();
        return get(node.children(alphabet.index(Character.getNumericValue(key.charAt(i)))), key, i+1);
    }

    

    // 8773 -> tree
    public static void main(String[] args){
        Trie<Integer> words = new Trie<Integer>(new TelephoneDigitAlphabet());
        
    }
}