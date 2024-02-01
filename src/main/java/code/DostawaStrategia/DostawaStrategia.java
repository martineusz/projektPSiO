package code.DostawaStrategia;


import code.Main.Koszyk;

public interface DostawaStrategia {
    void dodajKoszt(Koszyk koszyk);
    void wyslijPaczke(String adres);
}
