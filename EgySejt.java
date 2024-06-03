package AJatek;

import java.awt.Color;
import javax.swing.JButton;

/**
 * A sejtek állapotának, színük változtatásáért felelős osztály
 */
class EgySejt implements Kattintas {
    /**
     * A függvény a kattintás kapott koordinátái alapján elvégzi a szükséges állapot (szín) változtatást
     * @param forras a kattintás helyzete
     * @param helyzet a cella kattintás előtti állapota
     */
    @Override
    public void put(JButton forras, boolean helyzet) 
    {
        int i;
        SejtRacs sejtracs = SejtRacs.tablak.get(SejtRacs.szamlalo);
        int y = forras.getY() / JatekAblak.jatekgrafika.sejtmeretei;;
        int x = forras.getX() / JatekAblak.jatekgrafika.sejtmeretei;;
        if (forras.getBackground() == Color.WHITE) 
        {
            forras.setBackground(Color.BLACK);
            sejtracs.setSejt(y, x, 1);
        } else if (forras.getBackground() == Color.BLACK) 
        {
            forras.setBackground(Color.ORANGE);
            sejtracs.setSejt(y, x, 2);
        } else if (forras.getBackground() == Color.ORANGE) 
        {
            forras.setBackground(Color.RED);
            sejtracs.setSejt(y, x, 3);
        } else 
        {
            forras.setBackground(Color.WHITE);
            sejtracs.setSejt(y, x, 0);

        }
    }

}
