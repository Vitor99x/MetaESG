package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import servico.EmpresaServico;

public class ProjecaoViewController implements Initializable {
	private List<Double> valores = new ArrayList<>();
	@FXML
	private TextField TxtSoma;

	@FXML
	private TextField TxtEmissaoDoMes;

	@FXML
	private Label enviado;

	@FXML
	private Label emissaoTotal;

	@FXML
	private Label TipoEMissao;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Button bntAbrirProjecao;

	@FXML
	private BarChart<String, Double> barChart;

	@FXML
	private CategoryAxis categoryAxis;

	@FXML
	private NumberAxis numberAxis;

	private double totalEmissao;
	private int valorDigitado2;
	private double mediaValoresDigitados;

	private MainViewController controller;
	private EmpresaServico controllerEmpresas;
	private GraficoProjecaoViewController controllerGrafico;

	public EmpresaServico getControllerEmpresas() {
		return controllerEmpresas;
	}

	public void setControllerEmpresas(EmpresaServico controllerEmpresas) {
		this.controllerEmpresas = controllerEmpresas;
	}

	public ProjecaoViewController() {
	}

	public ProjecaoViewController(MainViewController controller) {
		this.controller = controller;
	}

	public ProjecaoViewController(EmpresaServico controllerEmpresas) {
		this.controllerEmpresas = controllerEmpresas;
	}

	@FXML
	public void btnCancelarAcao() {

		limparCampos();
	}

	public String getTxtEmissaoDoMesText() {
		return TxtEmissaoDoMes.getText();
	}

	@FXML
	public void btnAbrirGrafico() {
		try {
			controllerEmpresas.findall();
			controllerEmpresas.getTipoCombustivel();

			controller.loadView("/gui/graficoProjecaonew.fxml", x -> {
				GraficoProjecaoViewController controller = (GraficoProjecaoViewController) x;
				controller.setProjecaoViewController(this); // Passando a referência de ProjecaoViewController
			});
		} catch (Exception e) {
			Alerts.showAlert("IO Exception", "Estão faltando dados que precisam ser inseridos", e.getMessage(),
					AlertType.INFORMATION);
		}
	}

	@FXML
	public void btnSalvarEnviarAcao() {
		enviado.setText(SomarValoreDigitados());
		this.TxtEmissaoDoMes.requestFocus();

	}

	public void setTipoCombustivel() {
		try {
			controllerEmpresas.findall();
			String TipoDeCombustivel = controllerEmpresas.getTipoCombustivel();
			TipoEMissao.setText(String.valueOf(TipoDeCombustivel));
		} catch (Exception e) {
			Alerts.showAlert("IO Exception",
					"A emissão total não está sendo exibida porque os valores da tela principal não foram definidos.",
					e.getMessage(), AlertType.INFORMATION);
		}
	}

	public int getValorDigitado2() {
		return valorDigitado2;
	}

	public void setValorDigitado2(int valorDigitado2) {
		this.valorDigitado2 = valorDigitado2;
	}

	public void setEmissaoTotal() {
		try {
			controllerEmpresas.findall();
			totalEmissao = controllerEmpresas.getTotalEmissao();
			emissaoTotal.setText(String.valueOf(totalEmissao));
		} catch (Exception e) {
			Alerts.showAlert("IO Exception",
					"A emissão total não está sendo exibida porque os valores da tela principal não foram definidos.",
					e.getMessage(), AlertType.INFORMATION);
		}
	}

	public double getTotalEmissao() {
		return totalEmissao;
	}

	public void setTotalEmissao(double totalEmissao) {
		this.totalEmissao = totalEmissao;
	}

	public void getValorDigitadoMes() {
		valorDigitado2 = Integer.parseInt(TxtEmissaoDoMes.getText());
		setValorDigitado2(valorDigitado2); // Definindo o valor digitado do mês
		TxtEmissaoDoMes.setText(""); // Limpando o campo de texto
		TxtSoma.requestFocus(); // Focando no próximo campo de texto
	}

	public void FocarNoId() {
		String textoDigitado = TxtSoma.getText();
		if (!textoDigitado.isEmpty()) {
			double valorDigitado = Double.parseDouble(textoDigitado);
			valores.add(valorDigitado);
		}

		SomarValoreDigitados();

		enviado.setText(SomarValoreDigitados());
		setEmissaoTotal();
		setTipoCombustivel();
		List<Double> valoresParaExibir = obterValoresDeCadaMes();

		enviado.setText(SomarValoreDigitados());

		calcularMedia();

	}

	public TextField getTxtSoma() {
		return TxtSoma;
	}

	public void setTxtSoma(TextField txtSoma) {
		TxtSoma = txtSoma;
	}

	public List<Double> obterValoresDeCadaMes() {
		List<Double> valores = new ArrayList<>();
		for (Double valor : this.valores) {
			valores.add(valor);
		}
		return valores;
	}

	public void calcularMedia() {
		if (valores.isEmpty()) {
			System.out.println("Nenhum valor armazenado.");
			return;
		}

		double soma = 0.0;
		for (Double valor : valores) {
			soma += valor;
		}

		mediaValoresDigitados = soma / valores.size();
		

	}

	public double getMediaValoresDigitados() {
		return mediaValoresDigitados;
	}

	public void setMediaValoresDigitados(double mediaValoresDigitados) {
		this.mediaValoresDigitados = mediaValoresDigitados;
	}

	public String SomarValoreDigitados() {
		if (valores.isEmpty()) {
			return "Nenhum valor armazenado.";
		}

		double soma = 0.0;
		for (Double valor : valores) {
			soma += valor;
		}

		return Double.toString(soma); // Convertendo a soma para String antes de retornar
	}

	public TextField getTxtEmissaoDoMes() {
		return TxtEmissaoDoMes;
	}

	public void setTxtEmissaoDoMes(TextField txtEmissaoDoMes) {
		TxtEmissaoDoMes = txtEmissaoDoMes;
	}

	public void limparCampos() {
		TxtSoma.clear();
		TxtEmissaoDoMes.clear();

		TxtEmissaoDoMes.requestFocus();
		enviado.setText("");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Constraints.setTextFieldDouble(TxtSoma);
		Constraints.setTextFieldInteger(TxtEmissaoDoMes);
		this.TxtEmissaoDoMes.focusedProperty();

	}

	public void setController(MainViewController controller) {
		this.controller = controller;
	}

}
