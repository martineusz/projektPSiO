package InterfejsGraficzny;

import Main.Sklep;
import Produkt.Produkt;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class DodajPromocjeGUI implements ActionListener, ItemListener {
    private Sklep sklep;
    private JFrame ramka;
    private JPanel promocjaPolnoc;
    private JPanel panelGlowny;
    private JPanel tabelka2Panel;
    private JPanel panelDodawania;
    private JButton zatwierdz;
    private JButton returnButton;
    private JTextField nazwaPromocji;
    private JCheckBox[] checkBox;
    private float wartoscPromocji;
    private JScrollPane scrollPane2;
    private List<Produkt> tempList = new ArrayList<>();
    private DefaultTableModel model;

    private String[] kolumny2;

    public static void openGUI(Sklep sklep, JFrame ramka){
        DodajPromocjeGUI GUI = new DodajPromocjeGUI();
        GUI.dodajPromocjeGUI(sklep, ramka);
    }
    public void dodajPromocjeGUI(Sklep sklep, JFrame ramka){
        this.sklep=sklep;
        this.ramka=ramka;

        panelDodawania = new JPanel();
        panelDodawania.setLayout(new BorderLayout());

        panelGlowny = new JPanel();
        panelGlowny.setLayout(new BorderLayout());

        //checkboxy
        JPanel panelCheckBox = new JPanel();
        checkBox = new JCheckBox[sklep.getListaProduktow().size()];

        panelCheckBox.setLayout(new BoxLayout(panelCheckBox, BoxLayout.Y_AXIS));

        panelCheckBox.add(Box.createVerticalStrut(20));

        for(int i=0; i<sklep.getListaProduktow().size(); i++){
            panelCheckBox.add(checkBox[i]= new JCheckBox());
            checkBox[i].addItemListener(this);
        }

        //panel promocji
        JPanel panelPromocji = new JPanel();
        JLabel znizka = new JLabel("Znizka: "+wartoscPromocji*100+"%");
        JLabel nazwa = new JLabel("Podaj nazwe promocji");

        //slider
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    wartoscPromocji = (float) source.getValue()/100;
                    znizka.setText("Znizka: "+wartoscPromocji*100+"%");
                    Object[][] newDat = new Object[tempList.size()][4];
                    for(int j=0; j<tempList.size(); j++){
                        newDat[j][0]=tempList.get(j).getIdProduktu();
                        newDat[j][1]=tempList.get(j).getNazwa();
                        float temp= (float) (tempList.get(j).getCena()-(tempList.get(j).getCena()*source.getValue()/100.0));
                        newDat[j][2]=Math.floor(temp * 100.0) / 100.0;
                        newDat[j][3]=tempList.get(j).getProducent().getMarka();
                    }
                    model.setDataVector(newDat, kolumny2);
                }
            }
        });


        nazwaPromocji = new JTextField();


        zatwierdz = new JButton("dodaj");
        zatwierdz.setBackground(Color.WHITE);
        zatwierdz.setMargin(new Insets(10, 20, 10, 20));
        zatwierdz.addActionListener(this);

        panelPromocji.setLayout(new GridLayout(0,2,5,5));

        panelPromocji.add(znizka);
        panelPromocji.add(slider);
        panelPromocji.add(nazwa);
        panelPromocji.add(nazwaPromocji);
        panelPromocji.add(zatwierdz);

        promocjaPolnoc = new JPanel();
        promocjaPolnoc.setLayout(new BorderLayout());
        promocjaPolnoc.add(panelPromocji, BorderLayout.NORTH);



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
        tabela.setRowHeight(21);


        //tabelka promocje
        kolumny2 = new String[] {"Id", "Nazwa", "Cena", "Marka"};

        tabelka2Panel = new JPanel();


        Object[][] komorka2 = new Object[tempList.size()][4];

        for(int i=0; i<tempList.size(); i++){
            komorka2[i][0]=tempList.get(i).getIdProduktu();
            komorka2[i][1]=tempList.get(i).getNazwa();
            komorka2[i][2]=tempList.get(i).getCena();
            komorka2[i][3]=tempList.get(i).getProducent().getMarka();
        }

        model = new DefaultTableModel(komorka2, kolumny2);
        JTable produktPoPrzeceniePanel = new JTable(model);

        scrollPane2 = new JScrollPane(produktPoPrzeceniePanel);
        scrollPane2.setPreferredSize(new Dimension(scrollPane2.getPreferredSize().width, produktPoPrzeceniePanel.getRowHeight() * 15));
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2.getVerticalScrollBar().setUnitIncrement(16);
        tabelka2Panel.add(scrollPane2);
        promocjaPolnoc.add(tabelka2Panel, BorderLayout.CENTER);

        //powrot
        returnButton = new JButton("powrot");
        returnButton.setBackground(Color.WHITE);
        returnButton.setMargin(new Insets(10, 20, 10, 20));
        returnButton.addActionListener(this);

        JLabel labelTytul = new JLabel("Produkty do promocji");

        JPanel panelTabeli = new JPanel(new BorderLayout());
        panelTabeli.add(tabela.getTableHeader(), BorderLayout.NORTH);
        panelTabeli.add(tabela, BorderLayout.CENTER);

        panelDodawania.add(labelTytul, BorderLayout.NORTH);
        panelDodawania.add(panelCheckBox, BorderLayout.WEST);
        panelDodawania.add(panelTabeli, BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(panelDodawania);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);


        panelGlowny.add(scrollPane, BorderLayout.CENTER);
        panelGlowny.add(promocjaPolnoc, BorderLayout.EAST);
        panelGlowny.add(returnButton, BorderLayout.SOUTH);

        ramka.setLayout(null);
        ramka.setContentPane(panelGlowny);
        ramka.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==zatwierdz){
            for(int i=0; i<sklep.getListaProduktow().size(); i++) {
                if(checkBox[i].isSelected()){
                    float cena = (float) (Math.floor(wartoscPromocji * 100.0) / 100.0);
                    sklep.promocja.ustawPromocjeNaProdukt(sklep.getListaProduktow().get(i), cena, nazwaPromocji.getText());

                    tempList.clear();

                    Object[][] newDat = new Object[tempList.size()][4];
                    for(int j=0; j<tempList.size(); j++){
                        newDat[j][0]=tempList.get(j).getIdProduktu();
                        newDat[j][1]=tempList.get(j).getNazwa();
                        newDat[j][2]=tempList.get(j).getCena();
                        newDat[j][3]=tempList.get(j).getProducent().getMarka();
                    }
                    model.setDataVector(newDat, kolumny2);
                }
            }
            ramka.getContentPane().removeAll();
            ramka.revalidate();
            ramka.repaint();
            ramka.setLayout(null);
            dodajPromocjeGUI(sklep, ramka);
        }
        if(e.getSource()==returnButton){
            ramka.getContentPane().removeAll();
            ramka.revalidate();
            ramka.repaint();
            ramka.setLayout(null);

            GUIadmin.openAdmin(sklep, ramka);
        }
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        for (int i = 0; i < checkBox.length; i++) {
            if (checkBox[i] == e.getSource()) {
                if (e.getStateChange()==ItemEvent.SELECTED) {
                    tempList.add(sklep.getListaProduktow().get(i));
                } else {
                    tempList.remove(sklep.getListaProduktow().get(i));
                }
                Object[][] newDat = new Object[tempList.size()][4];
                for(int j=0; j<tempList.size(); j++){
                    newDat[j][0]=tempList.get(j).getIdProduktu();
                    newDat[j][1]=tempList.get(j).getNazwa();
                    newDat[j][2]=tempList.get(j).getCena();
                    newDat[j][3]=tempList.get(j).getProducent().getMarka();
                }
                model.setDataVector(newDat, kolumny2);
                break;
            }
        }
    }
}
