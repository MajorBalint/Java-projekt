package AJatek;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A sejtrács osztály tesztjei
 */
public class SejtRacsTest {
    /**
     * A sejt lekérdező tsztje
     */
    @Test
    public void testGetSejt(){
        SejtRacs i = new SejtRacs(10, 10);
        Sejt j = new Sejt(8, 8);
        j.setErtek(0);
        i.torlo();
        Sejt h = i.getSejt(8, 8);
        assertEquals(j.getX(), h.getX());
        assertEquals(j.getY(), h.getY());
    }

    /**
     * A sejt beállítás tesztje
     */
    @Test
    public void testSetSejt(){
        SejtRacs i = new SejtRacs(10, 10);
        i.setSejt(8, 8, 2);
        Sejt j = i.getSejt(8, 8);
        assertEquals(j.ertek, 2);
    }

    /**
     * A tábla törlés tesztje
     */
    @Test
    public void testTorlo() {
        SejtRacs i = new SejtRacs(10, 10);
        i.setSejt(1, 1, 2);
        i.torlo();
        for (int k = 0; k < 10 + 2; k++)
        {
            for (int l = 0; l < 10 + 2; l++)
            {
                int h = i.racs[k][l].getErtek();
                assertEquals(h, 0);
            }
        }

    }

    /**
     * A magasság lekérdező tesztje
     */
    @Test
    public void testGetMagassag() {
        SejtRacs i = new SejtRacs(10, 10);
        assertEquals(i.getMagassag(), 10);
    }
    /**
     * A szélesség lekérdező tesztje
     */
    @Test
    public void testGetSzelesseg() {
        SejtRacs i = new SejtRacs(10, 10);
        assertEquals(i.getSzelesseg(), 10);
    }



}