package Grafika.Koszyk;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KoszykFrame extends JFrame implements ActionListener {

    JTextField textField;
    JButton buttonDalej;
    JFrame currentFrame;
    JPanel panel4;
    JScrollPane scrollPane;


    KoszykFrame() {
        currentFrame = this;
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();


        //buttons
        buttonDalej = new JButton("DALEJ");
        buttonDalej.setFocusable(false);
        buttonDalej.setFont(new Font(null, Font.BOLD, 10));
        buttonDalej.setBounds(175, 650, 70, 25);
        buttonDalej.setBorder(BorderFactory.createEtchedBorder());
        buttonDalej.setBackground(Color.white);
        buttonDalej.addActionListener(this);

        //textFields
        textField = new JTextField(10);

        //Label twojKoszyk
        JLabel twojKoszyk = new JLabel();
        ImageIcon koszykImage = new ImageIcon("src/resources/Obrazki/koszyk.png");


        twojKoszyk.setText("TWOJ KOSZYK");
        twojKoszyk.setIcon(koszykImage);
        twojKoszyk.setVerticalAlignment(JLabel.CENTER);
        twojKoszyk.setHorizontalAlignment(JLabel.LEFT);
        twojKoszyk.setHorizontalTextPosition(JLabel.RIGHT);
        twojKoszyk.setFont(new Font(null, Font.BOLD, 25));
        twojKoszyk.setIconTextGap(0);
        twojKoszyk.setBounds(0,0,100,100);
        twojKoszyk.setPreferredSize(new Dimension(50,50));

        //Label PODSUMOWANIE
        JLabel podsumowanie = new JLabel();
        podsumowanie.setText("PODSUMOWANIE: ");
        podsumowanie.setBounds(10,10,150,25);
        podsumowanie.setFont(new Font(null, Font.BOLD, 15));

        //PANEL 1
        panel1.setPreferredSize(new Dimension(0, 100));
        panel1.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        //PANEL 2
        panel2.setPreferredSize(new Dimension(0, 780));
        panel2.setLayout(new BorderLayout());
        panel2.setBorder(BorderFactory.createLineBorder(Color.lightGray));


        panel2.add(BorderLayout.CENTER, panel4);
        panel2.add(BorderLayout.NORTH, panel5);
        panel2.add(BorderLayout.EAST, panel7);


        //PANEL 3
        panel3.setPreferredSize(new Dimension(0, 200));
        panel3.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        //PANEL 4

        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
        panel4.setBorder(BorderFactory.createLineBorder(Color.lightGray));

        scrollPane = new JScrollPane(panel4, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        panel2.add(scrollPane, BorderLayout.CENTER); // Dodanie JScrollPane z panel4 do panel2

        //PANEL 5
        panel5.setLayout(new BorderLayout());
        panel5.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        panel5.add(twojKoszyk);
        //PANEL 6

        //PANEL 7
        panel7.setLayout(null);
        panel7.setPreferredSize(new Dimension(300,0));
        panel7.add(podsumowanie);
        panel7.add(buttonDalej);
        //PANEL 8


        this.add(panel1);
        this.add(panel2);
        this.add(panel3);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1980, 1080));
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonDalej){
            new Zamowienie();
            currentFrame.dispose();
            System.out.println("poo");
        }
    }


    public void dodajPanelDoPanel4(JPanel panel) {
        panel4.add(panel);
        panel4.revalidate();
        panel4.repaint();
    }




    public static void main(String[] args) {
        new Test();
    }
}
