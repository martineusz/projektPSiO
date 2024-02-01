package code.InterfejsGraficzny;

import code.Main.Sklep;
import code.Obserwator.Promocja;
import code.Obserwator.PromocjaLogika;
import code.Produkt.Produkt;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UsunPromocjeGUI {
    private Sklep sklep;
    private JFrame ramka;
    private JButton returnButton;
    public static void openGUI(Sklep sklep, JFrame ramka){
        UsunPromocjeGUI GUI = new UsunPromocjeGUI();
        GUI.usunPromocjeGUI(sklep, ramka);
    }
    public void usunPromocjeGUI(Sklep sklep, JFrame ramka){
        this.sklep = sklep;
        this.ramka = ramka;
        JPanel panelGlowny = new JPanel();
        panelGlowny.setLayout(new BorderLayout());

        //return button
        JPanel panelGora = new JPanel();
        panelGora.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelGora.setBackground(new Color(255, 69, 0));

        JLabel labelNazwa = new JLabel("USUŃ PROMOCJE");
        labelNazwa.setFont(new Font("Serif", Font.BOLD, 17));
        labelNazwa.setForeground(Color.WHITE);


        returnButton = new JButton();
        returnButton.setIcon(SklepGUI.scaleIcon("src\\resources\\Obrazki\\cofnij.png", 50));
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ramka.getContentPane().removeAll();
                ramka.revalidate();
                ramka.repaint();
                ramka.setLayout(null);
                GUIadmin.openAdmin(sklep, ramka);
            }
        });
        returnButton.setFocusPainted(false);
        returnButton.setOpaque(false);
        returnButton.setBackground(new Color(250, 246, 246, 0));
        returnButton.setBounds(10,10,50,50);

        panelGora.add(returnButton);
        panelGora.add(labelNazwa);
        panelGlowny.add(BorderLayout.NORTH, panelGora);


        JPanel panele = new JPanel();
        panele.setLayout(new GridLayout(0,1,10,10));
        JScrollPane scrollPane = new JScrollPane(panele);
        scrollPane.setSize(800,400);

        if(PromocjaLogika.promocje.size()==0){
            JLabel brakPromocji = new JLabel("Obecnie nie ma żadnych promocji");
            brakPromocji.setFont(new Font("Serif", Font.BOLD, 30));
            brakPromocji.setHorizontalAlignment(SwingConstants.CENTER);
            panelGlowny.add(BorderLayout.CENTER, brakPromocji);
        }
        for(Promocja i : PromocjaLogika.promocje){
            panele.add(new panelPromocja(i));
        }
        panelGlowny.add(BorderLayout.CENTER, scrollPane);

        ramka.setLayout(null);
        ramka.setContentPane(panelGlowny);
        ramka.setVisible(true);
    }
    class panelPromocja extends JPanel{
        private Promocja promocja;
        public panelPromocja(Promocja promocja){
            this.promocja = promocja;

            setLayout(new BorderLayout());
            setBackground(Color.LIGHT_GRAY);
            setPreferredSize(new Dimension(800,200));

            //nazwa promocji
            JLabel nazwaPromocji = new JLabel(promocja.getNazwa());
            nazwaPromocji.setFont(new Font("Serif", Font.BOLD, 17));
            nazwaPromocji.setHorizontalAlignment(SwingConstants.CENTER);

            add(BorderLayout.NORTH, nazwaPromocji);

            String[] naglowekProdukty = {"NAZWA", "WARTOSC PRZED ZNIZKA", "WARTOSC PO ZNIZCE"};
            Object[][] daneProdukty = new Object[promocja.getProdukty().size()][3];

            for(int i=0; i<promocja.getProdukty().size(); i++){
                daneProdukty[i][0] =promocja.getProdukty().get(i).getNazwa();
                daneProdukty[i][1] = (float) (Math.floor(promocja.getProdukty().get(i).getCena()/(1-promocja.getObnizka()) * 100.0) / 100.0);
                daneProdukty[i][2] = promocja.getProdukty().get(i).getCena();
            }

            DefaultTableModel modelProdukty = new DefaultTableModel(daneProdukty, naglowekProdukty);

            JTable tabelaProduktow = new JTable(modelProdukty);
            JScrollPane scrollProdukty = new JScrollPane(tabelaProduktow);

            add(BorderLayout.CENTER, scrollProdukty);

            //button usuwania
            JPanel usunContainer = new JPanel();

            JButton buttonUsun = new JButton("USUN PROMOCJE");
            buttonUsun.setBackground(Color.WHITE);
            buttonUsun.setFocusPainted(false);
            buttonUsun.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PromocjaLogika.usunPromocje(promocja.getNazwa());
                    ramka.getContentPane().removeAll();
                    ramka.revalidate();
                    ramka.repaint();
                    ramka.setLayout(null);
                    UsunPromocjeGUI.openGUI(sklep, ramka);
                }
            });
            buttonUsun.setPreferredSize(new Dimension(200,50));
            usunContainer.add(buttonUsun);
            add(BorderLayout.EAST, usunContainer);
        }
    }
}

