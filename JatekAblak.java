package AJatek;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * A játék fő ablakának összes grafikai eleméért, az ezeken való kattintásokért, a szimuláció futtatásáért,
 * elindiításáért, megállításáért és léptetésért felelős osztály
 */

public class JatekAblak extends JFrame 
{

    public static JatekAblak jatekgrafika;
    
    int l;
    
    private final Timer idozito;
   
    public int sejtmeretei = 14;
    public int szelesseg;
    public int magassag;

    private final Leptetes kovetkezoszimulacio;
    private final JButton elozolepes;
    private final JButton torles;
    private final JButton szimulaciok;

    public JButton[][] gombsejt;
    private final JLabel sorszam;
    private final JButton kovetkezolepes;
    private final JButton allj;
    public JButton mentes;
    public JButton elsoallapot;

    /**
     * Létrehozza a fő ablakot, ezen a gombokat, a gombokból álló tervezőrácsot, a görgetőket, beállítja a színeket
     * majd láthatóvá és módosítható méretűvé teszi
     * @param h a tervezőrács celláinak száma vertikálisan
     * @param w a tervezőrács celláinak száma vízszintesen
     * @throws Exception
     */
    public JatekAblak(int h, int w) throws Exception 
    {
        super("Wireworld");
        
        idozito = new Timer(350, new Futtatas());

        JatekAblak.jatekgrafika = this;
        szelesseg = w;
        magassag = h;
        if (w > 100 || h > 100) 
        {
            sejtmeretei = 10;
        }
        if (w > 150 || h > 150) 
        {
            sejtmeretei = 9;
        }
        JPanel gombokpanel = new JPanel();
        JPanel sejtekpanel = new JPanel();
        JPanel sejtracs = new JPanel();
        kovetkezoszimulacio = new Leptetes();
        kovetkezolepes = new JButton("Kovetkezo Allapot");
        elozolepes = new JButton("Elozo allapot");
        elsoallapot = new JButton("0-dik allapot");
        allj = new JButton("   Allj    ");
        torles = new JButton("Munkafuzet torlese");
        szimulaciok = new JButton("Inditas");
        sorszam = new JLabel("Lepes szama: 0");
        mentes = new JButton("Mentes");
       
        gombokpanel.setPreferredSize(new Dimension(230, 950));
        sejtekpanel.setPreferredSize(new Dimension(950, 950));

        this.add(gombokpanel, BorderLayout.EAST);

        gombokpanel.setBackground(Color.WHITE);
        sejtekpanel.setBackground(Color.WHITE );

        sejtekpanel.add(sejtracs, BorderLayout.WEST);

        JScrollPane gorgetes = new JScrollPane(sejtekpanel);
        int ymeret = 3800;
        int xmeret = 3800;
        sejtekpanel.setPreferredSize(new Dimension(xmeret, ymeret));
        gorgetes.getViewport().setViewPosition(new java.awt.Point(1400, 0));
        this.getContentPane().add(gorgetes);

        gombokpanel.add(torles);
        torles.setPreferredSize(new Dimension(220, 40));
        gombokpanel.add(allj);
        gombokpanel.add(szimulaciok);
        gombokpanel.add(elozolepes);
        gombokpanel.add(elsoallapot);
        gombokpanel.add(sorszam);
        gombokpanel.add(kovetkezolepes);
        kovetkezolepes.setPreferredSize(new Dimension(220, 40));
        gombokpanel.add(mentes, BorderLayout.SOUTH);
        mentes.setPreferredSize(new Dimension(220, 40));
       
        GombKezelo kezelo = new GombKezelo();
        Kurzorkezelo kattintaskezelo = new Kurzorkezelo();
        torles.addActionListener(kezelo);
        szimulaciok.addActionListener(kezelo);
        elozolepes.addActionListener(kezelo);
        elsoallapot.addActionListener(kezelo);
        kovetkezolepes.addActionListener(kezelo);
        allj.addActionListener(kezelo);
        mentes.addActionListener(kezelo);

        gombsejt = new JButton[h + 2][w + 2];
        sejtracs.setLayout(new GridLayout(h + 2, w + 2));
        for (int i = 0; i < h + 2; i++) 
        {
            for (int j = 0; j < w + 2; j++) 
            {
                gombsejt[i][j] = new JButton("");
                gombsejt[i][j].addMouseListener(kattintaskezelo);
                sejtracs.add(gombsejt[i][j]);
                gombsejt[i][j].setPreferredSize(new Dimension(sejtmeretei, sejtmeretei));
                gombsejt[i][j].setBackground(Color.WHITE);
            }
        }
        for (int i = 0; i < w + 2; i++) 
        {
            gombsejt[0][i].setVisible(false);
            gombsejt[h + 1][i].setVisible(false);
        }
        for (int i = 0; i < h + 2; i++) 
        {
            gombsejt[i][0].setVisible(false);
            gombsejt[i][w + 1].setVisible(false);
        }
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
    }

    /**
     * Frissíti a tervezőrácsot, és beáálítja a cellákhoz kiszámolt állapotnak megfelelő színt, fentről lefelé a
     * sorokat, majd ebben balról jobbra a cellákat
     */
    public void tablafissites() 
    {
        SejtRacs sejtracs = SejtRacs.tablak.get(SejtRacs.szamlalo);
        int v = 0;
        for (int i = 1; i < sejtracs.getMagassag() + 1; i++)
        {
            for (int j = 1; j < sejtracs.getSzelesseg() + 1; j++)
            {
                v = sejtracs.getSejt(i, j).getErtek();

                switch (v) 
                {
                    case (1):
                        gombsejt[i][j].setBackground(Color.BLACK);
                        break;
                    case (2):
                        gombsejt[i][j].setBackground(Color.ORANGE);
                        break;
                    case (3):
                        gombsejt[i][j].setBackground(Color.RED);
                        break;
                    case (0):
                        gombsejt[i][j].setBackground(Color.WHITE);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * Annak a sejtnek az állapotát változtatja, amire kattintottunk
     */
    private class Kurzorkezelo implements MouseListener 
    {

        boolean helyzet = true;

        @Override
        public void mouseClicked(MouseEvent me) 
        {
            JButton forras = (JButton) me.getSource();
            EgySejt egyset = new EgySejt();
            egyset.put(forras, helyzet);
        }

        @Override
        public void mousePressed(MouseEvent me) {}

        @Override
        public void mouseReleased(MouseEvent me) {}

        @Override
        public void mouseEntered(MouseEvent me) {}

        @Override
        public void mouseExited(MouseEvent me) {}
    }

    /**
     * A gombokra való kattintásnak megfelelően elvégzi az alábbi műveleteket:
     * a tábla törlése
     * előző állapotba lépés
     * 0. állapotba lépés
     * következő állapotba lépés
     * Elindítás
     * Megállítás
     * Mentés
     * Kiírja az aktuális lépés számát
     */
    private class GombKezelo implements ActionListener 
    {
        
        private boolean megallmegy = true;

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            SejtRacs sejtracs = SejtRacs.tablak.get(SejtRacs.szamlalo);
            if (e.getSource() == torles) 
            {
                sejtracs.torlo();
                SejtRacs.szamlalo = 0;
                jatekgrafika.tablafissites();
            }
            if (e.getSource() == elozolepes) 
            {
                if (SejtRacs.szamlalo > 0) 
                {
                    SejtRacs.szamlalo--;
                    jatekgrafika.tablafissites();
                }
            }

            if(e.getSource() == elsoallapot)
            {
                SejtRacs.szamlalo = 0;
                jatekgrafika.tablafissites();
            }

            if (e.getSource() == kovetkezolepes) 
            {
                new SejtRacs(magassag, szelesseg);
                kovetkezoszimulacio.letrehozo();
                jatekgrafika.tablafissites();
            }
            if (e.getSource() == allj) 
            {
                idozito.stop();
            }
            if (e.getSource() == szimulaciok) 
            {
                    idozito.start();
            }
            if (e.getSource() == mentes) 
            {
                Fajlkezelo mentes = new Fajlkezelo();

                try {
                    mentes.mentes();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            sorszam.setText("Lepes szama " + SejtRacs.szamlalo);
        }
    }

    /**
     * A futtatásért, a lépések között eltelt idő hosszáért, a tábla frissítéséért felelős függvény
     */
    private class Futtatas implements ActionListener
    {
		@Override
		public void actionPerformed(ActionEvent e) 
		{
            new SejtRacs(magassag, szelesseg);
            kovetkezoszimulacio.letrehozo();
            jatekgrafika.tablafissites();
            sorszam.setText("Lepes szama " + SejtRacs.szamlalo);
		}
    }
}
