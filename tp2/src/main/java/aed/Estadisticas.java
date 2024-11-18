package aed;

import java.util.ArrayList;

public class Estadisticas {
    ArrayList<Ciudad> ciudades;
    ArrayList<Integer> ciudadesMayorGanancia;
    ArrayList<Integer> ciudadesMayorPerdida;
    Heap<Ciudad> heapSuperavit;
    int maxima_ganancia;
    int maxima_perdida;
    int ganancia_total;
    int traslados_total;

    public Estadisticas(int cantidad_ciudades) { // O(|C|)
        ciudadesMayorGanancia = new ArrayList<>(); // O(|C|)
        ciudadesMayorPerdida = new ArrayList<>(); // O(|C|)
        int j = 0;
        ArrayList<Ciudad> lista_ciudades = new ArrayList<>(); // O(|C|)
        while (j < cantidad_ciudades) { // O(|C|) 
            Ciudad c = new Ciudad(j);
            lista_ciudades.add(c); // O(|1|)
            j++;
        }
        heapSuperavit = new Heap<>(lista_ciudades, new ComparadorSuperavit(), true, false); // O(|C|)
        ciudades = new ArrayList<>(lista_ciudades); //O(|C|)
        maxima_ganancia = 0;
        maxima_perdida = 0;
        ganancia_total = 0;
        traslados_total = 0;
    }

    public void registrar_finanzas(Traslado traslado) { // O(log(n))
        ganancia_total = ganancia_total + traslado.gananciaNeta;
        traslados_total++;

        Ciudad origen = ciudades.get(traslado.origen);
        origen.actualizarGanancia(traslado.gananciaNeta); // O(1)
        determinarMayorGanancia(origen); // O(1)

        Ciudad destino = ciudades.get(traslado.destino);
        destino.actualizarPerdida(traslado.gananciaNeta); // O(1)
        determinarMayorPerdida(destino); // O(1)

        int indice = origen.indice_superavit;
        heapSuperavit.borrar(indice); // O(log(n))
        heapSuperavit.encolar(origen); // O(log(n))

        int indice2 = destino.indice_superavit; //O(1)
        heapSuperavit.borrar(indice2); // O(log(n))
        heapSuperavit.encolar(destino); // O(log(n))
    }

    public void determinarMayorGanancia(Ciudad origen) { // O(1)
        if (ciudadesMayorGanancia.isEmpty() || origen.ganancia == maxima_ganancia) { // O(1)
            ciudadesMayorGanancia.add(origen.id); // O(1)
            maxima_ganancia = origen.ganancia;
        } else {
            if (origen.ganancia > maxima_ganancia) {
                ciudadesMayorGanancia.clear(); // O(1)
                ciudadesMayorGanancia.add(origen.id); // O(1)
                maxima_ganancia = origen.ganancia;
            }
        }
    }

    public void determinarMayorPerdida(Ciudad destino) { // O(1)
        if (ciudadesMayorPerdida.isEmpty() || destino.perdida == maxima_perdida) { // O(1)
            ciudadesMayorPerdida.add(destino.id); // O(1)
            maxima_perdida = destino.perdida;
        } else {
            if (destino.perdida > maxima_perdida) {
                ciudadesMayorPerdida.clear(); // O(1)
                ciudadesMayorPerdida.add(destino.id); // O(1)
                maxima_perdida = destino.perdida;
            }
        }
    }

    public Heap<Ciudad> heapSuperavit() { // O(1)
        return heapSuperavit;
    }
}




