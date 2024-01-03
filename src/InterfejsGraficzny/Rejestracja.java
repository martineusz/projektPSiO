package InterfejsGraficzny;


import Main.Klient;
import Main.Main;
import Main.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class Rejestracja  {

    private static JTextField jTextField6;
    private static JFrame jframe;
    private static JTextField jTextField1;
    private static JTextField jTextField2;
    private static JTextField jTextField3;
    private static JTextField jTextField4;
    private static JTextField jTextField5;
    private static JFrame rejestrFrame;

    private static JLabel e1;
    private static JLabel e2;
    private static JLabel e3;
    private static JLabel e4;
    private static JLabel e5;
    private static JLabel e6;

    public static void ShopPage(Sklep sklep) {
        jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setTitle("Sklep");
        jframe.setSize(1000, 500);


        JPanel jpanel1 = new JPanel();
        jpanel1.setBackground(Color.ORANGE);
        jpanel1.setBounds(0,0, 400, 500);
        jframe.add(jpanel1);
        jpanel1.setLayout(null);

        JPanel jPanel2 = new JPanel();
        jPanel2.setBounds(400, 0, 600, 500);
        jframe.add(jPanel2);
        jPanel2.setLayout(null);

        Label label1 = new Label("LOGIN");
        Label label3 = new Label("Jesteś tu pierwszy raz?");
        Button rejestrButton = new Button("Zarejestruj się");


        label1.setForeground(Color.black);
        label3.setForeground(Color.black);
        JTextField jTextField1 = new JTextField();
        JTextField jTextField2 = new JTextField();
        Label label2 = new Label("HASŁO");
        Button apllyButton = new Button("Zatwierdź");
        label2.setForeground(Color.black);

        jPanel2.add(jTextField1);
        jPanel2.add(jTextField2);
        jPanel2.add(label1);
        jPanel2.add(label2);
        jPanel2.add(label3);
        jPanel2.add(apllyButton);
        jPanel2.add(rejestrButton);
        label1.setBounds(100, 110, 50, 10);
        label2.setBounds(100,160,50,10);
        label3.setBounds(185, 350, 130, 20);
        jTextField1.setBounds(150, 100, 300, 30);
        jTextField2.setBounds(150, 150, 300,30);
        apllyButton.setBounds(275, 200, 53, 15);
        apllyButton.addActionListener(e -> {if (sklep.zalogujSie(jTextField1.getText(), jTextField2.getText())){
            jframe.dispose();
        }
        else {jTextField1.setText("");
        jTextField2.setText("");}
        });
        rejestrButton.addActionListener(e -> RejestrPage(sklep));
        rejestrButton.setBounds(350, 350, 80, 20);

        jframe.setLayout(null);
        jframe.setVisible(true);
    }



    public static void RejestrPage(Sklep sklep) {
        rejestrFrame = new JFrame();
        rejestrFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rejestrFrame.setSize(500, 500);
        rejestrFrame.setLayout(null);
        rejestrFrame.setTitle("REJESTRACJA");

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
            rejestrFrame.add(JLabel);
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
            rejestrFrame.add(textField);
            i += 50;
        }

        l1.setBounds(50,50,100,20);
        rejestrFrame.add(l1);
        l2.setBounds(50,100,100,20);
        rejestrFrame.add(l2);
        l3.setBounds(50,150,100,20);
        rejestrFrame.add(l3);
        l4.setBounds(50,200,100,20);
        rejestrFrame.add(l4);
        l5.setBounds(50,250,100,20);
        rejestrFrame.add(l5);
        l6.setBounds(50,300,100,20);
        rejestrFrame.add(l6);

        Button buttonRejestr = new Button("Zatwierdź");
        buttonRejestr.setBounds(245, 350, 80, 30);
        buttonRejestr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (CheckIfCorrect()) {
                    sklep.getListaKlientow().add(new Klient(jTextField1.getText(), jTextField2.getText(), jTextField3.getText(), jTextField4.getText(), jTextField5.getText(), jTextField6.getText()));
                    sklep.setZalogowanyKlient(sklep.getListaKlientow().get(sklep.getListaKlientow().size() - 1));
                    sklep.setCzyZalogowany(true);
                    rejestrFrame.dispose();
                };

            }
        });

        rejestrFrame.add(buttonRejestr);
        rejestrFrame.setVisible(true);
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
}
