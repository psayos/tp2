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
        actualizarMayorGanancia(origen, traslado.gananciaNeta);
        actualizarMayorPerdida(destino, traslado.gananciaNeta);

        int indice = origen.indice_superavit;
        heapSuperavit.borrar(indice);
        heapSuperavit.encolar(origen);

        int indice2 = destino.indice_superavit; //O(1)
        heapSuperavit.borrar(indice2);
        heapSuperavit.encolar(destino);
    }

    public void actualizarMayorGanancia(Ciudad ciudadOrigen, int GananciaNeta) {
        if (ciudadesMayorGanancia.isEmpty() || ciudadOrigen.ganancia == maxima_ganancia) {
            ciudadesMayorGanancia.add(ciudadOrigen.id);
            maxima_ganancia = ciudadOrigen.ganancia;
        } else {
            if (ciudadOrigen.ganancia > maxima_ganancia) {
                ciudadesMayorGanancia.clear();
                ciudadesMayorGanancia.add(ciudadOrigen.id);
                maxima_ganancia = ciudadOrigen.ganancia;
            }
        }
    }

    public void actualizarMayorPerdida(Ciudad ciudadDestino, int GananciaNeta) {
        if (ciudadesMayorPerdida.isEmpty() || ciudadDestino.perdida == maxima_perdida) {
            ciudadesMayorPerdida.add(ciudadDestino.id);
            maxima_perdida = ciudadDestino.perdida;
        } else {
            if (ciudadDestino.perdida > maxima_perdida) {
                ciudadesMayorPerdida.clear();
                ciudadesMayorPerdida.add(ciudadDestino.id);
                maxima_perdida = ciudadDestino.perdida;
            }
        }
    }


    public Heap<Ciudad> heapSuperavit() {
        return heapSuperavit;
    }


}




