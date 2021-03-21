package algorithms.tads.Trie;

public class TelephoneDigitAlphabet implements Alphabet<Integer> {

    public int size() {
        return 9;
    }

    public int index(Integer elem) {
        if(0<= elem && elem <=9)
            return elem;
        
        throw new RuntimeException("Base invalida"+ elem);
    }
    
}
