package InterfejsGraficzny;

import DostawaStrategia.DostawaKurier;
import DostawaStrategia.DostawaPaczkomat;
import inputValidate.PustyKoszykException;
import PlacenieStrategia.placBlikiem;
import PlacenieStrategia.placKarta;
import Produkt.*;
import Main.*;
import inputValidate.ZlyAdresException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KoszykPanel extends JPanel implements ActionListener {
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
    JScrollPane scrollPaneKoszyk;
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

    KoszykPanel(JFrame frame, Sklep sklep) {
        this.frame = frame;
        this.sklep = sklep;
        this.koszyk = this.sklep.zalogowanyKlient.getKoszyk();
        JLabel mojKoszyk = new JLabel("MOJ KOSZYK");
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
        ImageIcon koszykImage = new ImageIcon("src/resources/Obrazki/koszyk.png");

//        TEXT FIELDY
        textImie = new JTextField();
        textImie.setBounds(10,100,250,40);
        panelDostawa.add(textImie);
        labelImie = new JLabel("IMIĘ");
        labelImie.setBounds(10,85,50,10);
        panelDostawa.add(labelImie);

        textNazwisko = new JTextField();
        textNazwisko.setBounds(10,175,250,40);
        panelDostawa.add(textNazwisko);
        labelNazwisko = new JLabel("NAZWISKO");
        labelNazwisko.setBounds(10,160,150,10);
        panelDostawa.add(labelNazwisko);

        textUlica = new JTextField();
        textUlica.setBounds(10,250,150,40);
        panelDostawa.add(textUlica);
        labelUlica = new JLabel("ULICA");
        labelUlica.setBounds(10,235,50,10);
        panelDostawa.add(labelUlica);

        textNumerDomu = new JTextField();
        textNumerDomu.setBounds(180,250,80,40);
        panelDostawa.add(textNumerDomu);
        labelNumerDomu = new JLabel("NR. DOMU");
        labelNumerDomu.setBounds(195,235,150,10);
        panelDostawa.add(labelNumerDomu);

        textKodPocztowy = new JTextField();
        textKodPocztowy.setBounds(10,325,80,40);
        panelDostawa.add(textKodPocztowy);
        labelKodPocztowy = new JLabel("KOD POCZTOWY");
        labelKodPocztowy.setBounds(10,310,150,10);
        panelDostawa.add(labelKodPocztowy);

        textMiejscowosc = new JTextField();
        textMiejscowosc.setBounds(110,325,150,40);
        panelDostawa.add(textMiejscowosc);
        labelMiejscowosc = new JLabel("MIEJSCOWOSC");
        labelMiejscowosc.setBounds(170,310,150,10);
        panelDostawa.add(labelMiejscowosc);

        textKraj = new JTextField();
        textKraj.setBounds(280,100,250,40);
        panelDostawa.add(textKraj);
        labelKraj = new JLabel("KRAJ");
        labelKraj.setBounds(280,85,50,10);
        panelDostawa.add(labelKraj);

        textNumerTelefonu = new JTextField();
        textNumerTelefonu.setBounds(280,175,250,40);
        panelDostawa.add(textNumerTelefonu);
        labelNumerTelefonu = new JLabel("NR TEL");
        labelNumerTelefonu.setBounds(280,160,150,10);
        panelDostawa.add(labelNumerTelefonu);

        textEmail = new JTextField();
        textEmail.setBounds(280,250,250,40);
        panelDostawa.add(textEmail);
        labelEmail = new JLabel("EMAIL");
        labelEmail.setBounds(280,235,50,10);
        panelDostawa.add(labelEmail);

        textKodBlik = new JTextField();
        textKodBlik.setBounds(20,70,250,40);
        panelPlatnosc.add(textKodBlik);
        labelKodBlik = new JLabel("KOD BLIK");
        labelKodBlik.setBounds(20,55,120,10);
        panelPlatnosc.add(labelKodBlik);

        textNumerKarty = new JTextField();
        textNumerKarty.setBounds(20,210,250,40);
        panelPlatnosc.add(textNumerKarty);
        labelNumerKarty = new JLabel("NR KARTY");
        labelNumerKarty.setBounds(20,195,120,10);
        panelPlatnosc.add(labelNumerKarty);

        textDataWygasniecia = new JTextField();
        textDataWygasniecia.setBounds(20,275,150,40);
        panelPlatnosc.add(textDataWygasniecia);
        labelDataWygasniecia = new JLabel("DATA WYGASNIECIA");
        labelDataWygasniecia.setBounds(20,260,120,10);
        panelPlatnosc.add(labelDataWygasniecia);

        textCvv = new JTextField();
        textCvv.setBounds(190,275,80,40);
        panelPlatnosc.add(textCvv);
        labelCvv = new JLabel("CVV");
        labelCvv.setBounds(190,260,120,10);
        panelPlatnosc.add(labelCvv);

        textKartaImie = new JTextField();
        textKartaImie.setBounds(290,210,250,40);
        panelPlatnosc.add(textKartaImie);
        labelKartaImie = new JLabel("IMIĘ");
        labelKartaImie.setBounds(290,195,120,10);
        panelPlatnosc.add(labelKartaImie);

        textKartaNazwisko = new JTextField();
        textKartaNazwisko.setBounds(290,275,250,40);
        panelPlatnosc.add(textKartaNazwisko);
        labelKartaNazwisko = new JLabel("NAZWISKO");
        labelKartaNazwisko.setBounds(290,260,120,10);
        panelPlatnosc.add(labelKartaNazwisko);

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

        //PANEL KOSZYK
        panelGlowny.setBackground(Color.WHITE);
        panelKoszyk.setBorder(BorderFactory.createLineBorder(new Color(220,220,220)));
        panelKoszyk.setBackground(Color.WHITE);
        panelKoszyk.setPreferredSize(new Dimension(600, 450));

        scrollPaneKoszyk = new JScrollPane(panelKoszyk);
        JScrollBar szybkoscScrollPaneKoszyk = scrollPaneKoszyk.getVerticalScrollBar();
        szybkoscScrollPaneKoszyk.setUnitIncrement(20);
        szybkoscScrollPaneKoszyk.setBlockIncrement(40);

        scrollPaneKoszyk.setBackground(Color.WHITE);
        scrollPaneKoszyk.setBorder(BorderFactory.createLineBorder(new Color(220,220,220)));
        scrollPaneKoszyk.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneKoszyk.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneKoszyk.setBounds(200, 150, 700, 450);

        //PANEL PRODUKT
        for (int i = 0; i < koszyk.getListaProduktow().size(); i++) {
            JButton buttonUsun = new JButton("x");
            Produkt produkt = koszyk.getListaProduktow().get(i);
            JLabel labelNazwa = new JLabel(produkt.getNazwa());
            JLabel labelCena = new JLabel(produkt.getCena() + " PLN");
            JPanel panelProdukt = new JPanel();
            obrazek = new ImageIcon();
            int wysokoscPanelProdukt = (scrollPaneKoszyk.getHeight()/4);

            panelProdukt.setLayout(null);
            panelProdukt.setBackground(new Color(242, 242, 242));
            panelProdukt.setPreferredSize(new Dimension(scrollPaneKoszyk.getWidth()-25,wysokoscPanelProdukt));

            labelNazwa.setBounds(wysokoscPanelProdukt+10,5,scrollPaneKoszyk.getWidth()-50,18);
            labelNazwa.setFont(new Font(null, Font.BOLD, 16));

            labelCena.setBounds(labelNazwa.getX(),labelNazwa.getY()+22,scrollPaneKoszyk.getWidth()-50,16);
            labelCena.setFont(new Font(null, Font.BOLD, 16));


            obrazek = produkt.getIcon(wysokoscPanelProdukt,wysokoscPanelProdukt);
            labelObrazek = new JLabel();
            labelObrazek.setIcon(obrazek);
            labelObrazek.setBorder(BorderFactory.createLineBorder(new Color(242, 242, 242)));
            labelObrazek.setBounds(0,0,wysokoscPanelProdukt,wysokoscPanelProdukt);

            int buttonX = scrollPaneKoszyk.getWidth()-50;
            buttonUsun.setBounds(buttonX, 0,20,20);
            buttonUsun.addActionListener(this);
            buttonUsun.setFocusable(false);
            buttonUsun.setFont(new Font(null, Font.PLAIN, 20));
            buttonUsun.setBorder(null);
            buttonUsun.setBackground(null);
            buttonUsun.setForeground(Color.gray);

            opcjeWyboru = new String[produkt.getIloscWMagazynie()];
            for (int j = 1; j <= produkt.getIloscWMagazynie(); j++) {
                opcjeWyboru[j-1] = String.valueOf(j);
            }

            comboList.add(ComboBombo = new JComboBox(opcjeWyboru));
            comboList.get(i).setBounds(labelNazwa.getX(), wysokoscPanelProdukt-40,50,30);
            comboList.get(i).setBackground(Color.WHITE);
            comboList.get(i).setBorder(null);
            comboList.get(i).addActionListener(this);
            comboList.get(i).setSelectedItem(0);

            panelProdukt.add(labelObrazek);
            panelProdukt.add(comboList.get(i));
            panelProdukt.add(buttonUsun);
            panelProdukt.add(labelCena);
            panelProdukt.add(labelNazwa);
            panelKoszyk.add(panelProdukt);

            comboProduktMap.put(comboList.get(i), produkt);
            buttonProduktMap.put(buttonUsun, produkt);
        }

        panelKoszyk.setPreferredSize(new Dimension(600, panelKoszyk.getComponentCount() * ((scrollPaneKoszyk.getHeight()/4)+10)));
        panelGlowny.add(scrollPaneKoszyk);

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
        panelPodsumowanie.setBounds(1270,150,450,450);
        panelPodsumowanie.setLayout(null);

        //BUTTON PODSUMOWANIE
        buttonZamowienie = new JButton("ZLOZ ZAMOWIENIE");
        buttonZamowienie.setBounds(25,300,350,80);
        buttonZamowienie.addActionListener(this);
        buttonZamowienie.setFocusable(false);
        buttonZamowienie.setFont(new Font(null, Font.BOLD, 20));
        buttonZamowienie.setBackground(Color.WHITE);
        buttonZamowienie.setBorder(BorderFactory.createEtchedBorder());
        panelPodsumowanie.add(buttonZamowienie);

        //PODSUMOWANIE LABEL
        JLabel labelPodsumowanie = new JLabel("PODSUMOWANIE");
        labelPodsumowanie.setBounds(75,0,400,100);
        labelPodsumowanie.setFont(new Font(null, Font.BOLD, 30));
        panelPodsumowanie.add(labelPodsumowanie);

        //KOSZYK CENA LABEL
        cenaKoszyk = koszyk.getWartoscZamowienia();
        labelCenaKoszyka = new JLabel("KOSZYK: " + cenaKoszyk + " PLN");
        labelCenaKoszyka.setBounds(25,75,200,100);
        labelCenaKoszyka.setFont(new Font(null, Font.BOLD, 15));
        panelPodsumowanie.add(labelCenaKoszyka);

        //KOSZYK DOSTAWA CENA LABEL
        labelCenaDostawy = new JLabel("DOSTAWA: " + dostawaCena + " PLN");
        labelCenaDostawy.setBounds(25,125,200,100);
        labelCenaDostawy.setFont(new Font(null, Font.BOLD, 15));
        panelPodsumowanie.add(labelCenaDostawy);

        //SUMA CEN WSZYSTKO
        labelSumaCen = new JLabel("SUMA: " + (dostawaCena + koszyk.getWartoscZamowienia()) + " PLN");
        labelSumaCen.setBounds(25,175,200,100);
        labelSumaCen.setFont(new Font(null, Font.BOLD, 20));
        panelPodsumowanie.add(labelSumaCen);

        //BUTTON DOSTAWA
        buttonDostawa = new JButton("FINALIZUJ DOSTAWE");
        buttonDostawa.setBounds(25,300,350,80);
        buttonDostawa.setFocusable(false);
        buttonDostawa.addActionListener(this);
        buttonDostawa.setFont(new Font(null, Font.BOLD, 20));
        buttonDostawa.setBackground(Color.WHITE);
        buttonDostawa.setBorder(BorderFactory.createEtchedBorder());
        panelPodsumowanie.add(buttonDostawa);
        buttonDostawa.setVisible(false);


        //BUTTON PLATNOSC
        buttonPlatnosc = new JButton("ZREALIZUJ ZAMÓWIENIE");
        buttonPlatnosc.setBounds(25,300,350,80);
        buttonPlatnosc.setFocusable(false);
        buttonPlatnosc.addActionListener(this);
        buttonPlatnosc.setFont(new Font(null, Font.BOLD, 20));
        buttonPlatnosc.setBackground(Color.WHITE);
        buttonPlatnosc.setBorder(BorderFactory.createEtchedBorder());
        panelPodsumowanie.add(buttonPlatnosc);
        buttonPlatnosc.setVisible(false);

        ImageIcon cofnijImage = new ImageIcon("src/resources/Obrazki/cofnij.png");
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

        //MOJ KOSZYK label
        mojKoszyk.setIcon(koszykImage);
        mojKoszyk.setHorizontalTextPosition(JLabel.RIGHT);
        mojKoszyk.setVerticalTextPosition(JLabel.CENTER);
        mojKoszyk.setBackground(new Color(242, 242, 242));
        mojKoszyk.setOpaque(true);
        mojKoszyk.setBounds(scrollPaneKoszyk.getX(),scrollPaneKoszyk.getY()-40,200,40);
        mojKoszyk.setFont(new Font(null, Font.BOLD, 20));

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
                labelCenaDostawy.setText("DOSTAWA: " + dostawaCena + " PLN");
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
                labelCenaDostawy.setText("DOSTAWA: " + dostawaCena + " PLN");
                labelSumaCen.setText("SUMA: " + cenaZaWszystko + " PLN");
            }
        } else if (e.getSource() == boxBlik) {
            if (boxBlik.isSelected()) {
                koszyk.ustawMetodePlatnosci(new placBlikiem());

                boxKarta.setSelected(false);
                boxKarta.setEnabled(true);
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
                boxBlik.setEnabled(true);
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
                cenaKoszyk = 0;

                for (int i = 0; i < comboList.size(); i++) {
                    x = comboProduktMap.get(comboList.get(i));
                    cenaKoszyk += x.getCena() * (Integer.parseInt((comboList.get(i).getSelectedItem()).toString()));
                }

                cenaZaWszystko = cenaKoszyk + dostawaCena;
                labelSumaCen.setText("SUMA: " + cenaZaWszystko + " PLN");
                labelCenaKoszyka.setText("KOSZYK: " + cenaKoszyk + " PLN");
            }
        }


        if(e.getSource() instanceof JButton){
            JButton sourceButton = (JButton) e.getSource();
            Produkt produktToRemove = buttonProduktMap.get(sourceButton);

            if (produktToRemove != null) {
                Produkt x;
                koszyk.getListaProduktow().remove(produktToRemove);
                panelKoszyk.remove(sourceButton.getParent()); // Usunięcie całego panelu produktu
                panelKoszyk.revalidate();
                panelKoszyk.repaint();

                for (int i = 0; i < comboList.size(); i++) {
                    x = comboProduktMap.get(comboList.get(i));
                    if (x == produktToRemove){
                        cenaKoszyk -= (x.getCena() * (Integer.parseInt((comboList.get(i).getSelectedItem()).toString())));
                        comboProduktMap.remove(comboList.get(i));
                        comboList.remove(i);
                    }
                }

                panelKoszyk.setPreferredSize(new Dimension(600, panelKoszyk.getComponentCount() * 110));




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
                    scrollPaneKoszyk.setVisible(false);
                    dostawaCena = 15.99;
                    cenaZaWszystko = (dostawaCena + cenaKoszyk);
                    labelSumaCen.setText("SUMA: " + cenaZaWszystko + " PLN");
                    labelCenaDostawy.setText("DOSTAWA: " + dostawaCena + " PLN");
                    panelPodsumowanie.revalidate();
                    panelPodsumowanie.repaint();
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
                scrollPaneKoszyk.setVisible(true);
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
                            koszyk.getListaProduktow().remove(x);
                            if(x.getIloscWMagazynie()<=0) {
                                sklep.getListaProduktow().remove(x);
                            }
                    }
                    frame.getContentPane().removeAll();
                    frame.revalidate();
                    frame.repaint();
                    SklepGUI.openSklepGUI(frame,sklep);
//                    //WYJSCIE Z MENU SKLEPU
                }


            }
        }
    }
}


