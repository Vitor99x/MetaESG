package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import entidades.Empresas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import servico.EmpresaServico;

public class EmpresaListController implements Initializable {

	private EmpresaServico empresa;
	@FXML
	private TableView<Empresas> tableViewEmpresas;

	@FXML
	private TableColumn<Empresas, String> tableColumNome;

	@FXML
	private TableColumn<Empresas, Double> tableColumEmissao;

	@FXML
	private TableColumn<Empresas, Double> tableColumPorcetagem;

	@FXML
	private TableColumn<Empresas, Double> tableColumTotalEmissao;
	
	@FXML
	private TableColumn<Empresas, String> tableColumTipoEmissao;

	private ObservableList<Empresas> obsList;

	public void setEmpresaServico(EmpresaServico empresa) {
		this.empresa = empresa;

	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();

	}

	private void initializeNodes() {
		tableColumNome.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		tableColumEmissao.setCellValueFactory(new PropertyValueFactory<>("Emissao"));
		tableColumPorcetagem.setCellValueFactory(new PropertyValueFactory<>("Porcentagem"));
		tableColumTotalEmissao.setCellValueFactory(new PropertyValueFactory<>("totalEmissao"));
		
		
		VBox mainVBox = (VBox) tableViewEmpresas.getParent();
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewEmpresas.prefHeightProperty().bind(stage.heightProperty());

	}

	public void updateTableView() {
		if (empresa == null) {
			throw new IllegalStateException("Serviço está nulo");
		}
		List<Empresas> list = empresa.findall();
		obsList = FXCollections.observableArrayList(list);
		tableViewEmpresas.setItems(obsList);
	}

}
