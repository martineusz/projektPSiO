package InterfejsGraficzny;

import Produkt.*;
import Main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KoszykFrame extends JFrame implements ActionListener {
    JButton dalej;
    Koszyk koszyk;
    JPanel panelKoszyk;
    Map<JButton, Produkt> buttonProduktMap;

    KoszykFrame(Koszyk koszyk){
        this.koszyk = koszyk;
        JPanel panelGorny = new JPanel();
        JPanel panelGlowny = new JPanel();
        JPanel panelDolny = new JPanel();
        panelKoszyk = new JPanel();
        JPanel panelPodsumowanie = new JPanel();
        buttonProduktMap = new HashMap<>();
        ImageIcon koszykImage = new ImageIcon("src/Obrazki/koszyk.png");


        //MOJ KOSZYK label
        JLabel mojKoszyk = new JLabel();
        mojKoszyk.setText("MOJ KOSZYK");
        mojKoszyk.setIcon(koszykImage);
        mojKoszyk.setHorizontalTextPosition(JLabel.RIGHT);
        mojKoszyk.setVerticalTextPosition(JLabel.CENTER);
        mojKoszyk.setBounds(25,0,200,100);
        mojKoszyk.setFont(new Font(null, Font.BOLD, 20));


        //PANEL GORNY
        panelGorny.setPreferredSize(new Dimension(0,150));
        panelGorny.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //PANEL GLOWNY
        panelGlowny.setPreferredSize(new Dimension(0,750));
        panelGlowny.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelGlowny.setLayout(null);

        panelGlowny.add(mojKoszyk);
        panelGlowny.add(panelKoszyk);
        panelGlowny.add(panelPodsumowanie);

        //PANEL KOSZYK
        panelKoszyk.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelKoszyk.setBounds(100,105,600,450);

        //PANEL PRODUKT
        for (int i = 0; i < koszyk.getListaProduktow().size(); i++) {
            JButton buttonUsun = new JButton();
            buttonUsun.setText("X");
            buttonUsun.setPreferredSize(new Dimension(25,25));
            buttonUsun.addActionListener(this);

            Produkt produkt = koszyk.getListaProduktow().get(i);

            JLabel labelNazwa = new JLabel();
            labelNazwa.setText("CENA: " + String.valueOf(produkt.getCena()));
            labelNazwa.setFont(new Font(null, Font.BOLD, 20));

            JLabel labelCena = new JLabel();
            labelCena.setText("NAZWA: " + produkt.getNazwa());
            labelCena.setFont(new Font(null, Font.BOLD, 20));

            JPanel panelProdukt = new JPanel();
            panelProdukt.setLayout(new BoxLayout(panelProdukt, BoxLayout.Y_AXIS));
            panelProdukt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panelProdukt.setPreferredSize(new Dimension(500,100));

            panelProdukt.add(buttonUsun);
            panelProdukt.add(labelCena);
            panelProdukt.add(labelNazwa);
            panelKoszyk.add(panelProdukt);

            buttonProduktMap.put(buttonUsun, produkt);
        }

        //PANEL PODSUMOWANIE
        panelPodsumowanie.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelPodsumowanie.setBounds(1000,105,400,450);


        //PANEL DOLNY
        panelDolny.setPreferredSize(new Dimension(0, 180));
        panelDolny.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.add(panelGorny);
        this.add(panelGlowny);
        this.add(panelDolny);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(new Dimension(1980, 1080));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Koszyk");
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton sourceButton = (JButton) e.getSource();
        Produkt produktToRemove = buttonProduktMap.get(sourceButton);

        if (produktToRemove != null) {
            koszyk.getListaProduktow().remove(produktToRemove);
            panelKoszyk.remove(sourceButton.getParent()); // Usunięcie całego panelu produktu
            panelKoszyk.revalidate();
            panelKoszyk.repaint();
        }

    }

    public static void main(String[] args) {
        Sklep sklep = new Sklep(new ArrayList<Klient>(), null, false, new ArrayList<Produkt>());
        sklep.wczytajListeProduktow();
        sklep.wczytajListeKlientow();

        sklep.zalogowanyKlient = sklep.getListaKlientow().get(0);
        sklep.zalogowanyKlient.getKoszyk().dodajProdukt(sklep.getListaProduktow().get(0));
        sklep.zalogowanyKlient.getKoszyk().dodajProdukt(sklep.getListaProduktow().get(1));

            if (sklep.zalogowanyKlient.getKoszyk().getListaProduktow().size() == 0) {
                System.out.println("PUSTY KOSZYK");
            } else {
                for (int i = 0; i < sklep.zalogowanyKlient.getKoszyk().getListaProduktow().size(); i++) {
                    System.out.println((i+1) + ".");
                    System.out.println(sklep.zalogowanyKlient.getKoszyk().getListaProduktow().get(i).toString());
                }
            }

        new KoszykFrame(sklep.zalogowanyKlient.getKoszyk());
    }
}



