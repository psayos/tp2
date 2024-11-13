package aed;

import java.util.ArrayList;

public class Estadisticas {
    int cant_ciudades;
    Heap<Ciudad> heapSuperavit;
    ArrayList<Integer> ganadora;
    ArrayList<Integer> perdedora;
    int ganancia_total;
    int traslados;
    ArrayList<Ciudad> ciudades; // En lugar los array de ganancias y perdidas tener algo asi.



    public void EstadisticasVacias(int cant_ciudades) {
        ArrayList<Ciudad> ciudades = new ArrayList<>();
        for(int i = 0; i<cant_ciudades;i++){
            Ciudad nueva_ciudad = new Ciudad(i,0,0);
            ciudades.add(nueva_ciudad);
        }
        ComparadorSuperavit comp_superavit = new ComparadorSuperavit();
        this.heapSuperavit = new Heap<>(ciudades,comp_superavit); // Es vacio, encola ciudades cuando los traslados son despachados.
        this.ciudades = ciudades;
        this.perdedora = new ArrayList<>();
        this.ganadora = new ArrayList<>();
        this.ganancia_total = 0;
        this.traslados = 0;
    }

    public void registrarFinanzas(Traslado traslado) {
        traslados = traslados + 1;
        ganancia_total = ganancia_total + traslado.gananciaNeta();
        Ciudad ciudad_origen = ciudades.get(traslado.origen());
        Ciudad ciudad_destino = ciudades.get(traslado.destino());
        int nueva_ganancia = ciudad_origen.ganancia() + traslado.gananciaNeta();
        ciudad_origen.cambiar_ganancia(nueva_ganancia);

        int nueva_perdida = ciudad_destino.perdida() + traslado.gananciaNeta();
        ciudad_destino.cambiar_ganancia(nueva_perdida);

        ganadora = actualizar_registro(ganadora, ciudad_origen,ciudad_origen.ganancia());
        perdedora = actualizar_registro(perdedora, ciudad_destino, ciudad_destino.perdida());
        // Modifica el heap de superavit. Tiene que ser O(log(|C|)
        heapSuperavit.borrar(ciudad_origen.id());
        heapSuperavit.borrar(ciudad_destino.id());
        // Encolarlo con el valor nuevo de superavit.
        heapSuperavit.encolar(ciudad_origen);
        heapSuperavit.encolar(ciudad_destino);
    }

    public ArrayList<Integer> actualizar_registro(ArrayList<Integer> anterior, Ciudad ciudad, int valor) {
        int i = 0;
        ArrayList<Integer> nuevo_registro = new ArrayList<>();
        if (valor > anterior.get(0)) {
            nuevo_registro.add(0, ciudad.id());
        }else{
            if(valor == anterior.get(0)){
            nuevo_registro = anterior;
            nuevo_registro.add(ciudad.id()); // O(1)
            }
        }
        return nuevo_registro;
    }
}


































