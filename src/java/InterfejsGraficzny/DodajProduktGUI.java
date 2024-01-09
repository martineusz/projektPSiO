package InterfejsGraficzny;

import Main.Sklep;
import inputValidate.InvalidFileFormatException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DodajProduktGUI implements ActionListener {

    private Sklep sklep;
    private JFrame ramka;

    private JTextField[] podstawoweDaneTextField = new JTextField[9];
    private JTextField[] textFieldObuwie = new JTextField[3];
    private JTextField[] textFieldBluza = new JTextField[4];
    private JTextField[] textFieldKoszulka = new JTextField[3];
    private JTextField[] textFieldSpodnie = new JTextField[4];
    JRadioButton czyZKapturem;

    private JRadioButton radioButtonObuwie;
    private JRadioButton radioButtonBluza;
    private JRadioButton radioButtonKoszulka;
    private JRadioButton radioButtonSpodnie;
    private JButton returnButton;
    private JButton dodajzdjecie;
    private JButton zatwierdzTyp;
    private JButton dodajProdukt;
    private JButton przyciskWyboru;
    private JPanel panelPodstawowy;
    private JPanel panelGlowny;
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

        zatwierdzTyp = new JButton("zatwierdz");
        zatwierdzTyp.setBackground(Color.WHITE);
        zatwierdzTyp.setMargin(new Insets(10, 20, 10, 20));
        zatwierdzTyp.addActionListener(this);

        JPanel panelWyboru = new JPanel();
        dodajProduktBaza();



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


        returnButton = new JButton("powrot");
        returnButton.setBackground(Color.WHITE);
        returnButton.setMargin(new Insets(10, 20, 10, 20));
        returnButton.addActionListener(this);

        //dodawanie ikony
        JPanel dodawanieIkonyPanel = new JPanel();
        JLabel ikonaLabel = new JLabel("Wybierz obraz");

        przyciskWyboru= new JButton();
        przyciskWyboru.addActionListener(this);

        dodawanieIkonyPanel.setLayout(new BorderLayout());
        dodawanieIkonyPanel.add(BorderLayout.CENTER, przyciskWyboru);
        dodawanieIkonyPanel.add(BorderLayout.SOUTH, ikonaLabel);


        panelGlowny.add(BorderLayout.EAST, dodawanieIkonyPanel);
        panelGlowny.add(BorderLayout.WEST, panelWyboru);
        panelGlowny.add(BorderLayout.SOUTH, returnButton);

        ramka.setLayout(null);
        ramka.setContentPane(panelGlowny);
        ramka.setVisible(true);
    }
    public void dodajProduktBaza(){
        panelPodstawowy = new JPanel();

        JLabel[] podstawoweDaneLabel = new JLabel[9];

        podstawoweDaneLabel[0] = new JLabel("id:");
        podstawoweDaneLabel[1] = new JLabel("cena:");
        podstawoweDaneLabel[2] = new JLabel("nazwa:");
        podstawoweDaneLabel[3] = new JLabel("ilosc w magazynie:");
        podstawoweDaneLabel[4] = new JLabel("opis:");
        podstawoweDaneLabel[5] = new JLabel("material:");
        podstawoweDaneLabel[6] = new JLabel("kolor:");
        podstawoweDaneLabel[7] = new JLabel("nazwa producenta:");
        podstawoweDaneLabel[8] = new JLabel("kraj producnta:");


        panelGlowny.add(BorderLayout.CENTER, panelPodstawowy);
        panelPodstawowy.setLayout(new GridLayout(14,2));

        for(int i=0; i<9; i++){
            panelPodstawowy.add(podstawoweDaneLabel[i]);
            panelPodstawowy.add(podstawoweDaneTextField[i]= new JTextField());
        }
    }
    public void dodajObuwie(){

        JLabel[] labelObuwie = new JLabel[3];

        labelObuwie[0] = new JLabel("rozmiar:");
        labelObuwie[1] = new JLabel("typ obuwia:");
        labelObuwie[2] = new JLabel("typ podeszwy:");

        dodajProdukt = new JButton("dodaj produkt");
        dodajProdukt.setBackground(Color.WHITE);
        dodajProdukt.setMargin(new Insets(10, 20, 10, 20));
        dodajProdukt.addActionListener(this);

        for(int i=0; i<3; i++){
            panelPodstawowy.add(labelObuwie[i]);
            panelPodstawowy.add(textFieldObuwie[i] = new JTextField());
        }
        panelPodstawowy.add(dodajProdukt);

    }
    public void dodajBluze(){

        JLabel[] labelBluza = new JLabel[4];

        czyZKapturem = new JRadioButton("kaptur");

        labelBluza[0] = new JLabel("rozmiar:");
        labelBluza[1] = new JLabel("kaptur:");
        labelBluza[2] = new JLabel("dekolt:");
        labelBluza[3] = new JLabel("kroj:");

        dodajProdukt = new JButton("dodaj produkt");
        dodajProdukt.setBackground(Color.WHITE);
        dodajProdukt.setMargin(new Insets(10, 20, 10, 20));
        dodajProdukt.addActionListener(this);

        for(int i=0; i<4; i++){
            panelPodstawowy.add(labelBluza[i]);
            if(i==1){
                panelPodstawowy.add(czyZKapturem);
            }else {
                panelPodstawowy.add(textFieldBluza[i] = new JTextField());
            }
        }
        panelPodstawowy.add(dodajProdukt);

    }

    public void dodajKoszulke(){

        JLabel[] labelKoszulka = new JLabel[3];

        labelKoszulka[0] = new JLabel("rozmiar:");
        labelKoszulka[1] = new JLabel("dekolt:");
        labelKoszulka[2] = new JLabel("kroj:");

        dodajProdukt = new JButton("dodaj produkt");
        dodajProdukt.setBackground(Color.WHITE);
        dodajProdukt.setMargin(new Insets(10, 20, 10, 20));
        dodajProdukt.addActionListener(this);

        for(int i=0; i<3; i++){
            panelPodstawowy.add(labelKoszulka[i]);
            panelPodstawowy.add(textFieldKoszulka[i] = new JTextField());
        }
        panelPodstawowy.add(dodajProdukt);

    }

    public void dodajSpodnie(){

        JLabel[] labelSpodnie = new JLabel[4];

        labelSpodnie[0] = new JLabel("rozmiar:");
        labelSpodnie[1] = new JLabel("dlugosc:");
        labelSpodnie[2] = new JLabel("typ:");
        labelSpodnie[3] = new JLabel("kroj:");

        dodajProdukt = new JButton("dodaj produkt");
        dodajProdukt.setBackground(Color.WHITE);
        dodajProdukt.setMargin(new Insets(10, 20, 10, 20));
        dodajProdukt.addActionListener(this);

        for(int i=0; i<4; i++){
            panelPodstawowy.add(labelSpodnie[i]);
            panelPodstawowy.add(textFieldSpodnie[i] = new JTextField());
        }
        panelPodstawowy.add(dodajProdukt);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == zatwierdzTyp) {
            panelGlowny.remove(panelPodstawowy);
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
            panelPodstawowy.revalidate();
            panelPodstawowy.repaint();
        }
        if(e.getSource() == dodajProdukt){
            if (radioButtonObuwie.isSelected()) {
                sklep.dodajObuwie(podstawoweDaneTextField[0].getText(), podstawoweDaneTextField[1].getText(), podstawoweDaneTextField[2].getText(), podstawoweDaneTextField[3].getText(), podstawoweDaneTextField[4].getText(), podstawoweDaneTextField[5].getText(), podstawoweDaneTextField[6].getText(), podstawoweDaneTextField[7].getText(), podstawoweDaneTextField[8].getText(), textFieldObuwie[0].getText(), textFieldObuwie[1].getText(), textFieldObuwie[2].getText(), icon);
            }
            if (radioButtonBluza.isSelected()) {
                sklep.dodajBluze(podstawoweDaneTextField[0].getText(), podstawoweDaneTextField[1].getText(), podstawoweDaneTextField[2].getText(), podstawoweDaneTextField[3].getText(), podstawoweDaneTextField[4].getText(), podstawoweDaneTextField[5].getText(), podstawoweDaneTextField[6].getText(), podstawoweDaneTextField[7].getText(), podstawoweDaneTextField[8].getText(), textFieldBluza[0].getText(), czyZKapturem.isSelected(), textFieldBluza[2].getText(), textFieldBluza[3].getText(), icon);
            }
            if (radioButtonKoszulka.isSelected()) {
                sklep.dodajKoszulke(podstawoweDaneTextField[0].getText(), podstawoweDaneTextField[1].getText(), podstawoweDaneTextField[2].getText(), podstawoweDaneTextField[3].getText(), podstawoweDaneTextField[4].getText(), podstawoweDaneTextField[5].getText(), podstawoweDaneTextField[6].getText(), podstawoweDaneTextField[7].getText(), podstawoweDaneTextField[8].getText(), textFieldKoszulka[0].getText(), textFieldKoszulka[1].getText(), textFieldKoszulka[2].getText(), icon);
            }
            if (radioButtonSpodnie.isSelected()) {
                sklep.dodajSpodnie(podstawoweDaneTextField[0].getText(), podstawoweDaneTextField[1].getText(), podstawoweDaneTextField[2].getText(), podstawoweDaneTextField[3].getText(), podstawoweDaneTextField[4].getText(), podstawoweDaneTextField[5].getText(), podstawoweDaneTextField[6].getText(), podstawoweDaneTextField[7].getText(), podstawoweDaneTextField[8].getText(), textFieldSpodnie[0].getText(), textFieldSpodnie[1].getText(), textFieldSpodnie[2].getText(), textFieldSpodnie[3].getText(), icon);
            }
        }
        if(e.getSource()==returnButton){
            ramka.getContentPane().removeAll();
            ramka.revalidate();
            ramka.repaint();
            ramka.setLayout(null);
            GUIadmin.openAdmin(sklep, ramka);
        }
        if (e.getSource() == przyciskWyboru) {
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
                    przyciskWyboru.setIcon(icon);
            } catch (InvalidFileFormatException ex) {
               JOptionPane.showMessageDialog(ramka, ex.getMessage(), "Błąd formatu pliku", JOptionPane.ERROR_MESSAGE);
            }
            }
        }
    }
}
