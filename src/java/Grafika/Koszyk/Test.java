package Grafika.Koszyk;

import Main.Koszyk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JFrame implements ActionListener {


    JButton dodajDoKoszyka;
    KoszykFrame koszyk = new KoszykFrame();
    JPanel testPanel;

    Test(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(100, 100));
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        dodajDoKoszyka = new JButton("DODAJ");
        dodajDoKoszyka.addActionListener(this);

        testPanel = new JPanel();
        testPanel.setPreferredSize(new Dimension(100, 100));
        testPanel.setBackground(Color.BLUE);
        testPanel.setBorder(BorderFactory.createLineBorder(Color.RED));

        this.add(dodajDoKoszyka);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==dodajDoKoszyka){
            JPanel nowyPanel2 = new JPanel();
            nowyPanel2.setPreferredSize(new Dimension(1000,200));
            nowyPanel2.setMaximumSize(new Dimension(1000,200));
            nowyPanel2.setMinimumSize(new Dimension(1000,200));
            nowyPanel2.setBorder(BorderFactory.createLineBorder(Color.lightGray,5,false));
            nowyPanel2.setBackground(Color.white);

            koszyk.dodajPanelDoPanel4(nowyPanel2);
        }
    }
}
