package AJatek;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A játék indításakor megjelenő ablak grafikai felületéért, az ezen való kattintások kezeléséért,
 * és a hiba/figyelmeztető üzenetekért felelős osztály
 */
public class InduloAblak extends JFrame 
{

    public static InduloAblak indulgrafika;
    private final JButton Kesz;
    private final JTextField magassagmezo;
    private final JTextField szelessegmezo;
    private final JButton betoltes;
    public JButton segitseg;

    /**
     * Ez a függvény hívódik meg a main-ből, és ez felel az első ablak grafikai részéért, bezárásáért,
     * innen hívódnak meg a kattintásra reagáló függvények is
     */
    public InduloAblak() {
        super("Wireworld");
        indulgrafika = this;
        JPanel megnyitas = new JPanel();
        this.add(megnyitas);

        magassagmezo = new JTextField("100", 6);
        szelessegmezo = new JTextField("100", 6);

        JLabel hosszu = new JLabel("Munkafuzet szelessege es magassaga:");
        JLabel magassagfelirat = new JLabel("Magassag");
        JLabel szelessegfelirat = new JLabel("Szelesseg");

        megnyitas.add(hosszu);
        megnyitas.add(magassagfelirat);
        megnyitas.add(magassagmezo);
        megnyitas.add(szelessegfelirat);
        megnyitas.add(szelessegmezo);
        betoltes = new JButton("Betoltes fajlbol");
        segitseg = new JButton("Segitseg!");
        Kesz = new JButton("Kesz");
        Kesz.setSize(180, 30);
        megnyitas.add(Kesz);
        megnyitas.add(betoltes);
        megnyitas.add(segitseg);
        GombFigyelo gombfigyelo = new GombFigyelo();
        Kesz.addActionListener(gombfigyelo);
        betoltes.addActionListener(gombfigyelo);
        segitseg.addActionListener(gombfigyelo);
        this.setSize(300, 120);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }

    /**
     * A hibaüzenetek megjelenítésére szolgáló függvény
     * @param uzenet a hibához tartozó üzenet szövege
     */
    public void figyelmeztetes(String uzenet) 
    {
        JFrame keret;
        keret = new JFrame("Informacio");
        JOptionPane.showMessageDialog(keret, uzenet);
    }

    /**
     * A kattintásokhoz tartozó gombok műveleteinek végrahajtását végzi.
     * Figyeli, hogy a szélességet és a magasságot helyesen adta-e meg a felhasználó, ha nem, akkor hibaüzenetet dob
     * Bezárja az induló ablakot, ha olyan gombra kattintottunk és meghívja a tervező felület ablakát
     */
    private class GombFigyelo implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent g) 
        {
            int h = 5001;
            int w = 5001;
            try 
            {
                h = Integer.parseInt(magassagmezo.getText());
                w = Integer.parseInt(szelessegmezo.getText());
            } catch (Exception ex) {
                figyelmeztetes("Kerem egy szamot adjon meg ");
            }
            if (g.getSource() == Kesz) 
            {
                if (h > 5001 || w > 5001) 
                {
                    figyelmeztetes("A munkafuzet merete maximum 5000*5000 lehet");
                }
                else if (h < 0 || w < 0)
                {
                    figyelmeztetes("Kerem pozitv szamot adjon meg");
                }
                else if (h == 0 || w == 0)
                {
                    figyelmeztetes("On 0-at adott meg magassagnak es/vagy szelessegnek, kerem egy pozitiv szamot adjon meg");
                }
                else if (h < 10 || w < 10)
                {
                    figyelmeztetes("A munkafuzet nem lehet kisebb, mint 10 * 10");
                }
                else if (h < 5001 && w < 5001)
                {
                    try {
                        new JatekAblak(h, w);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    new SejtRacs(h, w);
                    setVisible(false);
                    dispose();
                }
            }

            if (betoltes == g.getSource()) 
            {
                Fajlkezelo mentesolvasas = new Fajlkezelo();
                try {
                    mentesolvasas.betoltes();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                setVisible(false);
                dispose();
            }

            if(g.getSource() == segitseg)
            {
                Fajlkezelo segit = new Fajlkezelo();
                try {
                    segit.segito();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                setVisible(false);
                dispose();
            }
        }
    }
}
