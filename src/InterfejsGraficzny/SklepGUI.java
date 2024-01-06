package InterfejsGraficzny;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;


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
        private static JButton buttonKoszulki;
        private static JButton buttonSpodnie;
        private static JButton buttonButy;
        private static JButton buttonWszystko;
        private static JButton buttonBluza;
        private static JButton buttonKoszyk;
        private static JCheckBox boxPowiadomienia;
        private static ArrayList<Bluza> listaBluz = new ArrayList<>();
        private static ArrayList<Obuwie> listaButow = new ArrayList<>();
        private static ArrayList<Koszulka> listaKoszulek = new ArrayList<>();
        private static ArrayList<Spodnie> listaSpodni = new ArrayList<>();

        public static  void openSklepGUI (JFrame frame, Sklep sklep) {
            listaProduktowWKoszyku = new ArrayList<>();

            frame.setSize(1050,700);
            int liczbaBluz = 0;
            int liczbaButow = 0;
            int liczbaKoszulek = 0;
            int liczbaSpodni = 0;

            for (Produkt produkt: sklep.getListaProduktow())
                if (produkt instanceof Bluza) {
                    liczbaBluz += 1;
                    listaBluz.add((Bluza) produkt);
                }
                else if (produkt instanceof Obuwie) {
                    liczbaButow += 1;
                    listaButow.add((Obuwie) produkt);
                }
                else if (produkt instanceof Spodnie) {
                    liczbaSpodni += 1;
                    listaSpodni.add((Spodnie) produkt);
                }
                else if (produkt instanceof Koszulka) {
                    liczbaKoszulek += 1;
                    listaKoszulek.add((Koszulka) produkt);
                }

            int liczbaProduktow = sklep.getListaProduktow().size();
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

            ImageIcon carticon = new ImageIcon("cart.jpg");
            Image originalImage = carticon.getImage();
            Image scaledImage = originalImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            ImageIcon mailicon = new ImageIcon("mail.jpg");
            Image originalMailImage = mailicon.getImage();
            Image scaledMailImage = originalMailImage.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            ImageIcon scaledMailIcon = new ImageIcon(scaledMailImage);

            //boxPowiadomienia = new JCheckBox();


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

            buttonKoszyk.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == buttonKoszyk){
                        frame.getContentPane().removeAll();
                        frame.revalidate();
                        frame.repaint();
                        frame.setSize(new Dimension(1920,1080));
                        frame.add(new KoszykFrame(frame,sklep));
                        frame.revalidate();
                        frame.repaint();


                    }
                }
            });

            int finalLiczbaBluz = liczbaBluz;
            buttonBluza.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e
                ) {
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
                    if (scrollPane!=null) {
                        frame.remove(scrollPane);
                    }

                    // Utwórz nowy panel dla butów
                    panelBluza = new JPanel(new GridLayout(0, 3, 0, 0));
                    panelBluza.setPreferredSize(new Dimension(750, policzNewHeight(finalLiczbaBluz)));

                    for (Bluza bluza : listaBluz) {
                        panelBluza.add(createProductPanel(bluza.getNazwa(), bluza.getIcon(225,225), String.valueOf(bluza.getCena())));
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
                    if (scrollPane!=null) {
                        frame.remove(scrollPane);
                    }

                    panelButy = new JPanel(new GridLayout(0, 3, 0, 0));
                    panelButy.setPreferredSize(new Dimension(750, policzNewHeight(finalLiczbaButow)));

                    for (Obuwie obuwie : listaButow) {
                        panelButy.add(createProductPanel(obuwie.getNazwa(), obuwie.getIcon(225,225), String.valueOf(obuwie.getCena())));
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
                    if (scrollPane!=null) {
                        frame.remove(scrollPane);
                    }

                    panelKoszulki = new JPanel(new GridLayout(0, 3, 0, 0));
                    panelKoszulki.setPreferredSize(new Dimension(750, policzNewHeight(finalLiczbaKoszulek)));

                    for (Koszulka koszulka : listaKoszulek) {
                        panelKoszulki.add(createProductPanel(koszulka.getNazwa(), koszulka.getIcon(225,225), String.valueOf(koszulka.getCena())));
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

            int finalLiczbaSpodni = liczbaSpodni;
            buttonSpodnie.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
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
                    panelSpodnie.setPreferredSize(new Dimension(750, policzNewHeight(finalLiczbaSpodni)));

                    for (Spodnie spodnie : listaSpodni) {
                        if (spodnie.getIcon(225, 225) != null) {
                            panelSpodnie.add(createProductPanel(spodnie.getNazwa(), spodnie.getIcon(225,225), String.valueOf(spodnie.getCena())));}
                        }

                    scrollPaneSpodnie = new JScrollPane(panelSpodnie);
                    scrollPaneSpodnie.setBounds(300, 70, 737, 630);
                    scrollPaneSpodnie.getVerticalScrollBar().setUnitIncrement(16);
                    frame.add(scrollPaneSpodnie);

                    panelSpodnie.revalidate();
                    panelSpodnie.repaint();

                    buttonSpodnie.setEnabled(false);
                }
            });

            buttonWszystko.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                pokazWszystkieProdutky(frame,sklep);}
            });
        }


        private static JPanel createProductPanel(String name, String iconPath, String price) {
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
                listaProduktowWKoszyku.add(name);
                JOptionPane.showMessageDialog(null, "Dodano do koszyka", "Info",
                        JOptionPane.INFORMATION_MESSAGE);
            });

            return productPanel;
        }

        private static JPanel createProductPanel(String name, ImageIcon icon, String price) {
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
                listaProduktowWKoszyku.add(name);
            });

            return productPanel;
        }

        public static int policzNewHeight(int liczba) {
            int totalRows = (int) Math.ceil((double) liczba / liczbaKolumn);
            return totalRows * wysokoscProduktu;
        }

        public static void pokazWszystkieProdutky (Frame frame, Sklep sklep) {
            scrollPane.setVisible(false);
            scrollPaneBluza.setVisible(false);
            scrollPaneButy.setVisible(false);
            scrollPaneKoszulki.setVisible(false);
            scrollPane.setVisible(true);

            frame.revalidate();
            buttonKoszulki.setEnabled(true);
            buttonButy.setEnabled(true);
            buttonBluza.setEnabled(true);
            buttonSpodnie.setEnabled(true);

            Collections.shuffle(sklep.getListaProduktow());
            for (Produkt produkt : sklep.getListaProduktow()) {
                panelGlowny.add(createProductPanel(produkt.getNazwa(), produkt.getIcon(225,225), String.valueOf(produkt.getCena())));
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
            if (scrollPane!=null) {
                frame.remove(scrollPane);
            }

            panelGlowny = new JPanel(new GridLayout(0, 3, 0, 0));
            panelGlowny.setPreferredSize(new Dimension(750, policzNewHeight(sklep.getListaProduktow().size())));

            for (Produkt produkt : sklep.getListaProduktow()) {
                panelGlowny.add(createProductPanel(produkt.getNazwa(), produkt.getIcon(225,225), String.valueOf(produkt.getCena())));
            }

            scrollPane = new JScrollPane(panelGlowny);
            scrollPane.setBounds(300, 70, 737, 630);
            scrollPane.getVerticalScrollBar().setUnitIncrement(16);
            frame.add(scrollPane);

            panelGlowny.revalidate();
            panelGlowny.repaint();

            buttonWszystko.setEnabled(false);
        }
}
