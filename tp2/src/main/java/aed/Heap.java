package aed;

import java.util.ArrayList;
import java.util.Comparator;


public class Heap<T> {
    ArrayList<T> elementos;
    Comparator<T> comparador;
    ArrayList<Integer> indices;

    public Heap(ArrayList<T> array, Comparator<T> comparator) {
        this.comparador = comparator;
        this.elementos = new ArrayList<T>();
        this.elementos = heapify(array);
        for (int i = 0; i < array.size(); i++) {
            indices.add(i); // Inicializa con índices en orden
        }
    }


// Algoritmos

// Representado con un array.
// Si nodo v es la raiz, p(v)=0
// Su hijo izquierdo u es p(u) = 2p(v)+1
// Su hijo derecho w es p(w) = 2p(w)+2


    // Shift-up
    public void shift_up(int indice) {
        if (indice == 0) {
            return;
        } else {
            int indice_padre = 0;
            if (indice % 2 == 0) { // Es hijo derecho.
                indice_padre = (indice - 2) / 2;
            } else {
                indice_padre = (indice - 1) / 2;
            }
            if (comparador.compare(elementos.get(indice), elementos.get(indice_padre)) > 0) {
                swap(indice, indice_padre);
                shift_up(indice_padre);
            }
        }
    }

    public void swap(int indice1, int indice2) {
        T indice3 = elementos.get(indice1);
        elementos.set(indice1, elementos.get(indice2));
        elementos.set(indice2, indice3);

        int tempIndex = indices.get(indice1);
        indices.set(indice1, indices.get(indice2));
        indices.set(indice2, tempIndex);
    }


    // Encolar
// Inserta el elemento al final del heap.
// Hace shift-up, lo sube.
    public void encolar(T elemento_nuevo) {
        elementos.add(elemento_nuevo);
        shift_up(elementos.size() - 1);
    }


    // Desencolar
// Reemplaza la raiz por la ultima hoja.
// Hace shift- down.
    public T desencolar() {
        T res = elementos.get(0);
        swap(0, elementos.size()-1);
        elementos.remove(elementos.size() - 1);
        shift_down(0);
        return res;
    }

// Shift - down


    private void shift_down(int indice) {
        int n = elementos.size();

        while (true) {
            int l = 2 * indice + 1;
            int r = 2 * indice + 2;
            int maxIndex = indice;

            if (l < n && comparador.compare(elementos.get(l), elementos.get(maxIndex)) > 0) {
                maxIndex = l;
            }

            if (r < n && comparador.compare(elementos.get(r), elementos.get(maxIndex)) > 0) {
                maxIndex = r;
            }

            if (indice == maxIndex) {
                break;
            }
            swap(indice, maxIndex);
            indice = maxIndex;
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















// Si ahora tengo un arreglo que me va guardando
// en que posicion del heap esta cada una de las ciudades,
// puedo modificar el valor de superavit en O(log(C)), ya que
// evita buscar en el heap.

    public void borrar(int indice_ciudad) {
        // Hay que borrarlo
        int indice_heap = indices.get(indice_ciudad);
        // Swapeo el indice con el ultimo elemento del array
        swap(indice_heap, elementos.size());
        elementos.remove(elementos.size()-1);
        shift_down(indice_heap);
    }
}



















