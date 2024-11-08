package aed;



public class ComparadorTiempo implements Comparator<Traslado> {
    public int compare(Traslado traslado1, Traslado traslado2){
        return Integer.compare(traslado1.timestamp(), traslado2.timestamp())
}