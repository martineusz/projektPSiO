package code.InterfejsGraficzny;

import code.Main.Sklep;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsunProduktGUI implements ActionListener {

    private Sklep sklep;
    private JButton usunButton;
    private JButton returnButton;
    private JCheckBox[] checkBox;
    private JPanel panelGlowny;
    private JFrame ramka;


    public static void openGUI(Sklep sklep, JFrame ramka){
        UsunProduktGUI GUI = new UsunProduktGUI();
        GUI.usunProduktGUI(sklep, ramka);
    }
    public void usunProduktGUI(Sklep sklep, JFrame ramka) {
        this.sklep = sklep;
        this.ramka = ramka;

        panelGlowny = new JPanel();
        panelGlowny.setLayout(new BorderLayout());

        JPanel panelCheckBox = new JPanel();
        checkBox = new JCheckBox[sklep.getListaProduktow().size()];

        panelCheckBox.setLayout(new BoxLayout(panelCheckBox, BoxLayout.Y_AXIS));

        panelCheckBox.add(Box.createVerticalStrut(20));
        for(int i=0; i<sklep.getListaProduktow().size(); i++){
            panelCheckBox.add(checkBox[i]= new JCheckBox());
        }

        JPanel usunProduktPanel = new JPanel();

        usunButton = new JButton("usun");
        usunButton.setBackground(Color.WHITE);
        usunButton.setMargin(new Insets(10, 20, 10, 20));
        usunButton.addActionListener(this);



        usunProduktPanel.setLayout(new BoxLayout(usunProduktPanel, BoxLayout.Y_AXIS));
        usunProduktPanel.add(Box.createVerticalStrut(20));
        usunProduktPanel.add(usunButton);


        String[] kolumny = new String[] {"Id", "Nazwa", "Cena", "Marka"};
        Object[][] komorka = new Object[sklep.getListaProduktow().size()][4];

        for(int i=0; i<sklep.getListaProduktow().size(); i++){
            komorka[i][0]=sklep.getListaProduktow().get(i).getIdProduktu();
            komorka[i][1]=sklep.getListaProduktow().get(i).getNazwa();
            komorka[i][2]=sklep.getListaProduktow().get(i).getCena();
            komorka[i][3]=sklep.getListaProduktow().get(i).getProducent().getMarka();
        }

        JTable tabela = new JTable(komorka, kolumny);

        tabela.setRowHeight(21);


        //return button
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

        JLabel labelNazwa = new JLabel("USUŃ PRODUKT");
        labelNazwa.setFont(new Font("Serif", Font.BOLD, 17));
        labelNazwa.setForeground(Color.WHITE);
        panelGora.add(labelNazwa);

        panelGlowny.add(BorderLayout.NORTH, panelGora);

        JPanel panelTabeli = new JPanel(new BorderLayout());
        panelTabeli.add(tabela.getTableHeader(), BorderLayout.NORTH);
        panelTabeli.add(tabela, BorderLayout.CENTER);

        JPanel panelScroll = new JPanel();
        panelScroll.setLayout(new BorderLayout());
        panelScroll.add(panelTabeli, BorderLayout.CENTER);
        panelScroll.add(panelCheckBox, BorderLayout.WEST);

        JScrollPane scrollPane = new JScrollPane(panelScroll);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        panelGlowny.add(scrollPane, BorderLayout.CENTER);
        panelGlowny.add(usunProduktPanel, BorderLayout.EAST);

        ramka.setLayout(null);
        ramka.setContentPane(panelGlowny);
        ramka.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==usunButton){
            int counter=0;
            int x=sklep.getListaProduktow().size();
            for(int i=0; i<x; i++){
                if(checkBox[i].isSelected()){
                    sklep.usunProdukt(i-counter);
                    counter++;
                }
            }
            JOptionPane.showMessageDialog(null, "Produkty usuniete", "ADMIN",
                    JOptionPane.INFORMATION_MESSAGE);
            sklep.zapiszListeProduktow();
            ramka.getContentPane().removeAll();
            ramka.revalidate();
            ramka.repaint();
            ramka.setLayout(null);
            usunProduktGUI(sklep, ramka);
        }
        if(e.getSource()==returnButton){
            ramka.getContentPane().removeAll();
            ramka.revalidate();
            ramka.repaint();
            ramka.setLayout(null);
            GUIadmin.openAdmin(sklep, ramka);
        }
    }
}
