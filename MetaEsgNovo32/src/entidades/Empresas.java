package entidades;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.TextField;

public class Empresas implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private Double Emissao;
	private Double Porcentagem;
	private Double totalEmissao;
	private TextField textField;

	public Empresas() {
	}

	public Empresas(TextField textField) {
		this.textField=textField;
	}
	public Empresas(String nome, Double emissao, Double porcentagem, Double totalEmissao) {
		this.nome = nome;
		this.Emissao = emissao;
		this.Porcentagem = porcentagem;
		this.totalEmissao= totalEmissao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getEmissao() {
		return Emissao;
	}

	public void setEmissao(Double emissao) {
		Emissao = emissao;
	}

	public Double getPorcentagem() {
		return Porcentagem;
	}

	public void setPorcentagem(Double porcentagem) {
		Porcentagem = porcentagem;
	}

	public Double getTotalEmissao() {
		return totalEmissao;
	}

	public void setTotalEmissao(Double totalEmissao) {
		this.totalEmissao = totalEmissao;
	}

	// Suponha que você tenha um método que retorne os dados de emissões por mês
    public Map<String, Double> getEmissionsData() {
        
        Map<String, Double> emissionsData = new HashMap<>();
        emissionsData.put("janeiro", 10.0);
        emissionsData.put("Fevereiro", 20.0);
        // Adicione os outros meses conforme necessário
        return emissionsData;
    }
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresas other = (Empresas) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empresas [nome=" + nome + ", Emissao=" + Emissao + ", Porcentagem=" + Porcentagem + "]";
	}

}
