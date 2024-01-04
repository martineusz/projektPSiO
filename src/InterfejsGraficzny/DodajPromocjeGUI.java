package InterfejsGraficzny;

import Main.Sklep;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DodajPromocjeGUI implements ActionListener {
    private Sklep sklep;
    private JFrame ramka;
    private JButton zatwierdz;
    private JButton returnButton;
    private JTextField nazwaPromocji;
    private JCheckBox[] checkBox;
    private float wartoscPromocji;

    public static void openGUI(Sklep sklep, JFrame ramka){
        DodajPromocjeGUI GUI = new DodajPromocjeGUI();
        GUI.dodajPromocjeGUI(sklep, ramka);
    }
    public void dodajPromocjeGUI(Sklep sklep, JFrame ramka){
        this.sklep=sklep;
        this.ramka=ramka;

        JPanel panelGlowny = new JPanel();
        panelGlowny.setLayout(new BorderLayout());

        //checkboxy
        JPanel panelCheckBox = new JPanel();
        checkBox = new JCheckBox[sklep.getListaProduktow().size()];

        panelCheckBox.setLayout(new BoxLayout(panelCheckBox, BoxLayout.Y_AXIS));

        panelCheckBox.add(Box.createVerticalStrut(20));
        for(int i=0; i<sklep.getListaProduktow().size(); i++){
            panelCheckBox.add(checkBox[i]= new JCheckBox());
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
                }
            }
        });


        nazwaPromocji = new JTextField();


        zatwierdz = new JButton("dodaj");
        zatwierdz.addActionListener(this);

        panelPromocji.setLayout(new GridLayout(0,2,5,5));

        panelPromocji.add(znizka);
        panelPromocji.add(slider);
        panelPromocji.add(nazwa);
        panelPromocji.add(nazwaPromocji);
        panelPromocji.add(zatwierdz);

        JPanel promocjaPolnoc = new JPanel();
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
        tabela.setRowHeight(20);


        //powrot
        returnButton = new JButton("powrot");
        returnButton.addActionListener(this);


        JScrollPane scrollPane = new JScrollPane(tabela);
        panelGlowny.add(scrollPane, BorderLayout.CENTER);
        panelGlowny.add(panelCheckBox, BorderLayout.WEST);
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
                    sklep.promocja.ustawPromocjeNaProdukt(sklep.getListaProduktow().get(i), wartoscPromocji, nazwaPromocji.getText());
                }
            }

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
}
