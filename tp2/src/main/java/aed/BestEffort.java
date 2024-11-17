package aed;

import java.util.ArrayList;

public class BestEffort {
    private Operaciones operaciones;
    private Estadisticas estadisticas;


    public BestEffort(int cantCiudades, Traslado[] traslados){
        ArrayList<Traslado> array_traslados = new ArrayList<>();
        for(Traslado t : traslados){
            array_traslados.add( t);
        }
        this.operaciones = new Operaciones(array_traslados);
        this.estadisticas = new Estadisticas(cantCiudades);
    }

    public void registrarTraslados(Traslado[] traslados_){
        for (Traslado t : traslados_) {
            operaciones.heapGanancia.encolar(t);
            operaciones.heapTiempo.encolar(t);
        }
    }

    public int[] despacharMasRedituables(int n){
        int[] mas_redituables;
        int tamaño = operaciones.heapGanancia.tamaño();

        if( n > tamaño){
            n = tamaño;
        }
            mas_redituables = new int[n];
            for (int i=0; i<n; i++){
                Traslado max = operaciones.heapGanancia.desencolar();
                mas_redituables[i]= max.id;
                int indice= max.indice_tiempo;
                operaciones.heapTiempo.borrar(indice);
                 estadisticas.registrar_finanzas(max);
                }
        return mas_redituables;
    }


    public int[] despacharMasAntiguos(int n){
        int[] mas_antiguos;
        int tamaño = operaciones.heapTiempo.tamaño();
        if( n > tamaño){
            n = tamaño;
        }
            mas_antiguos = new int[n];
            for (int i=0; i<n; i++){ // O(n)
                Traslado max =operaciones.heapTiempo.desencolar();
                mas_antiguos[i]= max.id;
                int indice = max.indice_ganancia;
                operaciones.heapGanancia.borrar(indice);
                estadisticas.registrar_finanzas(max);
            }
        return mas_antiguos;
    }




    public int ciudadConMayorSuperavit(){
        return estadisticas.heapSuperavit().proximo().id;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        return  estadisticas.ciudadesMayorGanancia;
    }


    public ArrayList<Integer> ciudadesConMayorPerdida(){
            return  estadisticas.ciudadesMayorPerdida;
        }

    public int gananciaPromedioPorTraslado(){
        return estadisticas.ganancia_total/estadisticas.traslados_total;
    }
    
}
