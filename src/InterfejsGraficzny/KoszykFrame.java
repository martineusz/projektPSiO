package InterfejsGraficzny;

import DostawaStrategia.DostawaKurier;
import DostawaStrategia.DostawaPaczkomat;
import PlacenieStrategia.placBlikiem;
import PlacenieStrategia.placKarta;
import Produkt.*;
import Main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KoszykFrame extends JPanel implements ActionListener {
    Map<JButton, Produkt> buttonProduktMap;
    Map<JComboBox, Produkt> comboProduktMap;
    ArrayList<JComboBox> comboList;
    double cenaKoszyk =0;
    double cenaZaWszystko = 0;
    double dostawaCena = 0;
    String[] opcjeWyboru;
    String numerTelefonu;
    String Email;
    String adres;
    JLabel labelSumaCen;
    JLabel labelCenaKoszyka;
    JLabel labelCenaDostawy;
    JPanel panelPodsumowanie;
    Koszyk koszyk;
    Sklep sklep;
    JPanel panelKoszyk;
    JPanel panelDostawa;
    JButton buttonZamowienie;
    JButton buttonDostawa;
    JButton buttonPlatnosc;
    JButton cofnijDostawa;
    JButton cofnijPlatnosc;
    JButton cofnijKoszyk;
    JButton buttonOpcjeWyobru;
    JCheckBox boxPaczkomat;
    JCheckBox boxKurier;
    JCheckBox boxBlik;
    JCheckBox boxKarta;
    JPanel panelGlowny;
    JPanel panelGorny;
    JPanel panelPlatnosc;
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
    JTextField textKodBlik;
    JTextField textNumerKarty;
    JTextField textDataWygasniecia;
    JTextField textCvv;
    JTextField textKartaImie;
    JTextField textKartaNazwisko;
    JLabel labelImie;
    JLabel labelNazwisko;
    JLabel labelUlica;
    JLabel labelNumerDomu;
    JLabel labelKodPocztowy;
    JLabel labelMiejscowosc;
    JLabel labelKraj;
    JLabel labelNumerTelefonu;
    JLabel labelEmail;
    JLabel labelKodBlik;
    JLabel labelNumerKarty;
    JLabel labelDataWygasniecia;
    JLabel labelCvv;
    JLabel labelKartaImie;
    JLabel labelKartaNazwisko;
    JComboBox ComboBombo;
    ImageIcon obrazek;
    JLabel labelObrazek;
    JFrame frame;

    KoszykFrame(JFrame frame, Sklep sklep) {
        this.frame = frame;
        this.sklep = sklep;
        this.koszyk = this.sklep.zalogowanyKlient.getKoszyk();
        panelGorny = new JPanel();
        panelGlowny = new JPanel();
        JPanel panelDolny = new JPanel();
        panelKoszyk = new JPanel();
        panelDostawa = new JPanel();
        panelPodsumowanie = new JPanel();
        panelPlatnosc = new JPanel();
        buttonProduktMap = new HashMap<>();
        comboProduktMap = new HashMap<>();
        comboList = new ArrayList<>();
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

        textKodBlik = new JTextField();
        textKodBlik.setPreferredSize(new Dimension(250,40));
        textKodBlik.setBounds(20,70,250,40);
        panelPlatnosc.add(textKodBlik);
        labelKodBlik = new JLabel();
        labelKodBlik.setBounds(20,55,120,10);
        labelKodBlik.setText("KOD BLIK");
        panelPlatnosc.add(labelKodBlik);

        textNumerKarty = new JTextField();
        textNumerKarty.setPreferredSize(new Dimension(250,40));
        textNumerKarty.setBounds(20,210,250,40);
        panelPlatnosc.add(textNumerKarty);
        labelNumerKarty = new JLabel();
        labelNumerKarty.setBounds(20,195,120,10);
        labelNumerKarty.setText("NUMER KARTY");
        panelPlatnosc.add(labelNumerKarty);

        textDataWygasniecia = new JTextField();
        textDataWygasniecia.setPreferredSize(new Dimension(250,40));
        textDataWygasniecia.setBounds(20,275,150,40);
        panelPlatnosc.add(textDataWygasniecia);
        labelDataWygasniecia = new JLabel();
        labelDataWygasniecia.setBounds(20,260,120,10);
        labelDataWygasniecia.setText("DATA WYGAŚNIĘCIA");
        panelPlatnosc.add(labelDataWygasniecia);

        textCvv = new JTextField();
        textCvv.setPreferredSize(new Dimension(250,40));
        textCvv.setBounds(190,275,80,40);
        panelPlatnosc.add(textCvv);
        labelCvv = new JLabel();
        labelCvv.setBounds(190,260,120,10);
        labelCvv.setText("CVV");
        panelPlatnosc.add(labelCvv);

        textKartaImie = new JTextField();
        textKartaImie.setPreferredSize(new Dimension(250,40));
        textKartaImie.setBounds(290,210,250,40);
        panelPlatnosc.add(textKartaImie);
        labelKartaImie = new JLabel();
        labelKartaImie.setBounds(290,195,120,10);
        labelKartaImie.setText("IMIĘ");
        panelPlatnosc.add(labelKartaImie);

        textKartaNazwisko = new JTextField();
        textKartaNazwisko.setPreferredSize(new Dimension(250,40));
        textKartaNazwisko.setBounds(290,275,250,40);
        panelPlatnosc.add(textKartaNazwisko);
        labelKartaNazwisko = new JLabel();
        labelKartaNazwisko.setBounds(290,260,120,10);
        labelKartaNazwisko.setText("NAZWISKO");
        panelPlatnosc.add(labelKartaNazwisko);

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
        panelGorny.setLayout(null);

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
            buttonUsun.setBounds(450,40,30,30);
            buttonUsun.setText("X");
            buttonUsun.addActionListener(this);
            buttonUsun.setFocusable(false);
            buttonUsun.setFont(new Font(null, Font.BOLD, 20));
            buttonUsun.setBackground(Color.WHITE);
            buttonUsun.setForeground(Color.RED);
            buttonUsun.setBorder(BorderFactory.createEtchedBorder());

            Produkt produkt = koszyk.getListaProduktow().get(i);

            JLabel labelNazwa = new JLabel();
            labelNazwa.setBounds(110,45,400,40);
            labelNazwa.setText("CENA: " + produkt.getCena() + " PLN");
            labelNazwa.setFont(new Font(null, Font.BOLD, 18));

            JLabel labelCena = new JLabel();
            labelCena.setBounds(110,20,400,40);
            labelCena.setText("NAZWA: " + produkt.getNazwa());
            labelCena.setFont(new Font(null, Font.BOLD, 18));

            JPanel panelProdukt = new JPanel();
            panelProdukt.setLayout(null);
            panelProdukt.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panelProdukt.setPreferredSize(new Dimension(500,100));

            opcjeWyboru = new String[produkt.getIloscWMagazynie()];
            for (int j = 1; j <= produkt.getIloscWMagazynie(); j++) {
                opcjeWyboru[j-1] = String.valueOf(j);
            }

            comboList.add(ComboBombo = new JComboBox(opcjeWyboru));
            comboList.get(i).setBounds(400,35,40,40);
            comboList.get(i).addActionListener(this);
            comboList.get(i).setSelectedItem(0);

            obrazek = new ImageIcon();
            obrazek = produkt.getIcon(75,75);
            labelObrazek = new JLabel();
            labelObrazek.setIcon(obrazek);
            labelObrazek.setBounds(20,15,75,75);

            panelProdukt.add(labelObrazek);
            panelProdukt.add(comboList.get(i));
            panelProdukt.add(buttonUsun);
            panelProdukt.add(labelCena);
            panelProdukt.add(labelNazwa);
            panelKoszyk.add(panelProdukt);

            comboProduktMap.put(comboList.get(i), produkt);
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
        panelDostawa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelDostawa.setSize(new Dimension(600, 450));
        panelDostawa.setBounds(100,105,600, 450);
        panelDostawa.setLayout(null);
        panelDostawa.setVisible(false);
        panelGlowny.add(panelDostawa);

        //PANEL PLATNOSC
        panelPlatnosc.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelPlatnosc.setSize(new Dimension(600, 450));
        panelPlatnosc.setBounds(100,105,600, 450);
        panelPlatnosc.setLayout(null);
        panelPlatnosc.setVisible(false);
        panelGlowny.add(panelPlatnosc);

        //PANEL PODSUMOWANIE
        panelPodsumowanie.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelPodsumowanie.setBounds(1000,105,400,450);
        panelPodsumowanie.setLayout(null);

        //BUTTON PODSUMOWANIE
        buttonZamowienie = new JButton();
        buttonZamowienie.setBounds(25,300,350,80);
        buttonZamowienie.addActionListener(this);
        buttonZamowienie.setText("ZŁÓŻ ZAMÓWIENIE");
        buttonZamowienie.setFocusable(false);
        buttonZamowienie.setFont(new Font(null, Font.BOLD, 20));
        buttonZamowienie.setBackground(Color.WHITE);
        buttonZamowienie.setBorder(BorderFactory.createEtchedBorder());
        panelPodsumowanie.add(buttonZamowienie);

        //PODSUMOWANIE LABEL
        JLabel labelPodsumowanie = new JLabel();
        labelPodsumowanie.setText("PODSUMOWANIE");
        labelPodsumowanie.setBounds(75,0,400,100);
        labelPodsumowanie.setFont(new Font(null, Font.BOLD, 30));
        panelPodsumowanie.add(labelPodsumowanie);

        //KOSZYK CENA LABEL
        labelCenaKoszyka = new JLabel("KOSZYK: " + koszyk.getWartoscZamowienia() + " PLN");
        labelCenaKoszyka.setBounds(25,75,200,100);
        labelCenaKoszyka.setFont(new Font(null, Font.BOLD, 15));
        panelPodsumowanie.add(labelCenaKoszyka);

        //KOSZYK DOSTAWA CENA LABEL
        labelCenaDostawy = new JLabel("DOSTAWA" + dostawaCena + " PLN");
        labelCenaDostawy.setBounds(25,125,200,100);
        labelCenaDostawy.setFont(new Font(null, Font.BOLD, 15));
        panelPodsumowanie.add(labelCenaDostawy);

        //SUMA CEN WSZYSTKO
        labelSumaCen = new JLabel();
        labelSumaCen.setText("SUMA: " + (dostawaCena + koszyk.getWartoscZamowienia()) + " PLN");
        labelSumaCen.setBounds(25,175,200,100);
        labelSumaCen.setFont(new Font(null, Font.BOLD, 20));
        panelPodsumowanie.add(labelSumaCen);

        //BUTTON DOSTAWA
        buttonDostawa = new JButton();
        buttonDostawa.setText("FINALIZUJ DOSTAWE");
        buttonDostawa.setBounds(25,300,350,80);
        buttonDostawa.setFocusable(false);
        buttonDostawa.addActionListener(this);
        buttonDostawa.setFont(new Font(null, Font.BOLD, 20));
        buttonDostawa.setBackground(Color.WHITE);
        buttonDostawa.setBorder(BorderFactory.createEtchedBorder());
        panelPodsumowanie.add(buttonDostawa);
        buttonDostawa.setVisible(false);


        //BUTTON PLATNOSC
        buttonPlatnosc = new JButton();
        buttonPlatnosc.setText("ZREALIZUJ ZAMÓWIENIE");
        buttonPlatnosc.setBounds(25,300,350,80);
        buttonPlatnosc.setFocusable(false);
        buttonPlatnosc.addActionListener(this);
        buttonPlatnosc.setFont(new Font(null, Font.BOLD, 20));
        buttonPlatnosc.setBackground(Color.WHITE);
        buttonPlatnosc.setBorder(BorderFactory.createEtchedBorder());
        panelPodsumowanie.add(buttonPlatnosc);
        buttonPlatnosc.setVisible(false);

        ImageIcon cofnijImage = new ImageIcon("src/Obrazki/cofnij.png");
        Image originalImage = cofnijImage.getImage();

        cofnijImage = new ImageIcon(originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH));


        //BUTTON COFNIj PLATNOSC
        cofnijPlatnosc = new JButton();
        cofnijPlatnosc.addActionListener(this);
        cofnijPlatnosc.setIcon(cofnijImage);
        cofnijPlatnosc.setBorder(BorderFactory.createEtchedBorder());
        cofnijPlatnosc.setFocusable(false);
        cofnijPlatnosc.setBackground(Color.WHITE);
        cofnijPlatnosc.setVisible(false);
        cofnijPlatnosc.setBounds(10,10,100,100);
        panelGorny.add(cofnijPlatnosc);


        //BUTTON COFNIJ DOSTAWA
        cofnijDostawa = new JButton();
        cofnijDostawa.addActionListener(this);
        cofnijDostawa.setIcon(cofnijImage);
        cofnijDostawa.setBorder(BorderFactory.createEtchedBorder());
        cofnijDostawa.setFocusable(false);
        cofnijDostawa.setBackground(Color.WHITE);
        cofnijDostawa.setVisible(false);
        cofnijDostawa.setBounds(10,10,100,100);
        panelGorny.add(cofnijDostawa);

        //BUTTON COFNIJ KOSZYK
        cofnijKoszyk = new JButton();
        cofnijKoszyk.addActionListener(this);
        cofnijKoszyk.setIcon(cofnijImage);
        cofnijKoszyk.setBorder(BorderFactory.createEtchedBorder());
        cofnijKoszyk.setBackground(Color.WHITE);
        cofnijKoszyk.setBounds(10,10,100,100);
        panelGorny.add(cofnijKoszyk);



        // CHECKBOX PACZKOMAT
        boxPaczkomat = new JCheckBox("Paczkomat");
        boxPaczkomat.setBounds(20, 20, 100, 20);
        boxPaczkomat.setFocusable(true);
        panelDostawa.add(boxPaczkomat);
        boxPaczkomat.addActionListener(this);
        boxPaczkomat.setSelected(true);
        boxPaczkomat.setEnabled(false);
        koszyk.ustawMetodeDostawy(new DostawaPaczkomat());

        // CHECKBOX KURIER
        boxKurier = new JCheckBox("Kurier");
        boxKurier.setBounds(20, 50, 100, 20);
        boxKurier.setFocusable(true);
        panelDostawa.add(boxKurier);
        boxKurier.addActionListener(this);

        //CHECKBOX BLIK
        boxBlik = new JCheckBox("Blik");
        boxBlik.setBounds(20, 20, 100, 20);
        boxBlik.setFocusable(true);
        panelPlatnosc.add(boxBlik);
        boxBlik.addActionListener(this);
        boxBlik.setSelected(true);
        boxBlik.setEnabled(false);
        koszyk.ustawMetodePlatnosci(new placBlikiem());
        textKartaNazwisko.setEnabled(false);
        labelKartaNazwisko.setEnabled(false);
        textKartaImie.setEnabled(false);
        labelKartaImie.setEnabled(false);
        textNumerKarty.setEnabled(false);
        labelNumerKarty.setEnabled(false);
        textCvv.setEnabled(false);
        labelCvv.setEnabled(false);
        textDataWygasniecia.setEnabled(false);
        labelDataWygasniecia.setEnabled(false);

        //CHECKBOX KARTA
        boxKarta = new JCheckBox("Karta");
        boxKarta.setBounds(20, 160, 100, 20);
        boxKarta.setFocusable(true);
        panelPlatnosc.add(boxKarta);
        boxKarta.addActionListener(this);

        //PANEL DOLNY
        panelDolny.setPreferredSize(new Dimension(0, 180));
        panelDolny.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.add(panelGorny);
        this.add(panelGlowny);
        this.add(panelDolny);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setSize(new Dimension(1980, 1080));
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boxPaczkomat) {
            if (boxPaczkomat.isSelected()) {
                koszyk.ustawMetodeDostawy(new DostawaPaczkomat());

                boxKurier.setSelected(false);
                boxKurier.setFocusable(true);
                boxKurier.setEnabled(true);
                boxPaczkomat.setFocusable(false);
                boxPaczkomat.setEnabled(false);
                dostawaCena = 15.99;
                cenaZaWszystko = cenaKoszyk + dostawaCena;
                labelCenaDostawy.setText("DOSTAWA " + dostawaCena + " PLN");
                labelSumaCen.setText("SUMA: " + cenaZaWszystko + " PLN");
            }
        } else if (e.getSource() == boxKurier) {
            if (boxKurier.isSelected()) {
                koszyk.ustawMetodeDostawy(new DostawaKurier());

                boxPaczkomat.setSelected(false);
                boxPaczkomat.setFocusable(true);
                boxPaczkomat.setEnabled(true);
                boxKurier.setFocusable(false);
                boxKurier.setEnabled(false);
                dostawaCena = 19.99;
                cenaZaWszystko = cenaKoszyk + dostawaCena;
                labelCenaDostawy.setText("DOSTAWA " + dostawaCena + " PLN");
                labelSumaCen.setText("SUMA: " + cenaZaWszystko + " PLN");
            }
        } else if (e.getSource() == boxBlik) {
            if (boxBlik.isSelected()) {
                koszyk.ustawMetodePlatnosci(new placBlikiem());

                boxKarta.setSelected(false);
                boxKarta.setFocusable(true);
                boxKarta.setEnabled(true);
                boxBlik.setFocusable(false);
                boxBlik.setEnabled(false);

                textKodBlik.setEnabled(true);
                labelKodBlik.setEnabled(true);

                textKartaNazwisko.setEnabled(false);
                labelKartaNazwisko.setEnabled(false);
                textKartaImie.setEnabled(false);
                labelKartaImie.setEnabled(false);
                textNumerKarty.setEnabled(false);
                labelNumerKarty.setEnabled(false);
                textCvv.setEnabled(false);
                labelCvv.setEnabled(false);
                textDataWygasniecia.setEnabled(false);
                labelDataWygasniecia.setEnabled(false);
            }
        } else if (e.getSource() == boxKarta) {
            if (boxKarta.isSelected()) {
                koszyk.ustawMetodePlatnosci(new placKarta());

                boxBlik.setSelected(false);
                boxBlik.setFocusable(true);
                boxBlik.setEnabled(true);
                boxKarta.setFocusable(false);
                boxKarta.setEnabled(false);

                textKodBlik.setEnabled(false);
                labelKodBlik.setEnabled(false);

                textKartaNazwisko.setEnabled(true);
                labelKartaNazwisko.setEnabled(true);
                textKartaImie.setEnabled(true);
                labelKartaImie.setEnabled(true);
                textNumerKarty.setEnabled(true);
                labelNumerKarty.setEnabled(true);
                textCvv.setEnabled(true);
                labelCvv.setEnabled(true);
                textDataWygasniecia.setEnabled(true);
                labelDataWygasniecia.setEnabled(true);
            }
        }

        for (int j = 0; j < comboList.size(); j++) {
            if(e.getSource() == comboList.get(j)){
                Produkt x;
                ComboBombo.getSelectedItem();
                cenaKoszyk = 0;

                for (int i = 0; i < comboList.size(); i++) {
                    x = comboProduktMap.get(comboList.get(i));
                    cenaKoszyk += x.getCena() * (Integer.parseInt((comboList.get(i).getSelectedItem()).toString()));
                }

                cenaZaWszystko = cenaKoszyk + dostawaCena;
                labelSumaCen.setText("SUMA: " + cenaZaWszystko + " PLN");
                labelCenaKoszyka.setText("KOSZYK: " + cenaKoszyk + " PLN");
                panelPodsumowanie.revalidate();
                panelPodsumowanie.repaint();
            }
        }


        if(e.getSource() instanceof JButton){
            JButton sourceButton = (JButton) e.getSource();
            Produkt produktToRemove = buttonProduktMap.get(sourceButton);

            if (produktToRemove != null) {
                koszyk.getListaProduktow().remove(produktToRemove);
                panelKoszyk.remove(sourceButton.getParent()); // Usunięcie całego panelu produktu
                panelKoszyk.revalidate();
                panelKoszyk.repaint();

                cenaZaWszystko = cenaKoszyk + dostawaCena;
                labelCenaKoszyka.setText("KOSZYK: " + cenaKoszyk + " PLN");
                labelSumaCen.setText("SUMA: " + cenaZaWszystko + " PLN");

            }
            if(e.getSource() == buttonZamowienie) {
                try{
                    PustyKoszykException.checkIfEmpty(koszyk);

                    cofnijDostawa.setVisible(true);
                    buttonPlatnosc.setVisible(true);
                    panelKoszyk.setVisible(false);
                    buttonDostawa.setVisible(true);
                    panelGorny.revalidate();
                    panelGorny.repaint();
                    panelKoszyk.revalidate();
                    panelKoszyk.repaint();
                    panelDostawa.setVisible(true);
                    panelDostawa.revalidate();
                    panelDostawa.repaint();
                    buttonZamowienie.setVisible(false);
                    scrollPane.setVisible(false);
                    dostawaCena = 15.99;

                    cenaZaWszystko = (dostawaCena + cenaKoszyk);
                    labelSumaCen.setText("SUMA: " + cenaZaWszystko + " PLN");
                    labelCenaDostawy.setText("DOSTAWA " + dostawaCena + " PLN");
                }catch(PustyKoszykException e2){
                    JOptionPane.showMessageDialog(null, e2.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                }


            }
            if(e.getSource() == buttonDostawa){
                try {
                    ZlyAdresException.checkIfEmpty(textImie.getText(), textNazwisko.getText(), textUlica.getText(), textNumerDomu.getText(),
                            textMiejscowosc.getText(), textKraj.getText());
                    ZlyAdresException.checkPhoneNumber(textNumerTelefonu.getText());
                    ZlyAdresException.checkEmail(textEmail.getText());
                    ZlyAdresException.checkKodPocztowy(textKodPocztowy.getText());
                    cofnijPlatnosc.setVisible(true);
                    cofnijDostawa.setVisible(false);
                    buttonPlatnosc.setVisible(false);
                    buttonDostawa.setVisible(true);
                    buttonDostawa.setVisible(false);
                    panelDostawa.setVisible(false);
                    buttonPlatnosc.setVisible(true);
                    panelPlatnosc.setVisible(true);

                    numerTelefonu = textNumerTelefonu.getText();
                    Email = textEmail.getText();
                    adres = textImie.getText() +
                            " " + textNazwisko.getText() +
                            "\n" + textUlica.getText() +
                            " " + textNumerDomu.getText() +
                            "\n" + textKodPocztowy.getText() +
                            " " + textMiejscowosc.getText() +
                            " " + textKraj.getText();
                }catch (ZlyAdresException e1){
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
            if(e.getSource() == cofnijPlatnosc){
                cofnijPlatnosc.setVisible(false);
                cofnijDostawa.setVisible(true);
                buttonPlatnosc.setVisible(true);
                buttonDostawa.setVisible(false);
                buttonDostawa.setVisible(true);
                panelDostawa.setVisible(true);
                buttonPlatnosc.setVisible(false);
                panelPlatnosc.setVisible(false);
            }

            if(e.getSource() == cofnijDostawa){
                cofnijDostawa.setVisible(false);
                panelKoszyk.setVisible(true);
                buttonDostawa.setVisible(false);
                panelKoszyk.revalidate();
                panelKoszyk.repaint();
                panelDostawa.setVisible(false);
                panelDostawa.revalidate();
                panelDostawa.repaint();
                buttonZamowienie.setVisible(true);
                scrollPane.setVisible(true);
            }
            if(e.getSource() == cofnijKoszyk){
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
                SklepGUI.openSklepGUI(frame,sklep);
            }
            if(e.getSource() == buttonPlatnosc) {
                JOptionPane warning = new JOptionPane();
                if(koszyk.zrealizujDostawe(adres, textKodBlik.getText(), textNumerKarty.getText(), textDataWygasniecia.getText(), textCvv.getText(), textKartaImie.getText(), textKartaNazwisko.getText())){
                    warning.showMessageDialog(null,"Wysłano paczkę na adres: " + adres, "ZAMÓWIENIE WYSŁANE", JOptionPane.INFORMATION_MESSAGE);



                    for (int j = 0; j < comboList.size(); j++) {
                            Produkt x = comboProduktMap.get(comboList.get(j));
                            x.setIloscWMagazynie(x.getIloscWMagazynie() - (Integer.parseInt((comboList.get(j).getSelectedItem()).toString())));
                            panelKoszyk.removeAll();
                    }
//                    //WYJSCIE Z MENU SKLEPU
                }


            }
        }
    }

//    public static void main(String[] args) {
//        Sklep sklep = new Sklep(new ArrayList<>(), null, false, new ArrayList<>());
//        sklep.wczytajListeProduktow();
//        sklep.wczytajListeKlientow();
//
//        sklep.zalogowanyKlient = sklep.getListaKlientow().get(0);
//        sklep.zalogowanyKlient.getKoszyk().dodajProdukt(sklep.getListaProduktow().get(0));
//        sklep.zalogowanyKlient.getKoszyk().dodajProdukt(sklep.getListaProduktow().get(1));
//
//        if (sklep.zalogowanyKlient.getKoszyk().getListaProduktow().size() == 0) {
//            System.out.println("PUSTY KOSZYK");
//        } else {
//            for (int i = 0; i < sklep.zalogowanyKlient.getKoszyk().getListaProduktow().size(); i++) {
//                System.out.println((i+1) + ".");
//                System.out.println(sklep.zalogowanyKlient.getKoszyk().getListaProduktow().get(i).toString());
//            }
//        }
//
//        //new KoszykFrame(sklep.zalogowanyKlient.getKoszyk());
//
//        sklep.wczytajListeProduktow();
//        sklep.wczytajListeKlientow();
//
//    }
}


