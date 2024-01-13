package code.InterfejsGraficzny.Koszyk;

import code.DostawaStrategia.DostawaKurier;
import code.DostawaStrategia.DostawaPaczkomat;
import code.InterfejsGraficzny.SklepGUI;
import code.Produkt.Produkt;

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
        if (e.getSource() == koszykPane.getButtonUsun()) {
            Produkt produktToRemove = koszykPane.getButtonProduktMap().get(koszykPane.getButtonUsun());

            if (produktToRemove != null) {
                Produkt x;
                koszykPane.getSklep().getZalogowanyKlient().getKoszyk().getListaProduktow().remove(produktToRemove);
                koszykPane.getPanelKoszyk().remove((koszykPane.getButtonUsun()).getParent()); // Usunięcie całego panelu produktu
                koszykPane.getPanelKoszyk().revalidate();
                koszykPane.getPanelKoszyk().repaint();
            }
        }
    }
}
