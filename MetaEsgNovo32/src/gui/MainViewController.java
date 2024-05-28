package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import entidades.Empresas;
import gui.util.Alerts;
import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import servico.EmpresaServico;

public class MainViewController implements Initializable {

	
	private ProjecaoViewController projecaoViewController;
	public ProjecaoViewController getProjecaoViewController() {
		return projecaoViewController;
	}

	public void setProjecaoViewController(ProjecaoViewController projecaoViewController) {
		this.projecaoViewController = projecaoViewController;
	}

	public EmpresaServico getEmpresaServico() {
		return empresaServico;
	}

	public void setEmpresaServico(EmpresaServico empresaServico) {
		this.empresaServico = empresaServico;
	}

	private Empresas entidade;
	private EmpresaServico empresaServico;
	@FXML
	private MenuItem menuItemEmpresas;

	@FXML
	private MenuItem menuItemAjuda;
	@FXML
	private MenuItem menuItemHome;
	
	@FXML MenuItem menuItemProjecao;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtEmissao;

	@FXML
	private TextField txtPorcentagem;

	@FXML
	private Button btnSalvar;

	@FXML
	private Button btnCancelar;

	@FXML
	private Label enviado;

	@FXML 
	private TextField tipoEmissao;
	@FXML
	public void btnSalvarEnviar() {
		this.txtNome.requestFocus();
		enviado.setText("Enviado");
		

	}

	@FXML
	public void btnCancelarLimpar() {

		this.txtNome.setText(new String());
		this.txtEmissao.setText(new String());
		this.txtPorcentagem.setText(new String());
		this.tipoEmissao.setText(new String());
		this.txtNome.requestFocus();
		
		enviado.setText("");
	}

	@FXML
	public void onMenuItemEmpresasAcao() {
	    try {
	        loadView("/gui/ListaDeEmpresas.fxml", (EmpresaListController controller) -> {
	            controller.setEmpresaServico(new EmpresaServico(this));
	            controller.updateTableView();
	        });
	    } catch (Exception e) {
			Alerts.showAlert("IO Exception", "Nenhum dado cadastrado. Defina os dados na tela principal",e.getMessage(), AlertType.INFORMATION);
		}
	}

	@FXML
	public void onMenuItemTelaPrincipal() {
		loadView("/gui/MainView.fxml",x -> {
			
		});
	}
	@FXML
	public void onMenuItemProjecao() {
	    loadView("/gui/Projecao.fxml", x -> {
	        ProjecaoViewController controller = (ProjecaoViewController) x;
	        controller.setControllerEmpresas(new EmpresaServico(this));
	        controller.setController(this); // Passando a referência de MainViewController
	    });
	}

	@FXML
	public void onGraficoProjecao() {
	    loadView("/gui/graficoProjecaonew.fxml", x -> {
	        GraficoProjecaoViewController controller = (GraficoProjecaoViewController) x;
	        controller.setProjecaoViewController(projecaoViewController);
	    });
	}
	@FXML
	public void onMenuItemAjudaAcao() {
		loadView("/gui/Ajuda.fxml", x -> {
		});
	}

	public TextField getTxtNome() {
		return txtNome;
	}

	public TextField getTipoEmissao() {
		return tipoEmissao;
	}

	public void setTipoEmissao(TextField tipoEmissao) {
		this.tipoEmissao = tipoEmissao;
	}

	public void setTxtNome(TextField txtNome) {
		this.txtNome = txtNome;
	}

	public TextField getTxtEmissao() {
		return txtEmissao;
	}

	public void setTxtEmissao(TextField txtEmissao) {
		this.txtEmissao = txtEmissao;
	}

	public TextField getTxtPorcentagem() {
		return txtPorcentagem;
	}

	public void setTxtPorcentagem(TextField txtPorcentagem) {
		this.txtPorcentagem = txtPorcentagem;
	}

	public void setEmpresas(Empresas enitdade) {
		this.entidade = entidade;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		// carregar tela
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));

			VBox newVbox = loader.load();

			// mostrar view na janela principal
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVbox.getChildren());

			T controller = loader.getController();
			initializingAction.accept(controller);
		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro de carregamento de pagina", e.getMessage(), AlertType.ERROR);
		}

	}

	private void initializeNodes() {
		Constraints.setTextFieldMaxLength(txtNome, 30);
		Constraints.setTextFieldDouble(txtEmissao);
		Constraints.setTextFieldDouble(txtPorcentagem);
		Constraints.setTextFieldMaxLength(tipoEmissao, 15);

	}

	public void updateFormData() {
		if (entidade == null) {
			throw new IllegalStateException("Entidade está nulo");
		}
		txtNome.setText(entidade.getNome());
		txtEmissao.setText(String.valueOf(entidade.getEmissao()));
		txtPorcentagem.setText(String.valueOf(entidade.getPorcentagem()));
	}
}
