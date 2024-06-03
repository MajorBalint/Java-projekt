package AJatek;

/**
 * létrehozza a következő állapotú tervezőrácsot, beállítja a sejtek következő értékét
 */
public class Leptetes 
{
    /**
     * Létrehozza a tervezőrács következő állapotát, minden sejtet megvizsgálva a szomszédos állapotoknak megfelelően
     */
    public void letrehozo()
    {
        SejtRacs kovetkezoracs = SejtRacs.tablak.get(SejtRacs.szamlalo);
        SejtRacs uresracs = SejtRacs.tablak.get(SejtRacs.szamlalo-1);
        for (int i = 1; i < uresracs.getMagassag() + 1; i++)
        {
            for (int j = 1; j < uresracs.getSzelesseg() + 1; j++)
            {
                if (uresracs.getSejt(i, j).getErtek() == 0) 
                {
                    kovetkezoracs.setSejt(i, j, 0);
                }
                else if (uresracs.getSejt(i, j).getErtek() == 3)
                {
                    kovetkezoracs.setSejt(i, j, 2);
                }
                else if (uresracs.getSejt(i, j).getErtek() == 2)
                {
                    kovetkezoracs.setSejt(i, j, 1);
                }
                else if ((uresracs.szomszedszamlalo(i, j) == 2) || (uresracs.szomszedszamlalo(i, j) == 1))
                {
                    kovetkezoracs.setSejt(i, j, 3);
                }
                else {
                    kovetkezoracs.setSejt(i, j, 1);
                }
            }
        }
    }
}
