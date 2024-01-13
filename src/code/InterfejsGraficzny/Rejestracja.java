package code.InterfejsGraficzny;

import code.Main.Sklep;

import javax.swing.*;
import code.Main.Klient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class Rejestracja  {

    private static JTextField jTextField6;
    private static JTextField jTextField1;
    private static JTextField jTextField2;
    private static JTextField jTextField3;
    private static JTextField jTextField4;
    private static JTextField jTextField5;
    private static JTextField jTextFieldLogowanie;
    private static JTextField jTextFieldHaslo;
    private static JLabel error;

    private static JLabel e1;
    private static JLabel e2;
    private static JLabel e3;
    private static JLabel e4;
    private static JLabel e5;
    private static JLabel e6;

    public static void ShopPage(Sklep sklep, JFrame frame) {
        JPanel shopJpanel = new JPanel();
        JPanel jpanel1 = new JPanel();
        jpanel1.setBackground(Color.RED);
        jpanel1.setBounds(0,0, 400, 500);
        shopJpanel.add(jpanel1);

        jpanel1.setLayout(null);
        shopJpanel.setBounds(0,0,1050,500);

        JPanel jPanel2 = new JPanel();
        jPanel2.setBounds(400, 0, 600, 500);
        shopJpanel.add(jPanel2);
        jPanel2.setLayout(null);

        Label label1 = new Label("LOGIN");
        Label label3 = new Label("Jesteś tu pierwszy raz?");
        Button rejestrButton = new Button("Zarejestruj się");


        label1.setForeground(Color.black);
        label3.setForeground(Color.black);
        jTextFieldLogowanie = new JTextField();
        jTextFieldHaslo = new JTextField();
        Label label2 = new Label("HASŁO");
        Button apllyButton = new Button("Zatwierdź");
        label2.setForeground(Color.black);

        error = new JLabel("");
        error.setForeground(Color.RED);
        jPanel2.add(error);
        error.setBounds(200, 70, 250, 20);
        JCheckBox adminBox = new JCheckBox("Zaloguj jako administrator");

        jPanel2.add(jTextFieldLogowanie);
        jPanel2.add(jTextFieldHaslo);
        jPanel2.add(label1);
        jPanel2.add(label2);
        jPanel2.add(label3);
        jPanel2.add(adminBox);
        jPanel2.add(apllyButton);
        jPanel2.add(rejestrButton);

        label1.setBounds(100, 110, 50, 10);
        label2.setBounds(100,160,50,10);
        label3.setBounds(185, 350, 130, 20);
        jTextFieldLogowanie.setBounds(150, 100, 300, 30);
        jTextFieldHaslo.setBounds(150, 150, 300,30);
        apllyButton.setBounds(275, 200, 53, 15);
        adminBox.setBounds(325,10, 225,30);
        apllyButton.addActionListener(e -> {if (!adminBox.isSelected()){
        loguj(sklep, frame);
        }
        else {
            logujJakoAdmin(frame, sklep);
        }
        });
        rejestrButton.addActionListener(e -> RejestrPage(sklep, frame));
        rejestrButton.setBounds(350, 350, 80, 20);

        shopJpanel.setLayout(null);
        frame.add(shopJpanel);
        frame.setBounds(0,0,1050,500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }



    public static void RejestrPage(Sklep sklep, JFrame frame) {
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
        JPanel rejestrPanel = new JPanel();
        rejestrPanel.setBounds(0,0,500,500);
        rejestrPanel.setLayout(null);

        JLabel l1 = new JLabel("Imię: ");
        JLabel l2 = new JLabel("Nazwisko: ");
        JLabel l3 = new JLabel("Login: ");
        JLabel l4 = new JLabel("Hasło: ");
        JLabel l5 = new JLabel("Numer Telefonu: ");
        JLabel l6 = new JLabel("E-Mail: ");
        e1 = new JLabel();
        e2 = new JLabel();
        e3 = new JLabel();
        e4 = new JLabel();
        e5 = new JLabel();
        e6 = new JLabel();

        List<JLabel> errors = new ArrayList<>();
        errors.add(e1);
        errors.add(e2);
        errors.add(e3);
        errors.add(e4);
        errors.add(e5);
        errors.add(e6);
        int i = 30;

        for (JLabel JLabel : errors) {
            JLabel.setForeground(Color.red);
            JLabel.setFont(JLabel.getFont().deriveFont(10.0f));
            JLabel.setBounds(160, i,250, 20);
            i += 50;
            rejestrPanel.add(JLabel);
        }


        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jTextField4 = new JTextField();
        jTextField5 = new JTextField();
        jTextField6 = new JTextField();

        List<JTextField> textFields = new ArrayList<>();
        textFields.add(jTextField1);
        textFields.add(jTextField2);
        textFields.add(jTextField3);
        textFields.add(jTextField4);
        textFields.add(jTextField5);
        textFields.add(jTextField6);

        i = 50;
        for (JTextField textField: textFields) {
            textField.setBounds(160,i,250,30);
            rejestrPanel.add(textField);
            i += 50;
        }

        l1.setBounds(50,50,100,20);
        rejestrPanel.add(l1);
        l2.setBounds(50,100,100,20);
        rejestrPanel.add(l2);
        l3.setBounds(50,150,100,20);
        rejestrPanel.add(l3);
        l4.setBounds(50,200,100,20);
        rejestrPanel.add(l4);
        l5.setBounds(50,250,100,20);
        rejestrPanel.add(l5);
        l6.setBounds(50,300,100,20);
        rejestrPanel.add(l6);

        Button buttonRejestr = new Button("Zatwierdź");
        buttonRejestr.setBounds(245, 350, 80, 30);
        buttonRejestr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (CheckIfCorrect()) {
                    sklep.getListaKlientow().add(new Klient(jTextField1.getText(), jTextField2.getText(), jTextField3.getText(), Sklep.hashPassword(jTextField4.getText()), jTextField5.getText(), jTextField6.getText()));
                    sklep.setZalogowanyKlient(sklep.getListaKlientow().get(sklep.getListaKlientow().size() - 1));
                    sklep.setCzyZalogowany(true);
                    frame.getContentPane().removeAll();
                    frame.revalidate();
                    frame.repaint();
                    frame.setSize(1000, 500);
                    SklepGUI.openSklepGUI(frame,sklep);
                };

            }
        });

        Button buttonWrocRej = new Button("Wróć");
        buttonWrocRej.setBounds(45, 350, 80, 30);
        buttonWrocRej.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
                frame.setLayout(null);
                Rejestracja.ShopPage(sklep, frame);

            }
        });
        rejestrPanel.add(buttonWrocRej);

        rejestrPanel.add(buttonRejestr);
        rejestrPanel.setVisible(true);
        rejestrPanel.setBounds(0,0,500,500);
        frame.setSize(500,500);
        frame.add(rejestrPanel);
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static boolean CheckIfCorrect() {
        boolean ifAllCorrect = true;
        String text1 = jTextField1.getText();
        String text2 = jTextField2.getText();
        String text3 = jTextField3.getText();
        String text4 = jTextField4.getText();
        String text5 = jTextField5.getText();
        String text6 = jTextField6.getText();
        if (text1.equals("")) {
            e1.setText("Wpisz imię");
            ifAllCorrect = false;
        }
        else {
            e1.setText("");
        }
        if (text2.equals("")) {
            e2.setText("Wpisz nazwisko");
            ifAllCorrect = false;
        }
        else {
            e2.setText("");
        }
        if (text3.equals("")) {
            e3.setText("Niepoprawny login");
            ifAllCorrect = false;
        }
        else {
            e3.setText("");
        }
        if (text4.length()<8) {
            e4.setText("Niepoprawne hasło");
            ifAllCorrect = false;
        }
        else {
            e4.setText("");
        }
        if (text5.length()!=9 || !isNumeric(text5)) {
            e5.setText("Numer telefonu musi mieć 9 cyfr");
            ifAllCorrect = false;
        }
        else {
            e5.setText("");
        }
        if (!text6.contains("@") || text6.indexOf("@") == 0 || text6.indexOf("@") == text6.length() - 1) {
            e6.setText("Niepoprawny email");
            ifAllCorrect = false;
        }
        else {
            e6.setText("");
        }
        return ifAllCorrect;
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void loguj(Sklep sklep, JFrame frame) {
        if (sklep.zalogujSie(jTextFieldLogowanie.getText(), jTextFieldHaslo.getText())){
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
        SklepGUI.openSklepGUI(frame,sklep);
    }
    else {jTextFieldLogowanie.setText("");
        jTextFieldHaslo.setText("");}
        error.setText("Niepoprawny login lub hasło");
    }

    public static void logujJakoAdmin(JFrame frame, Sklep sklep) {
        if (jTextFieldLogowanie.getText().equals("admin") && jTextFieldHaslo.getText().equals("12345678")) {
            System.out.println("zalogowano jako admin");
            frame.getContentPane().removeAll();
            frame.revalidate();
            frame.repaint();
            frame.setLayout(null);

            GUIadmin.openAdmin(sklep, frame);
        }
        else {
            jTextFieldLogowanie.setText("");
            jTextFieldHaslo.setText("");
            error.setText("Niepoprawny login lub hasło");
        }
    }
}
