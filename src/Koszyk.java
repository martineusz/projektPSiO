import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Koszyk implements Serializable {
    private List<Produkt> listaProduktow = new ArrayList<>();
    private PlacenieStrategia placenieStrategia;

    public void setPlacenieStrategia(PlacenieStrategia placenieStrategia) {
        this.placenieStrategia = placenieStrategia;
    }

    public void platnosc(){
        placenieStrategia.zaplac();
    }
}
