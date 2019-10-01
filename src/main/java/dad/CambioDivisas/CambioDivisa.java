package dad.CambioDivisas;

import com.sun.javafx.scene.control.DoubleField;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {

	private TextField numText;
	private TextField numText2;
	private ComboBox<String> DivisaCombo;
	private ComboBox<String> DivisaCombo2;
	private Button changeButton;

	@Override
	public void start(Stage primaryStage) throws Exception {
		numText = new TextField();
		numText.setPromptText("0");
		numText.setMaxWidth(80);
		
		numText2 = new TextField();
		numText2.setPromptText("0");
		numText2.setMaxWidth(80);
		numText2.setEditable(false);
		
		DivisaCombo = new ComboBox<String>();
		DivisaCombo.getItems().addAll("Euro", "Libra", "Dolar","Yen");
		DivisaCombo.getSelectionModel().select(0);
		
		DivisaCombo2 = new ComboBox<String>();
		DivisaCombo2.getItems().addAll("Euro", "Libra", "Dolar","Yen");
		DivisaCombo2.getSelectionModel().select(1);
		 
		changeButton = new Button("Cambiar");
		changeButton.setDefaultButton(true);
		changeButton.setOnAction(e -> onChangeButtonAction(e));

		HBox divBox1 = new HBox(5, numText,DivisaCombo);
		divBox1.setAlignment(Pos.CENTER);
		
		HBox divBox2 = new HBox(5, numText2,DivisaCombo2);
		divBox2.setAlignment(Pos.CENTER);
		
		VBox root = new VBox(divBox1,divBox2,changeButton);
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("Cambio de Divisa");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	

	private Object onChangeButtonAction(ActionEvent e) {
		try {
			if(numText.getText().isEmpty()) {
			Divisa euro = new Divisa("Euro", 1.0);
			Divisa libra = new Divisa("Libra", 0.8873);
			Divisa dolar = new Divisa("Dolar", 1.2007);
			Divisa yen = new Divisa("Yen", 133.59);
			Divisa origen = euro;
			Divisa destino = libra; 
			switch(DivisaCombo.getSelectionModel(). getSelectedItem()) {
			case "Euro":
				origen = euro;
				break;
			case "Libra":
				 origen = libra;
				 break;
			case "Yen":
				origen = yen;
				break;
			case "Dolar":
				origen = dolar;
				break;
			}
			switch(DivisaCombo2.getSelectionModel(). getSelectedItem()) {
			case "Euro":
				destino = euro;
				break;
			case "Libra":
				 destino = libra;
				 break;
			case "Yen":
				destino = yen;
				break;
			case "Dolar":
				destino = dolar;
				break;
			}
			
			Double cantidad = Double.parseDouble(numText.getText());    
			numText2.setPromptText(String.valueOf(destino.fromEuro(origen.toEuro(cantidad))));
			}
			else
			{
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e1) {
			Alert alert1 = new Alert(AlertType.WARNING);
			alert1.setTitle("");
			alert1.setHeaderText("Error al introducir los datos");
			alert1.showAndWait();
			
		}
		return e;
		
	}



	public static void main(String[] args) {
		launch(args);
		
	}

}
