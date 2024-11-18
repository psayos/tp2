package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TestsPropios {

    int cantCiudades;
    Traslado[] listaTraslados;
    ArrayList<Integer> actual;


    @BeforeEach
    void init(){
        //Reiniciamos los valores de las ciudades y traslados antes de cada test
        cantCiudades = 7;
        listaTraslados = new Traslado[] {
                                            new Traslado(1, 1, 3, 100, 10),
                                            new Traslado(2, 2, 4, 400, 20),
                                            new Traslado(3, 3, 0, 500, 50),
                                            new Traslado(4, 4, 1, 500, 11),
                                            new Traslado(5, 2, 4, 1000, 40),
                                            new Traslado(6, 1, 0, 1000, 41),
                                            new Traslado(7, 0, 2, 2000, 42)
                                        };
        
        
    }

    void assertSetEquals(ArrayList<Integer> s1, ArrayList<Integer> s2) {
        assertEquals(s1.size(), s2.size());
        for (int e1 : s1) {
            boolean encontrado = false;
            for (int e2 : s2) {
                if (e1 == e2) encontrado = true;
            }
            assertTrue(encontrado, "No se encontró el elemento " +  e1 + " en el arreglo " + s2.toString());
        }
    }

    @Test
    void primer_despacho(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        sis.despacharMasRedituables(1);
        
        assertEquals(0, sis.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(0)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(2)), sis.ciudadesConMayorPerdida());
        assertEquals(2000, sis.gananciaPromedioPorTraslado());
    }

    @Test
    void multiples_despachos_redituables(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        sis.despacharMasRedituables(4);
        
        assertEquals(0, sis.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(0)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(2)), sis.ciudadesConMayorPerdida());
        assertEquals(1125, sis.gananciaPromedioPorTraslado());
    }

    @Test
    void multiples_despachos_antiguos(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        sis.despacharMasAntiguos(3);
        
        assertEquals(2, sis.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(4)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(1)), sis.ciudadesConMayorPerdida());
        assertEquals(333, sis.gananciaPromedioPorTraslado());
    }

    @Test
    void despachos_mixtos(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        sis.despacharMasRedituables(3);
        sis.despacharMasAntiguos(2);
        
        assertEquals(0, sis.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(0)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(2)), sis.ciudadesConMayorPerdida());
        assertEquals(920, sis.gananciaPromedioPorTraslado());
        
    }

    @Test
    void despachos_alternados(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        sis.despacharMasRedituables(1);
        sis.despacharMasAntiguos(1);
        
        assertEquals(0, sis.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(0)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(2)), sis.ciudadesConMayorPerdida());
        assertEquals(1050, sis.gananciaPromedioPorTraslado());

        sis.despacharMasAntiguos(1);
        sis.despacharMasRedituables(1);

        assertEquals(0, sis.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(0)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(2)), sis.ciudadesConMayorPerdida());
        assertEquals(900, sis.gananciaPromedioPorTraslado());
    }
    
    @Test
    void despachos_alternados_con_registros(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        Traslado[] trasladosA = new Traslado[]{
            new Traslado(8, 6, 3, 200, 45),
            new Traslado(9, 4, 6, 300, 9),
        };

        Traslado[] trasladosB = new Traslado[]{
            new Traslado(10, 3, 2, 3000, 30),
            new Traslado(11, 0, 1, 1500, 6),
        };

        sis.registrarTraslados(trasladosA);
        sis.despacharMasRedituables(2);
        sis.despacharMasAntiguos(2);

        assertEquals(1, sis.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(0)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(2)), sis.ciudadesConMayorPerdida());
        assertEquals(850, sis.gananciaPromedioPorTraslado());

        sis.registrarTraslados(trasladosB);
        sis.despacharMasAntiguos(2);
        sis.despacharMasRedituables(2);

        assertEquals(3, sis.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(0)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(2)), sis.ciudadesConMayorPerdida());
        assertEquals(1175, sis.gananciaPromedioPorTraslado());

    }
    
    @Test
    void despachos_en_bloque_totales(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        Traslado[] traslados = new Traslado[]{
            new Traslado(8, 6, 3, 200, 45),
            new Traslado(9, 4, 6, 300, 9),
            new Traslado(10, 3, 2, 3000, 30),
            new Traslado(11, 0, 1, 1500, 6),
        };

        sis.registrarTraslados(traslados);
        sis.despacharMasRedituables(5);
        sis.despacharMasAntiguos(6);

        assertEquals(3, sis.ciudadConMayorSuperavit());
        assertSetEquals(new ArrayList<>(Arrays.asList(0,3)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(2)), sis.ciudadesConMayorPerdida());
        assertEquals(954, sis.gananciaPromedioPorTraslado());
    }
}
