public class Companie {
    private String nume;
    private double pretActiune;
    private int numarActiuni;


    public Companie(String nume, double pretActiune, int numarActiuni) {
        this.nume = nume;
        this.pretActiune = pretActiune;
        this.numarActiuni = numarActiuni;
    }

    public String getNume() {
        return nume;
    }

    public String getPretActiune() {
        return String.format("%.2f", pretActiune);
    }

    public int getNumarActiuni() {
        return numarActiuni;
    }

    public void updatePret(String pretNou) {
        try {
            this.pretActiune = Double.parseDouble(pretNou);
        } catch (NumberFormatException e) {
            System.out.println("Invalid price format: " + pretNou);
        }
    }
    @Override
    public String toString() {
        return nume;
    }
    public void setNumarActiuni(int newShares) {
        this.numarActiuni = newShares;
    }

}
