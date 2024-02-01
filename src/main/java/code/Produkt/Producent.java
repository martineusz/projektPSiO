package code.Produkt;


import java.io.Serializable;

public class Producent implements Serializable {
    private String marka;
    private String krajPochodenia;

    public Producent(String marka, String krajPochodenia) {
        this.marka = marka;
        this.krajPochodenia = krajPochodenia;
    }
    private static final long serialVersionUID = 1_275_997_691_580_326_078L;

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getKrajPochodenia() {
        return krajPochodenia;
    }

    public void setKrajPochodenia(String krajPochodenia) {
        this.krajPochodenia = krajPochodenia;
    }

    @Override
    public String toString() {
        super.toString();
        return "\nMarka: " + marka +
                "\nKraj pochodzenia: " + krajPochodenia;
    }
}