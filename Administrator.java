import java.util.Random;

import static javax.swing.UIManager.getString;

public class Administrator {
    public String nume;


    protected String parola;
    int ATMamount=500000;
    public Administrator(String nume, String parola){
        this.nume=nume;

        this.parola=parola;
    }

    public void setATMamount(int ATMamount) {
        this.ATMamount = ATMamount;
    }
    public boolean autentificare(String nume, String parola) {
        if (!this.nume.equals(nume)) {
            System.out.println("Nume de utilizator gresit, incercati din nou.");
            return false;
        }
        if (!this.parola.equals(parola)) {
            System.out.println("Parola greșita, incercati din nou.");
            return false;
        }

        System.out.println("Logare reusita");
        return true;
    }
}

