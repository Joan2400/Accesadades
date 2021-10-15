package ae02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Modelo {

	private String fichero_lectura;
	private String fichero_escritura;

	public Modelo() {
		fichero_lectura = ".\\src\\ae02\\AE02_T1_2_Streams_Groucho.txt";
		fichero_escritura = ".\\src\\ae02\\fichero_escritura.txt";
	}


	//Nombre metodo: contenidoFichero
	//Parametro de entrada: Ninguno
	//Parametro de salida: String
	public String contenidoFichero(){
		String valor = "";
		try {
			FileReader fr = new FileReader(fichero_lectura);
			BufferedReader br = new BufferedReader(fr);

			String linea = "";

			while((linea = br.readLine()) != null) {
				valor = valor + linea + "\n";
			}
			br.close();
			fr.close();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return valor;
	}

	//Nombre metodo: ficheroEscritura
	//Parametro de entrada: Ninguno
	//Parametro de salida: String
	public String ficheroEscritura() {
		return fichero_escritura;
	}

	//Nombre metodo: ficheroLectura
	//Parametro de entrada: Ninguno
	//Parametro de salida: String
	public String ficheroLectura() {
		return fichero_lectura;
	}


	//Nombre metodo: buscarPalabras
	//Parametro de entrada: String palabra
	//Parametro de salida: void
	public void buscarPalabras(String palabra){
		int contarPalabras =0;
		int contar = 0;
		try {
			String texto = contenidoFichero();

			contar = texto.indexOf(palabra);
			while (contar != -1) {
				contar = texto.indexOf(palabra, contar + 1);
				contarPalabras++;
			}

		}
		catch (Exception e){
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(new JFrame(),"Palabras encontradas: " + contarPalabras,"INFO", JOptionPane.INFORMATION_MESSAGE);

	}


	//Nombre metodo: reemplazarPalabras
	//Parametro de entrada: String palabra, textoBuscar, texto
	//Parametro de salida: String
	public String reemplazarPalabras(String palabra, String textoBuscar, String texto) {
		String reemplazar = "";
		reemplazar = texto.replaceAll(textoBuscar, palabra);
		try {

			File ruta = new File(fichero_escritura);
			ruta.createNewFile();

			BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));


			bw.write(reemplazar);	

			bw.close();


		}
		catch (Exception e){
			e.printStackTrace();
		}
		return reemplazar;

	}

}
