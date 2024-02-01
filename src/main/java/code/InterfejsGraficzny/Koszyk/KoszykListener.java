
package code.InterfejsGraficzny.Koszyk;

import code.DostawaStrategia.DostawaKurier;
import code.DostawaStrategia.DostawaPaczkomat;
import code.InterfejsGraficzny.Rejestracja;
import code.InterfejsGraficzny.SklepGUI;
import code.PlacenieStrategia.placBlikiem;
import code.PlacenieStrategia.placKarta;
import code.Produkt.Produkt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KoszykListener implements ActionListener {
    private final KoszykPane koszykPane;

    public KoszykListener(KoszykPane koszykPane) {
        this.koszykPane = koszykPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //1
        if (e.getSource() == koszykPane.getButtonZamowienie()) {
            if (!koszykPane.getKoszyk().getProduktyWKoszyku().isEmpty()) {
                //USUWANIE
                koszykPane.getPanelGlowny().remove(koszykPane.getPanelKoszyk());
                koszykPane.getPanelGlowny().remove(koszykPane.getScrollPaneKoszyk());
                koszykPane.getPanelPodsumowanie().remove(koszykPane.getButtonZamowienie());
                koszykPane.getPanelGlowny().revalidate();
                koszykPane.getPanelGlowny().repaint();

                //RYSOWANIE
                koszykPane.rysujPanelDostawy(koszykPane.getPanelGlowny(), koszykPane.getSklep());
                koszykPane.getLabelDostawa().setBackground(new Color(184, 255, 184));
                koszykPane.getLabelKoszyk().setText("DOSTAWA");
                koszykPane.getPanelPodsumowanie().add(koszykPane.getButtonDostawa());
                koszykPane.getPanelPodsumowanie().add(koszykPane.getButtonCofnijDostawa());
                koszykPane.setCenaDostawa(15.99F);
                koszykPane.getLabelSumaCen().setText("SUMA: " + koszykPane.getSumaCen() + " PLN");
                koszykPane.getLabelCenaDostawy().setText("Dostawa: " + koszykPane.getCenaDostawa() + " PLN");
            } else {
                JOptionPane.showMessageDialog(null, "Koszyk jest pusty!", "Blad", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == koszykPane.getButtonKurier()) {
            koszykPane.getKoszyk().ustawMetodeDostawy(new DostawaKurier());
            koszykPane.setCenaDostawa(0.00F);
            koszykPane.setCenaDostawa(koszykPane.getCenaDostawa() + 19.99F);
            koszykPane.getLabelCenaDostawy().setText("Dostawa: " + koszykPane.getCenaDostawa() + " PLN");
            koszykPane.getLabelSumaCen().setText("SUMA: " + koszykPane.getSumaCen() + " PLN");
        } else if (e.getSource() == koszykPane.getButtonPaczkomat()) {
            koszykPane.getKoszyk().ustawMetodeDostawy(new DostawaPaczkomat());
            koszykPane.setCenaDostawa(0.00F);
            koszykPane.setCenaDostawa(koszykPane.getCenaDostawa() + 15.99F);
            koszykPane.getLabelCenaDostawy().setText("Dostawa: " + koszykPane.getCenaDostawa() + " PLN");
            koszykPane.getLabelSumaCen().setText("SUMA: " + koszykPane.getSumaCen() + " PLN");
        }

        if (e.getSource() == koszykPane.getButtonLogoSklep()) {
            koszykPane.getFrame().getContentPane().removeAll();
            koszykPane.getFrame().revalidate();
            koszykPane.getFrame().repaint();
            SklepGUI.openSklepGUI(koszykPane.getFrame(), koszykPane.getSklep());
        }
        if (e.getSource() instanceof JButton sourceButton) {
            Produkt produktToRemove = koszykPane.getButtonProduktMap().get(sourceButton);

            if (produktToRemove != null) {
                koszykPane.getKoszyk().usunProduktZKoszyka(produktToRemove);
                koszykPane.getPanelKoszyk().remove(sourceButton.getParent());
                koszykPane.getComboList().remove(produktToRemove);
                koszykPane.getPanelKoszyk().revalidate();
                koszykPane.getPanelKoszyk().repaint();
                koszykPane.getLabelCenaKoszyka().setText("Koszyk: " + koszykPane.getKoszyk().getWartoscZamowienia() + " PLN");
                koszykPane.getLabelSumaCen().setText("SUMA: " + koszykPane.getSumaCen() + " PLN");
            }
        }
        //2
        if (e.getSource() == koszykPane.getButtonDostawa()) {
            koszykPane.setAdres(koszykPane.getKoszyk().stworzAdres(koszykPane.getTextImie().getText(), koszykPane.getTextNazwisko().getText(), koszykPane.getTextKraj().getText(), koszykPane.getTextNumerTelefonu().getText(), koszykPane.getTextUlica().getText(), koszykPane.getTextNumerDomu().getText(), koszykPane.getTextEmail().getText(), koszykPane.getTextKodPocztowy().getText(), koszykPane.getTextMiejscowosc().getText()));
            if (koszykPane.getAdres() != null) {
                //USUWANIE
                koszykPane.getPanelGlowny().remove(koszykPane.getPanelDostawy());
                koszykPane.getPanelPodsumowanie().remove(koszykPane.getButtonDostawa());
                koszykPane.getPanelPodsumowanie().remove(koszykPane.getButtonCofnijDostawa());
                koszykPane.getPanelGlowny().revalidate();
                koszykPane.getPanelGlowny().repaint();

                //RYSOWANIE
                koszykPane.rysujPanelPlatnosci(koszykPane.getPanelGlowny(), koszykPane.getSklep());
                koszykPane.getLabelPlatnosc().setBackground(new Color(184, 255, 184));
                koszykPane.getLabelKoszyk().setText("PLATNOSC");
                koszykPane.getPanelPodsumowanie().add(koszykPane.getButtonPlatnosc());
                koszykPane.getPanelPodsumowanie().add(koszykPane.getButtonCofnijPlatnosc());
            }
        }

        if (e.getSource() == koszykPane.getButtonWylogujSie()) {
            koszykPane.getFrame().getContentPane().removeAll();
            koszykPane.getFrame().revalidate();
            koszykPane.getFrame().repaint();
            koszykPane.getSklep().setZalogowanyKlient(null);
            Rejestracja.ShopPage(koszykPane.getSklep(), koszykPane.getFrame());
        }

        if (e.getSource() == koszykPane.getButtonBlik()) {
            koszykPane.getKoszyk().ustawMetodePlatnosci(new placBlikiem());
        }
        if (e.getSource() == koszykPane.getButtonKartaKredytowa()) {
            koszykPane.getKoszyk().ustawMetodePlatnosci(new placKarta());
        }
        if (e.getSource() == koszykPane.getButtonPlatnosc()) {
            if (koszykPane.getKoszyk().zrealizujDostawe(koszykPane.getAdres(), koszykPane.getTextKodBlik().getText(), koszykPane.getTextNumerKarty().getText(), koszykPane.getTextDataWygasniecia().getText(), koszykPane.getTextCvv().getText(), koszykPane.getTextKartaImie().getText(), koszykPane.getTextKartaNazwisko().getText())) {
                JOptionPane.showMessageDialog(null, "Wysłano paczkę na adres: " + koszykPane.getAdres(), "ZAMÓWIENIE WYSŁANE", JOptionPane.INFORMATION_MESSAGE);
                for (int i = 0; i < koszykPane.getKoszyk().getProduktyWKoszyku().size(); i++) {
                    for (int j = 0; j < koszykPane.getSklep().getListaProduktow().size(); j++) {
                        if (koszykPane.getSklep().getListaProduktow().get(j).equals(koszykPane.getKoszyk().getProduktyWKoszyku().get(i).getProdukt())) {
                            koszykPane.getSklep().getListaProduktow().get(j).getRozmiary().put(koszykPane.getKoszyk().getProduktyWKoszyku().get(i).getRozmiar(), koszykPane.getSklep().getListaProduktow().get(j).getRozmiary().get(koszykPane.getKoszyk().getProduktyWKoszyku().get(i).getRozmiar()) - (koszykPane.getComboList().get(i).getSelectedIndex() + 1));
                            if (koszykPane.getSklep().getListaProduktow().get(j).getRozmiary().get(koszykPane.getKoszyk().getProduktyWKoszyku().get(i).getRozmiar()) == 0) {
                                koszykPane.getSklep().getListaProduktow().get(j).getRozmiary().remove(koszykPane.getKoszyk().getProduktyWKoszyku().get(i).getRozmiar());
                                koszykPane.getSklep().getListaProduktow().get(j).getRozmiaryAsList().remove(koszykPane.getKoszyk().getProduktyWKoszyku().get(i).getRozmiar());
                                if (koszykPane.getSklep().getListaProduktow().get(j).getRozmiaryAsList().isEmpty()) {
                                    koszykPane.getSklep().getListaProduktow().remove(j);
                                }
                            }
                        }
                    }
                }
                koszykPane.getKoszyk().getProduktyWKoszyku().clear();
                koszykPane.getFrame().getContentPane().removeAll();
                koszykPane.getFrame().revalidate();
                koszykPane.getFrame().repaint();
                SklepGUI.openSklepGUI(koszykPane.getFrame(), koszykPane.getSklep());
            }
        }
        for (int i = 0; i < koszykPane.getComboList().size(); i++) {
            if (e.getSource() == koszykPane.getComboList().get(i)) {
                koszykPane.getKoszyk().getProduktyWKoszyku().get(i).setIloscWKoszyku(koszykPane.getComboList().get(i).getSelectedIndex() + 1);
                koszykPane.getLabelCenaKoszyka().setText("Koszyk: " + koszykPane.getKoszyk().getWartoscZamowienia() + " PLN");
                koszykPane.getLabelSumaCen().setText("SUMA: " + koszykPane.getSumaCen() + " PLN");
            }
        }

        if (e.getSource() == koszykPane.getButtonCofnijDostawa()) {
            //USUWANIE
            koszykPane.getPanelGlowny().remove(koszykPane.getPanelDostawy());
            koszykPane.getPanelPodsumowanie().remove(koszykPane.getButtonCofnijDostawa());
            koszykPane.getPanelPodsumowanie().remove(koszykPane.getButtonDostawa());
            koszykPane.getPanelGlowny().revalidate();
            koszykPane.getPanelGlowny().repaint();


            //RYSOWANIE
            koszykPane.rysujPanelKoszyk(koszykPane.getPanelGlowny());
            koszykPane.getLabelDostawa().setBackground(Color.lightGray);
            koszykPane.getLabelKoszyk().setText("TWOJ KOSZYK");
            koszykPane.getPanelPodsumowanie().add(koszykPane.getButtonZamowienie());
        }
        if (e.getSource() == koszykPane.getButtonCofnijPlatnosc()) {
            //USUWANIE
            koszykPane.getPanelGlowny().remove(koszykPane.getPanelPlatnosci());
            koszykPane.getPanelPodsumowanie().remove(koszykPane.getButtonPlatnosc());
            koszykPane.getPanelPodsumowanie().remove(koszykPane.getButtonCofnijPlatnosc());
            koszykPane.getPanelGlowny().revalidate();
            koszykPane.getPanelGlowny().repaint();


            //RYSOWANIE
            koszykPane.rysujPanelDostawy(koszykPane.getPanelGlowny(), koszykPane.getSklep());
            koszykPane.getLabelPlatnosc().setBackground(Color.lightGray);
            koszykPane.getLabelKoszyk().setText("DOSTAWA");
            koszykPane.getPanelPodsumowanie().add(koszykPane.getButtonDostawa());
            koszykPane.getPanelPodsumowanie().add(koszykPane.getButtonCofnijDostawa());
        }
        if (e.getSource() == koszykPane.getBuutonPowiadomienia()) {
            //TODO PANEL POWIADOMIEN
            System.out.println("TRZEBA ZROBIC");
        }
    }
}

