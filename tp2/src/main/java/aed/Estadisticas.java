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

    public Estadisticas(int cantidad_ciudades) {
        ciudadesMayorGanancia = new ArrayList<>();
        ciudadesMayorPerdida = new ArrayList<>();
        int j = 0;
        ArrayList<Ciudad> arrCiudades = new ArrayList<Ciudad>();
        while (j < cantidad_ciudades) {
            Ciudad c = new Ciudad(j);
            arrCiudades.add(c);
            j++;
        }
        heapSuperavit = new Heap<>(arrCiudades, new ComparadorSuperavit(), true, false); //O(C)
        ciudades = new ArrayList<>(arrCiudades); //O(1)
        maxima_ganancia = 0;
        maxima_perdida = 0;
        ganancia_total = 0;
        traslados_total = 0;
    }

    public void registrar_finanzas(Traslado traslado) {
        ganancia_total = ganancia_total + traslado.gananciaNeta;
        traslados_total++;
        Ciudad origen = ciudades.get(traslado.origen);
        origen.actualizarGanancia(traslado.gananciaNeta);
        Ciudad destino = ciudades.get(traslado.destino);
        destino.actualizarPerdida(traslado.gananciaNeta);
        actualizarMayorGanancia(origen);
        actualizarMayorPerdida(destino);

        int indice = origen.indice_superavit;
        heapSuperavit.borrar(indice);
        heapSuperavit.encolar(origen);

        int indice2 = destino.indice_superavit; //O(1)
        heapSuperavit.borrar(indice2);
        heapSuperavit.encolar(destino);
    }

    public void actualizarMayorGanancia(Ciudad origen) {
        if (ciudadesMayorGanancia.isEmpty() || origen.ganancia == maxima_ganancia) {
            ciudadesMayorGanancia.add(origen.id);
            maxima_ganancia = origen.ganancia;
        } else {
            if (origen.ganancia > maxima_ganancia) {
                ciudadesMayorGanancia.clear();
                ciudadesMayorGanancia.add(origen.id);
                maxima_ganancia = origen.ganancia;
            }
        }
    }

    public void actualizarMayorPerdida(Ciudad destino) {
        if (ciudadesMayorPerdida.isEmpty() || destino.perdida == maxima_perdida) {
            ciudadesMayorPerdida.add(destino.id);
            maxima_perdida = destino.perdida;
        } else {
            if (destino.perdida > maxima_perdida) {
                ciudadesMayorPerdida.clear();
                ciudadesMayorPerdida.add(destino.id);
                maxima_perdida = destino.perdida;
            }
        }
    }


    public Heap<Ciudad> heapSuperavit() {
        return heapSuperavit;
    }


}




