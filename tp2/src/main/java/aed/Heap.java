package aed;
import java.util.ArrayList;
import java.util.Comparator;

public class Heap<T> {

    private ArrayList<T> elementos;
    int tamaño;
    private Comparator<T> comparador; 
    private boolean esCiudad;
    private boolean esGanancia;


    public Heap(ArrayList<T> array_elementos, Comparator<T> comp, boolean esCiudad, boolean esGanancia) { // Complejidad O(n)
        tamaño = array_elementos.size();
        elementos = new ArrayList<T>(array_elementos);
        comparador = comp;
        this.esCiudad = esCiudad;
        this.esGanancia = esGanancia;
        for (int i = 0; i < tamaño; i++) {
            T actual = elementos.get(i);
            if (!esCiudad){
                if(esGanancia){
                    Traslado elem = (Traslado) actual;
                    elem.cambiar_indice_ganancia(i);
                }else{
                    Traslado elem = (Traslado) actual;
                    elem.cambiar_indice_tiempo(i);
                }
            } else {
                Ciudad elem = (Ciudad) actual;
                elem.cambiar_indice_superavit(i);
            }
        }
        heapify();
    }


    public T proximo() {
        return elementos.get(0);
        }

    public void encolar(T elem) {
        elementos.add(elem);
        tamaño = tamaño + 1;
        sift_up(tamaño - 1);
    }
    
    private void sift_up(int indice){
        if (indice == 0 ) {
            return;
        }
        int indicePadre = (indice - 1) / 2;
        if (comparador.compare(elementos.get(indice), elementos.get(indicePadre)) > 0) {
            swap(indice, indicePadre);
            sift_up(indicePadre);
        }
    }

    public void swap(int indice1, int indice2){
        T actual = elementos.get(indice1); //O(1)
        T padre = elementos.get(indice2);//O(1)
        elementos.set(indice1, elementos.get(indice2));//O(1)
        elementos.set(indice2, actual);//O(1)
        if (!esCiudad) {
            if(esGanancia){
                Traslado elem = (Traslado) actual;
                Traslado elem_padre = (Traslado) padre;
                elem.cambiar_indice_ganancia(indice2);
                elem_padre.cambiar_indice_ganancia(indice1);
            }else{
                Traslado elem = (Traslado) actual;
                Traslado elem_padre = (Traslado) padre;
                elem.cambiar_indice_tiempo(indice2);
                elem_padre.cambiar_indice_tiempo(indice1);
            }
        } else { //O(1)
            Ciudad elem = (Ciudad) actual;
            Ciudad elem_padre = (Ciudad) padre;
            elem.cambiar_indice_superavit(indice2);
            elem_padre.cambiar_indice_superavit(indice1);
        }
    }

    public T desencolar() {
        T maximo = elementos.get(0);
        if (tamaño==1){
            elementos.remove(0);
            tamaño--;
        }
        else{  //O(1)
            elementos.set(0, elementos.get(tamaño - 1));
            elementos.remove(tamaño - 1);
            tamaño = tamaño -1;
            sift_down(0);
        }
        return maximo;
    }

    public int tamaño(){
        return tamaño;
    }

    private void sift_down(int indice) {
        int r = 2 * indice + 1;
        int l = 2 * indice + 2;
        int indiceMaximo = indice;

        if (l < tamaño && comparador.compare(elementos.get(l), elementos.get(indiceMaximo)) > 0) {
            indiceMaximo = l;
        }

        if (r < tamaño && comparador.compare(elementos.get(r), elementos.get(indiceMaximo)) > 0) {
            indiceMaximo = r;
        }

        if (indiceMaximo != indice) {
            swap(indice,indiceMaximo);
            sift_down(indiceMaximo);
        }
    }

    public void borrar(int indice){
        if (indice < 0 || indice>= tamaño){
            return;
        }
        else {
            T ultimo= elementos.get(tamaño-1);
            elementos.set(indice,ultimo);
            sift_down(indice);
            elementos.remove(tamaño-1);
            tamaño--;
        }
    }

    public void heapify() {
        for (int i = (tamaño / 2) - 1; i >= 0; i--) {
            sift_down(i);
        }
    }
}


