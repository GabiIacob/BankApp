public class Bursa {
    private static Companie[] companii = new Companie[10];
    private static int index = 0;

    public Bursa(String nume, double pretActiune, int numarActiuni) {
        if (index < companii.length) {
            companii[index++] = new Companie(nume, pretActiune, numarActiuni);
        }
    }

    public static void initializeazaCompanii() {
        new Bursa("GazRom", 100.0, 500);
        new Bursa("eFuel", 150.0, 300);
        new Bursa("MobiWood", 75.0, 1000);
        new Bursa("TransAir", 200.0, 200);
        new Bursa("Dacia", 120.0, 600);
        new Bursa("Patiser", 60.0, 800);
        new Bursa("GrillMarket", 85.0, 400);
        new Bursa("24hService", 95.0, 350);
        new Bursa("Palas", 110.0, 450);
        new Bursa("BeConstructor", 130.0, 550);
    }

    public static Companie[] getCompanii() {

        return companii;
    }

}
