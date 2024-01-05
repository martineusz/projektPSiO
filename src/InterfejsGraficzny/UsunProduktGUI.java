package InterfejsGraficzny;

import Main.Sklep;

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

        tabela.setRowHeight(20);


        //return button
        returnButton = new JButton("powrot");
        returnButton.setBackground(Color.WHITE);
        returnButton.setMargin(new Insets(10, 20, 10, 20));
        returnButton.addActionListener(this);


        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane(tabela);
        panelGlowny.add(scrollPane, BorderLayout.CENTER);
        panelGlowny.add(panelCheckBox, BorderLayout.WEST);
        panelGlowny.add(usunProduktPanel, BorderLayout.EAST);
        panelGlowny.add(returnButton, BorderLayout.SOUTH);

        ramka.setLayout(null);
        ramka.setContentPane(panelGlowny);
        ramka.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==usunButton){
            for(int i=0; i<sklep.getListaProduktow().size(); i++){
                if(checkBox[i].isSelected()){
                    sklep.usunProdukt(i);
                    ramka.getContentPane().removeAll();
                    ramka.revalidate();
                    ramka.repaint();
                    ramka.setLayout(null);
                    usunProduktGUI(sklep, ramka);

                }
            }
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
