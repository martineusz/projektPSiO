package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsunProduktGUI implements ActionListener {

    private Sklep sklep;
    private JButton usunButton;
    private JCheckBox[] checkBox;
    private JFrame ramka;


    public static void openGUI(Sklep sklep){
        UsunProduktGUI GUI = new UsunProduktGUI();
        GUI.usunProduktGUI(sklep);
    }
    public void usunProduktGUI(Sklep sklep) {
        this.sklep = sklep;

        ramka = new JFrame();

        JPanel panelCheckBox = new JPanel();
        checkBox = new JCheckBox[sklep.getListaProduktow().size()];

        panelCheckBox.setLayout(new BoxLayout(panelCheckBox, BoxLayout.Y_AXIS));

        panelCheckBox.add(Box.createVerticalStrut(20));
        for(int i=0; i<sklep.getListaProduktow().size(); i++){
            panelCheckBox.add(checkBox[i]= new JCheckBox());
        }

        JPanel usunProduktPanel = new JPanel();

        usunButton = new JButton("usun");
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


        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane(tabela);
        ramka.add(scrollPane, BorderLayout.CENTER);
        ramka.add(panelCheckBox, BorderLayout.WEST);
        ramka.add(usunProduktPanel, BorderLayout.EAST);

        ramka.setSize(1000,600);
        ramka.pack();
        ramka.setResizable(false);
        ramka.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==usunButton){
            for(int i=0; i<sklep.getListaProduktow().size(); i++){
                if(checkBox[i].isSelected()){
                    sklep.usunProdukt(i);
                    ramka.dispose();
                    usunProduktGUI(sklep);
                }
            }
        }
    }
}
