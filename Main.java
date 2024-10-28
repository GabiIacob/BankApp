

public class Main {

    public static void main(String[] args) {
        Bursa.initializeazaCompanii();
        ContClient.adaugaClienti();
        PriceUpdates.startPriceUpdates();
        Panel.launch(Panel.class, args);


    }
}
