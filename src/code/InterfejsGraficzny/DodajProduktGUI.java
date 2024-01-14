package code.InterfejsGraficzny;


import code.Main.Sklep;
import code.inputValidate.InvalidFileFormatException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DodajProduktGUI implements ActionListener {

    private Sklep sklep;
    private JFrame ramka;
    private JLabel ikonaLabel;

    private JTextField[] podstawoweDaneTextField = new JTextField[9];
    private JTextField[] textFieldObuwie = new JTextField[3];
    private JTextField[] textFieldBluza = new JTextField[4];
    private JTextField[] textFieldKoszulka = new JTextField[3];
    private JTextField[] textFieldSpodnie = new JTextField[4];
    JRadioButton czyZKapturem;
    private int licznik;

    private JRadioButton radioButtonObuwie;
    private JRadioButton radioButtonBluza;
    private JRadioButton radioButtonKoszulka;
    private JRadioButton radioButtonSpodnie;
    private JButton returnButton;
    private JButton ikonaPrzycisk;
    private JButton zatwierdzTyp;
    private JButton dodajProdukt;
    private JPanel panelPodstawowy;
    private JPanel panelWyboru;
    private JScrollPane scrollPanel;
    private JButton dodajRozmiar;
    private JPanel panelGlowny;
    private ArrayList<JTextField> listaPolRozmiarow;
    private TreeMap<String, Integer> mapaRozmiarow;

    private ImageIcon icon;
    public static void openGUI(Sklep sklep, JFrame ramka){
        DodajProduktGUI GUI = new DodajProduktGUI();
        GUI.dodajProduktGUI(sklep, ramka);
    }
    public void dodajProduktGUI(Sklep sklep, JFrame ramka){
        this.sklep = sklep;
        this.ramka=ramka;

        panelGlowny = new JPanel();
        panelGlowny.setLayout(new BorderLayout());

        radioButtonObuwie = new JRadioButton("Obuwie");
        radioButtonBluza = new JRadioButton("Bluza");
        radioButtonKoszulka = new JRadioButton("Koszulka");
        radioButtonSpodnie = new JRadioButton("Spodnie");

        zatwierdzTyp = new JButton("Zatwierdź");
        zatwierdzTyp.setBackground(Color.WHITE);
        zatwierdzTyp.setMargin(new Insets(10, 20, 10, 20));
        zatwierdzTyp.addActionListener(this);

        panelWyboru = new JPanel();

        panelWyboru.setLayout(new BoxLayout(panelWyboru, BoxLayout.Y_AXIS));

        ButtonGroup grupa = new ButtonGroup();


        panelWyboru.add(radioButtonObuwie);
        panelWyboru.add(radioButtonBluza);
        panelWyboru.add(radioButtonKoszulka);
        panelWyboru.add(radioButtonSpodnie);
        panelWyboru.add(zatwierdzTyp);

        grupa.add(radioButtonObuwie);
        grupa.add(radioButtonBluza);
        grupa.add(radioButtonKoszulka);
        grupa.add(radioButtonSpodnie);

        JPanel panelGora = new JPanel();
        panelGora.setLayout(new FlowLayout(FlowLayout.LEFT));
        panelGora.setBackground(new Color(255, 69, 0));


        returnButton = new JButton();
        returnButton.setIcon(SklepGUI.scaleIcon("src\\resources\\Obrazki\\cofnij.png", 50));
        returnButton.addActionListener(this);
        returnButton.setFocusPainted(false);
        returnButton.setOpaque(false);
        returnButton.setBackground(new Color(250, 246, 246, 0));
        returnButton.setBounds(10,10,50,50);

        panelGora.add(returnButton);

        //dodawanie ikony
        JPanel dodawanieIkonyPanel = new JPanel();
        dodawanieIkonyPanel.setLayout(new BoxLayout(dodawanieIkonyPanel, BoxLayout.Y_AXIS));
        dodawanieIkonyPanel.setSize(200,600);
        ikonaLabel = new JLabel();
        ikonaLabel.setMaximumSize(new Dimension(200, 200));
        ikonaLabel.setBorder(new LineBorder(Color.BLACK, 5));

        ikonaPrzycisk= new JButton("DODAJ OBRAZ");
        ikonaPrzycisk.setPreferredSize(new Dimension(210,50));
        ikonaPrzycisk.setMaximumSize(new Dimension(200,50));
        ikonaPrzycisk.setBackground(Color.WHITE);
        ikonaPrzycisk.addActionListener(this);

        dodawanieIkonyPanel.add(ikonaPrzycisk);
        dodawanieIkonyPanel.add(ikonaLabel);

        //dodawanie rozmiarow
        JPanel panelDodawaniaRozmiarow = new JPanel();
        panelDodawaniaRozmiarow.setLayout(new BorderLayout());

        JPanel panelRozmiarow = new JPanel();
        panelRozmiarow.setLayout(new GridLayout(0,2));
        panelRozmiarow.add(new JLabel("ROZMIAR"));
        panelRozmiarow.add(new JLabel("ILOŚĆ"));

        dodajRozmiar = new JButton("DODAJ, ROZMIAR");
        dodajRozmiar.setBackground(Color.WHITE);
        dodajRozmiar.setMargin(new Insets(10, 20, 10, 20));
        dodajRozmiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                licznik++;
                JTextField rozmiarPole = new JTextField();
                JTextField iloscPole = new JTextField();
                listaPolRozmiarow.add(rozmiarPole);
                listaPolRozmiarow.add(iloscPole);
                panelRozmiarow.add(rozmiarPole);
                panelRozmiarow.add(iloscPole);
                panelRozmiarow.revalidate();
                panelRozmiarow.repaint();
            }
        });

        panelDodawaniaRozmiarow.add(BorderLayout.NORTH, panelRozmiarow);
        panelDodawaniaRozmiarow.add(BorderLayout.SOUTH, dodajRozmiar);
        JScrollPane scrollRozmiary = new JScrollPane(panelDodawaniaRozmiarow);
        panelWyboru.add(scrollRozmiary);

        dodajProduktBaza();

        panelGlowny.add(BorderLayout.EAST, dodawanieIkonyPanel);
        panelGlowny.add(BorderLayout.WEST, panelWyboru);
        panelGlowny.add(BorderLayout.NORTH, panelGora);

        ramka.setLayout(null);
        ramka.setContentPane(panelGlowny);
        ramka.setVisible(true);

    }
    public void dodajProduktBaza(){
        licznik = 0;
        listaPolRozmiarow = new ArrayList<>();
        mapaRozmiarow = new TreeMap<>();
        panelPodstawowy = new JPanel();
        scrollPanel = new JScrollPane();
        scrollPanel.setViewportView(panelPodstawowy);

        JLabel[] podstawoweDaneLabel = new JLabel[8];

        podstawoweDaneLabel[0] = new JLabel("id:");
        podstawoweDaneLabel[1] = new JLabel("cena:");
        podstawoweDaneLabel[2] = new JLabel("nazwa:");
        podstawoweDaneLabel[3] = new JLabel("opis:");
        podstawoweDaneLabel[4] = new JLabel("material:");
        podstawoweDaneLabel[5] = new JLabel("kolor:");
        podstawoweDaneLabel[6] = new JLabel("nazwa producenta:");
        podstawoweDaneLabel[7] = new JLabel("kraj producnta:");


        panelGlowny.add(BorderLayout.CENTER, scrollPanel);
        panelPodstawowy.setLayout(new GridLayout((14),2));

        for(int i=0; i<8; i++){
            panelPodstawowy.add(podstawoweDaneLabel[i]);
            panelPodstawowy.add(podstawoweDaneTextField[i]= new JTextField());
        }

    }

    public void dodajObuwie(){

        JLabel[] labelObuwie = new JLabel[2];

        labelObuwie[0] = new JLabel("typ obuwia:");
        labelObuwie[1] = new JLabel("typ podeszwy:");

        dodajProdukt = new JButton("Dodaj produkt");
        dodajProdukt.setBackground(Color.WHITE);
        dodajProdukt.setMargin(new Insets(10, 20, 10, 20));
        dodajProdukt.addActionListener(this);

        for(int i=0; i<2; i++){
            panelPodstawowy.add(labelObuwie[i]);
            panelPodstawowy.add(textFieldObuwie[i] = new JTextField());
        }
        panelPodstawowy.add(dodajProdukt);

    }
    public void dodajBluze(){

        JLabel[] labelBluza = new JLabel[3];

        czyZKapturem = new JRadioButton("kaptur");

        labelBluza[0] = new JLabel("kaptur:");
        labelBluza[1] = new JLabel("dekolt:");
        labelBluza[2] = new JLabel("kroj:");

        dodajProdukt = new JButton("Dodaj produkt");
        dodajProdukt.setBackground(Color.WHITE);
        dodajProdukt.setMargin(new Insets(10, 20, 10, 20));
        dodajProdukt.addActionListener(this);

        for(int i=0; i<3; i++){
            panelPodstawowy.add(labelBluza[i]);
            if(i==0){
                panelPodstawowy.add(czyZKapturem);
            }else {
                panelPodstawowy.add(textFieldBluza[i] = new JTextField());
            }
        }
        panelPodstawowy.add(dodajProdukt);

    }

    public void dodajKoszulke(){

        JLabel[] labelKoszulka = new JLabel[2];

        labelKoszulka[0] = new JLabel("dekolt:");
        labelKoszulka[1] = new JLabel("kroj:");

        dodajProdukt = new JButton("Dodaj produkt");
        dodajProdukt.setBackground(Color.WHITE);
        dodajProdukt.setMargin(new Insets(10, 20, 10, 20));
        dodajProdukt.addActionListener(this);

        for(int i=0; i<2; i++){
            panelPodstawowy.add(labelKoszulka[i]);
            panelPodstawowy.add(textFieldKoszulka[i] = new JTextField());
        }
        panelPodstawowy.add(dodajProdukt);

    }

    public void dodajSpodnie(){

        JLabel[] labelSpodnie = new JLabel[3];

        labelSpodnie[0] = new JLabel("dlugosc:");
        labelSpodnie[1] = new JLabel("typ:");
        labelSpodnie[2] = new JLabel("kroj:");

        dodajProdukt = new JButton("Dodaj produkt");
        dodajProdukt.setBackground(Color.WHITE);
        dodajProdukt.setMargin(new Insets(10, 20, 10, 20));
        dodajProdukt.addActionListener(this);

        for(int i=0; i<3; i++){
            panelPodstawowy.add(labelSpodnie[i]);
            panelPodstawowy.add(textFieldSpodnie[i] = new JTextField());
        }
        panelPodstawowy.add(dodajProdukt);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == zatwierdzTyp) {
            panelGlowny.remove(scrollPanel);
            if (dodajProdukt != null) panelWyboru.remove(dodajProdukt);
            dodajProduktBaza();
            if (radioButtonObuwie.isSelected()) {
                dodajObuwie();
            }
            if (radioButtonBluza.isSelected()) {
                dodajBluze();
            }
            if (radioButtonKoszulka.isSelected()) {
                dodajKoszulke();
            }
            if (radioButtonSpodnie.isSelected()) {
                dodajSpodnie();
            }
            panelGlowny.revalidate();
            panelGlowny.repaint();
        }
        if(e.getSource() == dodajProdukt){
            if (radioButtonObuwie.isSelected()) {
                for (int i = 0; i < listaPolRozmiarow.size(); i = i+2) {
                    mapaRozmiarow.put(listaPolRozmiarow.get(i).getText(), Integer.parseInt(listaPolRozmiarow.get(i+1).getText()));
                }
                sklep.dodajObuwie(podstawoweDaneTextField[0].getText(), podstawoweDaneTextField[1].getText(), podstawoweDaneTextField[2].getText(), mapaRozmiarow, podstawoweDaneTextField[3].getText(), podstawoweDaneTextField[4].getText(), podstawoweDaneTextField[5].getText(), podstawoweDaneTextField[6].getText(), podstawoweDaneTextField[7].getText(), textFieldObuwie[0].getText(), textFieldObuwie[1].getText(), icon);
            }
            if (radioButtonBluza.isSelected()) {
                for (int i = 0; i < listaPolRozmiarow.size(); i = i+2) {
                    mapaRozmiarow.put(listaPolRozmiarow.get(i).getText(), Integer.parseInt(listaPolRozmiarow.get(i+1).getText()));
                }
                sklep.dodajBluze(podstawoweDaneTextField[0].getText(), podstawoweDaneTextField[1].getText(), podstawoweDaneTextField[2].getText(), mapaRozmiarow, podstawoweDaneTextField[3].getText(), podstawoweDaneTextField[4].getText(), podstawoweDaneTextField[5].getText(), podstawoweDaneTextField[6].getText(), podstawoweDaneTextField[7].getText(), czyZKapturem.isSelected(), textFieldBluza[1].getText(), textFieldBluza[2].getText(), icon);
            }
            if (radioButtonKoszulka.isSelected()) {
                for (int i = 0; i < listaPolRozmiarow.size(); i = i+2) {
                    mapaRozmiarow.put(listaPolRozmiarow.get(i).getText(), Integer.parseInt(listaPolRozmiarow.get(i+1).getText()));
                }
                sklep.dodajKoszulke(podstawoweDaneTextField[0].getText(), podstawoweDaneTextField[1].getText(), podstawoweDaneTextField[2].getText(), mapaRozmiarow, podstawoweDaneTextField[3].getText(), podstawoweDaneTextField[4].getText(), podstawoweDaneTextField[5].getText(), podstawoweDaneTextField[6].getText(), podstawoweDaneTextField[7].getText(), textFieldKoszulka[0].getText(), textFieldKoszulka[1].getText(), icon);
            }
            if (radioButtonSpodnie.isSelected()) {
                for (int i = 0; i < listaPolRozmiarow.size(); i = i+2) {
                    mapaRozmiarow.put(listaPolRozmiarow.get(i).getText(), Integer.parseInt(listaPolRozmiarow.get(i+1).getText()));
                }

                for (Map.Entry<String, Integer> entry : mapaRozmiarow.entrySet()) {
                    System.out.println(entry.getKey());
                    System.out.println(entry.getValue());
                }
                sklep.dodajSpodnie(podstawoweDaneTextField[0].getText(), podstawoweDaneTextField[1].getText(), podstawoweDaneTextField[2].getText(), mapaRozmiarow, podstawoweDaneTextField[3].getText(), podstawoweDaneTextField[4].getText(), podstawoweDaneTextField[5].getText(), podstawoweDaneTextField[6].getText(), podstawoweDaneTextField[7].getText(), textFieldSpodnie[0].getText(), textFieldSpodnie[1].getText(), textFieldSpodnie[2].getText(), icon);
            }
        }
        if(e.getSource()==returnButton){
            ramka.getContentPane().removeAll();
            ramka.revalidate();
            ramka.repaint();
            ramka.setLayout(null);
            GUIadmin.openAdmin(sklep, ramka);
        }
        if (e.getSource() == ikonaPrzycisk) {
            JFileChooser fileChooser = new JFileChooser();
            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                try {
                InvalidFileFormatException.checkFileFormat(fileChooser.getSelectedFile());
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                    icon = new ImageIcon(filePath);
                    Image img = icon.getImage();
                    Image newImg = img.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
                    icon = new ImageIcon(newImg);
                    ikonaLabel.setIcon(icon);
            } catch (InvalidFileFormatException ex) {
               JOptionPane.showMessageDialog(ramka, ex.getMessage(), "Błąd formatu pliku", JOptionPane.ERROR_MESSAGE);
            }
            }
        }

    }
}
