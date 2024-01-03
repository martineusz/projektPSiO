package Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIadmin implements ActionListener {
    private Sklep sklep;


    private JFrame ramka;
    private JButton dodajPromocjeButton;
    private JButton dodajProduktButton;
    private JButton usunProduktButton;
    private JButton powrotButton;


    public static void openAdmin(Sklep sklep){
        GUIadmin GUI = new GUIadmin();
        GUI.adminMenu(sklep);
    }
    public void adminMenu(Sklep sklep){
        this.sklep=sklep;
        ramka = new JFrame();

        dodajPromocjeButton = new JButton("DODAJ PROMOCJE");
        dodajProduktButton = new JButton("DODAJ PRODUKT");
        usunProduktButton = new JButton("USUN PRODUKT");
        powrotButton = new JButton("POWROT");

        dodajPromocjeButton.addActionListener(this);
        dodajProduktButton.addActionListener(this);
        usunProduktButton.addActionListener(this);
        powrotButton.addActionListener(this);

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(4,1, 20, 20));
        panel.setBorder(new EmptyBorder(30,30,30,30));

        panel.add(dodajPromocjeButton);
        panel.add(dodajProduktButton);
        panel.add(usunProduktButton);
        panel.add(powrotButton);

        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramka.getContentPane().add(BorderLayout.CENTER, panel);



        panel.add(dodajPromocjeButton);
        panel.add(dodajProduktButton);
        panel.add(usunProduktButton);
        panel.add(powrotButton);


        ramka.setSize(300,600);
        ramka.pack();
        ramka.setResizable(false);
        ramka.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == dodajPromocjeButton){
            ramka.dispose();
            DodajPromocjeGUI.openGUI(sklep);
        }
        if(e.getSource() == dodajProduktButton){
            ramka.dispose();
            DodajProduktGUI.openGUI(sklep);
        }
        if(e.getSource() == usunProduktButton){
            ramka.dispose();
            UsunProduktGUI.openGUI(sklep);
        }
        if(e.getSource() == powrotButton){
            ramka.dispose();
        }
    }
}
