package code.Main;

import code.DostawaStrategia.DostawaKurier;
import code.DostawaStrategia.DostawaPaczkomat;
import code.PlacenieStrategia.placBlikiem;
import code.PlacenieStrategia.placKarta;
import code.Produkt.Produkt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KoszykTest {
    Koszyk koszyk = new Koszyk();
    Produkt produkt1;
    Produkt produkt2;
    Produkt produkt3;

    String adres;
    String kodBlik;
    String numerKarty;
    String dataWygasniecia;
    String cvv;
    String imie;
    String nazwisko;
    @BeforeEach
    void setUp() {
        String adres  = "";
        String kodBlik = "";
        String numerKarty = "";
        String dataWygasniecia = "";
        String cvv= "";
        String imie  = "";
        String nazwisko  = "";

        produkt1 = mock(Produkt.class);
        produkt2 = mock(Produkt.class);
        produkt3 = mock(Produkt.class);

        ProduktWKoszyku produktWKoszyku1 = new ProduktWKoszyku(produkt1, "L", 1);
        ProduktWKoszyku produktWKoszyku2 = new ProduktWKoszyku(produkt2, "L", 1);
        ProduktWKoszyku produktWKoszyku3 = new ProduktWKoszyku(produkt3, "L", 1);

        ArrayList<ProduktWKoszyku> produktyWKoszyku = new ArrayList<>();
        produktyWKoszyku.add(produktWKoszyku1);
        produktyWKoszyku.add(produktWKoszyku2);
        produktyWKoszyku.add(produktWKoszyku3);

        koszyk.setProduktyWKoszyku(produktyWKoszyku);
    }


    //GET WARTOSC ZAMOWIENIA
    @Test
    public void testGetWartoscZamowienia1() {
        when(produkt1.getCena()).thenReturn(10.99F);
        when(produkt2.getCena()).thenReturn(20.99F);
        when(produkt3.getCena()).thenReturn(0F);
        assertEquals(31.98F, koszyk.getWartoscZamowienia());
    }
    @Test
    public void testGetWartoscZamowienia2() {
        when(produkt1.getCena()).thenReturn(10.99F);
        when(produkt2.getCena()).thenReturn(20.99F);
        when(produkt3.getCena()).thenReturn(19.99F);
        assertEquals(51.97F, koszyk.getWartoscZamowienia());
    }
    @Test
    public void testGetWartoscZamowienia3() {
        when(produkt1.getCena()).thenReturn(1110.99F);
        when(produkt2.getCena()).thenReturn(2899.99F);
        when(produkt3.getCena()).thenReturn(10000.99F);
        assertEquals(14011.97F, koszyk.getWartoscZamowienia());
    }

    @Test
    public void testGetWartoscZamowieniaEmpty() {
        koszyk.setProduktyWKoszyku(new ArrayList<>());
        assertEquals(0F, koszyk.getWartoscZamowienia());
    }

    @Test
    public void testStworzAdres1(){
        String imie = "Jan";
        String nazwisko = "Kowalski";
        String kraj = "Polska";
        String numerTelefonu = "123456789";
        String ulica = "Długa";
        String numerDomu = "1";
        String email = "jk@gmail.pl";
        String kodPocztowy = "00-111";
        String miejscowosc = "Wrocław";

        assertTrue(koszyk.stworzAdres(imie, nazwisko, kraj, numerTelefonu, ulica, numerDomu, email, kodPocztowy, miejscowosc) instanceof String);
    }
    @Test
    public void testStworzAdres2(){
        //Puste pola
        String imie = "";
        String nazwisko = "Kowalski";
        String kraj = "Polska";
        String numerTelefonu = "123456789";
        String ulica = "Długa";
        String numerDomu = "1";
        String email = "jk@gmail.pl";
        String kodPocztowy = "00-111";
        String miejscowosc = "Wrocław";

        assertFalse(koszyk.stworzAdres(imie, nazwisko, kraj, numerTelefonu, ulica, numerDomu, email, kodPocztowy, miejscowosc) instanceof String);
    }
    @Test
    public void testStworzAdres3(){
        String imie = "Jan";
        String nazwisko = "Kowalski";
        String kraj = "Polska";
        String numerTelefonu = "123456789";
        String ulica = "Długa";
        String numerDomu = "1";
        String email = "jk.pl"; //zły format maila
        String kodPocztowy = "00-111";
        String miejscowosc = "Wrocław";

        assertFalse(koszyk.stworzAdres(imie, nazwisko, kraj, numerTelefonu, ulica, numerDomu, email, kodPocztowy, miejscowosc) instanceof String);
    }
    @Test
    public void testStworzAdres4(){
        String imie = "Jan";
        String nazwisko = "Kowalski";
        String kraj = "Polska";
        String numerTelefonu = "12345678"; //zły format numeru
        String ulica = "Długa";
        String numerDomu = "1";
        String email = "jk@gmail.pl";
        String kodPocztowy = "00-111";
        String miejscowosc = "Wrocław";

        assertFalse(koszyk.stworzAdres(imie, nazwisko, kraj, numerTelefonu, ulica, numerDomu, email, kodPocztowy, miejscowosc) instanceof String);
    }
    @Test
    public void testStworzAdres5(){
        String imie = "Jan";
        String nazwisko = "Kowalski";
        String kraj = "Polska";
        String numerTelefonu = "123456789";
        String ulica = "Długa";
        String numerDomu = "1";
        String email = "jk@gmail.pl";
        String kodPocztowy = "00111";  //zły format kodu
        String miejscowosc = "Wrocław";

        assertFalse(koszyk.stworzAdres(imie, nazwisko, kraj, numerTelefonu, ulica, numerDomu, email, kodPocztowy, miejscowosc) instanceof String);
    }
    @Test
    public void testStworzAdres6(){
        String imie = "Jan";
        String nazwisko = "Kowalski";
        String kraj = "Polska";
        String numerTelefonu = "123456789";
        String ulica = "Długa";
        String numerDomu = "11";
        String email = "jk@gmail.pl";
        String kodPocztowy = "aa-aaa"; //litery w kodzie
        String miejscowosc = "Wrocław";

        assertFalse(koszyk.stworzAdres(imie, nazwisko, kraj, numerTelefonu, ulica, numerDomu, email, kodPocztowy, miejscowosc) instanceof String);
    }


    //ZREALIZUJ DOSTAWE
    @Test
    public void testZrealizujDostaweBlik1() {
        koszyk.setDostawaStrategia(new DostawaKurier());
        koszyk.setPlacenieStrategia(new placBlikiem());
        kodBlik = "123456";


        assertTrue(koszyk.zrealizujDostawe(adres, kodBlik, numerKarty, dataWygasniecia, cvv, imie, nazwisko));
    }

    @Test
    public void testZrealizujDostaweBlik2() {
        koszyk.setDostawaStrategia(new DostawaPaczkomat());
        koszyk.setPlacenieStrategia(new placBlikiem());
        String kodBlik = "12345";

        assertFalse(koszyk.zrealizujDostawe(adres, kodBlik, numerKarty, dataWygasniecia, cvv, imie, nazwisko));
    }

    @Test
    public void testZrealizujDostaweBlikNullDostawa() {
        koszyk.setDostawaStrategia(null);
        koszyk.setPlacenieStrategia(new placBlikiem());
        String kodBlik = "123456";

        assertFalse(koszyk.zrealizujDostawe(adres, kodBlik, numerKarty, dataWygasniecia, cvv, imie, nazwisko));
    }

    @Test
    public void testZrealizujDostaweKarta1() {
        koszyk.setDostawaStrategia(new DostawaPaczkomat());
        koszyk.setPlacenieStrategia(new placKarta());
        String numerKarty = "1234567890123456";
        String dataWygasniecia = "1234";
        String cvv= "123";

        assertTrue(koszyk.zrealizujDostawe(adres, kodBlik, numerKarty, dataWygasniecia, cvv, imie, nazwisko));
    }

    @Test
    public void testZrealizujDostaweKarta2() {
        koszyk.setDostawaStrategia(new DostawaPaczkomat());
        koszyk.setPlacenieStrategia(new placKarta());
        String numerKarty = "123456789012345"; //zmieniony numer
        String dataWygasniecia = "1234";
        String cvv= "123";

        assertFalse(koszyk.zrealizujDostawe(adres, kodBlik, numerKarty, dataWygasniecia, cvv, imie, nazwisko));
    }
    @Test
    public void testZrealizujDostaweKarta3() {
        koszyk.setDostawaStrategia(new DostawaPaczkomat());
        koszyk.setPlacenieStrategia(new placKarta());
        String numerKarty = "1234567890123456";
        String dataWygasniecia = "12345";//zmieniona data
        String cvv= "123";

        assertFalse(koszyk.zrealizujDostawe(adres, kodBlik, numerKarty, dataWygasniecia, cvv, imie, nazwisko));
    }
    @Test
    public void testZrealizujDostaweKarta4() {
        koszyk.setDostawaStrategia(new DostawaPaczkomat());
        koszyk.setPlacenieStrategia(new placKarta());
        String numerKarty = "1234567890123456";
        String dataWygasniecia = "1234";
        String cvv= "12";//zmienione cvv

        assertFalse(koszyk.zrealizujDostawe(adres, kodBlik, numerKarty, dataWygasniecia, cvv, imie, nazwisko));
    }
    @Test
    public void testZrealizujDostaweKartaNullPlatnosc() {
        koszyk.setDostawaStrategia(new DostawaPaczkomat());
        koszyk.setPlacenieStrategia(null);
        String numerKarty = "1234567890123456";
        String dataWygasniecia = "1234";
        String cvv= "123";

        assertFalse(koszyk.zrealizujDostawe(adres, kodBlik, numerKarty, dataWygasniecia, cvv, imie, nazwisko));
    }
    @Test
    public void testZrealizujDostaweKartaNullDostawa() {
        koszyk.setDostawaStrategia(null);
        koszyk.setPlacenieStrategia(new placKarta());
        String numerKarty = "1234567890123456";
        String dataWygasniecia = "1234";
        String cvv= "123";

        assertFalse(koszyk.zrealizujDostawe(adres, kodBlik, numerKarty, dataWygasniecia, cvv, imie, nazwisko));
    }
}
