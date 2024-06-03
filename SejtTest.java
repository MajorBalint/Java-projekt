package AJatek;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A sejt osztály tesztjei
 */
public class SejtTest {
    /**
     * Azt teszteli, hogy a visszakapott érték megfelelőe
     */
    @Test
    public void getErtek() {
        Sejt i = new Sejt(0, 0);
        assertEquals(0, i.getErtek());
    }

    /**
     * Azt teszteli, hogy az érték, amit beállítottunk tényleg megfelelő-e
     */
    @Test
    public void setErtek() {
        Sejt i = new Sejt(0, 0);
        i.setErtek(2);
        assertEquals(2, i.getErtek());
    }

    /**
     * Azt teszteli, hogy a függvény megfelelő értéket ad-e vissza
     */
    @Test
    public void testGetY(){
        Sejt i = new Sejt(5, 5);
        assertEquals(5, i.getY());
    }
    /**
     * Azt teszteli, hogy a függvény megfelelő értéket ad-e vissza
     */
    @Test
    public void testGetX(){
        Sejt i = new Sejt(5, 5);
        assertEquals(5, i.getX());
    }
}