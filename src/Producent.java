import java.io.Serializable;

public class Producent implements Serializable {
    private String marka;
    private String krajPochodzenia;

    public Producent(String marka, String krajPochodzenia) {
        this.marka = marka;
        this.krajPochodzenia = krajPochodzenia;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getKrajPochodzenia() {
        return krajPochodzenia;
    }

    public void setKrajPochodzenia(String krajPochodzenia) {
        this.krajPochodzenia = krajPochodzenia;
    }

    @Override
    public String toString() {
        return "Producent{" +
                "marka='" + marka + '\'' +
                ", krajPochodzenia='" + krajPochodzenia + '\'' +
                '}';
    }
}
