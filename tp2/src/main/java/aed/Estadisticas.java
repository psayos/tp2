package aed;

import java.util.ArrayList;

public class Estadisticas {
    ArrayList<Integer> ganancias;
    ArrayList<Integer> perdidas;
    int cant_ciudades;
    Heap<Ciudad> heapSuperavit;
    ArrayList<Ciudad> ganadora;
    ArrayList<Ciudad> perdedora;
    int ganancia_total;
    int traslados;

    private class Ciudad {
        int id;
        int valor;

        Ciudad(int nuevo_id, int nuevo_valor) {
            id = nuevo_id;
            valor = nuevo_valor;
        }
    }

    public EstadisticasVacias(int cant_ciudades) {
        this.ganancias = new ArrayList<>(cant_ciudades);
        this.perdidas = new ArrayList<>(cant_ciudades);
        this.heapSuperavit = new Heap<Ciudad>();
        this.perdidas = new ArrayList<>();
        this.ganadora = new ArrayList<>();
        this.ganancia_total = 0;
        this.traslados = 0;
    }

    public registrarFinanzas(Traslado traslado) {
        traslados = traslados + 1;
        ganancia_total = ganancia_total + traslado.gananciaNeta();
        ganancias.set(traslado.origen(), ganancias.get(traslado.origen()) + traslado.gananciaNeta());
        perdidas.set(traslado.destino(), perdidas.get(traslado.destino) + traslado.gananciaNeta());
        ganadora = actualizar_registro(ganadora, ganancias.get(traslado.origen()), traslado.origen());
        perdedora = actualizar_registro(perdedora, ganancias.get(traslado.destino()), traslado.destino());
        // Lo del superavit
    }

    public ArrayList<Ciudad> actualizar_registro(ArrayList<Ciudad> anterior, int valorCandidato, int ciudadCandidata) {
        int i = 0;
        ArrayList<Ciudad> nuevo_registro = new ArrayList<>();
        Ciudad ciudad_candidata = new Ciudad(valorCandidato, ciudadCandidata);
        if (valorCandidato > anterior.get(0).valor) {
            nuevo_registro.add(0, ciudad_candidata);
        }else{
            if(valorCandidato == anterior.get(0).valor){
            nuevo_registro = anterior;
            nuevo_registro.add(ciudad_candidata);
            }
        }
        return nuevo_registro;
    }

}