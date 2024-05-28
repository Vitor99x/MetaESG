package gui;

import java.lang.ModuleLayer.Controller;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert.AlertType;
import servico.EmpresaServico;

public class GraficoProjecaoViewController implements Initializable {
	

	
	private ProjecaoViewController projecaoViewController;

	
	
	@FXML
	private BarChart<String, Double> barChart;

	@FXML
	private CategoryAxis categoryAxis;
	private double Soma;
	private int meses;
	@FXML
	private NumberAxis numberAxis;

	private Double media;
	private String TipoCobustivel;
	private double EmissaoTotal;
	private double somaTotal;
	private double limiteEmissaoPorMÊS;
	private List<Double> valoresParaExibir;
	public GraficoProjecaoViewController() {

	}

	private ObservableList<String> observableListMeses = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		

	}

	public void findall() {
		try {
	    if (projecaoViewController != null) {
	        String txtEmissaoDoMes = projecaoViewController.getTxtEmissaoDoMesText();
	        meses = projecaoViewController.getValorDigitado2();
	        EmissaoTotal = projecaoViewController.getTotalEmissao();
	        Soma = Double.parseDouble(projecaoViewController.getTxtSoma().getText());
	        media = projecaoViewController.getMediaValoresDigitados();
	       
	        String soma = projecaoViewController.SomarValoreDigitados();
	         valoresParaExibir = projecaoViewController.obterValoresDeCadaMes();
	        barChart.getData().clear();
	        String somaString = projecaoViewController.SomarValoreDigitados();
         	somaTotal = Double.parseDouble(somaString);
         	
         	 limiteEmissaoPorMÊS = EmissaoTotal/12;
	        
	        
	        criarGraficoBarras();
	    }else {
	        System.out.println("ProjecaoViewController não inicializado corretamente.");
	    }}catch (Exception e) {
			Alerts.showAlert("IO Exception", "Estão faltando dados que precisam ser inseridos", e.getMessage(),
					AlertType.INFORMATION);
		}
	}
	
	public void setProjecaoViewController(ProjecaoViewController projecaoViewController) {
	    this.projecaoViewController = projecaoViewController;
	}
	private void criarGraficoBarras() {
	
		
		categoryAxis.setLabel("Meses");
		numberAxis.setLabel("Emissoes");
		
		XYChart.Series emissoesMesses = new XYChart.Series();
		emissoesMesses.setName("EMISSOES DE CADA MÊS");
		for (int i = 0; i < valoresParaExibir.size(); i++) {
			emissoesMesses.getData().add(new XYChart.Data<>("MÊS " + (i + 1), valoresParaExibir.get(i)));
	    }
		
		XYChart.Series quantidadeDeMesesEscolhidos = new XYChart.Series();
		quantidadeDeMesesEscolhidos.setName("QUANTIDADE DE MESES ESCOLHIDOS");
		quantidadeDeMesesEscolhidos.getData().add(new XYChart.Data<>("MESES ESCOLHIDOS", meses));
		
		
		XYChart.Series valorTotalEmitidoEmpresa = new XYChart.Series();
		valorTotalEmitidoEmpresa.setName("VALOR TOTAL EMITIDO PELA EMRESA");
		valorTotalEmitidoEmpresa.getData().add(new XYChart.Data<>("VALOR TOTAL", somaTotal));
		
		XYChart.Series limiteEmissaoMes = new XYChart.Series();
		limiteEmissaoMes.setName("LIMITE EMISSÃO POR MES");
		limiteEmissaoMes.getData().add(new XYChart.Data<>("LIMITE POR MÊS", limiteEmissaoPorMÊS)); 
		
		XYChart.Series mediaDasEmissoes = new XYChart.Series();
		mediaDasEmissoes.setName("MÉDIA DAS EMISSÕES");
		mediaDasEmissoes.getData().add(new XYChart.Data<>("MÉDIA EMISSÕES", media));
		
		XYChart.Series limiteEmissao = new XYChart.Series();
		limiteEmissao.setName("LIMITE ATÉ ONDE PODE EMITIR");
		limiteEmissao.getData().add(new XYChart.Data<>("LIMITE DE EMISSÃO", EmissaoTotal));
		
		

		barChart.getData().addAll(emissoesMesses, quantidadeDeMesesEscolhidos,limiteEmissaoMes,mediaDasEmissoes,valorTotalEmitidoEmpresa,limiteEmissao);
	}

	

	
}
