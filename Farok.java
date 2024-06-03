package AJatek;

/**
 * A sejt classból örököltetett osztály, beállítja a sejt koordinátáit és az állapotát a megfelelőre
 */
public class Farok extends Sejt
{
    /**
     * A sejt class konstruktorát használva beállítja az x,y értékeket, valamint a sejt állapotát
     * @param y kapott y koordinátája a sejtnek
     * @param x kapott x koordinátája a sejtnek
     */
    public Farok(int y, int x)
    {
        super(y,x);
        ertek = 2;
    }
}
