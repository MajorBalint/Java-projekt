package AJatek;

import java.io.Serializable;

/**
 * Az összes sejt állapt osztály ősosztálya
 * Ebben vannak az állapot, és a koordináta változói
 * Ezekhez tartozó változtató és lekérdző függvények
 */
public class Sejt implements Serializable
{
    protected  int ertek;
    protected  int x;
    protected  int y;

    /**
     * Az osztály konstruktora, alapesetben a sejt állapotát üresnek állítja be
     * @param ykapott az y koordináta
     * @param xkapott az x koordináta
     */
    public Sejt(int ykapott, int xkapott)
    {
        y=ykapott;
        x=xkapott;
        ertek = 0;
    }

    /**
     * Beállítja a sejt állapotát
     * @param v az új állapot
     */
    public  void setErtek(int v)
    {
        ertek = v;
    }

    /**
     * Visszaadja a sejt állapotát
     * @return a sejt jelenlegi állapota
     */
    public int getErtek()
    {
        return ertek;
    }

    /**
     * Visszaadja az y koordinátát
     * @return az y koordináta
     */
    public int getY() {
        return y;
    }

    /**
     * Visszaadja az x koordinátát
     * @return az x koordináta
     */
    public int getX() {
        return x;
    }
}
