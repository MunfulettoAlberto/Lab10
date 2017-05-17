package it.polito.tdp.porto;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {

	Model model ;
	public void setModel(Model m){
		this.model = m ;
		boxPrimo.getItems().addAll(model.createGraph());
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Author> boxPrimo;

    @FXML
    private ComboBox<Author> boxSecondo;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCoautori(ActionEvent event) {
    	txtResult.clear();
    	Author a = boxPrimo.getValue();
    	String s = model.restituiscicoAutori(a);
    	
    	txtResult.setText(s);
    	boxSecondo.getItems().addAll(model.restituisciINonCoautori(boxPrimo.getValue()));
    }

    @FXML
    void handleSequenza(ActionEvent event) {
    	Author a = boxPrimo.getValue();
    	Author b = boxSecondo.getValue();
    	txtResult.clear();
    	String s = model.restituisciArticoli(a, b) ;
    	txtResult.setText(s);
    }

    @FXML
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";

    }
}
