package aed;



public class ComparadorGanancias implements Comparator<Traslado> {
    public int compare(Traslado traslado1, Traslado traslado2){
        if(traslado1.gananciaNeta() == traslado2.gananciaNeta()){
            return Integer.compare(traslado1.id(),traslado2.id())
        }else{
        }
        return Integer.compare(traslado1.gananciaNeta(), traslado2.gananciaNeta())
    }
}