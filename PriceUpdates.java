import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PriceUpdates {
    private static Timer timer = new Timer();
    private static Random random = new Random();
    private static PriceUpdateListener listener;

    public static void setPriceUpdateListener(PriceUpdateListener priceUpdateListener) {
        listener = priceUpdateListener;
    }

    public static void startPriceUpdates() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Companie[] companii = Bursa.getCompanii();

                for (int i = 0; i < companii.length; i++) {
                    Companie companie = companii[i];

                    if (companie != null) {
                        double pretActual = Double.parseDouble(companie.getPretActiune());
                        double variatie = (random.nextDouble() * 0.2 - 0.1);
                        double pretNou = pretActual * (1 + variatie);
                        companie.updatePret(String.format("%.2f", pretNou));

                        if (listener != null) {
                            listener.onPriceUpdated(i, pretNou);
                        }
                    }
                }
            }
        }, 0, 5000);
    }

    public static void stopPriceUpdates() {
        timer.cancel();
    }

    public interface PriceUpdateListener {
        void onPriceUpdated(int companyIndex, double newPrice);
    }
}
