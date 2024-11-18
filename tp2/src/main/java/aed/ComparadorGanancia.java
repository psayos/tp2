// ComparadorGanancia.java
package aed;

import java.util.Comparator;

public class ComparadorGanancia implements Comparator<Traslado> {
    @Override
    public int compare(Traslado t1, Traslado t2) {
        int resultado = Integer.compare(t1.gananciaNeta, t2.gananciaNeta);
        return (resultado != 0) ? resultado : Integer.compare(t1.id, t2.id);
    }
}

