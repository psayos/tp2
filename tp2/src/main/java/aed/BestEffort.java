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


    }

    public void registrarTraslados(Traslado[] traslados) {
        for (int i = 0; i < traslados.length; i++) {
            this.operaciones.heap_tiempo().encolar(traslados[i]);
            this.operaciones.heap_ganancia().encolar(traslados[i]);
        }
    }
// Complejidad: O(nlog(n))

    public int[] despacharMasRedituables(int n){
        int[] res = new int[n];
        Traslado mas_redituable;
        for(int i = 0; i<n;i++){
            mas_redituable = this.operaciones.heap_ganancia().desencolar();
            res[i] = mas_redituable.id;
        }
        return res;
    }

    public int[] despacharMasAntiguos(int n){
        int[] res = new int[n];
        Traslado mas_antiguo;
        for(int i = 0;i<n;i++){
            mas_antiguo = this.operaciones.heap_tiempo().desencolar();
            res[i] = mas_antiguo.id;
        }
        return res;
    }

    public int ciudadConMayorSuperavit(){
        // Implementar
        return 0;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        // Implementar
        return null;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        // Implementar
        return null;
    }

    public int gananciaPromedioPorTraslado(){
        // Implementar
        return 0;
    }
    
}
