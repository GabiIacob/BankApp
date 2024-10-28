import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public class StockChartFX extends Application implements PriceUpdates.PriceUpdateListener {

    private XYSeries series;
    private ComboBox<String> companyComboBox;
    private ChartPanel chartPanel;
    private int timeCounter = 0;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Stock Price Chart");

        companyComboBox = new ComboBox<>();
        Companie[] companii = Bursa.getCompanii();
        for (Companie companie : companii) {
            if (companie != null) {
                companyComboBox.getItems().add(companie.getNume());
            }
        }

        series = new XYSeries("Stock Prices");
        JFreeChart chart = createChart();
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        SwingNode swingNode = new SwingNode();
        swingNode.setContent(chartPanel);

        VBox vbox = new VBox(companyComboBox, swingNode);
        StackPane root = new StackPane();
        root.getChildren().add(vbox);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        companyComboBox.setOnAction(event -> updateChart());

        PriceUpdates.setPriceUpdateListener(this);
        PriceUpdates.startPriceUpdates();
    }

    private JFreeChart createChart() {
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return ChartFactory.createXYLineChart(
                "Stock Price Over Time",
                "Time",
                "Price",
                dataset
        );
    }

    private void updateChart() {
        series.clear();
        timeCounter = 0;

        int selectedIndex = companyComboBox.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Companie[] companii = Bursa.getCompanii();
            double pret = Double.parseDouble(companii[selectedIndex].getPretActiune());
            series.add(timeCounter++, pret);
        }
    }

    @Override
    public void onPriceUpdated(int companyIndex, double newPrice) {
        int selectedIndex = companyComboBox.getSelectionModel().getSelectedIndex();
        if (selectedIndex == companyIndex) {
            series.add(timeCounter++, newPrice);
            if (series.getItemCount() > 20) {
                series.remove(0);
            }
        }
    }
    public void updateCompanyComboBox() {
        companyComboBox.getItems().clear();
        Companie[] companii = Bursa.getCompanii();
        for (Companie companie : companii) {
            if (companie != null) {
                companyComboBox.getItems().add(companie.getNume());
            }
        }
    }


}
