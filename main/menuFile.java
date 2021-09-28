package main;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Acceso a datos
 * 2º DAM
 * @author Joan Peñarrocha Crespo
 *
 */

public class menuFile {

	//Este es el main que es donde vamos a ejecutar el menu de los metodos que hemos hecho
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String archivo = args[0];
		File fichero = new File(archivo);
		int opcion;

		//Se inicializa el menu de la aplicacion
		do {
			System.out.println("Bienvenido al programa para la gestion de ficheros y directorios.");
			System.out.println("Pulse 1 si quiere saber la informacion de un directorio o un fichero");
			System.out.println("Pulse 2 si quiere crear una carpeta");
			System.out.println("Pulse 3 si quiere crear un fichero");
			System.out.println("Pulse 4 si quiere eliminar un directorio o un fichero");
			System.out.println("Pulse 5 si quiere cambiar el nombre de un directorio o un fichero");
			System.out.println("Pulse 0 si quiere salir del programa");
			opcion = sc.nextInt();

			//Se encuentran los diferentes casos que se haran segun el numero que pulse el usuario 
			switch(opcion) {

			case 0:
				System.out.println("Gracias por utilizar nuestro programa");
				break;

			case 1: 
				getInformacio(fichero);
				break;

			case 2:
				creaCarpeta(fichero);
				break;

			case 3:
				creaFitxer(fichero);
				break;

			case 4:
				elimina(fichero);
				break;

			case 5:
				renomena(fichero);
				break;

			default:
				System.out.println("Error. Ponga un numero del 0 al 5");
				break;
			}
		}
		while(opcion != 0);



	}


	//Este metodo sirve para ver la informacion completa de la carpeta o fichero que se le pasa por la ruta
	//El parametro de entrada fichero es la ruta que se le pasa por parametro para que se muestre su informacion
	//La informacion que muestra es su ultima modificacion, nombre, su espacio, si es oculto, etc...
	public static void getInformacio(File fichero) {
		String formato = "dd-MM-yyyy";
		SimpleDateFormat formarData = new SimpleDateFormat(formato);
		Date ultimaModificacion = new Date();

		File[] lista = fichero.listFiles();

		System.out.println("El nombre del archivo es: " + fichero);
		if(fichero.isDirectory() == true) {
			System.out.println("El archivo es un directorio");
			System.out.println(" Hay " +  lista.length + " elementos en el directorio");
			System.out.println("Espacio libre: " + fichero.getFreeSpace());
			System.out.println("Espacio disponible: " + fichero.getUsableSpace());
			System.out.println("Espacio total: " + fichero.getTotalSpace());

		}
		else {
			System.out.println("El archivo es un fichero");
			System.out.println("La grandaria del fichero es: " + fichero.length());
		}

		System.out.println(fichero.getAbsolutePath());

		System.out.println("La última modificacion del fichero es: " + formarData.format(ultimaModificacion) );
		System.out.println("El archivo es oculto: " + fichero.isHidden());

	}

	//Aqui creamos la carpeta sobre la ruta que le pasamos por parametro
	//El parametro fichero es la ruta que se le pasa al usuario donde sobre esa ruta el usuario crea la carpeta
	public static void creaCarpeta(File fichero) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Dime el nombre de la carpeta que quieres crear");
		String nombre = sc.nextLine();

		String rutaNueva = fichero + "\\" + nombre;

		File carpeta = new File(rutaNueva);

		if(carpeta.mkdir()) {
			System.out.println("Directorio creado correctamente");
		}
		else {
			System.out.println("Error al crear el directorio");
		}

	}

	//Aqui creamos el fichero sobre la ruta que le pasamos por parametro
	//El parametro fichero es la ruta que se le pasa al usuario donde sobre esa ruta el usuario crea el fichero con su respectiva extension
	public static void creaFitxer(File fichero) throws IOException {

		Scanner sc = new Scanner(System.in);

		System.out.println("Dime el nombre del fichero que quieres crear con su extension");
		String nombre = sc.nextLine();

		String rutaNueva = fichero + "\\" + nombre;

		File nuevoFichero = new File(rutaNueva);

		if(nuevoFichero.createNewFile()) {
			System.out.println("El fichero se ha creado correctamente");
		}
		else {
			System.out.println("Error al crear el fichero");
		}
	}

	//Aqui eliminamos el fichero o carpeta sobre la ruta que se le pasa por parametro
	//El parametro fichero es la ruta que le pasamos al usuario que es donde se eliminara segun el nombre un fihero o carpeta
	public static void elimina(File fichero) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Dime el nombre del fichero o directorio que quieres borrar");
		String nombre = sc.nextLine();

		String rutaNueva = fichero + "\\" + nombre;

		File eliminar = new File(rutaNueva);

		if(eliminar.delete()) {
			System.out.println("El fichero o directorio se ha eliminado correctamente");
		}
		else {
			System.out.println("Error al eliminar el fichero o el directorio");
		}
	}

	//En este caso se le pasa la ruta al usuario como los otros pero en este caso le decimos al usuario que pase la misma ruta pero con el nombre del archivo o directorio cambiado
	//El parametro fichero es la ruta que se le pasa al usuario 
	public static void renomena(File fichero) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Dime la ruta del fichero o carpeta que quieres renombrar");
		String ruta = sc.nextLine();

		File nuevoNombre = new File(ruta);

		if(fichero.renameTo(nuevoNombre)) {
			System.out.println("Archivo renombrado");
		}
		else {
			System.out.println("Error");
		}
	}


}
