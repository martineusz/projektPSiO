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
    double sumaCen;
    JLabel labelSumaCen;
    JPanel panelPodsumowanie;
    Koszyk koszyk;
    JPanel panelKoszyk;
    JPanel panelDostawa;
    Map<JButton, Produkt> buttonProduktMap;
    JButton buttonZamowienie;
    JButton buttonDostawa;
    JCheckBox boxPaczkomat;
    JCheckBox boxKurier;
    JPanel panelGlowny;
    JScrollPane scrollPane;
    //text filedy
    JTextField textImie;
    JTextField textNazwisko;
    JTextField textUlica;
    JTextField textNumerDomu;
    JTextField textKodPocztowy;
    JTextField textMiejscowosc;
    JTextField textKraj;
    JTextField textNumerTelefonu;
    JTextField textEmail;

    JLabel labelImie;
    JLabel labelNazwisko;
    JLabel labelUlica;
    JLabel labelNumerDomu;
    JLabel labelKodPocztowy;
    JLabel labelMiejscowosc;
    JLabel labelKraj;
    JLabel labelNumerTelefonu;
    JLabel labelEmail;

    KoszykFrame(Koszyk koszyk) {
        this.koszyk = koszyk;
        JPanel panelGorny = new JPanel();
        panelGlowny = new JPanel();
        JPanel panelDolny = new JPanel();
        panelKoszyk = new JPanel();
        panelDostawa = new JPanel();
        panelPodsumowanie = new JPanel();
        buttonProduktMap = new HashMap<>();
        ImageIcon koszykImage = new ImageIcon("src/Obrazki/koszyk.png");

//        TEXT FIELDY
        textImie = new JTextField();
        textImie.setPreferredSize(new Dimension(250,40));
        textImie.setBounds(10,100,250,40);
        panelDostawa.add(textImie);
        labelImie = new JLabel();
        labelImie.setBounds(10,85,50,10);
        labelImie.setText("IMIĘ");
        panelDostawa.add(labelImie);


        textNazwisko = new JTextField();
        textNazwisko.setPreferredSize(new Dimension(250,40));
        textNazwisko.setBounds(10,175,250,40);
        panelDostawa.add(textNazwisko);
        labelNazwisko = new JLabel();
        labelNazwisko.setBounds(10,160,150,10);
        labelNazwisko.setText("NAZWISKO");
        panelDostawa.add(labelNazwisko);

        textUlica = new JTextField();
        textUlica.setPreferredSize(new Dimension(250,40));
        textUlica.setBounds(10,250,150,40);
        panelDostawa.add(textUlica);
        labelUlica = new JLabel();
        labelUlica.setBounds(10,235,50,10);
        labelUlica.setText("ULICA");
        panelDostawa.add(labelUlica);

        textNumerDomu = new JTextField();
        textNumerDomu.setPreferredSize(new Dimension(250,40));
        textNumerDomu.setBounds(180,250,80,40);
        panelDostawa.add(textNumerDomu);
        labelNumerDomu = new JLabel();
        labelNumerDomu.setBounds(195,235,150,10);
        labelNumerDomu.setText("NR. DOMU");
        panelDostawa.add(labelNumerDomu);

        textKodPocztowy = new JTextField();
        textKodPocztowy.setPreferredSize(new Dimension(250,40));
        textKodPocztowy.setBounds(10,325,80,40);
        panelDostawa.add(textKodPocztowy);
        labelKodPocztowy = new JLabel();
        labelKodPocztowy.setBounds(10,310,150,10);
        labelKodPocztowy.setText("KOD POCZTOWY");
        panelDostawa.add(labelKodPocztowy);

        textMiejscowosc = new JTextField();
        textMiejscowosc.setPreferredSize(new Dimension(250,40));
        textMiejscowosc.setBounds(110,325,150,40);
        panelDostawa.add(textMiejscowosc);
        labelMiejscowosc = new JLabel();
        labelMiejscowosc.setBounds(170,310,150,10);
        labelMiejscowosc.setText("MIEJSCOWOŚĆ");
        panelDostawa.add(labelMiejscowosc);

        textKraj = new JTextField();
        textKraj.setPreferredSize(new Dimension(250,40));
        textKraj.setBounds(280,100,250,40);
        panelDostawa.add(textKraj);
        labelKraj = new JLabel();
        labelKraj.setBounds(280,85,50,10);
        labelKraj.setText("KRAJ");
        panelDostawa.add(labelKraj);

        textNumerTelefonu = new JTextField();
        textNumerTelefonu.setPreferredSize(new Dimension(250,40));
        textNumerTelefonu.setBounds(280,175,250,40);
        panelDostawa.add(textNumerTelefonu);
        labelNumerTelefonu = new JLabel();
        labelNumerTelefonu.setBounds(280,160,150,10);
        labelNumerTelefonu.setText("NR. TELEFONU");
        panelDostawa.add(labelNumerTelefonu);

        textEmail = new JTextField();
        textEmail.setPreferredSize(new Dimension(250,40));
        textEmail.setBounds(280,250,250,40);
        panelDostawa.add(textEmail);
        labelEmail = new JLabel();
        labelEmail.setBounds(280,235,50,10);
        labelEmail.setText("EMAIL");
        panelDostawa.add(labelEmail);

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
        panelGlowny.add(panelDostawa);
        panelGlowny.add(panelPodsumowanie);



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

        //PANEL KOSZYK
        panelKoszyk.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelKoszyk.setPreferredSize(new Dimension(600, panelKoszyk.getComponentCount() * 110));

        scrollPane = new JScrollPane(panelKoszyk);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(100, 105, 600, 450);
        panelGlowny.add(scrollPane);

        //PANEL DOSTAWA
        panelDostawa.setBorder(BorderFactory.createLineBorder(Color.red));
        panelDostawa.setSize(new Dimension(600, 450));
        panelDostawa.setBounds(100,105,600, 450);
        panelDostawa.setLayout(null);
        panelDostawa.setVisible(false);
        panelGlowny.add(panelDostawa);




        //PANEL PODSUMOWANIE
        panelPodsumowanie.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelPodsumowanie.setBounds(1000,105,400,450);
        panelPodsumowanie.setLayout(null);

        //BUTTON PODSUMOWANIE
        buttonZamowienie = new JButton();
        buttonZamowienie.setText("ZŁÓŻ ZAMÓWIENIE");
        buttonZamowienie.setPreferredSize(new Dimension(200,50));
        buttonZamowienie.setBounds(0,300,400,80);
        buttonZamowienie.addActionListener(this);
        //buttonZamowienie.setFont(new Font(null));
        panelPodsumowanie.add(buttonZamowienie);

        //BUTTON DOSTAWA
        buttonDostawa = new JButton();
        buttonDostawa.setText("FINALIZUJ DOSTAWE");
        buttonDostawa.setPreferredSize(new Dimension(200,50));
        buttonDostawa.setBounds(0,300,400,80);
        buttonDostawa.addActionListener(this);
        //buttonZamowienie.setFont(new Font(null));
        panelPodsumowanie.add(buttonDostawa);
        buttonDostawa.setVisible(false);


// CHECKBOX PACZKOMAT
        boxPaczkomat = new JCheckBox("Paczkomat");
        boxPaczkomat.setBounds(20, 20, 100, 20);
        boxPaczkomat.setFocusable(true);
        panelDostawa.add(boxPaczkomat);
        boxPaczkomat.addActionListener(this);
        boxPaczkomat.setSelected(true);
        boxPaczkomat.setEnabled(false);
// CHECKBOX KURIER
        boxKurier = new JCheckBox("Kurier");
        boxKurier.setBounds(20, 50, 100, 20);
        boxKurier.setFocusable(true);
        panelDostawa.add(boxKurier);
        boxKurier.addActionListener(this);

        //PODSUMOWANIE LABEL
        JLabel labelPodsumowanie = new JLabel();
        labelPodsumowanie.setText("PODSUMOWANIE");
        labelPodsumowanie.setBounds(25,0,200,100);
        labelPodsumowanie.setFont(new Font(null, Font.BOLD, 20));
        panelPodsumowanie.add(labelPodsumowanie);

        //SUMA CEN PRODUKTOW Z KOSZYKA LABELLLL
        labelSumaCen = new JLabel();
        sumaCen = 0;

        for (int i = 0; i < koszyk.getListaProduktow().size(); i++) {
            sumaCen+=koszyk.getListaProduktow().get(i).getCena();
        }

        labelSumaCen.setText("SUMA: " + sumaCen);
        labelSumaCen.setBounds(25,50,200,100);
        labelSumaCen.setFont(new Font(null, Font.BOLD, 20));
        panelPodsumowanie.add(labelSumaCen);



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
        if (e.getSource() == boxPaczkomat) {
            if (boxPaczkomat.isSelected()) {
                boxKurier.setSelected(false); // Jeśli paczkomat jest zaznaczony, odznacz kuriera
                boxKurier.setFocusable(true);
                boxKurier.setEnabled(true);
                boxPaczkomat.setFocusable(false);
                boxPaczkomat.setEnabled(false);
                sumaCen += 4;
                labelSumaCen.setText(String.valueOf("SUMA: " + sumaCen));
            }
        } else if (e.getSource() == boxKurier) {
            if (boxKurier.isSelected()) {
                boxPaczkomat.setSelected(false); // Jeśli kurier jest zaznaczony, odznacz paczkomat
                boxPaczkomat.setFocusable(true);
                boxPaczkomat.setEnabled(true);
                boxKurier.setFocusable(false);
                boxKurier.setEnabled(false);
                sumaCen -= 4;
                labelSumaCen.setText(String.valueOf("SUMA: " + sumaCen));
            }
        }

        if(e.getSource() instanceof JButton){
            JButton sourceButton = (JButton) e.getSource();
            Produkt produktToRemove = buttonProduktMap.get(sourceButton);

            if (produktToRemove != null) {
                sumaCen -= produktToRemove.getCena();
                koszyk.getListaProduktow().remove(produktToRemove);
                panelKoszyk.remove(sourceButton.getParent()); // Usunięcie całego panelu produktu
                panelKoszyk.revalidate();
                panelKoszyk.repaint();
                labelSumaCen.setText(String.valueOf("SUMA: " + sumaCen));
            }
            if(e.getSource() == buttonZamowienie) {
                panelKoszyk.setVisible(false);
                buttonDostawa.setVisible(true);
                panelKoszyk.revalidate();
                panelKoszyk.repaint();
                panelDostawa.setVisible(true);
                panelDostawa.revalidate();
                panelDostawa.repaint();
                buttonZamowienie.setVisible(false);
                scrollPane.setVisible(false);
                sumaCen += 15.99;
                labelSumaCen.setText(String.valueOf("SUMA: " + sumaCen));
            }
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


