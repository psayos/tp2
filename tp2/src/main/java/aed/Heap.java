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
            T Actual = elementos.get(i);
            if (!esCiudad){
                ((Traslado) Actual).indice_ganancia = i;
                ((Traslado) Actual).indice_tiempo = i;
            } else {
                ((Ciudad) Actual).indice_superavit = i; //O(1)
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
    
    private void sift_up(int indiceActual){
        if (indiceActual == 0 ) {
            return;
        }
        int indicePadre = (indiceActual - 1) / 2; 
        if (comparador.compare(elementos.get(indiceActual), elementos.get(indicePadre)) > 0) {
            swap(indiceActual, indicePadre);
            sift_up(indicePadre);
        }
    }

    public void swap(int indice1, int indice2){
        T Actual = elementos.get(indice1); //O(1)
        T ElemIndicePadre = elementos.get(indice2);//O(1)
        elementos.set(indice1, elementos.get(indice2));//O(1)
        elementos.set(indice2, Actual);//O(1)
        if (!esCiudad) {
            if(esGanancia){
                ((Traslado) Actual).indice_ganancia =indice2;
                ((Traslado) ElemIndicePadre).indice_ganancia =indice1;
            }else{
                ((Traslado) Actual).indice_tiempo =indice2;
                ((Traslado) ElemIndicePadre).indice_tiempo =indice1;
            }
        } else { //O(1)
            ((Ciudad) Actual).indice_superavit = indice2;
            ((Ciudad) ElemIndicePadre).indice_superavit = indice1;
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


