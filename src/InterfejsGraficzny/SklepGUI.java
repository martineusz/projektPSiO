package InterfejsGraficzny;

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
import Obserwator.*;


import Main.Sklep;
import Main.Sklep.*;
import Produkt.Produkt;
import Produkt.*;

public class SklepGUI {
    private static ArrayList<String> listaProduktowWKoszyku;
    private static int liczbaKolumn;
    private static int wysokoscProduktu;
    private static JScrollPane scrollPaneButy = new JScrollPane();
    private static JScrollPane scrollPaneBluza = new JScrollPane();
    private static JScrollPane scrollPaneKoszulki = new JScrollPane();
    private static JScrollPane scrollPaneSpodnie = new JScrollPane();
    private static JScrollPane scrollPane = new JScrollPane();
    private static JPanel panelBluza = new JPanel();
    private static JPanel panelButy = new JPanel();
    private static JPanel panelKoszulki = new JPanel();
    private static JPanel panelSpodnie = new JPanel();
    private static JPanel panelGlowny = new JPanel();
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
    private static ArrayList<Bluza> listaBluz = new ArrayList<>();
    private static ArrayList<Obuwie> listaButow = new ArrayList<>();
    private static ArrayList<Koszulka> listaKoszulek = new ArrayList<>();
    private static ArrayList<Spodnie> listaSpodni = new ArrayList<>();

    public static void openSklepGUI(JFrame frame, Sklep sklep) {
        listaProduktowWKoszyku = new ArrayList<>();
        Map<JButton, String> buttonPromocjeMap = new HashMap<>();
        panelPowiadomienia = new JPanel();
        JScrollPane scrollPanePowiadomienia = new JScrollPane(panelPowiadomienia);
        JScrollBar szybkoscScroll = scrollPanePowiadomienia.getVerticalScrollBar();

        frame.setSize(1050, 700);
        int liczbaBluz = 0;
        int liczbaButow = 0;
        int liczbaKoszulek = 0;
        int liczbaSpodni = 0;

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

        wysokoscProduktu = 250;
        liczbaKolumn = 2;

        JSeparator separator1 = new JSeparator(SwingConstants.HORIZONTAL);
        frame.add(separator1);
        separator1.setBounds(0, 70, 1050, 2);
        separator1.setBackground(Color.lightGray);

        JPanel panelLewy = new JPanel();
        JPanel panelGora = new JPanel();
        panelGlowny = new JPanel();
        panelGora.setBounds(0, 0, 1050, 70);
        panelLewy.setBounds(0, 70, 300, 630);
        panelGlowny.setBounds(300, 70, 750, 630);

        panelLewy.setLayout(null);
        panelGora.setLayout(null);
        panelGlowny.setLayout(new GridLayout(0, 3, 0, 0));
        scrollPane = new JScrollPane(panelGlowny);
        frame.add(scrollPane);
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setBounds(300, 70, 737, 630);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);


        //POWIADOMIENIA!!!!!! DODAWANIE RECZNE DLA TESTU (DO USUNIECIA JAK PIHI SKONCZY SIE OPIERDALAC)
        sklep.getZalogowanyKlient().zapiszNaPromocje(sklep.promocja, "sms");
        sklep.getZalogowanyKlient().zapiszNaPromocje(sklep.promocja, "email");
        sklep.zalogowanyKlient.obs.getPowiadomienia().add("Promocja1");
        sklep.zalogowanyKlient.obs.getPowiadomienia().add("Promocja2");
        sklep.zalogowanyKlient.obs.getPowiadomienia().add("Promocja3");
        sklep.zalogowanyKlient.obs.getPowiadomienia().add("Promocja4");
        sklep.zalogowanyKlient.obs.getPowiadomienia().add("Promocja5");
        sklep.zalogowanyKlient.obs.getPowiadomienia().add("Promocja6");


            if (sklep.zalogowanyKlient.obs != null) {
                for (int i = 0; i < sklep.zalogowanyKlient.obs.getPowiadomienia().size(); i++) {
                    JButton buttonUsun = new JButton();
                    buttonUsun.setBounds(200, 40, 30, 30);
                    buttonUsun.setText("X");
                    buttonUsun.setFocusable(false);
                    buttonUsun.setFont(new Font(null, Font.BOLD, 20));
                    buttonUsun.setBackground(Color.WHITE);
                    buttonUsun.setForeground(Color.RED);
                    buttonUsun.setBorder(BorderFactory.createEtchedBorder());

                    String promocja = sklep.zalogowanyKlient.obs.getPowiadomienia().get(i);

                    JLabel labelPromocja = new JLabel();
                    labelPromocja.setBounds(15, 15, 200, 40);
                    labelPromocja.setText("Promocja: " + promocja);
                    labelPromocja.setFont(new Font(null, Font.BOLD, 10));


                    JPanel panelPromocji = new JPanel();
                    panelPromocji.setLayout(null);
                    panelPromocji.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    panelPromocji.setPreferredSize(new Dimension(300, 100));

                    panelPowiadomienia.add(panelPromocji);
                    panelPromocji.add(buttonUsun);
                    panelPromocji.add(labelPromocja);

                    buttonPromocjeMap.put(buttonUsun, promocja);
                    buttonUsun.addActionListener(e -> {
                        JButton sourceButton = (JButton) e.getSource();
                        String promocjaToRemove = buttonPromocjeMap.get(sourceButton);

                        if (promocjaToRemove != null) {
                            sklep.zalogowanyKlient.obs.getPowiadomienia().remove(promocjaToRemove);
                            panelPowiadomienia.remove(sourceButton.getParent());
                            panelPowiadomienia.revalidate();
                            panelPowiadomienia.repaint();
                            panelPowiadomienia.setPreferredSize(new Dimension(300,panelPowiadomienia.getComponentCount() * 110));
                        }
                    });
                }
            }
            else {
                JLabel brakPromocji = new JLabel("Nie jestes zapisany za zadna promocje!!");
                brakPromocji.setAlignmentY(100);
                panelPowiadomienia.add(brakPromocji);
            }

            //SCROLL DO PANELU POWIADOMIEC NA PIHACZA
        panelPowiadomienia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelPowiadomienia.setEnabled(false);
        panelPowiadomienia.setVisible(false);
        panelPowiadomienia.setPreferredSize(new Dimension(300,panelPowiadomienia.getComponentCount() * 110));

        szybkoscScroll.setUnitIncrement(20);
        szybkoscScroll.setBlockIncrement(40);
        scrollPanePowiadomienia.setVisible(false);
        scrollPanePowiadomienia.setEnabled(false);
        scrollPanePowiadomienia.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPanePowiadomienia.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanePowiadomienia.setBounds(700, 70, 300, 450);
        frame.add(scrollPanePowiadomienia);




        ImageIcon carticon = new ImageIcon("cart.jpg");
        Image originalImage = carticon.getImage();
        Image scaledImage = originalImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        ImageIcon mailicon = new ImageIcon("mail.jpg");
        Image originalMailImage = mailicon.getImage();
        Image scaledMailImage = originalMailImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledMailIcon = new ImageIcon(scaledMailImage);

        boxPowiadomienia = new JCheckBox();
        panelGora.add(boxPowiadomienia);
        boxPowiadomienia.setBounds(100, 17, 340, 40);
        boxPowiadomienia.setText("Czy chcesz otrzymywać powiadomienia o promocjach?");
        boxPowiadomienia.setSelected(false);
        boxPowiadomienia.setFocusable(false);

        radioSMS = new JRadioButton("sms");
        radioEmail = new JRadioButton("email");
        radioPowiadomienia = new ButtonGroup();
        radioPowiadomienia.add(radioEmail);
        radioPowiadomienia.add(radioSMS);
        panelGora.add(radioEmail);
        panelGora.add(radioSMS);
        radioEmail.setBounds(550, 17, 100, 40);
        radioSMS.setBounds(650, 17, 100, 40);
        radioEmail.setEnabled(false);
        radioSMS.setEnabled(false);


        buttonKoszyk = new JButton();
        panelGora.add(buttonKoszyk);
        buttonKoszyk.setBounds(900, 17, 40, 40);
        buttonKoszyk.setFocusable(false);
        buttonKoszyk.setIcon(scaledIcon);

        JButton buttonMail = new JButton();
        panelGora.add(buttonMail);
        buttonMail.setBounds(840, 17, 40, 40);
        buttonMail.setFocusable(false);
        buttonMail.setIcon(scaledMailIcon);

        buttonMail.setEnabled(false);

        buttonButy = new JButton("Buty");
        buttonKoszulki = new JButton("Koszulki");
        buttonSpodnie = new JButton("Spodnie");
        buttonBluza = new JButton("Bluzy");
        buttonWszystko = new JButton("Wszystko");

        JButton[] buttons = {buttonButy, buttonKoszulki, buttonSpodnie, buttonBluza, buttonWszystko};
        for (JButton button : buttons) {
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setOpaque(false);
            panelLewy.add(button);

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
        }

        int startY = 20;
        int buttonHeight = 30;
        int buttonWidth = 100;
        int spacing = 4;
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setBounds((300 - buttonWidth) / 4, startY + (buttonHeight + spacing) * i, buttonWidth, buttonHeight);
        }

        pokazWszystkieProdutky(frame, sklep);
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
                    }
                }
            }
        });

        buttonKoszyk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == buttonKoszyk) {
                    frame.getContentPane().removeAll();
                    frame.revalidate();
                    frame.repaint();
                    frame.setSize(new Dimension(1920, 1080));
                    frame.add(new KoszykFrame(frame, sklep));
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

        int finalLiczbaBluz = liczbaBluz;
        buttonBluza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                pokazWszystkieBluzy(frame, sklep);
            }
        });


        int finalLiczbaButow = liczbaButow;
        buttonButy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollPane.setVisible(false);
                scrollPaneBluza.setVisible(false);
                scrollPaneKoszulki.setVisible(false);
                scrollPaneButy.setVisible(false);
                frame.revalidate();
                buttonKoszulki.setEnabled(true);
                buttonBluza.setEnabled(true);
                buttonSpodnie.setEnabled(true);
                buttonWszystko.setEnabled(true);

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

                panelButy = new JPanel(new GridLayout(0, 3, 0, 0));
                panelButy.setPreferredSize(new Dimension(750, policzNewHeight(listaButow.size())));

                for (Obuwie obuwie : listaButow) {
                    panelButy.add(createProductPanel(obuwie.getNazwa(), obuwie.getIcon(225, 225), String.valueOf(obuwie.getCena()), sklep, obuwie));
                }

                scrollPaneButy = new JScrollPane(panelButy);
                scrollPaneButy.setBounds(300, 70, 737, 630);
                scrollPaneButy.getVerticalScrollBar().setUnitIncrement(16);
                frame.add(scrollPaneButy);

                panelButy.revalidate();
                panelButy.repaint();

                buttonButy.setEnabled(false);
            }
        });

        int finalLiczbaKoszulek = liczbaKoszulek;
        buttonKoszulki.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollPane.setVisible(false);
                scrollPaneBluza.setVisible(false);
                scrollPaneButy.setVisible(false);
                scrollPaneSpodnie.setVisible(false);
                frame.revalidate();
                buttonSpodnie.setEnabled(true);
                buttonButy.setEnabled(true);
                buttonBluza.setEnabled(true);
                buttonWszystko.setEnabled(true);


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

                panelKoszulki = new JPanel(new GridLayout(0, 3, 0, 0));
                panelKoszulki.setPreferredSize(new Dimension(750, policzNewHeight(listaKoszulek.size())));

                for (Koszulka koszulka : listaKoszulek) {
                    panelKoszulki.add(createProductPanel(koszulka.getNazwa(), koszulka.getIcon(225, 225), String.valueOf(koszulka.getCena()), sklep, koszulka));
                }

                scrollPaneKoszulki = new JScrollPane(panelKoszulki);
                scrollPaneKoszulki.setBounds(300, 70, 737, 630);
                scrollPaneKoszulki.getVerticalScrollBar().setUnitIncrement(16);
                frame.add(scrollPaneKoszulki);

                panelKoszulki.revalidate();
                panelKoszulki.repaint();

                buttonKoszulki.setEnabled(false);
            }
        });

        buttonSpodnie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pokazWszystkieSpodnie(frame, sklep);
            }
        });

        buttonWszystko.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pokazWszystkieProdutky(frame, sklep);
            }
        });
    }


    private static JPanel createProductPanel(String name, String iconPath, String price, Sklep sklep, Produkt produkt) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(null);

        ImageIcon icon = new ImageIcon(iconPath);
        JLabel labelIcon = new JLabel(icon);
        productPanel.add(labelIcon);
        labelIcon.setBounds(0, 0, 225, 225);

        JLabel labelName = new JLabel(name);
        Font myFont = new Font("Serif", Font.BOLD, 9);
        labelName.setFont(myFont);
        productPanel.add(labelName);
        labelName.setBounds(0, 230, 100, 25);

        JLabel labelPrice = new JLabel(price);
        productPanel.add(labelPrice);
        labelPrice.setBounds(100, 230, 30, 25);

        JButton addButton = new JButton("Dodaj");
        productPanel.add(addButton);
        addButton.setBounds(130, 230, 95, 25);

        addButton.addActionListener(e -> {
            System.out.println("Dodano " + name);

        });

        return productPanel;
    }

    private static JPanel createProductPanel(String name, ImageIcon icon, String price, Sklep sklep, Produkt produkt) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(null);

        JLabel labelIcon = new JLabel(icon);
        productPanel.add(labelIcon);
        labelIcon.setBounds(0, 0, 225, 225);

        JLabel labelName = new JLabel(name);
        Font myFont = new Font("Serif", Font.BOLD, 9);
        labelName.setFont(myFont);
        productPanel.add(labelName);
        labelName.setBounds(0, 230, 100, 25);

        JLabel labelPrice = new JLabel(price);
        productPanel.add(labelPrice);
        labelPrice.setBounds(100, 230, 30, 25);

        JButton addButton = new JButton("Dodaj");
        productPanel.add(addButton);
        addButton.setBounds(130, 230, 95, 25);

        addButton.addActionListener(e -> {
            System.out.println("Dodano " + name);
            boolean xd = true;
            if (sklep.zalogowanyKlient.getKoszyk().getListaProduktow().isEmpty()) {
                sklep.zalogowanyKlient.getKoszyk().dodajProdukt(produkt);
            } else {
                for (int i = 0; i < sklep.zalogowanyKlient.getKoszyk().getListaProduktow().size(); i++) {
                    if (sklep.zalogowanyKlient.getKoszyk().getListaProduktow().get(i).equals(produkt)) {
                        xd = false;
                        break;
                    }
                }
                if (xd) {
                    sklep.zalogowanyKlient.getKoszyk().dodajProdukt(produkt);
                } else {
                    JOptionPane.showMessageDialog(null, "Produkt jest juz w koszyku, bro", "NIE MOZESZ TEGO ZROBIC", JOptionPane.WARNING_MESSAGE);
                }
            }
            listaProduktowWKoszyku.add(name);
        });

        return productPanel;
    }

    public static int policzNewHeight(int liczba) {
        int totalRows = (int) Math.ceil((double) liczba / liczbaKolumn);
        return totalRows * wysokoscProduktu;
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
        for (Produkt produkt : sklep.getListaProduktow()) {
            panelGlowny.add(createProductPanel(produkt.getNazwa(), produkt.getIcon(225, 225), String.valueOf(produkt.getCena()), sklep, produkt));
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

        panelGlowny = new JPanel(new GridLayout(0, 3, 0, 0));
        panelGlowny.setPreferredSize(new Dimension(750, policzNewHeight(sklep.getListaProduktow().size())));

        for (Produkt produkt : sklep.getListaProduktow()) {
            panelGlowny.add(createProductPanel(produkt.getNazwa(), produkt.getIcon(225, 225), String.valueOf(produkt.getCena()), sklep, produkt));
        }

        scrollPane = new JScrollPane(panelGlowny);
        scrollPane.setBounds(300, 70, 737, 630);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        frame.add(scrollPane);

        panelGlowny.revalidate();
        panelGlowny.repaint();

        buttonWszystko.setEnabled(false);
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

        panelSpodnie = new JPanel(new GridLayout(0, 3, 0, 0));
        panelSpodnie.setPreferredSize(new Dimension(750, policzNewHeight(listaSpodni.size())));

        for (Spodnie spodnie : listaSpodni) {
            if (spodnie.getIcon(225, 225) != null) {
                panelSpodnie.add(createProductPanel(spodnie.getNazwa(), spodnie.getIcon(225, 225), String.valueOf(spodnie.getCena()), sklep, spodnie));
            }
        }

        scrollPaneSpodnie = new JScrollPane(panelSpodnie);
        scrollPaneSpodnie.setBounds(300, 70, 737, 630);
        scrollPaneSpodnie.getVerticalScrollBar().setUnitIncrement(16);
        frame.add(scrollPaneSpodnie);

        panelSpodnie.revalidate();
        panelSpodnie.repaint();

        buttonSpodnie.setEnabled(false);
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
        panelBluza = new JPanel(new GridLayout(0, 3, 0, 0));
        panelBluza.setPreferredSize(new Dimension(750, policzNewHeight(listaBluz.size())));

        for (Bluza bluza : listaBluz) {
            panelBluza.add(createProductPanel(bluza.getNazwa(), bluza.getIcon(225, 225), String.valueOf(bluza.getCena()), sklep, bluza));
        }

        scrollPaneBluza = new JScrollPane(panelBluza);
        scrollPaneBluza.setBounds(300, 70, 737, 630);
        scrollPaneBluza.getVerticalScrollBar().setUnitIncrement(16);
        frame.add(scrollPaneBluza);

        // Odświeżanie widoku
        panelBluza.revalidate();
        panelBluza.repaint();

        buttonBluza.setEnabled(false);
    }
}
