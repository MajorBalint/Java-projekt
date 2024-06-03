package AJatek;

import javax.swing.JButton;
/**
 * Egy interfész osztály, amelyet az egysejt osztály implementál, ebben találhatóak a későbbiekben a tervezőrács
 * méretetit tároló intek és egy put függvény, amelyet az egysejt osztályban definiálunk felül
 */
public interface Kattintas
{
    int h = JatekAblak.jatekgrafika.magassag;
    
    int w = JatekAblak.jatekgrafika.szelesseg;

    /**
     * Az interfész üres függvénye, az egysejt osztályban használjuk, itt van megírva a törzse
     * @param forras a gomb koordinátái
     * @param helyzet a sejt állapota
     */
    void put(JButton forras, boolean helyzet);
}
