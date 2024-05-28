package servico;

import java.util.ArrayList;
import java.util.List;

import entidades.Empresas;
import gui.MainViewController;

public class EmpresaServico {
	private  MainViewController controller;
	
	private String nome;
	private double emissao;
	private double porcentagem;
	private double totalEmissao;
	private String tipoCombustivel;
	
	

	public EmpresaServico(MainViewController controller) {
		this.controller = controller;
	}
	
	public EmpresaServico() {
	
	}

	public List<Empresas> findall() {
		

		List<Empresas> list = new ArrayList<Empresas>();

		// Obtenha os valores dos campos de texto do MainViewController para cada
		// empresa
		 nome = controller.getTxtNome().getText();
		 emissao = Double.parseDouble(controller.getTxtEmissao().getText());
		 porcentagem = Double.parseDouble(controller.getTxtPorcentagem().getText());
		 totalEmissao = emissao - (emissao * porcentagem / 100);
		 tipoCombustivel = controller.getTipoEmissao().getText();
		 
		// Adicione a empresa à lista com os valores obtidos
		list.add(new Empresas(nome, emissao, porcentagem, totalEmissao));

		
		

		return list;
	}
	
	public String getTipoCombustivel() {
		return tipoCombustivel;
		
	}

	public double getTotalEmissao() {
		return totalEmissao;
	}

	public void setTotalEmissao(double totalEmissao) {
		this.totalEmissao = totalEmissao;
	}
	
	
}
