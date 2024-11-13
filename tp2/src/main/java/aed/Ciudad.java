package aed;

public class Ciudad {
    int id;
    int ganancia;
    int perdida;
    int superavit;

    public Ciudad(int nuevo_id,int nuevo_ganancia,int nuevo_perdida){
        this.id = nuevo_id;
        this.ganancia = nuevo_ganancia;
        this.perdida = nuevo_perdida;
        this.superavit = nuevo_ganancia-nuevo_perdida;
    }

    public int id(){
        return id;
    }

    public int ganancia(){
        return ganancia;
    }

    public int perdida(){
        return perdida;
    }

    public int superavit(){
        return superavit;
    }

    public void cambiar_ganancia(int nueva_ganancia){
        this.ganancia = nueva_ganancia;
    }


    public void cambiar_perdida(int nueva_perdida){
        this.perdida = nueva_perdida;
    }
}
