package ae02;

import ae02.Controlador;
import ae02.Modelo;
import ae02.Vista;

public class Principal {

	//Nombre metodo: main
	//Parametro de entrada: args
	//Parametro de salida: void
	public static void main(String[] args) {
		Vista vista = new Vista();
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador(modelo, vista);

	}

}
