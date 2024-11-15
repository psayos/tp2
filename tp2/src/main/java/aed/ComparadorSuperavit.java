package aed;

import java.util.Comparator;

public class ComparadorSuperavit implements Comparator<Ciudad> {
    public int compare(Ciudad c1, Ciudad c2) {
        if (c1.superavit() == c2.superavit()) {
            return Integer.compare(c2.id(), c1.id());
        } else {
            return Integer.compare(c1.superavit(), c2.superavit());
        }
    }
}

