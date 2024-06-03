package AJatek;

import static AJatek.SejtRacs.tablak;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * A játktér tárolásáért és az azon végzett műveletekért felelős osztály.
 */
public class Fajlkezelo implements Serializable 
{
    /**
     * Betölti az előre mentett segítő dokumentumot, és frissíti a tervezőrácsot
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public void segito() throws ClassNotFoundException, Exception
    {
        SejtRacs racsbetoltes = null;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("segito.txt")); //ezt a program konyvatraba kell a felhasznalonak behelyeznie
        racsbetoltes = (SejtRacs) in.readObject();
        in.close();

        new JatekAblak(racsbetoltes.magassag, racsbetoltes.szelesseg);
        SejtRacs.szamlalo++;
        tablak.add(racsbetoltes);
        System.out.println(SejtRacs.szamlalo);
        JatekAblak.jatekgrafika.tablafissites();
    }

    /**
     * Elmenti az éppen aktuális állapotát a tervezőrácsnak a program mappájába
     * A mentett fájl neve mindig mentettpalya.txt lesz
     * @throws IOException
     */
    public void mentes() throws IOException
    {
        SejtRacs sejtek = SejtRacs.tablak.get(SejtRacs.szamlalo);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("mentettpalya.txt"));
        oos.writeObject(sejtek);
        oos.close();
    }

    /**
     * Betölti a mentett páylát a mentettpalya.txt-ből
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public void betoltes() throws IOException, ClassNotFoundException, Exception
    {
        SejtRacs racsbetoltes = null;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("mentettpalya.txt"));
        racsbetoltes = (SejtRacs) in.readObject();
        in.close();

        new JatekAblak(racsbetoltes.magassag, racsbetoltes.szelesseg);
        SejtRacs.szamlalo++;
        tablak.add(racsbetoltes);
        System.out.println(SejtRacs.szamlalo);
        JatekAblak.jatekgrafika.tablafissites();
    }
}
