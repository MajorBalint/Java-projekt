package AJatek;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A main tesztosztálya
 */
public class MainTest {
    /**
     * Az induló ablak méretét teszteli
     */
    @Test
    public void main() {
        InduloAblak i = new InduloAblak();
        assertEquals(300, i.getWidth());
        assertEquals(120, i.getHeight());
    }
}