package aed;

import java.util.ArrayList;

private class heap<T> {
    ArrayList<T> elementos;
    Comparator<T> comparador;

    public heap(ArrayList<T> array, Comparator<T> comparator) {
        this.elementos = heapfy(array);
        this.comparador = comparator;
    }


// Algoritmos

// Representado con un array.
// Si nodo v es la raiz, p(v)=0
// Su hijo izquierdo u es p(u) = 2p(v)+1
// Su hijo derecho w es p(w) = 2p(w)+2


    // Shift-up
    public void shift_up(int indice){
        if(indice == 0){
            return;
        } else{
            if(indice % 2 == 0) { // Es hijo derecho.
                int indice_padre = (i-2)/2;
            }else{
                indice_padre = (i-1)/2;
            }
            if(comparador.compare(elementos.get(indice), elementos.get(indice_padre)) > 0){
                swap(indice,indice_padre);
                shift_up(indice_padre);
            }
        }
    }

    public void swap(int indice1,int indice2){
        T indice3 = elementos.get(indice1);
        elementos.set(indice1,array.get(indice2));
        elementos.set(indice2,indice3);
    }

























// Encolar
// Inserta el elemento al final del heap.
// Hace shift-up, lo sube.
public void encolar(T elemento_nuevo){
    elementos.add(elemento_nuevo);
    shift_up(elementos.size()-1);
}



// Desencolar
// Reemplaza la raiz por la ultima hoja.
// Hace shift- down.
public T desencolar(){
        T res = elementos.get(0);
        T ultimo_elemento = elementos.get(elementos.size()-1);
        elementos.set(0,ultimo_elemento);
        elementos.remove(elementos.size()-1);
        shift_down(0);
        return res;
}

// Shift - down





    public void shift_down(int indice){
        int indice1 = indice;
        while(true){
            int l = 2*indice1 + 1;
            int r = 2*indice1 + 2;
            f (l < tamaño && comparador.compare(elementos.get(l), elementos.get(maxIndex)) > 0) {
                indice1 = l;
            }

            // Verifica si el hijo derecho existe y es mayor que el mayor actual
            if (r < tamaño && comparador.compare(elementos.get(r), elementos.get(maxIndex)) > 0) {
                indice1 = r;
            }

            // Si no se necesita hacer más intercambios, se detiene
            if (i == indice1) {
                break;
            }

            // Intercambia el nodo con el mayor hijo
            swap(i, indice1);
            i = indice1;
        }
    }






    public ArrayList<T> heapify(ArrayList<T> array) {
        int n = array.size();
        this.elementos = new ArrayList<>(array);
        for (int i = (n / 2) - 1; i >= 0; i--) {
            shift_down(i);
        }
        return this.elementos;
    }

}
