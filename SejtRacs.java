package AJatek;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A táblát tároló osztály
 * Ebben vannak a tábla méretei, egy számláló int, ami a lépések számát tárolja és egy két dimenziós tömb, amiben a
 * sejtek vannak
 */
public class SejtRacs implements Serializable 
{

    public static int szamlalo = -1;

    public static ArrayList<SejtRacs> tablak = new ArrayList<>();
   
    public int magassag;
    
    public int szelesseg;
    
    public final Sejt[][] racs;

    /**
     * Az osztály konstruktora, beállítja a szélességet, magasságot, létrehozza a sejtek tároloó tömböt a megfelelő
     * méretekkel és fel is tölti azt
     * @param h kapott magasság
     * @param w kapott szélesség
     */
    public SejtRacs(int h, int w) 
    {
        szelesseg = w;
        magassag = h;
        SejtRacs.szamlalo++;
        tablak.add(this);
        racs = new Sejt[h + 2][w + 2];
        for (int i = 0; i < h + 2; i++) 
        {
            for (int j = 0; j < w + 2; j++) 
            {
                racs[i][j] = new Sejt(i, j);
            }
        }
    }

    /**
     * Visszaadja a kérdezett sejtet
     * @param y a sejt y koordinátája
     * @param x a sejt x koordinátája
     * @return a kért sejt a tömbből
     */
    public Sejt getSejt(int y, int x) 
    {
        return racs[y][x];
    }

    /**
     * Visszaállítja az egész tervezőrács össze celláját üresre
     */
    public void torlo()
    {
        SejtRacs.tablak.subList(1, SejtRacs.tablak.size()).clear();
        SejtRacs sejtracs = SejtRacs.tablak.get(0);
        for (int i = 0; i < magassag + 2; i++) 
        {
            for (int j = 0; j < szelesseg + 2; j++) 
            {
                sejtracs.racs[i][j].setErtek(0);
            }
        }
    }

    /**
     * Megváltoztatja a kapott helyen található sejt állapotát, és ellenőrzi, hogy a kapott koordináták megfelelőek-e
     * @param y a kapott magasság
     * @param x a kapott szélesség
     * @param v a beállítani kívánt állapot
     */
    public void setSejt(int y, int x, int v)
    {
        Sejt c;
        switch (v)
        {
            case 0:
            {
                c = new Ures(y, x);
                break;
            }
            case 1:
            {
                c = new Vezeto(y, x);
                break;
            }
            case 2:
            {
                c = new Farok(y, x);
                break;
            }
            case 3:
            {
                c = new Elektronfej(y, x);
                break;
            }
            default:
            {
                c = new Sejt(y, x);
                break;
            }
        }
        if (x > szelesseg) 
        {
            throw new ArrayIndexOutOfBoundsException("x > szelesseg");
        }
        if (y > magassag) 
        {
            throw new ArrayIndexOutOfBoundsException("y > magassag");
        }
        if (x < 0) 
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (y < 0) 
        {
            throw new ArrayIndexOutOfBoundsException();
        }

        racs[y][x] = c;
    }

    /**
     * Visszaadja a tervezőrács magasságát
     * @return a classban tárolt magasság
     */
    public int getMagassag()
    {
        return magassag;
    }

    /**
     * Visszaadja a tervezőrács szélességét
     * @return a classban tárolt szélesség
     */
    public int getSzelesseg()
    {
        return szelesseg;
    }

    /**
     * A kapott koordinátákon található sejt szomszédainak állapotát vizsgálja meg, és ha ez egy elektronfej (3-as az
     * értéke), akkor számláló értékét növeli, ha a sejt maga is fej, akkor a számláló értékét csökkenti
     * @param y kapott y koordináta
     * @param x kapott x koordináta
     * @return visszaadja a számláló végső állapotát
     */
    public int szomszedszamlalo(int y, int x)
    {
        int szamlalo = 0;
        if (getSejt(y, x).getErtek() == 3) 
        {
            szamlalo--;
        }
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 3; j++) 
            {
                if (getSejt(y - 1 + i, x - 1 + j).getErtek() == 3) 
                {
                    szamlalo++;
                }
            }
        }
        return szamlalo;
    }
}