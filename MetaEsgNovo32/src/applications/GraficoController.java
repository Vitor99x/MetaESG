package applications;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

public class GraficoController {

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private NumberAxis numberAxis;

    @FXML
    private void initialize() {
        // Configuração do gráfico
        categoryAxis.setLabel("Categoria");
        numberAxis.setLabel("Valor");
    }
}
