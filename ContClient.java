import javax.swing.*;
import java.util.Scanner;

public class ContClient implements FunctiiClienti {
    public String nume;
    protected String parola;
    public double suma;
    public int tranzactiiefectuate;

    public ContClient(String nume, String parola, double suma, int tranzactiiefectuate) {
        this.nume = nume;
        this.parola = parola;
        this.suma = suma;
        this.tranzactiiefectuate = tranzactiiefectuate;
    }
    static Scanner sc=new Scanner(System.in);
    public static ContClient[] clienti = new ContClient[100];
    public static int clientCounter = 0;
    public static void adaugaClienti() {
        clienti[clientCounter] = new ContClient("Ion Popescu", "masinuta", 5000.0, 10);
        clientCounter++;

        clienti[clientCounter] = new ContClient("Maria Ionescu", "carticica", 3000.0, 5);
        clientCounter++;

        clienti[clientCounter] = new ContClient("Alexandru Vasile", "parolamea", 7000.0, 15);
        clientCounter++;

    }

    @Override
    public String toString() {
        return "ContClient{" +
                "nume='" + nume + '\'' +
                ", parola='" + parola + '\'' +
                ", suma=" + suma +
                ", tranzactiiefectuate=" + tranzactiiefectuate +
                '}';
    }

    public static void afisareClienti() {
        for (int i = 0; i < clientCounter; i++) {
            System.out.println(clienti[i]);

        }
    }
    public static void depunere(String nume, double suma) {
        for (int i = 0; i < clientCounter; i++) {
            if (clienti[i].nume.equals(nume)) {
                clienti[i].suma += suma;
                clienti[i].tranzactiiefectuate++;
                System.out.println("Depunere efectuata");
                return;
            }
        }
        System.out.println("Clientul cu acest nume nu a fost găsit.");
    }
    public void depunere2(double suma) {
        if (suma > 0) {
            this.suma += suma;
            this.tranzactiiefectuate++;
            System.out.println("Depunere efectuata. Soldul actual este: " + this.suma + " RON.");
        } else {
            System.out.println("Suma de depunere trebuie sa fie mai mare de 0.");
        }
    }

    public void retragere2(double suma) {
        if (suma > 0) {
            this.suma -= suma;
            this.tranzactiiefectuate++;
            System.out.println("Retragere efectuata. Soldul actual este: " + this.suma + " RON.");
        } else {
            System.out.println("Suma de retragere trebuie sa fie mai mare de 0.");
        }
    }


    public static void retragere(String nume, double suma) {
        for (int i = 0; i < clientCounter; i++) {
            if (clienti[i].nume.equals(nume)) {
                if (suma <= clienti[i].suma) {
                    clienti[i].suma -= suma;
                    clienti[i].tranzactiiefectuate++;
                    System.out.println("Retragere efectuata");
                } else {
                    System.out.println("Fonduri insuficiente");
                }
                return;
            }
        }
        System.out.println("Clientul cu acest nume nu a fost gasit.");
    }

    public static void modificaPIN(String nume, String newPin) {
        for (int i = 0; i < clientCounter; i++) {
            if (clienti[i].nume.equals(nume)) {
                clienti[i].parola = newPin;
                System.out.println("PIN-ul a fost actualizat");
                return;
            }
        }
        System.out.println("Clientul cu acest nume nu a fost gasit.");
    }

    public static void clientnou(String nume, String password)
    {
        clienti[clientCounter]=new ContClient(nume,password,0,0);
        clientCounter++;
    }
    @Override
    public String getNume() {
        return nume;
    }
    @Override
    public String getParola() {
        return parola;
    }
    public void transfer(String recipientName, double amount) {

        for (ContClient client : clienti) {
            if (client != null && client.nume.equals(recipientName)) {

                if (client != this) {
                    this.suma -= amount;
                    client.suma += amount;
                    this.tranzactiiefectuate++;
                    client.tranzactiiefectuate++;
                    return;
                } else {

                    JOptionPane.showMessageDialog(null, "Cannot transfer to your own account.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Recipient not found.", "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public double getSuma() {

        return suma;
    }
}