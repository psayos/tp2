package aed;


public class Ciudad {
    int id;
    int ganancia;
    int perdida;
    int superavit; 
    int indice_superavit;

    public Ciudad(int id){
        ganancia = 0;
        perdida = 0;
        superavit = 0; 
        this.id = id;
        this.indice_superavit = 0;
    }

    public void actualizarGanancia(int nueva_ganancia){
        ganancia = ganancia + nueva_ganancia;
        superavit = ganancia - perdida; 
    }

    public void actualizarPerdida(int nueva_perdida){
        perdida = perdida + nueva_perdida;
        superavit = ganancia - perdida;
    }


    public void cambiar_indice_superavit(int nuevo_indice){
        indice_superavit = nuevo_indice;
    }
}

