package InterfejsGraficzny;

import Main.Sklep;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIadmin implements ActionListener {
    private Sklep sklep;
    private JFrame ramka;


    private JPanel panelGlowny;
    private JButton dodajPromocjeButton;
    private JButton dodajProduktButton;
    private JButton usunProduktButton;
    private JButton powrotButton;


    public static void openAdmin(Sklep sklep, JFrame ramka){
        GUIadmin GUI = new GUIadmin();
        GUI.adminMenu(sklep, ramka);
    }
    public void adminMenu(Sklep sklep, JFrame ramka){
        this.sklep=sklep;
        this.ramka=ramka;

        panelGlowny = new JPanel();
        panelGlowny.setLayout(new BorderLayout());

        dodajPromocjeButton = new JButton("DODAJ PROMOCJE");
        dodajProduktButton = new JButton("DODAJ PRODUKT");
        usunProduktButton = new JButton("USUN PRODUKT");
        powrotButton = new JButton("POWROT");

        dodajPromocjeButton.addActionListener(this);
        dodajProduktButton.addActionListener(this);
        usunProduktButton.addActionListener(this);
        powrotButton.addActionListener(this);

        dodajPromocjeButton.setBackground(Color.WHITE);
        dodajPromocjeButton.setMargin(new Insets(10, 20, 10, 20));

        dodajProduktButton.setBackground(Color.WHITE);
        dodajProduktButton.setMargin(new Insets(10, 20, 10, 20));

        usunProduktButton.setBackground(Color.WHITE);
        usunProduktButton.setMargin(new Insets(10, 20, 10, 20));

        powrotButton.setBackground(Color.WHITE);
        powrotButton.setMargin(new Insets(10, 20, 10, 20));

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(4,1, 20, 20));
        panel.setBorder(new EmptyBorder(30,30,30,30));

        panel.add(dodajPromocjeButton);
        panel.add(dodajProduktButton);
        panel.add(usunProduktButton);
        panel.add(powrotButton);

        panelGlowny.add(BorderLayout.CENTER, panel);
        panel.add(dodajPromocjeButton);
        panel.add(dodajProduktButton);
        panel.add(usunProduktButton);
        panel.add(powrotButton);

        ramka.setLayout(null);
        ramka.setContentPane(panelGlowny);
        ramka.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == dodajPromocjeButton){
            ramka.getContentPane().removeAll();
            ramka.revalidate();
            ramka.repaint();
            ramka.setLayout(null);

            DodajPromocjeGUI.openGUI(sklep, ramka);
        }
        if(e.getSource() == dodajProduktButton){
            ramka.getContentPane().removeAll();
            ramka.revalidate();
            ramka.repaint();
            ramka.setLayout(null);
            DodajProduktGUI.openGUI(sklep, ramka);
        }
        if(e.getSource() == usunProduktButton){
            ramka.getContentPane().removeAll();
            ramka.revalidate();
            ramka.repaint();
            ramka.setLayout(null);

            UsunProduktGUI.openGUI(sklep, ramka);
        }
        if(e.getSource() == powrotButton){
            ramka.getContentPane().removeAll();
            ramka.revalidate();
            ramka.repaint();
            ramka.setLayout(null);
            Rejestracja.ShopPage(sklep, ramka);
        }
    }
}
