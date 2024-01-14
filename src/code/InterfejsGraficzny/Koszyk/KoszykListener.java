package code.InterfejsGraficzny.Koszyk;

import code.DostawaStrategia.DostawaKurier;
import code.DostawaStrategia.DostawaPaczkomat;
import code.InterfejsGraficzny.Rejestracja;
import code.InterfejsGraficzny.SklepGUI;
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
                koszykPane.getPanelKoszyk().remove(sourceButton.getParent()); // Usunięcie całego panelu produktu
                koszykPane.getButtonProduktMap().remove(sourceButton);
                koszykPane.getPanelKoszyk().revalidate();
                koszykPane.getPanelKoszyk().repaint();
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
    }
}
