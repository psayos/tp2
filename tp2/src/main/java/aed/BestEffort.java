package aed;

import java.util.ArrayList;

public class BestEffort {
    private Operaciones operaciones;
    private Estadisticas estadisticas;

    // Las sumas de las complejidades me permite concluir que la complejidad es O(|T|+|C|)
    public BestEffort(int cantCiudades, Traslado[] traslados){
        ArrayList<Traslado> array_traslados = new ArrayList<>(); // O(|T|)
        for(Traslado t : traslados){ // O(|T|)
            array_traslados.add(t); // O(1)
        }
        this.operaciones = new Operaciones(array_traslados); // O(|T|)
        this.estadisticas = new Estadisticas(cantCiudades);  // O(|C|)
    }

    /* Se recorre el arreglo de traslados una vez (O(|traslados|)). Por cada traslado, se realizan dos
     * operaciones de encolado en heaps, cada una con un cost de O(log(|T|)). La combinación tiene un costo total
     * de O(|traslados|.log(|T|)).
     */
    public void registrarTraslados(Traslado[] traslados){
        for (Traslado t : traslados) { // O(|traslados|)
            operaciones.heapGanancia.encolar(t); // O(log(|T|))
            operaciones.heapTiempo.encolar(t); // O(log(|T|))
        }
    }

    /* El bucle itera n veces y, en cada iteración, se realizan operaciones cuyo costo principal es O(log(|T|))
     * (desencolar y borrar en el heap) más O(log(|C|)) (registro de estadísticas). La combinación de estas 
     * operaciones da la complejidad total O(n.(log(|T|) + log(|C|)).
     */
    public int[] despacharMasRedituables(int n){
        int[] mas_redituables;
        int tamaño = operaciones.heapGanancia.tamaño(); // O(1)

        if( n > tamaño){
            n = tamaño;
        }
            mas_redituables = new int[n];
            for (int i=0; i<n; i++){ // O(n.log(|T|)+log(|C|))
                Traslado max = operaciones.heapGanancia.desencolar(); // O(log(|T|))
                mas_redituables[i]= max.id;
                int indice= max.indice_tiempo;
                operaciones.heapTiempo.borrar(indice); // O(log(|T|))
                estadisticas.registrar_finanzas(max); // O(log(|C|))
                }
        return mas_redituables;
    }

    /* El bucle itera n veces y, en cada iteración, se realizan operaciones cuyo costo principal es O(log(|T|))
     * (desencolar y borrar en el heap) más O(log(|C|)) (registro de estadísticas). La combinación de estas 
     * operaciones da la complejidad total O(n.(log(|T|) + log(|C|)).
     */
    public int[] despacharMasAntiguos(int n){
        int[] mas_antiguos;
        int tamaño = operaciones.heapTiempo.tamaño(); // O(1)
        if( n > tamaño){
            n = tamaño;
        }
            mas_antiguos = new int[n];
            for (int i=0; i<n; i++){ // O(n.log(|T|)+log(|C|))
                Traslado max =operaciones.heapTiempo.desencolar();
                mas_antiguos[i]= max.id;
                int indice = max.indice_ganancia;
                operaciones.heapGanancia.borrar(indice); // O(log(|T|))
                estadisticas.registrar_finanzas(max); // O(log(|C|))
            }
        return mas_antiguos;
    }

    /* Accede al próximo elemento del heap de superávit y obtiene su id. Esto implica simplemente una consulta
     * al elemento máximo del heap sin modificar la estructura, lo cual tiene un costo constante O(1).
     */
    public int ciudadConMayorSuperavit(){
        return estadisticas.heapSuperavit().proximo().id;
    }

    /* La función "ciudadesConMayorGanancia" simplemente devuelve la referencia al atributo "ciudadesMayorGanancia",
     * que ya contiene los datos preactualizados mediante la función "registrar_finanzas". No realiza operaciones
     * adicionales ni recorre estructuras, por lo que su costo es O(1).
    */
    public ArrayList<Integer> ciudadesConMayorGanancia(){
        return  estadisticas.ciudadesMayorGanancia;
    }

    /* La función "ciudadesConMayorPerdidaa" simplemente devuelve la referencia al atributo "ciudadesMayorPerdida",
     * que ya contiene los datos preactualizados mediante la función "registrar_finanzas". No realiza operaciones
     * adicionales ni recorre estructuras, por lo que su costo es O(1).
    */
    public ArrayList<Integer> ciudadesConMayorPerdida(){
        return  estadisticas.ciudadesMayorPerdida;
    }

    /* Únicamente accede a los valores prealmacenados en las variables "ganancia_total" y "traslados_total"
     * y realizar una división, ambas operaciones con costo O(1). 
    */
    public int gananciaPromedioPorTraslado(){
        return estadisticas.ganancia_total/estadisticas.traslados_total;
    }    
}
