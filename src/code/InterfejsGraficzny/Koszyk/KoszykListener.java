package code.InterfejsGraficzny.Koszyk;

import code.DostawaStrategia.DostawaKurier;
import code.DostawaStrategia.DostawaPaczkomat;
import code.InterfejsGraficzny.Rejestracja;
import code.InterfejsGraficzny.SklepGUI;
import code.PlacenieStrategia.placBlikiem;
import code.PlacenieStrategia.placKarta;
import code.Produkt.Produkt;
import code.Main.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KoszykListener implements ActionListener {
    private KoszykPane koszykPane;

    public KoszykListener(KoszykPane koszykPane) {
        this.koszykPane = koszykPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == koszykPane.getButtonZamowienie()) {
            //USUWANIE
            koszykPane.getPanelGlowny().remove(koszykPane.getPanelKoszyk());
            koszykPane.getPanelGlowny().remove(koszykPane.getScrollPaneKoszyk());
            koszykPane.getPanelGlowny().revalidate();
            koszykPane.getPanelGlowny().repaint();
            koszykPane.getPanelPodsumowanie().remove(koszykPane.getButtonZamowienie());

            //RYSOWANIE
            koszykPane.rysujPanelDostawy(koszykPane.getPanelGlowny(), koszykPane.getSklep());
            koszykPane.getLabelDostawa().setBackground(new Color(210, 255, 255));
            koszykPane.getLabelKoszyk().setText("DOSTAWA");
            koszykPane.getPanelPodsumowanie().add(koszykPane.getButtonDostawa());
            koszykPane.setCenaDostawa(15.99F);
            koszykPane.getLabelSumaCen().setText("SUMA: " + (koszykPane.getSklep().getZalogowanyKlient().getKoszyk().getWartoscZamowienia()+koszykPane.getCenaDostawa()) + " PLN");
            koszykPane.getLabelCenaDostawy().setText("Dostawa: " + koszykPane.getCenaDostawa() + " PLN");
        }
        if (e.getSource() == koszykPane.getButtonKurier()) {
            koszykPane.getSklep().getZalogowanyKlient().getKoszyk().ustawMetodeDostawy(new DostawaKurier());
            koszykPane.setCenaDostawa(0);
            koszykPane.setCenaDostawa(koszykPane.getCenaDostawa() + 19.99F);
            koszykPane.getLabelCenaDostawy().setText("Dostawa: " + koszykPane.getCenaDostawa() + " PLN");
            koszykPane.getLabelSumaCen().setText("SUMA: " + (koszykPane.getCenaDostawa() + koszykPane.getSklep().getZalogowanyKlient().getKoszyk().getWartoscZamowienia()) + " PLN");
        } else if (e.getSource() == koszykPane.getButtonPaczkomat()) {
            koszykPane.getSklep().getZalogowanyKlient().getKoszyk().ustawMetodeDostawy(new DostawaPaczkomat());
            koszykPane.setCenaDostawa(0);
            koszykPane.setCenaDostawa(koszykPane.getCenaDostawa() + 15.99F);
            koszykPane.getLabelCenaDostawy().setText("Dostawa: " + koszykPane.getCenaDostawa() + " PLN");
            koszykPane.getLabelSumaCen().setText("SUMA: " + (koszykPane.getCenaDostawa() + koszykPane.getSklep().getZalogowanyKlient().getKoszyk().getWartoscZamowienia()) + " PLN");
        }

        if (e.getSource() == koszykPane.getButtonLogoSklep()) {
            koszykPane.getFrame().getContentPane().removeAll();
            koszykPane.getFrame().revalidate();
            koszykPane.getFrame().repaint();
            SklepGUI.openSklepGUI(koszykPane.getFrame(), koszykPane.getSklep());
        }
        if (e.getSource() instanceof JButton) {
            JButton sourceButton = (JButton) e.getSource();
            Produkt produktToRemove = koszykPane.getButtonProduktMap().get(sourceButton);

            if (produktToRemove != null) {
                koszykPane.getSklep().getZalogowanyKlient().getKoszyk().usunProduktZKoszyka(produktToRemove);
                //koszykPane.getComboList().remove(koszykPane.getComboList().indexOf(produktToRemove));
                koszykPane.getPanelKoszyk().remove(sourceButton.getParent()); // Usunięcie całego panelu produktu
                koszykPane.getPanelKoszyk().revalidate();
                koszykPane.getPanelKoszyk().repaint();
                koszykPane.getLabelCenaKoszyka().setText("Koszyk: " + koszykPane.getSklep().getZalogowanyKlient().getKoszyk().getWartoscZamowienia() + " PLN");
                koszykPane.getLabelSumaCen().setText("SUMA: " + (koszykPane.getSklep().getZalogowanyKlient().getKoszyk().getWartoscZamowienia()+koszykPane.getCenaDostawa()) + " PLN");
            }
        }
        if(e.getSource()==koszykPane.getButtonDostawa()) {
            koszykPane.setAdres(koszykPane.getSklep().getZalogowanyKlient().getKoszyk().stworzAdres(koszykPane.getTextImie().getText(), koszykPane.getTextNazwisko().getText(), koszykPane.getTextKraj().getText(), koszykPane.getTextNumerTelefonu().getText(), koszykPane.getTextUlica().getText(), koszykPane.getTextNumerDomu().getText(), koszykPane.getTextEmail().getText(), koszykPane.getTextKodPocztowy().getText(), koszykPane.getTextMiejscowosc().getText()));
            if (koszykPane.getAdres() != null) {
                //USUWANIE
                koszykPane.getPanelGlowny().remove(koszykPane.getPanelDostawy());
                koszykPane.getPanelPodsumowanie().remove(koszykPane.getButtonDostawa());
                koszykPane.getPanelGlowny().revalidate();
                koszykPane.getPanelGlowny().repaint();

                //RYSOWANIE
                koszykPane.rysujPanelPlatnosci(koszykPane.getPanelGlowny(), koszykPane.getSklep());
                koszykPane.getLabelPlatnosc().setBackground(new Color(210, 255, 255));
                koszykPane.getLabelKoszyk().setText("PLATNOSC");
                koszykPane.getPanelPodsumowanie().add(koszykPane.getButtonPlatnosc());
            }
        }

        if(e.getSource()==koszykPane.getButtonWylogujSie()){
            koszykPane.getFrame().getContentPane().removeAll();
            koszykPane.getFrame().revalidate();
            koszykPane.getFrame().repaint();
            koszykPane.getSklep().setZalogowanyKlient(null);
            Rejestracja.ShopPage(koszykPane.getSklep(), koszykPane.getFrame());
        }

        if (e.getSource() == koszykPane.getButtonBlik()){
            koszykPane.getSklep().getZalogowanyKlient().getKoszyk().ustawMetodePlatnosci(new placBlikiem());
        }
        if (e.getSource() == koszykPane.getButtonKartaKredytowa()){
            koszykPane.getSklep().getZalogowanyKlient().getKoszyk().ustawMetodePlatnosci(new placKarta());
        }
        if (e.getSource() == koszykPane.getButtonPlatnosc()){
            if(koszykPane.getSklep().getZalogowanyKlient().getKoszyk().zrealizujDostawe(koszykPane.getAdres(), koszykPane.textKodBlik.getText(),koszykPane.textNumerKarty.getText(),koszykPane.textDataWygasniecia.getText(),koszykPane.textCvv.getText(),koszykPane.textKartaImie.getText(),koszykPane.textKartaNazwisko.getText())){
                JOptionPane warning = new JOptionPane();
                warning.showMessageDialog(null,"Wysłano paczkę na adres: " + koszykPane.getAdres(), "ZAMÓWIENIE WYSŁANE", JOptionPane.INFORMATION_MESSAGE);
                for (int i = 0; i < koszykPane.getSklep().getZalogowanyKlient().getKoszyk().getProduktyWKoszyku().size(); i++) {
                    for (int j = 0; j < koszykPane.getSklep().getListaProduktow().size(); j++) {
                        if (koszykPane.getSklep().getListaProduktow().get(j).equals(koszykPane.getSklep().zalogowanyKlient.getKoszyk().getProduktyWKoszyku().get(i).getProdukt())){
                            koszykPane.getSklep().getListaProduktow().get(j).getRozmiary().put(koszykPane.getSklep().zalogowanyKlient.getKoszyk().getProduktyWKoszyku().get(i).getRozmiar(),koszykPane.getSklep().getListaProduktow().get(j).getRozmiary().get(koszykPane.getSklep().zalogowanyKlient.getKoszyk().getProduktyWKoszyku().get(i).getRozmiar())-(koszykPane.getComboList().get(i).getSelectedIndex()+1));
                        }
                    }
                }
                koszykPane.getSklep().zalogowanyKlient.getKoszyk().getProduktyWKoszyku().clear();
                koszykPane.getFrame().getContentPane().removeAll();
                koszykPane.getFrame().revalidate();
                koszykPane.getFrame().repaint();
                SklepGUI.openSklepGUI(koszykPane.getFrame(), koszykPane.getSklep());
            }
        }
        for (int i = 0; i < koszykPane.comboList.size(); i++) {
            if (e.getSource() == koszykPane.comboList.get(i)){
                koszykPane.getSklep().zalogowanyKlient.getKoszyk().getProduktyWKoszyku().get(i).setIloscWKoszyku(koszykPane.getComboList().get(i).getSelectedIndex()+1);
                koszykPane.getLabelCenaKoszyka().setText("Koszyk: " + koszykPane.getSklep().getZalogowanyKlient().getKoszyk().getWartoscZamowienia() + " PLN");
                koszykPane.getLabelSumaCen().setText("SUMA: " + (koszykPane.getSklep().getZalogowanyKlient().getKoszyk().getWartoscZamowienia()+koszykPane.getCenaDostawa()) + " PLN");
            }
        }
    }
}
