package aed;
import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T> {

    private ArrayList<T> elementos;
    int tamaño;
    private Comparator<T> comparador; 
    private boolean esCiudad;
    private boolean esGanancia;

    // Como el arreglo puede estar compuesto por ciudades o traslados, llamaré a estos elementos generanicamente n
    public Heap(ArrayList<T> array_elementos, Comparator<T> comp, boolean esCiudad, boolean esGanancia) { // Complejidad O(n)
        tamaño = array_elementos.size(); // O(1)
        elementos = new ArrayList<T>(array_elementos); // O(n)
        comparador = comp; 
        this.esCiudad = esCiudad; 
        this.esGanancia = esGanancia; 
        for (int i = 0; i < tamaño; i++) {  // O(n)
            T actual = elementos.get(i); // O(1)
            if (!esCiudad){ 
                if(esGanancia){ 
                    Traslado elem = (Traslado) actual;
                    elem.cambiar_indice_ganancia(i);  // O(1)
                }else{
                    Traslado elem = (Traslado) actual;
                    elem.cambiar_indice_tiempo(i);  // O(1)
                }
            } else {
                Ciudad elem = (Ciudad) actual;
                elem.cambiar_indice_superavit(i);  // O(1)
            }
        }
        heapify();
    }


    public T proximo() {
        return elementos.get(0);
        }

    public void encolar(T elem) { // O(log(n))
        elementos.add(elem); // O(1)
        tamaño = tamaño + 1; 
        sift_up(tamaño - 1); // O(log(n))
    }
    
    private void sift_up(int indice){ // O(log(n))
        if (indice == 0 ) {
            return;
        }
        int indicePadre = (indice - 1) / 2;
        if (comparador.compare(elementos.get(indice), elementos.get(indicePadre)) > 0) {
            swap(indice, indicePadre); // O(1)
            sift_up(indicePadre); // O(log(n)), puesto que en el peor caso deberá recorrer toda la "altura" del heap
        }
    }

    public void swap(int indice1, int indice2){ // O(1)
        T actual = elementos.get(indice1); //O(1)
        T padre = elementos.get(indice2);//O(1)
        elementos.set(indice1, elementos.get(indice2));//O(1)
        elementos.set(indice2, actual);//O(1)
        if (!esCiudad) {
            if(esGanancia){
                Traslado elem = (Traslado) actual;
                Traslado elem_padre = (Traslado) padre;
                elem.cambiar_indice_ganancia(indice2); // O(1)
                elem_padre.cambiar_indice_ganancia(indice1); // O(1)
            }else{
                Traslado elem = (Traslado) actual;
                Traslado elem_padre = (Traslado) padre;
                elem.cambiar_indice_tiempo(indice2); // O(1)
                elem_padre.cambiar_indice_tiempo(indice1); // O(1)
            }
        } else { //O(1)
            Ciudad elem = (Ciudad) actual;
            Ciudad elem_padre = (Ciudad) padre;
            elem.cambiar_indice_superavit(indice2); // O(1)
            elem_padre.cambiar_indice_superavit(indice1); // O(1)
        }
    }

    public T desencolar() { // O(log(n))
        if (tamaño == 0) {
        }
        T maximo = elementos.get(0);
        elementos.set(0, elementos.get(tamaño - 1)); // Reemplaza la raíz con el último elemento.
        elementos.remove(tamaño - 1);
        tamaño--;

        if (tamaño > 0) {
            sift_down(0); // Reordena el heap.
        }

        return maximo;
    }

    public int tamaño(){ // O(1)
        return tamaño;
    }

    private void sift_down(int indice) { // O(log(n))
        while (true) {
            int hijoIzquierdo = 2 * indice + 1;
            int hijoDerecho = 2 * indice + 2;
            int indiceMayor = indice;

            if (hijoIzquierdo < tamaño && comparador.compare(elementos.get(hijoIzquierdo), elementos.get(indiceMayor)) > 0) {
                indiceMayor = hijoIzquierdo;
            }

            if (hijoDerecho < tamaño && comparador.compare(elementos.get(hijoDerecho), elementos.get(indiceMayor)) > 0) {
                indiceMayor = hijoDerecho;
            }

            if (indiceMayor == indice) {
                break;
            }

            swap(indice, indiceMayor);
            indice = indiceMayor;
        }
    }



    public void borrar(int indice) {  // O(log(n))
        if (indice < 0 || indice >= tamaño) {

        }
        swap(indice, tamaño - 1); // O(1)
        elementos.remove(tamaño - 1); // O(1)
        tamaño--;

        if (indice < tamaño) {
            sift_down(indice);  // O(log(n))
            sift_up(indice); //  O(log(n))
        }
    }

    public void heapify() { // O(n)
        for (int i = (tamaño / 2) - 1; i >= 0; i--) {
            sift_down(i);
        }
    }
}


