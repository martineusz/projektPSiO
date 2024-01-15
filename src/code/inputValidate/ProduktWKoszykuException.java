package code.inputValidate;

import code.Main.ProduktWKoszyku;
import code.Main.Sklep;
import code.Produkt.Produkt;

import javax.swing.*;
import java.util.Objects;

public class ProduktWKoszykuException extends Exception {
    public ProduktWKoszykuException(String message) {
        super(message);
    }

    public static void checkIfEmpty(Sklep sklep, Produkt produkt, JComboBox comboBox) throws ProduktWKoszykuException {
        boolean czyProduktWKoszyku = true;
        Object selectedRozmiar = comboBox.getSelectedItem();
        System.out.println(selectedRozmiar);

        if (comboBox.getSelectedIndex() != 0) {
            if (sklep.zalogowanyKlient.getKoszyk().getProduktyWKoszyku().isEmpty()) {
                sklep.zalogowanyKlient.getKoszyk().dodajProdukt(produkt, String.valueOf(comboBox.getSelectedItem()));
                JOptionPane.showMessageDialog(null, "Dodano do koszyka", "GRATULACJE", JOptionPane.INFORMATION_MESSAGE);
                czyProduktWKoszyku = false;
            } else {
                for(ProduktWKoszyku produkt1: sklep.zalogowanyKlient.getKoszyk().getProduktyWKoszyku()) {
                    if (produkt1.getProdukt().equals(produkt) && produkt1.getRozmiar().equals(String.valueOf(selectedRozmiar))) {
                        czyProduktWKoszyku = false;
                    }

                }
                if (czyProduktWKoszyku) {
                    sklep.zalogowanyKlient.getKoszyk().dodajProdukt(produkt, String.valueOf(comboBox.getSelectedItem()));
                    JOptionPane.showMessageDialog(null, "Dodano do koszyka", "GRATULACJE", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    throw new ProduktWKoszykuException("Koszyk jest pusty");
                }

            }
        }
    }
}


