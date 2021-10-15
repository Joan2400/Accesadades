package ae02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.Caret;

import ae02.Modelo;
import ae02.Vista;

public class Controlador {

	private ActionListener actionListenerBoton1, actionListenerBoton2;
	private Modelo modelo;
	private Vista vista;
	private String buscarPalabra, palabraReemplazar;

	public Controlador(Modelo modelo, Vista vista){
		this.modelo = modelo;
		this.vista = vista;
		initEventHandlers();
	}


	//Nombre metodo: initEventHandlers
	//Parametro de entrada: Ninguno
	//Parametro de salida: void
	public void initEventHandlers() {


		vista.getTextAreaOriginal().setText(modelo.contenidoFichero());


		actionListenerBoton1 = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				modelo.buscarPalabras(vista.getTextFieldBuscar().getText());
			}
		};
		vista.getBtnBuscar().addActionListener(actionListenerBoton1);

		actionListenerBoton2 = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				palabraReemplazar = vista.getTextFieldReemplazar().getText();
				buscarPalabra = vista.getTextFieldBuscar().getText();
				String modificado =modelo.reemplazarPalabras(palabraReemplazar, buscarPalabra, vista.getTextAreaOriginal().getText());
				vista.getTextAreaModificado().setText("" + modificado);;

			}
		};
		vista.getBtnReemplazar().addActionListener(actionListenerBoton2);
	}

}
