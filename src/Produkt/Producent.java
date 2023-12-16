package Produkt;

public class Producent {
    private String marka;
    private String krajPochodenia;

    public Producent(String marka, String krajPochodenia) {
        this.marka = marka;
        this.krajPochodenia = krajPochodenia;
    }

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
