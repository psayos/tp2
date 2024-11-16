package aed;

import java.util.ArrayList;

public class Operaciones {
     Heap<Traslado> heapGanancia;
     Heap<Traslado> heapTiempo;

    public Operaciones(ArrayList<Traslado> infoTraslados) {
        heapGanancia = new Heap<>(infoTraslados, new ComparadorGanancia(),false,true);
        heapTiempo = new Heap<>(infoTraslados, new ComparadorTiempo(),false,false);
    }
}
