package code.InterfejsGraficzny;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import code.InterfejsGraficzny.Koszyk.KoszykPane;
import code.Main.*;
import code.Produkt.*;
import code.Obserwator.*;
import code.inputValidate.ProduktWKoszykuException;

public class SklepGUI {
    private static ArrayList<String> listaProduktowWKoszyku;
    private static int liczbaKolumn;
    private static int wysokoscProduktu;
    private static Color myColor;
    private static JScrollPane scrollPaneButy = new JScrollPane();
    private static JScrollPane scrollPaneBluza = new JScrollPane();
    private static JScrollPane scrollPaneKoszulki = new JScrollPane();
    private static JScrollPane scrollPaneSpodnie = new JScrollPane();
    private static JScrollPane scrollPane = new JScrollPane();
    private static JPanel panelPowiadomienia;
    private static JButton buttonKoszulki;
    private static JButton buttonSpodnie;
    private static JButton buttonButy;
    private static JButton buttonWszystko;
    private static JButton buttonBluza;
    private static JButton buttonKoszyk;
    private static JCheckBox boxPowiadomienia;
    private static ButtonGroup radioPowiadomienia;
    private static JRadioButton radioSMS;
    private static JRadioButton radioEmail;
    private static JPanel panelBluza;
    private static JPanel panelSpodnie;
    private static JPanel panelGlowny;
    private static JPanel panelKoszulki;
    private static JPanel panelButy;
    private static JPanel panelKoszykButton;
    private static JLabel iloscWKoszyku;
    private static ImageIcon scaledAddImaged;
    private static final ArrayList<Bluza> listaBluz = new ArrayList<>();
    private static final ArrayList<Obuwie> listaButow = new ArrayList<>();
    private static final ArrayList<Koszulka> listaKoszulek = new ArrayList<>();
    private static final ArrayList<Spodnie> listaSpodni = new ArrayList<>();

    public static void openSklepGUI(JFrame frame, Sklep sklep) {
        listaProduktowWKoszyku = new ArrayList<>();
        Map<JButton, String> buttonPromocjeMap = new HashMap<>();
        panelPowiadomienia = new JPanel();
        JScrollPane scrollPanePowiadomienia = new JScrollPane(panelPowiadomienia);
        JScrollBar szybkoscScroll = scrollPanePowiadomienia.getVerticalScrollBar();
        frame.setResizable(false);
        myColor = new Color(255, 69, 0);
        frame.setSize(950, 770);
        frame.setLayout(null);

        for (Produkt produkt : sklep.getListaProduktow())
            if (produkt instanceof Bluza && !listaBluz.contains(produkt)) {
                listaBluz.add((Bluza) produkt);
            } else if (produkt instanceof Obuwie && !listaButow.contains(produkt)) {
                listaButow.add((Obuwie) produkt);
            } else if (produkt instanceof Spodnie && !listaSpodni.contains(produkt)) {
                listaSpodni.add((Spodnie) produkt);
            } else if (produkt instanceof Koszulka && !listaKoszulek.contains(produkt)) {
                listaKoszulek.add((Koszulka) produkt);
            }

        wysokoscProduktu = 390;
        liczbaKolumn = 2;

        JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        frame.add(separator1);
        separator1.setBounds(200, 70, 950, 2);
        separator1.setBackground(Color.lightGray);

        JPanel panelLewy = new JPanel();
        JPanel panelGora = new JPanel();
        panelGlowny = new JPanel();
        panelGora.setBounds(200, 0, 750, 70);
        panelLewy.setBounds(0, 0, 200, 770);
        panelGlowny.setBounds(200, 70, 737, 700);

        panelLewy.setLayout(null);
        panelLewy.setBackground(myColor);
        panelGora.setLayout(null);
        panelKoszykButton = new JPanel();
        panelGora.add(panelKoszykButton);
        panelKoszykButton.setLayout(null);
        panelKoszykButton.setBounds(585, 14, 40, 40);

        panelGlowny.setLayout(new GridLayout(0, 2, 0, 0));
        scrollPane = new JScrollPane(panelGlowny);
        frame.add(scrollPane);
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setBounds(300, 70, 737, 700);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        if (sklep.zalogowanyKlient.obs != null) {
            for (int i = 0; i < sklep.zalogowanyKlient.obs.getPowiadomienia().size(); i++) {
                JButton buttonUsun = new JButton("x");
                buttonUsun.setBounds(270, 5, 20, 20);
                buttonUsun.setFocusable(false);
                buttonUsun.setFont(new Font(null, Font.PLAIN, 20));
                buttonUsun.setBorder(null);
                buttonUsun.setBackground(null);
                buttonUsun.setForeground(Color.gray);

                String promocja = sklep.zalogowanyKlient.obs.getPowiadomienia().get(i);

                JLabel labelPromocja = new JLabel();
                labelPromocja.setBounds(15, 15, 200, 15);
                labelPromocja.setText("Promocja: ");
                labelPromocja.setFont(new Font(null, Font.BOLD, 10));

                JLabel labelPromocja2 = new JLabel();
                labelPromocja2.setBounds(15, 35, 300, 40);
                labelPromocja2.setText(" " + promocja);
                labelPromocja2.setFont(new Font(null, Font.BOLD, 10));

                JPanel panelPromocji = new JPanel();
                panelPromocji.setLayout(null);
                panelPromocji.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                panelPromocji.setBackground(Color.white);
                panelPromocji.setPreferredSize(new Dimension(300, 100));

                panelPowiadomienia.add(panelPromocji);
                panelPromocji.add(buttonUsun);
                panelPromocji.add(labelPromocja);
                panelPromocji.add(labelPromocja2);

                buttonPromocjeMap.put(buttonUsun, promocja);
                buttonUsun.addActionListener(e -> {
                    JButton sourceButton = (JButton) e.getSource();
                    String promocjaToRemove = buttonPromocjeMap.get(sourceButton);

                    if (promocjaToRemove != null) {
                        sklep.zalogowanyKlient.obs.getPowiadomienia().remove(promocjaToRemove);
                        panelPowiadomienia.remove(sourceButton.getParent());
                        panelPowiadomienia.revalidate();
                        panelPowiadomienia.repaint();
                        panelPowiadomienia.setPreferredSize(new Dimension(300, panelPowiadomienia.getComponentCount() * 105));
                    }
                });
            }
        } else {
            JLabel brakPromocji = new JLabel("Nie jestes zapisany za zadna promocje!!");
            brakPromocji.setAlignmentY(100);
            panelPowiadomienia.add(brakPromocji);
        }

        //SCROLL DO PANELU POWIADOMIEN
        panelPowiadomienia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelPowiadomienia.setEnabled(false);
        panelPowiadomienia.setVisible(false);
        panelPowiadomienia.setPreferredSize(new Dimension(300, panelPowiadomienia.getComponentCount() * 105));

        szybkoscScroll.setUnitIncrement(20);
        szybkoscScroll.setBlockIncrement(40);
        scrollPanePowiadomienia.setVisible(false);
        scrollPanePowiadomienia.setEnabled(false);
        scrollPanePowiadomienia.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPanePowiadomienia.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanePowiadomienia.setBounds(500, 70, 300, 450);
        frame.add(scrollPanePowiadomienia);

        ImageIcon scaledIcon = scaleIcon("src/resources/Obrazki/koszyk.png", 40);
        ImageIcon scaledMailIcon = scaleIcon("src/resources/Obrazki/mail.png", 40);
        scaledAddImaged = scaleIcon("src/resources/Obrazki/add.png", 25);

        boxPowiadomienia = new JCheckBox();
        panelGora.add(boxPowiadomienia);
        boxPowiadomienia.setBounds(10, 17, 340, 40);
        boxPowiadomienia.setText("Czy chcesz otrzymywać powiadomienia o promocjach?");
        boxPowiadomienia.setSelected(false);

        radioSMS = new JRadioButton("sms");
        radioEmail = new JRadioButton("email");
        radioPowiadomienia = new ButtonGroup();
        radioPowiadomienia.add(radioEmail);
        radioPowiadomienia.add(radioSMS);
        panelGora.add(radioEmail);
        panelGora.add(radioSMS);
        radioEmail.setBounds(370, 17, 60, 40);
        radioSMS.setBounds(450, 17, 60, 40);
        radioEmail.setEnabled(false);
        radioSMS.setEnabled(false);


        buttonKoszyk = new JButton(scaledIcon);
        panelKoszykButton.add(buttonKoszyk);
        iloscWKoszyku = new JLabel(String.valueOf(sklep.zalogowanyKlient.getKoszyk().getProduktyWKoszyku().size()));
        buttonKoszyk.setFont(new Font("Arial", Font.BOLD, 15));
        buttonKoszyk.setVerticalTextPosition(JButton.BOTTOM);
        buttonKoszyk.setBounds(0, 0, 40, 40);
        panelGora.add(iloscWKoszyku);
        iloscWKoszyku.setBounds(625, 47, 15, 15);
        iloscWKoszyku.setForeground(myColor);
        buttonKoszyk.setFocusable(false);

        ;
        addMouseEffect(buttonKoszyk);

        JButton buttonWyloguj = new JButton("Wyloguj");
        panelGora.add(buttonWyloguj);
        buttonWyloguj.setBackground(Color.WHITE);
        buttonWyloguj.setBounds(640, 17, 80, 40);
        buttonWyloguj.setFocusable(false);
        addMouseEffect(buttonWyloguj);

        JButton buttonMail = new JButton();
        panelGora.add(buttonMail);
        buttonMail.setBounds(520, 17, 40, 40);
        buttonMail.setFocusable(false);
        buttonMail.setIcon(scaledMailIcon);
        addMouseEffect(buttonMail);
        buttonMail.setEnabled(false);

        buttonButy = new JButton("Buty");
        buttonKoszulki = new JButton("Koszulki");
        buttonSpodnie = new JButton("Spodnie");
        buttonBluza = new JButton("Bluzy");
        buttonWszystko = new JButton("Wszystko");

        JButton[] buttons = {buttonWszystko, buttonKoszulki, buttonSpodnie, buttonBluza, buttonButy};
        for (JButton button : buttons) {
            button.setForeground(Color.WHITE);
            panelLewy.add(button);
            addMouseEffect(button);
        }

        int startY = 20;
        int buttonHeight = 50;
        int buttonWidth = 100;
        int spacing = 4;
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setBounds((300 - buttonWidth) / 4, startY + (buttonHeight + spacing) * i, buttonWidth, buttonHeight);
        }

        if (!sklep.zalogowanyKlient.isCzyPromocja()) {
            boxPowiadomienia.setSelected(false);
            boxPowiadomienia.setFocusable(false);
            radioPowiadomienia.clearSelection();
        } else if (sklep.zalogowanyKlient.obs instanceof ObserwatorEmail) {
            radioEmail.setSelected(true);
            boxPowiadomienia.setSelected(true);
            radioSMS.setEnabled(true);
            radioEmail.setEnabled(true);
            buttonMail.setEnabled(true);
        } else if (sklep.zalogowanyKlient.obs instanceof ObserwatorSMS) {
            radioSMS.setSelected(true);
            boxPowiadomienia.setSelected(true);
            radioSMS.setEnabled(true);
            radioEmail.setEnabled(true);
            buttonMail.setEnabled(true);
        }

        radioSMS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioSMS.isSelected()) {
                    sklep.zalogowanyKlient.wypiszZPromocji(sklep.promocja);
                    sklep.zalogowanyKlient.zapiszNaPromocje(sklep.promocja, "sms");
                }
            }
        });
        radioEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioEmail.isSelected()) {
                    sklep.zalogowanyKlient.wypiszZPromocji(sklep.promocja);
                    sklep.zalogowanyKlient.zapiszNaPromocje(sklep.promocja, "email");
                }
            }
        });


        pokazWszystkieProdutky(frame, sklep);
        buttonWszystko.setEnabled(false);

        frame.add(panelLewy);
        frame.add(panelGora);
        frame.setVisible(true);


        boxPowiadomienia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == boxPowiadomienia) {
                    if (boxPowiadomienia.isSelected()) {
                        radioEmail.setEnabled(true);
                        radioSMS.setEnabled(true);
                        buttonMail.setEnabled(true);
                    } else {
                        radioEmail.setEnabled(false);
                        radioSMS.setEnabled(false);
                        buttonMail.setEnabled(false);

                        radioPowiadomienia.clearSelection();
                        sklep.zalogowanyKlient.wypiszZPromocji(sklep.promocja);
                    }
                }
            }
        });

        buttonKoszyk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == buttonKoszyk) {
                    //TODO sklep.getZalogowanyKlient().getKoszyk().inicjalizujMape();

                    frame.getContentPane().removeAll();
                    frame.revalidate();
                    frame.repaint();
                    KoszykPane koszykPane = new KoszykPane();
                    koszykPane.otworzKoszyk(sklep, frame);
                    frame.revalidate();
                    frame.repaint();

                }
            }
        });

        buttonMail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == buttonMail) {
                    if (panelPowiadomienia.isVisible()) {
                        panelPowiadomienia.setEnabled(false);
                        panelPowiadomienia.setVisible(false);
                        scrollPanePowiadomienia.setVisible(false);
                        scrollPanePowiadomienia.setEnabled(false);
                        panelPowiadomienia.revalidate();
                        panelPowiadomienia.repaint();
                    } else {
                        panelPowiadomienia.setEnabled(true);
                        panelPowiadomienia.setVisible(true);
                        scrollPanePowiadomienia.setVisible(true);
                        scrollPanePowiadomienia.setEnabled(true);
                        panelPowiadomienia.revalidate();
                        panelPowiadomienia.repaint();
                    }
                }
            }
        });

        buttonWyloguj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sklep.zalogowanyKlient = null;
                frame.getContentPane().removeAll();
                frame.repaint();
                frame.revalidate();
                Rejestracja.ShopPage(sklep, frame);
            }
        });

        buttonWszystko.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pokazWszystkieProdutky(frame, sklep);
            }
        });

        buttonKoszulki.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pokazWszystkieKoszulki(frame, sklep);

            }
        });

        buttonButy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pokazWszystkieButy(frame, sklep);

            }
        });

        buttonSpodnie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pokazWszystkieSpodnie(frame, sklep);
            }
        });

        buttonBluza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pokazWszystkieBluzy(frame, sklep);
            }
        });
    }


    private static JPanel createProductPanel(String name, ImageIcon icon, String price, Sklep sklep, Produkt produkt) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(null);
        productPanel.setPreferredSize(new Dimension(360, 390));

        JButton addButton = new JButton(scaledAddImaged);
        addMouseEffect(addButton);

        addButton.setBounds(330, 0, 30, 30);

        JLabel labelIcon = new JLabel(icon);
        productPanel.add(labelIcon);
        labelIcon.setBounds(0, 0, 368, 360);

        productPanel.add(addButton);

        JLabel labelName = new JLabel(name);
        Font myFont = new Font("Serif", Font.BOLD, 14);
        labelName.setFont(myFont);
        labelName.setHorizontalAlignment(SwingConstants.LEFT);
        labelName.setVerticalAlignment(SwingConstants.CENTER);
        productPanel.add(labelName);
        labelName.setBounds(10, 360, 180, 30);

        JLabel labelPrice = new JLabel(price + " zł");
        productPanel.add(labelPrice);
        labelPrice.setFont(myFont);
        labelPrice.setHorizontalAlignment(SwingConstants.CENTER);
        labelPrice.setVerticalAlignment(SwingConstants.CENTER);
        labelPrice.setBounds(190, 360, 60, 30);


        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(produkt.getRozmiaryAsList().toArray(new String[0])));
        comboBox.insertItemAt("Wybierz rozmiar", 0);
        comboBox.setSelectedIndex(0);
        comboBox.setFont(new Font("Serif", Font.BOLD, 10));
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBox.getSelectedItem();
            }
        });

        productPanel.add(comboBox);
        comboBox.setBounds(250, 360, 110, 30);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajProdukt(name, sklep, produkt, comboBox);
            }
        });

        productPanel.setComponentZOrder(addButton, 0);
        productPanel.setComponentZOrder(labelIcon, 1);

        return productPanel;
    }

    public static int policzNewHeight(int liczba) {
        int totalRows = (int) Math.ceil((double) liczba / liczbaKolumn);
        return totalRows * (wysokoscProduktu + 25);
    }

    public static void pokazWszystkieKoszulki(JFrame frame, Sklep sklep) {
        scrollPane.setVisible(false);
        scrollPaneBluza.setVisible(false);
        scrollPaneButy.setVisible(false);
        scrollPaneSpodnie.setVisible(false);
        frame.revalidate();
        buttonSpodnie.setEnabled(true);
        buttonButy.setEnabled(true);
        buttonBluza.setEnabled(true);
        buttonWszystko.setEnabled(true);

        listaKoszulek.clear();

        for (Produkt produkt : sklep.getListaProduktow()) {
            if (produkt instanceof Koszulka && !listaKoszulek.contains(produkt)) {
                listaKoszulek.add((Koszulka) produkt);
            }
        }


        if (scrollPaneButy != null) {
            frame.remove(scrollPaneButy);
        }
        if (scrollPaneSpodnie != null) {
            frame.remove(scrollPaneSpodnie);
        }
        if (scrollPaneBluza != null) {
            frame.remove(scrollPaneBluza);
        }
        if (scrollPane != null) {
            frame.remove(scrollPane);
        }

        panelKoszulki = new JPanel(new GridLayout(0, 2, 0, 0));
        panelKoszulki.setPreferredSize(new Dimension(720, policzNewHeight(listaKoszulek.size())));

        for (Koszulka koszulka : listaKoszulek) {
            panelKoszulki.add(createProductPanel(koszulka.getNazwa(), koszulka.getIcon(360, 360), String.valueOf(koszulka.getCena()), sklep, koszulka));
        }

        scrollPaneKoszulki = new JScrollPane(panelKoszulki);
        scrollPaneKoszulki.setBounds(200, 70, 737, 700);
        scrollPaneKoszulki.getVerticalScrollBar().setUnitIncrement(16);
        frame.add(scrollPaneKoszulki);

        panelKoszulki.revalidate();
        panelKoszulki.repaint();

        buttonKoszulki.setEnabled(false);
    }

    public static void pokazWszystkieBluzy(JFrame frame, Sklep sklep) {
        scrollPane.setVisible(false);
        scrollPaneButy.setVisible(false);
        scrollPaneKoszulki.setVisible(false);
        scrollPaneSpodnie.setVisible(false);
        frame.revalidate();
        buttonKoszulki.setEnabled(true);
        buttonButy.setEnabled(true);
        buttonSpodnie.setEnabled(true);
        buttonWszystko.setEnabled(true);

        listaBluz.clear();

        for (Produkt produkt : sklep.getListaProduktow()) {
            if (produkt instanceof Bluza && !listaBluz.contains(produkt)) {
                listaBluz.add((Bluza) produkt);
            }
        }

        if (scrollPaneButy != null) {
            frame.remove(scrollPaneButy);
        }
        if (scrollPaneSpodnie != null) {
            frame.remove(scrollPaneSpodnie);
        }
        if (scrollPaneKoszulki != null) {
            frame.remove(scrollPaneKoszulki);
        }
        if (scrollPane != null) {
            frame.remove(scrollPane);
        }

        // Utwórz nowy panel dla butów
        panelBluza = new JPanel(new GridLayout(0, 2, 0, 0));
        panelBluza.setPreferredSize(new Dimension(720, policzNewHeight(listaBluz.size())));

        for (Bluza bluza : listaBluz) {
            panelBluza.add(createProductPanel(bluza.getNazwa(), bluza.getIcon(360, 360), String.valueOf(bluza.getCena()), sklep, bluza));
        }

        scrollPaneBluza = new JScrollPane(panelBluza);
        scrollPaneBluza.setBounds(200, 70, 737, 700);
        scrollPaneBluza.getVerticalScrollBar().setUnitIncrement(16);
        frame.add(scrollPaneBluza);

        // Odświeżanie widoku
        panelBluza.revalidate();
        panelBluza.repaint();

        buttonBluza.setEnabled(false);
    }

    public static void pokazWszystkieSpodnie(JFrame frame, Sklep sklep) {
        scrollPane.setVisible(false);
        scrollPaneBluza.setVisible(false);
        scrollPaneButy.setVisible(false);
        scrollPaneKoszulki.setVisible(false);
        frame.revalidate();
        buttonKoszulki.setEnabled(true);
        buttonButy.setEnabled(true);
        buttonBluza.setEnabled(true);
        buttonWszystko.setEnabled(true);

        listaSpodni.clear();

        for (Produkt produkt : sklep.getListaProduktow()) {
            if (produkt instanceof Spodnie && !listaSpodni.contains(produkt)) {
                listaSpodni.add((Spodnie) produkt);
            }
        }

        if (scrollPaneButy != null) {
            frame.remove(scrollPaneButy);
        }
        if (scrollPane != null) {
            frame.remove(scrollPane);
        }
        if (scrollPaneKoszulki != null) {
            frame.remove(scrollPaneKoszulki);
        }
        if (scrollPaneBluza != null) {
            frame.remove(scrollPaneBluza);
        }

        panelSpodnie = new JPanel(new GridLayout(0, 2, 0, 0));
        panelSpodnie.setPreferredSize(new Dimension(720, policzNewHeight(listaSpodni.size())));

        for (Spodnie spodnie : listaSpodni) {
            if (spodnie.getIcon(360, 360) != null) {
                panelSpodnie.add(createProductPanel(spodnie.getNazwa(), spodnie.getIcon(360, 360), String.valueOf(spodnie.getCena()), sklep, spodnie));
            }
        }

        scrollPaneSpodnie = new JScrollPane(panelSpodnie);
        scrollPaneSpodnie.setBounds(200, 70, 737, 700);
        scrollPaneSpodnie.getVerticalScrollBar().setUnitIncrement(16);
        frame.add(scrollPaneSpodnie);

        panelSpodnie.revalidate();
        panelSpodnie.repaint();

        buttonSpodnie.setEnabled(false);
    }

    public static void pokazWszystkieProdutky(Frame frame, Sklep sklep) {
        scrollPane.setVisible(false);
        scrollPaneBluza.setVisible(false);
        scrollPaneButy.setVisible(false);
        scrollPaneKoszulki.setVisible(false);
        scrollPane.setVisible(true);
        panelGlowny.removeAll();

        frame.revalidate();
        buttonKoszulki.setEnabled(true);
        buttonButy.setEnabled(true);
        buttonBluza.setEnabled(true);
        buttonSpodnie.setEnabled(true);

        Collections.shuffle(sklep.getListaProduktow());


        if (scrollPaneButy != null) {
            frame.remove(scrollPaneButy);
        }
        if (scrollPaneSpodnie != null) {
            frame.remove(scrollPaneSpodnie);
        }
        if (scrollPaneBluza != null) {
            frame.remove(scrollPaneBluza);
        }
        if (scrollPane != null) {
            frame.remove(scrollPane);
        }

        panelGlowny = new JPanel(new GridLayout(0, 2, 0, 0));
        panelGlowny.setPreferredSize(new Dimension(720, policzNewHeight(sklep.getListaProduktow().size())));

        for (Produkt produkt : sklep.getListaProduktow()) {
            panelGlowny.add(createProductPanel(produkt.getNazwa(), produkt.getIcon(360, 360), String.valueOf(produkt.getCena()), sklep, produkt));
        }

        scrollPane = new JScrollPane(panelGlowny);
        scrollPane.setBounds(200, 70, 737, 700);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        frame.add(scrollPane);

        panelGlowny.revalidate();
        panelGlowny.repaint();

        buttonWszystko.setEnabled(false);
    }

    public static void pokazWszystkieButy(JFrame frame, Sklep sklep) {
        scrollPane.setVisible(false);
        scrollPaneBluza.setVisible(false);
        scrollPaneKoszulki.setVisible(false);
        scrollPaneButy.setVisible(false);
        frame.revalidate();
        buttonKoszulki.setEnabled(true);
        buttonBluza.setEnabled(true);
        buttonSpodnie.setEnabled(true);
        buttonWszystko.setEnabled(true);

        listaButow.clear();

        for (Produkt produkt : sklep.getListaProduktow()) {
            if (produkt instanceof Obuwie && !listaButow.contains(produkt)) {
                listaButow.add((Obuwie) produkt);
            }
        }

        if (scrollPaneKoszulki != null) {
            frame.remove(scrollPaneKoszulki);
        }
        if (scrollPaneSpodnie != null) {
            frame.remove(scrollPaneSpodnie);
        }
        if (scrollPaneBluza != null) {
            frame.remove(scrollPaneBluza);
        }
        if (scrollPane != null) {
            frame.remove(scrollPane);
        }

        panelButy = new JPanel(new GridLayout(0, 2, 0, 0));
        panelButy.setPreferredSize(new Dimension(720, policzNewHeight(listaButow.size())));

        for (Obuwie obuwie : listaButow) {
            panelButy.add(createProductPanel(obuwie.getNazwa(), obuwie.getIcon(360, 360), String.valueOf(obuwie.getCena()), sklep, obuwie));
        }

        scrollPaneButy = new JScrollPane(panelButy);
        scrollPaneButy.setBounds(200, 70, 737, 700);
        scrollPaneButy.getVerticalScrollBar().setUnitIncrement(16);
        frame.add(scrollPaneButy);

        panelButy.revalidate();
        panelButy.repaint();

        buttonButy.setEnabled(false);
    }

    public static ImageIcon scaleIcon(String path, int size) {
        ImageIcon icon = new ImageIcon(path);
        Image originalImage = icon.getImage();
        Image scaledImage = originalImage.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        return scaledIcon;
    }

    public static void addMouseEffect(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JButton b = (JButton) e.getSource();
                b.setContentAreaFilled(true);
                b.setBackground(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JButton b = (JButton) e.getSource();
                b.setContentAreaFilled(false);
            }
        });
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setFocusable(false);
    }

    public static void dodajProdukt(String name, Sklep sklep, Produkt produkt, JComboBox comboBox) {
        try{
            ProduktWKoszykuException.checkIfEmpty(sklep,produkt,comboBox);
            iloscWKoszyku.setText(String.valueOf(sklep.zalogowanyKlient.getKoszyk().getProduktyWKoszyku().size()));
        } catch (ProduktWKoszykuException e){
            JOptionPane.showMessageDialog(null, "Produkt jest juz w koszyku", "Blad", JOptionPane.WARNING_MESSAGE);
        }
    }
}