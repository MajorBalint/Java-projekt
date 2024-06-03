package AJatek;
/**
 * A sejt classból örököltetett osztály, beállítja a sejt koordinátáit
 */
public class Ures extends Sejt 
{
    /**
     * A sejt class konstruktorát használva beállítja az x,y értékeket, valamint mivel a sejt állapota az eredeti, ezért azt nem változatja
     * @param y kapott y koordinátája a sejtnek
     * @param x kapott x koordinátája a sejtnek
     */
    public Ures(int x, int y)
    {
        super(x,y);
    }
}
