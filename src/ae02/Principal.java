package ae02;

import ae02.Controlador;
import ae02.Modelo;
import ae02.Vista;

public class Principal {

	public static void main(String[] args) {
		Vista vista = new Vista();
		Modelo modelo = new Modelo();
		Controlador controlador = new Controlador(modelo, vista);

	}

}
