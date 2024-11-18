// ComparadorSuperavit.java
package aed;

import java.util.Comparator;

public class ComparadorSuperavit implements Comparator<Ciudad> {
    @Override
    public int compare(Ciudad c1, Ciudad c2) {
        int resultado = Integer.compare(c1.superavit, c2.superavit);
        return (resultado != 0) ? resultado : Integer.compare(c2.id, c1.id);
    }
}

