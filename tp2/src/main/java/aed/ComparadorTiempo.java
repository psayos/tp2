package aed;

import java.util.Comparator;

public class ComparadorTiempo implements Comparator<Traslado>{
    public int compare(Traslado t1, Traslado t2){
        return Integer.compare(t2.timestamp(),t1.timestamp());
    }
}