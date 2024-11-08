package aed;

import java.util.ArrayList;

private class heap<T>{
    ArrayList<T> elementos;
    Comparator<T> comparador;

    public heap(ArrayList<T> array, Comparator<T> comparator){
        this.elementos = heapfy(array);
        this.comparador = comparator;
    }
}