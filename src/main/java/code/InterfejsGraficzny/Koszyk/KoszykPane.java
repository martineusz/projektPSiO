
package code.InterfejsGraficzny.Koszyk;

import code.DostawaStrategia.DostawaPaczkomat;
import code.InterfejsGraficzny.SklepGUI;
import code.Main.Koszyk;
import code.Main.ProduktWKoszyku;
import code.Main.Sklep;
import code.PlacenieStrategia.placBlikiem;
import code.Produkt.Produkt;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class KoszykPane extends JPanel {
    private String adres = null;
    private JFrame frame;
    private KoszykListener koszykListener;
    private Sklep sklep;
    private Koszyk koszyk;
    private float cenaDostawa = 0.00F;
    private float sumaCen = 0.00F;

    //BUTTONS
    private JButton buttonZamowienie;
    private JButton buttonLogoSklep;
    private JButton buttonWylogujSie;
    private JButton buutonPowiadomienia;
    private JButton buttonDostawa;
    private JRadioButton buttonPaczkomat;
    private JRadioButton buttonKurier;
    private JRadioButton buttonBlik;
    private JRadioButton buttonKartaKredytowa;
    private JButton buttonUsun;
    private JButton buttonPlatnosc;
    private JButton buttonCofnijDostawa;
    private JButton buttonCofnijPlatnosc;
    //PANELS
    private JPanel panelGorny;
    private JPanel panelGorny2;
    private JPanel panelPodsumowanie;
    private JPanel panelKoszyk;
    private JPanel panelGlowny;
    private JPanel panelDostawy;
    private JPanel panelPlatnosci;
    private JScrollPane scrollPaneKoszyk;
    // LABELs
    private JLabel labelKoszyk;
    private JLabel labelKoszyk2;
    private JLabel labelDostawa;
    private JLabel labelPlatnosc;
    private JLabel labelPodsumowanie;
    private JLabel labelCenaKoszyka;
    private JLabel labelCenaDostawy;
    private JLabel labelSumaCen;
    private Map<JButton, Produkt> buttonProduktMap;
    private ArrayList<JComboBox> comboList;
    private Map<JComboBox, Produkt> comboProduktMap;

    //TEXT FIELDS
    private JTextField textImie;
    private JTextField textNazwisko;
    private JTextField textUlica;
    private JTextField textNumerDomu;
    private JTextField textKodPocztowy;
    private JTextField textMiejscowosc;
    private JTextField textKraj;
    private JTextField textNumerTelefonu;
    private JTextField textEmail;
    private JTextField textKodBlik;
    private JTextField textNumerKarty;
    private JTextField textDataWygasniecia;
    private JTextField textCvv;
    private JTextField textKartaImie;
    private JTextField textKartaNazwisko;
    public void otworzKoszyk(Sklep sklep, JFrame frame) {
        this.frame = frame;
        this.sklep = sklep;
        this.koszyk = sklep.getZalogowanyKlient().getKoszyk();
        koszykListener = new KoszykListener(this);

        //IMAGE
        ImageIcon koszykImage = new ImageIcon("src/code/InterfejsGraficzny/Koszyk/Images/koszyk.png");
        ImageIcon dostawaImage = new ImageIcon("src/code/InterfejsGraficzny/Koszyk/Images/dostawa.png");
        ImageIcon platnoscImage = new ImageIcon("src/code/InterfejsGraficzny/Koszyk/Images/platnosc.png");

        ImageIcon logoImage;
        logoImage = SklepGUI.scaleIcon("src/resources/Obrazki/logoSklepu.png",100);

        ImageIcon powiadomieniaImage = new ImageIcon("src/code/InterfejsGraficzny/Koszyk/Images/powiadomienia.png");
        ImageIcon konotImage = new ImageIcon("src/code/InterfejsGraficzny/Koszyk/Images/account.png");

        // PANELs
        panelGorny = new JPanel();
        panelGorny2 = new JPanel();
        panelGlowny = new JPanel();
        panelPodsumowanie = new JPanel();

        // LABELs
        labelKoszyk = new JLabel();
        labelKoszyk2 = new JLabel();
        labelDostawa = new JLabel();
        labelPlatnosc = new JLabel();
        labelPodsumowanie = new JLabel();
        labelCenaKoszyka = new JLabel();
        labelCenaDostawy = new JLabel();
        labelSumaCen = new JLabel();

        //BUTTONS
        buttonZamowienie = new JButton();
        buttonLogoSklep = new JButton();
        buttonWylogujSie = new JButton();
        buutonPowiadomienia = new JButton();
        buttonDostawa = new JButton();
        buttonCofnijDostawa = new JButton();
        buttonPlatnosc = new JButton();
        buttonCofnijPlatnosc = new JButton();

        // PANEL GLOWNY
        panelGlowny.setPreferredSize(new Dimension(frame.getWidth(), 1620));
        panelGlowny.setLayout(null);
        panelGlowny.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelGlowny.setBackground(new Color(255,255,255));

        rysujPanelKoszyk(panelGlowny);

        //FRAME
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(panelGorny);
        frame.add(panelGorny2);
        frame.add(panelGlowny);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // PANEL GORNY
        panelGorny.setPreferredSize(new Dimension(frame.getWidth(), 250));
        panelGorny.setLayout(null);
        panelGorny.setBackground(new Color(255, 69, 0));
        panelGorny.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //BUTTON LOGO SKLEP
        buttonLogoSklep.setIcon(logoImage);
        buttonLogoSklep.setBounds(100, 10, 100, 100);
        buttonLogoSklep.setFocusable(false);
        buttonLogoSklep.setBackground(null);
        buttonLogoSklep.setBorder(null);
        buttonLogoSklep.addActionListener(koszykListener);
        panelGorny.add(buttonLogoSklep);

        //BUTTON WYLOGUJ SIE
        buttonWylogujSie.setIcon(konotImage);
        buttonWylogujSie.setText("Wyloguj sie");
        buttonWylogujSie.addActionListener(koszykListener);
        buttonWylogujSie.setBounds(frame.getWidth()-370, 40, 150, 50);
        buttonWylogujSie.setFocusable(false);
        buttonWylogujSie.setBackground(null);
        buttonWylogujSie.setBorder(null);
        panelGorny.add(buttonWylogujSie);

        //BUTTON POWIADOMIENIA
        buutonPowiadomienia.setIcon(powiadomieniaImage);
        buutonPowiadomienia.setText("Powiadomienia");
        buutonPowiadomienia.setIconTextGap(-5);
        buutonPowiadomienia.setBounds(frame.getWidth()-520, 40, 150, 50);
        buutonPowiadomienia.setFocusable(false);
        buutonPowiadomienia.setBackground(null);
        buutonPowiadomienia.setBorder(null);
        buutonPowiadomienia.addActionListener(koszykListener);
        panelGorny.add(buutonPowiadomienia);

        // PANEL GORNY2
        panelGorny2.setPreferredSize(new Dimension(frame.getWidth(), 200));
        panelGorny2.setLayout(null);
        panelGorny2.setBackground(new Color(240,252,255));
        panelGorny2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //LABEL KOSZYK
        labelKoszyk.setText("TWOJ KOSZYK");
        labelKoszyk.setBounds(100, 25, 300, 50);
        labelKoszyk.setFont(new Font(null, Font.BOLD, 30));
        panelGorny2.add(labelKoszyk);

        //LABEL KOSZYK 2
        labelKoszyk2.setText("KOSZYK  >");
        labelKoszyk2.setBounds(frame.getWidth()-620, 25, 125, 40);
        labelKoszyk2.setFont(new Font(null, Font.BOLD, 15));
        labelKoszyk2.setBackground(new Color(184, 255, 184));
        labelKoszyk2.setOpaque(true);
        labelKoszyk2.setIcon(koszykImage);
        labelKoszyk2.setHorizontalTextPosition(JLabel.RIGHT);
        panelGorny2.add(labelKoszyk2);

        //LABEL DOSTAWA
        labelDostawa.setText("DOSTAWA  >");
        labelDostawa.setBackground(Color.lightGray);
        labelDostawa.setBounds(frame.getWidth()-495, 25, 150, 40);
        labelDostawa.setFont(new Font(null, Font.BOLD, 15));
        labelDostawa.setOpaque(true);
        labelDostawa.setIcon(dostawaImage);
        labelDostawa.setHorizontalTextPosition(JLabel.RIGHT);
        panelGorny2.add(labelDostawa);

        //LABEL PLATNOSC
        labelPlatnosc.setText("PLATNOSC");
        labelPlatnosc.setBackground(Color.lightGray);
        labelPlatnosc.setBounds(frame.getWidth()-345, 25, 150, 40);
        labelPlatnosc.setFont(new Font(null, Font.BOLD, 15));
        labelPlatnosc.setOpaque(true);
        labelPlatnosc.setIcon(platnoscImage);
        labelPlatnosc.setHorizontalTextPosition(JLabel.RIGHT);
        panelGorny2.add(labelPlatnosc);

        // PANEL GLOWNY
        panelGlowny.setPreferredSize(new Dimension(frame.getWidth(), 1620));
        panelGlowny.setLayout(null);
        panelGlowny.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelGlowny.setBackground(new Color(255,255,255));

        //PANEL PODSUMOWANIE
        panelPodsumowanie.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        panelPodsumowanie.setBackground(new Color(240,252,255));
        panelPodsumowanie.setBounds(frame.getWidth()-650, 150, 450, 450);
        panelPodsumowanie.setLayout(null);
        panelGlowny.add(panelPodsumowanie);

        //BUTTON ZAMOWIENIE
        buttonZamowienie.setText("PRZEJDZ DO DOSTAWY");
        buttonZamowienie.setBounds(50, 300, 350, 80);
        buttonZamowienie.addActionListener(koszykListener);
        buttonZamowienie.setFocusable(false);
        buttonZamowienie.setFont(new Font(null, Font.BOLD, 20));
        buttonZamowienie.setBackground(new Color(255, 69, 0));
        buttonZamowienie.setForeground(new Color(255, 255, 255));
        buttonZamowienie.setBorder(BorderFactory.createEtchedBorder());
        panelPodsumowanie.add(buttonZamowienie);

        //BUTTON DOSTAWA
        buttonDostawa.setText("PRZEJDZ DO PLATNOSCI");
        buttonDostawa.setBounds(50, 300, 350, 80);
        buttonDostawa.addActionListener(koszykListener);
        buttonDostawa.setFocusable(false);
        buttonDostawa.setFont(new Font(null, Font.BOLD, 20));
        buttonDostawa.setBackground(new Color(255, 69, 0));
        buttonDostawa.setForeground(new Color(255, 255, 255));
        buttonDostawa.setBorder(BorderFactory.createEtchedBorder());

        //BUTTON COFNIJ DOSTAWA
        buttonCofnijDostawa.setText("POWROT");
        buttonCofnijDostawa.setBounds(180,385,100,20);
        buttonCofnijDostawa.addActionListener(koszykListener);
        buttonCofnijDostawa.setFocusable(false);
        buttonCofnijDostawa.setBackground(null);
        buttonCofnijDostawa.setBorder(null);

        //BUTTON PLATNOSC
        buttonPlatnosc.setText("ZREALIZUJ ZAMOWIENIE");
        buttonPlatnosc.setBounds(50, 300, 350, 80);
        buttonPlatnosc.addActionListener(koszykListener);
        buttonPlatnosc.setFocusable(false);
        buttonPlatnosc.setFont(new Font(null, Font.BOLD, 20));
        buttonPlatnosc.setBackground(new Color(255, 69, 0));
        buttonPlatnosc.setForeground(new Color(255, 255, 255));
        buttonPlatnosc.setBorder(BorderFactory.createEtchedBorder());

        //BUTTON COFNIJ PLATNOSC
        buttonCofnijPlatnosc.setText("POWROT");
        buttonCofnijPlatnosc.setBounds(180,385,100,20);
        buttonCofnijPlatnosc.addActionListener(koszykListener);
        buttonCofnijPlatnosc.setFocusable(false);
        buttonCofnijPlatnosc.setBackground(null);
        buttonCofnijPlatnosc.setBorder(null);

        //PODSUMOWANIE LABEL
        labelPodsumowanie.setText("Podsumowanie koszyka");
        labelPodsumowanie.setHorizontalAlignment(JLabel.CENTER);
        labelPodsumowanie.setBounds(50, 10, 350, 50);
        labelPodsumowanie.setFont(new Font(null, Font.BOLD, 27));
        panelPodsumowanie.add(labelPodsumowanie);

        //KOSZYK CENA LABEL
        labelCenaKoszyka.setText("Koszyk: " + koszyk.getWartoscZamowienia() + " PLN");
        labelCenaKoszyka.setBounds(25, 75, 200, 100);
        labelCenaKoszyka.setFont(new Font(null, Font.BOLD, 15));
        panelPodsumowanie.add(labelCenaKoszyka);

        //KOSZYK DOSTAWA CENA LABEL
        labelCenaDostawy.setText("Dostawa: " + cenaDostawa + " PLN");
        labelCenaDostawy.setBounds(25, 125, 200, 100);
        labelCenaDostawy.setFont(new Font(null, Font.BOLD, 15));
        panelPodsumowanie.add(labelCenaDostawy);

        //SUMA CEN WSZYSTKO
        sumaCen = (float) Math.ceil((cenaDostawa+koszyk.getWartoscZamowienia())*100)/100;
        labelSumaCen.setText("SUMA: " + (cenaDostawa + koszyk.getWartoscZamowienia()) + " PLN");
        labelSumaCen.setBounds(25, 175, 200, 100);
        labelSumaCen.setFont(new Font(null, Font.BOLD, 20));
        panelPodsumowanie.add(labelSumaCen);
    }

    public void rysujPanelKoszyk(JPanel panelGlowny) { //METODA RYSUJACA KOSZYK
        panelKoszyk = new JPanel();
        panelKoszyk.setBackground(new Color(240,252,255));
        panelKoszyk.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        panelGlowny.add(panelKoszyk);
        panelKoszyk.setPreferredSize(new Dimension(700, 700));
        panelKoszyk.setLayout(new FlowLayout(FlowLayout.CENTER));
        comboList = new ArrayList<>();
        comboProduktMap = new HashMap<>();

        // LABELs
        JLabel labelPustyKoszyk = new JLabel();

        //MAPY
        buttonProduktMap = new HashMap<>();

        scrollPaneKoszyk = new JScrollPane(panelKoszyk);
        JScrollBar szybkoscScrollPaneKoszyk = scrollPaneKoszyk.getVerticalScrollBar();
        szybkoscScrollPaneKoszyk.setUnitIncrement(20);
        szybkoscScrollPaneKoszyk.setBlockIncrement(40);

        scrollPaneKoszyk.setBackground(new Color(240,252,255));
        scrollPaneKoszyk.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        scrollPaneKoszyk.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneKoszyk.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneKoszyk.setBounds(100, 25, 700, 700);

        //PANEL PRODUKT
        if (koszyk.getProduktyWKoszyku() == null || koszyk.getProduktyWKoszyku().isEmpty()) {
            labelPustyKoszyk.setText("TWOJ KOSZYK JEST PUSTY");
            labelPustyKoszyk.setHorizontalAlignment(JLabel.CENTER);
            labelPustyKoszyk.setVerticalAlignment(JLabel.CENTER);
            labelPustyKoszyk.setPreferredSize(new Dimension(500, 600));
            labelPustyKoszyk.setForeground(Color.lightGray);
            labelPustyKoszyk.setFont(new Font(null, Font.BOLD, 25));
            panelKoszyk.add(labelPustyKoszyk);
        } else{
            for (int i = 0; i < koszyk.getProduktyWKoszyku().size(); i++) {
                buttonUsun = new JButton("x");
                ProduktWKoszyku produktWKoszyku = koszyk.getProduktyWKoszyku().get(i);
                JLabel labelNazwa = new JLabel("Nazwa: "+produktWKoszyku.getProdukt().getNazwa());
                JLabel labelCena = new JLabel("Cena: " +produktWKoszyku.getProdukt().getCena() + " PLN");
                JLabel labelRozmiar = new JLabel("Rozmiar: " + produktWKoszyku.getRozmiar());
                JPanel panelProdukt = new JPanel();
                ImageIcon obrazek;
                int wysokoscPanelProdukt = (scrollPaneKoszyk.getHeight() / 4);

                panelProdukt.setLayout(null);
                panelProdukt.setBackground(new Color(251,244,244));
                panelProdukt.setPreferredSize(new Dimension(scrollPaneKoszyk.getWidth() - 25, wysokoscPanelProdukt));

                labelNazwa.setBounds(wysokoscPanelProdukt + 10, 5, scrollPaneKoszyk.getWidth() - 50, 18);
                labelNazwa.setFont(new Font(null, Font.BOLD, 16));

                labelCena.setBounds(labelNazwa.getX(), labelNazwa.getY() + 30, scrollPaneKoszyk.getWidth() - 50, 16);
                labelCena.setFont(new Font(null, Font.BOLD, 16));

                labelRozmiar.setBounds(labelNazwa.getX(), labelNazwa.getY() + 60, scrollPaneKoszyk.getWidth() - 50, 16);
                labelRozmiar.setFont(new Font(null, Font.BOLD, 16));

                obrazek = produktWKoszyku.getProdukt().getIcon(wysokoscPanelProdukt, wysokoscPanelProdukt);
                JLabel labelObrazek = new JLabel();
                labelObrazek.setIcon(obrazek);
                labelObrazek.setBorder(BorderFactory.createLineBorder(new Color(242, 242, 242)));
                labelObrazek.setBounds(0, 0, wysokoscPanelProdukt, wysokoscPanelProdukt);

                int buttonX = scrollPaneKoszyk.getWidth() - 50;
                buttonUsun.setBounds(buttonX, 0, 20, 20);
                buttonUsun.addActionListener(koszykListener);
                buttonUsun.setFocusable(false);
                buttonUsun.setFont(new Font(null, Font.PLAIN, 20));
                buttonUsun.setBorder(null);
                buttonUsun.setBackground(null);
                buttonUsun.setForeground(Color.gray);

                int iloscWMagazynie = produktWKoszyku.getProdukt().getRozmiary().get(produktWKoszyku.getRozmiar());
                String[] opcjeWyboru = new String[iloscWMagazynie];

                for (int j = 1; j <= opcjeWyboru.length; j++) {
                    opcjeWyboru[j-1] = String.valueOf(j);
                }

                comboList.add(new JComboBox(opcjeWyboru));
                comboList.get(i).setBounds(labelNazwa.getX(), wysokoscPanelProdukt-40,50,30);
                comboList.get(i).setBackground(new Color(255,255,255));
                comboList.get(i).setBorder(null);
                comboList.get(i).addActionListener(koszykListener);
                comboList.get(i).setSelectedItem(0);

                panelProdukt.add(labelObrazek);
                panelProdukt.add(comboList.get(i));
                panelProdukt.add(buttonUsun);
                panelProdukt.add(labelCena);
                panelProdukt.add(labelNazwa);
                panelProdukt.add(labelRozmiar);
                panelKoszyk.add(panelProdukt);

                comboProduktMap.put(comboList.get(i), produktWKoszyku.getProdukt());
                buttonProduktMap.put(buttonUsun, produktWKoszyku.getProdukt());
            }
        }

        panelKoszyk.setPreferredSize(new Dimension(700, panelKoszyk.getComponentCount() * ((scrollPaneKoszyk.getHeight() / 4) + 10)));
        panelGlowny.add(scrollPaneKoszyk);

    }

    public void rysujPanelDostawy(JPanel panelGlowny, Sklep sklep) {
        panelDostawy = new JPanel();
        panelDostawy.setBackground(new Color(240,252,255));
        panelDostawy.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        panelDostawy.setBounds(100, 125, 700, 500);
        panelDostawy.setLayout(null);
        panelGlowny.add(panelDostawy);
        koszykListener = new KoszykListener(this);

        //RADIO BUTTONS
        buttonPaczkomat = new JRadioButton();
        buttonKurier = new JRadioButton();

        //GROUP
        ButtonGroup group = new ButtonGroup();
        group.add(buttonKurier);
        group.add(buttonPaczkomat);

        //LABELS
        JLabel labelImie;
        JLabel labelNazwisko;
        JLabel labelUlica;
        JLabel labelNumerDomu;
        JLabel labelKodPocztowy;
        JLabel labelMiejscowosc;
        JLabel labelKraj;
        JLabel labelNumerTelefonu;
        JLabel labelEmail;
        JLabel labelWybierzDostawe;

        // RADIO BUTTON PACZKOMAT
        buttonPaczkomat.setText("Paczkomat");
        buttonPaczkomat.setBackground(null);
        buttonPaczkomat.setFocusable(false);
        buttonPaczkomat.setBounds(65, 65, 100, 30);
        buttonPaczkomat.addActionListener(koszykListener);
        buttonPaczkomat.setSelected(true);
        sklep.getZalogowanyKlient().getKoszyk().ustawMetodeDostawy(new DostawaPaczkomat());
        panelDostawy.add(buttonPaczkomat);

        // RADIO BUTTON KURIER
        buttonKurier.setText("Kurier");
        buttonKurier.setBackground(null);
        buttonKurier.setFocusable(false);
        buttonKurier.setBounds(65, 90, 100, 30);
        buttonKurier.addActionListener(koszykListener);
        panelDostawy.add(buttonKurier);

        //LABEL WYBIERZ DOSTAWE
        labelWybierzDostawe = new JLabel("Wybierz sposob dostawy:");
        labelWybierzDostawe.setBounds(60, 15, 200, 40);
        labelWybierzDostawe.setFont(new Font(null, Font.BOLD, 15));
        panelDostawy.add(labelWybierzDostawe);

        //TEXT FIELDY/LABELE
        textImie = new JTextField(sklep.getZalogowanyKlient().getImie());
        textImie.setBounds(60, 170, 250, 40);
        panelDostawy.add(textImie);
        labelImie = new JLabel("IMIE");
        labelImie.setBounds(60, 155, 50, 10);
        panelDostawy.add(labelImie);

        textNazwisko = new JTextField(sklep.getZalogowanyKlient().getNazwisko());
        textNazwisko.setBounds(60, 245, 250, 40);
        panelDostawy.add(textNazwisko);
        labelNazwisko = new JLabel("NAZWISKO");
        labelNazwisko.setBounds(60, 230, 150, 10);
        panelDostawy.add(labelNazwisko);

        textUlica = new JTextField();
        textUlica.setBounds(60, 320, 150, 40);
        panelDostawy.add(textUlica);
        labelUlica = new JLabel("ULICA");
        labelUlica.setBounds(60, 305, 50, 10);
        panelDostawy.add(labelUlica);

        textNumerDomu = new JTextField();
        textNumerDomu.setBounds(240, 320, 80, 40);
        panelDostawy.add(textNumerDomu);
        labelNumerDomu = new JLabel("NR. DOMU");
        labelNumerDomu.setBounds(255, 305, 150, 10);
        panelDostawy.add(labelNumerDomu);

        textKodPocztowy = new JTextField();
        textKodPocztowy.setBounds(60, 395, 80, 40);
        panelDostawy.add(textKodPocztowy);
        labelKodPocztowy = new JLabel("KOD POCZTOWY");
        labelKodPocztowy.setBounds(60, 380, 150, 10);
        panelDostawy.add(labelKodPocztowy);

        textMiejscowosc = new JTextField();
        textMiejscowosc.setBounds(170, 395, 150, 40);
        panelDostawy.add(textMiejscowosc);
        labelMiejscowosc = new JLabel("MIEJSCOWOSC");
        labelMiejscowosc.setBounds(230, 380, 150, 10);
        panelDostawy.add(labelMiejscowosc);

        textKraj = new JTextField();
        textKraj.setBounds(340, 170, 250, 40);
        panelDostawy.add(textKraj);
        labelKraj = new JLabel("KRAJ");
        labelKraj.setBounds(340, 155, 50, 10);
        panelDostawy.add(labelKraj);

        textNumerTelefonu = new JTextField(sklep.getZalogowanyKlient().getNumer_telefonu());
        textNumerTelefonu.setBounds(340, 245, 250, 40);
        panelDostawy.add(textNumerTelefonu);
        labelNumerTelefonu = new JLabel("NR TEL");
        labelNumerTelefonu.setBounds(340, 230, 150, 10);
        panelDostawy.add(labelNumerTelefonu);

        textEmail = new JTextField(sklep.getZalogowanyKlient().getAdresEmail());
        textEmail.setBounds(340, 320, 250, 40);
        panelDostawy.add(textEmail);
        labelEmail = new JLabel("EMAIL");
        labelEmail.setBounds(340, 305, 50, 10);
        panelDostawy.add(labelEmail);

    }

    public void rysujPanelPlatnosci(JPanel panelGlowny, Sklep sklep) {
        panelPlatnosci = new JPanel();
        panelPlatnosci.setBackground(new Color(240,252,255));
        panelPlatnosci.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        panelPlatnosci.setBounds(100, 125, 700, 500);
        panelPlatnosci.setLayout(null);
        panelGlowny.add(panelPlatnosci);
        koszykListener = new KoszykListener(this);

        //RADIO BUTTONS
        buttonBlik = new JRadioButton();
        buttonKartaKredytowa = new JRadioButton();

        //GROUP
        ButtonGroup group = new ButtonGroup();
        group.add(buttonBlik);
        group.add(buttonKartaKredytowa);

        //LABELS
        JLabel labelKodBlik;
        JLabel labelNumerKarty;
        JLabel labelDataWygasniecia;
        JLabel labelCvv;
        JLabel labelKartaImie;
        JLabel labelKartaNazwisko;
        JLabel labelWybierzPlatnosc;

        // RADIO BUTTON BLIK
        buttonBlik.setText("Blik");
        buttonBlik.setBackground(null);
        buttonBlik.setFocusable(false);
        buttonBlik.setBounds(65, 85, 100, 30);
        buttonBlik.addActionListener(koszykListener);
        buttonBlik.setSelected(true);
        sklep.getZalogowanyKlient().getKoszyk().ustawMetodePlatnosci(new placBlikiem());
        panelPlatnosci.add(buttonBlik);

        // RADIO BUTTON KARTA KREDYTOWA
        buttonKartaKredytowa.setText("Karta kredytowa");
        buttonKartaKredytowa.setBackground(null);
        buttonKartaKredytowa.setFocusable(false);
        buttonKartaKredytowa.setBounds(65, 200, 150, 30);
        buttonKartaKredytowa.addActionListener(koszykListener);
        panelPlatnosci.add(buttonKartaKredytowa);

        //LABEL WYBIERZ DOSTAWE
        labelWybierzPlatnosc = new JLabel("Wybierz sposob platnosci:");
        labelWybierzPlatnosc.setBounds(60, 15, 200, 40);
        labelWybierzPlatnosc.setFont(new Font(null, Font.BOLD, 15));
        panelPlatnosci.add(labelWybierzPlatnosc);

        //TEXT FIELD/LABELS
        textKodBlik = new JTextField();
        textKodBlik.setBounds(60, 140, 250, 40);
        panelPlatnosci.add(textKodBlik);
        labelKodBlik = new JLabel("KOD BLIK");
        labelKodBlik.setBounds(60, 125, 120, 10);
        panelPlatnosci.add(labelKodBlik);

        textNumerKarty = new JTextField();
        textNumerKarty.setBounds(60, 260, 250, 40);
        panelPlatnosci.add(textNumerKarty);
        labelNumerKarty = new JLabel("NR KARTY");
        labelNumerKarty.setBounds(60, 245, 120, 10);
        panelPlatnosci.add(labelNumerKarty);

        textDataWygasniecia = new JTextField();
        textDataWygasniecia.setBounds(60, 335, 150, 40);
        panelPlatnosci.add(textDataWygasniecia);
        labelDataWygasniecia = new JLabel("DATA WYGASNIECIA");
        labelDataWygasniecia.setBounds(60, 320, 120, 10);
        panelPlatnosci.add(labelDataWygasniecia);

        textCvv = new JTextField();
        textCvv.setBounds(230, 335, 80, 40);
        panelPlatnosci.add(textCvv);
        labelCvv = new JLabel("CVV");
        labelCvv.setBounds(230, 320, 120, 10);
        panelPlatnosci.add(labelCvv);

        textKartaImie = new JTextField();
        textKartaImie.setBounds(330, 260, 250, 40);
        panelPlatnosci.add(textKartaImie);
        labelKartaImie = new JLabel("IMIĘ");
        labelKartaImie.setBounds(330, 245, 120, 10);
        panelPlatnosci.add(labelKartaImie);

        textKartaNazwisko = new JTextField();
        textKartaNazwisko.setBounds(330, 335, 250, 40);
        panelPlatnosci.add(textKartaNazwisko);
        labelKartaNazwisko = new JLabel("NAZWISKO");
        labelKartaNazwisko.setBounds(330, 320, 120, 10);
        panelPlatnosci.add(labelKartaNazwisko);
    }

    //GETTERY I SETTERY
    public JPanel getPanelPlatnosci() {
        return panelPlatnosci;
    }

    public JButton getButtonCofnijPlatnosc() {
        return buttonCofnijPlatnosc;
    }

    public JButton getButtonCofnijDostawa() {
        return buttonCofnijDostawa;
    }

    public ArrayList<JComboBox> getComboList() {
        return comboList;
    }

    public Map<JComboBox, Produkt> getComboProduktMap() {
        return comboProduktMap;
    }

    public JTextField getTextKodBlik() {
        return textKodBlik;
    }

    public JTextField getTextNumerKarty() {
        return textNumerKarty;
    }

    public JTextField getTextDataWygasniecia() {
        return textDataWygasniecia;
    }

    public JTextField getTextCvv() {
        return textCvv;
    }

    public JTextField getTextKartaImie() {
        return textKartaImie;
    }

    public JTextField getTextKartaNazwisko() {
        return textKartaNazwisko;
    }

    public JRadioButton getButtonBlik() {
        return buttonBlik;
    }

    public JRadioButton getButtonKartaKredytowa() {
        return buttonKartaKredytowa;
    }

    public KoszykListener getKoszykListener() {
        return koszykListener;
    }

    public JTextField getTextImie() {
        return textImie;
    }

    public JTextField getTextNazwisko() {
        return textNazwisko;
    }

    public JTextField getTextUlica() {
        return textUlica;
    }

    public JTextField getTextNumerDomu() {
        return textNumerDomu;
    }

    public JTextField getTextKodPocztowy() {
        return textKodPocztowy;
    }

    public JTextField getTextMiejscowosc() {
        return textMiejscowosc;
    }

    public JTextField getTextKraj() {
        return textKraj;
    }

    public JTextField getTextNumerTelefonu() {
        return textNumerTelefonu;
    }

    public JTextField getTextEmail() {
        return textEmail;
    }

    public Map<JButton, Produkt> getButtonProduktMap() {
        return buttonProduktMap;
    }

    public JButton getButtonLogoSklep() {
        return buttonLogoSklep;
    }

    public JButton getButtonPlatnosc() {
        return buttonPlatnosc;
    }

    public void setCenaDostawa(float cenaDostawa) {
        this.cenaDostawa = cenaDostawa;
    }

    public JButton getButtonWylogujSie() {
        return buttonWylogujSie;
    }

    public JButton getBuutonPowiadomienia() {
        return buutonPowiadomienia;
    }

    public float getCenaDostawa() {
        return cenaDostawa;
    }

    public JPanel getPanelDostawy() {
        return panelDostawy;
    }

    public JLabel getLabelKoszyk() {
        return labelKoszyk;
    }

    public JLabel getLabelKoszyk2() {
        return labelKoszyk2;
    }

    public JLabel getLabelDostawa() {
        return labelDostawa;
    }

    public JLabel getLabelPlatnosc() {
        return labelPlatnosc;
    }

    public JLabel getLabelPodsumowanie() {
        return labelPodsumowanie;
    }

    public JLabel getLabelCenaKoszyka() {
        return labelCenaKoszyka;
    }

    public JLabel getLabelCenaDostawy() {
        return labelCenaDostawy;
    }

    public JLabel getLabelSumaCen() {
        return labelSumaCen;
    }

    public JScrollPane getScrollPaneKoszyk() {
        return this.scrollPaneKoszyk;
    }

    public JPanel getPanelGlowny() {
        return this.panelGlowny;
    }

    public JPanel getPanelKoszyk() {
        return this.panelKoszyk;
    }

    public JButton getButtonZamowienie() {
        return buttonZamowienie;
    }

    public Sklep getSklep() {
        return sklep;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JPanel getPanelGorny() {
        return panelGorny;
    }

    public JPanel getPanelGorny2() {
        return panelGorny2;
    }

    public JPanel getPanelPodsumowanie() {
        return panelPodsumowanie;
    }

    public JButton getButtonDostawa() {
        return buttonDostawa;
    }

    public JRadioButton getButtonPaczkomat() {
        return buttonPaczkomat;
    }

    public JRadioButton getButtonKurier() {
        return buttonKurier;
    }

    public JButton getButtonUsun() {
        return buttonUsun;
    }

    public KoszykPane() {
        super();
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public Koszyk getKoszyk() {
        return koszyk;
    }

    public float getSumaCen() {
        sumaCen = (float)koszyk.getWartoscZamowienia()+cenaDostawa;
        return sumaCen;
    }

    public static void main(String[] args) {
        Sklep sklep = new Sklep(new ArrayList<>(), null, false, new ArrayList<>());

        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sklep.promocja.wczytajObserwatorowPromocji();
        sklep.wczytajListeProduktow();
        sklep.wczytajListeKlientow();

        sklep.zalogowanyKlient = sklep.getListaKlientow().get(0);

        KoszykPane koszykPane = new KoszykPane();
        koszykPane.otworzKoszyk(sklep, jFrame);
    }
}
