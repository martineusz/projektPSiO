package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DodajPromocjeGUI implements ActionListener {
    private Sklep sklep;
    private JButton zatwierdz;
    private JTextField wartoscPromocji;
    private JTextField nazwaPromocji;

    public static void openGUI(Sklep sklep){
        DodajPromocjeGUI GUI = new DodajPromocjeGUI();
        GUI.dodajPromocjeGUI(sklep);
    }
    public void dodajPromocjeGUI(Sklep sklep){
        this.sklep=sklep;
        JFrame ramka = new JFrame();

        //checkboxy
        JPanel panelCheckBox = new JPanel();
        JCheckBox[] checkBox = new JCheckBox[sklep.getListaProduktow().size()];

        panelCheckBox.setLayout(new BoxLayout(panelCheckBox, BoxLayout.Y_AXIS));

        panelCheckBox.add(Box.createVerticalStrut(20));
        for(int i=0; i<sklep.getListaProduktow().size(); i++){
            panelCheckBox.add(checkBox[i]= new JCheckBox());
        }

        //panel promocji
        JPanel panelPromocji = new JPanel();
        JLabel znizka = new JLabel("Podaj znizke (w %)");
        JLabel nazwa = new JLabel("Podaj nazwe promocji");

        wartoscPromocji = new JTextField();
        nazwaPromocji = new JTextField();


        zatwierdz = new JButton("dodaj");
        zatwierdz.addActionListener(this);

        panelPromocji.setLayout(new GridLayout(3,2,10,10));

        panelPromocji.add(znizka);
        panelPromocji.add(wartoscPromocji);
        panelPromocji.add(nazwa);
        panelPromocji.add(nazwaPromocji);
        panelPromocji.add(zatwierdz);



        //tabelka
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
        ramka.add(panelPromocji, BorderLayout.EAST);

        ramka.setSize(1000,600);
        ramka.pack();
        ramka.setResizable(false);
        ramka.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==zatwierdz){
            //dodanie danej promocji do danych checkboxow
        }
    }
}
