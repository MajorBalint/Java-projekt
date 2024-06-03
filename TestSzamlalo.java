package AJatek;


import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A sejtrács osztály számláló függvényének tesztje
 */
public class TestSzamlalo {
    /**
     * A sejtrács osztály számláló függvényének tesztje, azt viszgálja, hogy megfelelő mennyiségű szomszédot talál-e
     */
    @Test
    public void testSzomszedszamlalo() {
        SejtRacs i = new SejtRacs(3, 3);
        i.setSejt(1, 1, 3);
        int szamlalo = 0;
        for (int k = 0; k < 3; k++) {
            for (int h = 0; h < 3; h++) {
                if (i.getSejt(2 - 1 + k, 2 - 1 + h).getErtek() == 3) {
                    szamlalo++;
                }
            }
        }
        assertEquals(szamlalo, 1);
    }
}
