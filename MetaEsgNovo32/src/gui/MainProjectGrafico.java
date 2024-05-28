package gui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainProjectGrafico extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carregar o arquivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("graficoProjecaonew.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gráfico de Barras com Valores Pré-definidos");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
