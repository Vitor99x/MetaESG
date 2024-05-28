module MetaEsgNovo {
	requires javafx.controls;
	requires javafx.fxml;

	opens gui to javafx.fxml;
	opens entidades to javafx.base;

	exports applications; // Substitua "applications" pelo nome do seu pacote

	requires javafx.graphics;

	 exports gui;

	// Outras dependências...

	opens application to javafx.graphics, javafx.fxml;
}
