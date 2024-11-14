package aed;

import java.util.ArrayList;

public class BestEffort {
    private Operaciones operaciones;
    private Estadisticas estadisticas;


    public BestEffort(int cantCiudades, Traslado[] traslados){
        ArrayList<Traslado> array_traslados = new ArrayList<>();
        for(int i = 0; i<traslados.length;i++){
            array_traslados.add(traslados[i]);
        }
        this.operaciones = new Operaciones(array_traslados); // O(|T|)
        this.estadisticas = new Estadisticas(cantCiudades);
    }

    public void registrarTraslados(Traslado[] traslados) {
        for (int i = 0; i < traslados.length; i++) {
            this.operaciones.heap_tiempo().encolar(traslados[i]);
            this.operaciones.heap_ganancia().encolar(traslados[i]);
        }
    }

    public int[] despacharMasRedituables(int n){
        int[] res = new int[n];
        Traslado mas_redituable;
        for(int i = 0; i<n;i++){
            mas_redituable = this.operaciones.heap_ganancia().desencolar();
            res[i] = mas_redituable.id;
            estadisticas.registrarFinanzas(mas_redituable);
        }
        return res;
    }

    public int[] despacharMasAntiguos(int n){
        int[] res = new int[n];
        Traslado mas_antiguo;
        for(int i = 0;i<n;i++){
            mas_antiguo = this.operaciones.heap_tiempo().desencolar();
            res[i] = mas_antiguo.id;
            estadisticas.registrarFinanzas(mas_antiguo);
        }
        return res;
    }

    public int ciudadConMayorSuperavit(){
        Ciudad ciudad_mayor_superavit = estadisticas.heapSuperavit.desencolar();
        return ciudad_mayor_superavit.id();
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        return estadisticas.ganadora;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        return estadisticas.perdedora;
    }

    public int gananciaPromedioPorTraslado(){
        return estadisticas.ganancia_total/ estadisticas.traslados;
    }
}
