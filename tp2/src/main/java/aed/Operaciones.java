package aed;

import java.util.ArrayList;
import java.util.Comparator;

public class Operaciones{
    private Heap<Traslado> heap_ganancia;
    private Heap<Traslado> heap_tiempo;



    public Heap<Traslado> heap_ganancia(){
        return heap_ganancia;
    }

    public Heap<Traslado> heap_tiempo(){
        return heap_tiempo;
    }

// Armar constructor, pasa ArrayList a heaps.

    public Operaciones(ArrayList<Traslado> traslados){
        ComparadorTiempo comp_tiempo = new ComparadorTiempo();
        ComparadorGanancias comp_ganancia = new ComparadorGanancias();
        this.heap_tiempo = new Heap<>(traslados,comp_tiempo);
        this.heap_ganancia = new Heap<>(traslados,comp_ganancia);

    }
}

