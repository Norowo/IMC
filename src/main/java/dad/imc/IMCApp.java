package dad.imc;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IMCApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// ui
		
		TextField pesoText = new TextField();
		pesoText.setPrefColumnCount(5);
		TextField alturaText = new TextField();
		alturaText.setPrefColumnCount(5);
		Label imcLabel = new Label("IMC: (peso * altura^2)");
		Label  pesoLabel = new Label("Bajo peso | Normal | Sobrepeso | Obeso");
		
		HBox pesoHBox = new HBox(5, new Label("Peso:"),pesoText, new Label("kg"));
		pesoHBox.setAlignment(Pos.CENTER);
		HBox alturaHBox = new HBox(5, new Label("Altura:"), alturaText ,new Label("cm"));
		alturaHBox.setAlignment(Pos.CENTER);
		HBox imcHBox = new HBox(imcLabel);
		imcHBox.setAlignment(Pos.CENTER);
		
		VBox root = new VBox(5, pesoHBox,alturaHBox,imcHBox,pesoLabel);
		root.setAlignment(Pos.CENTER);
		
		primaryStage.setTitle("IMC");
		primaryStage.setScene(new Scene(root, 320, 200));
		primaryStage.show();
		
		// bindings
		
		DoubleProperty peso = new SimpleDoubleProperty();
		DoubleProperty altura = new SimpleDoubleProperty();
		DoubleProperty imc = new SimpleDoubleProperty();
		
		pesoText.textProperty().bindBidirectional(peso, new NumberStringConverter());
		alturaText.textProperty().bindBidirectional(altura, new NumberStringConverter());
		
		imc.bind(peso.divide(altura.divide(100).multiply(altura.divide(100))));
		imcLabel.textProperty().bind(imc.asString("%.2f"));
		
		
		
		
		// listeners
		
		imc.addListener((o, ov, nv) -> {
			double imcValor = nv.doubleValue();
			String pesoCadena ="";
			if(imcValor < 18.5) {
				pesoCadena ="Bajo peso";
			} else if(imcValor >= 18.5 && imcValor < 25) {
				pesoCadena ="Peso normal";
			} else if(imcValor >= 25 && imcValor < 30) {
				pesoCadena ="Sobrepeso";
			} else {
				pesoCadena ="Obesidad";;
			}
			
			pesoLabel.setText(pesoCadena);
			
		});
		
		

	}

}
