package algorithms.tads.Trie;

public interface Alphabet<T> {
    /*
        Numero de simbolos en el alfabeto
    */
    int size();

    /*
        Devuelve el indice correspondiente a un simbolo
        Debe tener complejidad O(1)
        RunTimeExcepion si no es valido
    */
    int index(T elem);

    
}
