package aed;

import java.util.ArrayList;

public class Traslado {
    
    int id;
    int origen;
    int destino;
    int gananciaNeta;
    int timestamp;
    int indice_ganancia;
    int indice_tiempo;

    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp){ // Complejidad O(1)
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.gananciaNeta = gananciaNeta;
        this.timestamp = timestamp;
        this.indice_ganancia = 0;
        this.indice_tiempo = 0;
    }

    public void cambiar_indice_ganancia(int nuevo_indice){
        indice_ganancia = nuevo_indice;
    }


    public void cambiar_indice_tiempo(int nuevo_indice){
        indice_tiempo = nuevo_indice;
    }
}