package aed;

import java.util.ArrayList;

public class Operaciones {
     Heap<Traslado> heapGanancia;
     Heap<Traslado> heapTiempo;

    public Operaciones(ArrayList<Traslado> arrayTraslados) {
        heapGanancia = new Heap<>(arrayTraslados, new ComparadorGanancia(),false,true);  // O(|T|)
        heapTiempo = new Heap<>(arrayTraslados, new ComparadorTiempo(),false,false); // O(|T|)
    }
}
