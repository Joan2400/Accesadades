package ae03;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Biblioteca {

	//crearLlibre
	//Parametro de entrada: Llibre llibre
	//Parametro de salida: String
	//Crea un nuevo libro en la biblioteca
	public static String crearLlibre(Llibre llibre) throws Exception {
		Scanner sc = new Scanner(System.in);

		ArrayList<Llibre> lista = llibre.datosLibro();
		int identificador = 0;

		for (int i = 0; i < lista.size(); i++) {
			identificador = Integer.parseInt(lista.get(i).getIdentificador());
		}
		identificador++;

		String identi = String.valueOf(identificador);

		System.out.println("Dame un titulo");
		String tituloNuevo = sc.nextLine();

		System.out.println("Dame un autor");
		String autorNuevo = sc.nextLine();

		System.out.println("Dame la fecha de publicacion");
		String fechaPublicacionN = sc.nextLine();

		System.out.println("Dame su editorial");
		String editorialNuevo = sc.nextLine();

		System.out.println("Dime su numero de paginas");
		String numPaginasN = sc.nextLine();

		Llibre llibren = new Llibre(identi, tituloNuevo, autorNuevo, fechaPublicacionN, editorialNuevo, numPaginasN);

		lista.add(llibren);

		writeXmlFile(lista);

		return identi;
	}

	//recuperarLlibre
	//Parametro de entrada: int identificador
	//Parametro de salida: Llibre
	//Coge el libro que quiere el usuario
	public static Llibre recuperarLlibre(int identificador) throws Exception {

		Llibre llibre = new Llibre();

		ArrayList<Llibre> lista = llibre.datosLibro();


		return llibre = lista.get(identificador - 1);

	}

	//mostrarLlibre
	//Parametro de entrada: Llibre llibre
	//Parametro de salida: void
	//Muestra toda la informacion del libro solicitado
	public static void mostrarLlibre(Llibre llibre) throws Exception {

		ArrayList<Llibre> lista = llibre.datosLibro();

		int identi = Integer.parseInt(llibre.getIdentificador());

		System.out.println(lista.get(identi).getIdentificador());
		System.out.println(lista.get(identi).getTitol());
		System.out.println(lista.get(identi).getAutor());
		System.out.println(lista.get(identi).getAnyoPublicacion());
		System.out.println(lista.get(identi).getEditorial());
		System.out.println(lista.get(identi).getNumeroPaginas());
	}

	//borrarLlibre
	//Parametro de entrada: int identificador
	//Parametro de salida: void
	//Borra el libro solicitado por el usuario
	public static void borrarLlibre(int identificador) throws Exception {

		int cont = 0;
		Llibre llibre = new Llibre();

		ArrayList<Llibre> lista = llibre.datosLibro();

		lista.remove(identificador - 1);

		for (int i = identificador - 1; i < lista.size(); i++) {
			cont = Integer.parseInt(lista.get(i).getIdentificador());

			cont = cont - 1;

			lista.get(i).setIdentificador(String.valueOf(cont));
		}

		writeXmlFile(lista);


	}

	//actualitzaLlibre
	//Parametro de entrada: int identificador
	//Parametro de salida: void
	//Cambia la informacion de un libro a traves del usuario
	public static void actualitzaLlibre(int identificador) throws Exception {
		Scanner sc = new Scanner(System.in);
		Llibre llibre = new Llibre();

		ArrayList<Llibre> lista = llibre.datosLibro();

		identificador--;
		System.out.println("Dime el titulo nuevo");
		String titulo = sc.nextLine();

		System.out.println("Dime el autor nuevo");
		String autor = sc.nextLine();

		System.out.println("Dime la nueva fecha de publicacion");
		String fechaPublicacion = sc.nextLine();

		System.out.println("Dime la nueva editorial");
		String editorial = sc.nextLine();

		System.out.println("Dime las paginas");
		String paginas = sc.nextLine();


		lista.get(identificador).setTitol(titulo);
		lista.get(identificador).setAutor(autor);
		lista.get(identificador).setAnyoPublicacion(fechaPublicacion);
		lista.get(identificador).setEditorial(editorial);
		lista.get(identificador).setNumeroPaginas(paginas);

		writeXmlFile(lista);

	}

	//recuperarTots
	//Parametro de entrada: nada
	//Parametro de salida: ArrayList<Llibre>
	//Coge todos los libros de la lista
	public ArrayList<Llibre> recuperarTots() throws Exception{

		Llibre llibre = new Llibre();
		ArrayList<Llibre> lista = llibre.datosLibro();

		return lista;




	}

	//writeXmlFile
	//Parametro de entrada: ArrayList<Llibre> lista
	//Parametro de salida: void
	//Sobrescribe el documento xml para actualizar los nuevos cambios
	public static void writeXmlFile(ArrayList<Llibre> lista) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			//Elemento raíz
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Biblioteca");
			doc.appendChild(rootElement);
			for (Llibre llibre: lista) {
				Element libro = doc.createElement("libro");
				String identificador = llibre.getIdentificador();
				libro.setAttribute("identificador", identificador);
				rootElement.appendChild(libro);
				Element titulo = doc.createElement("titulo");
				titulo.appendChild(doc.createTextNode(String.valueOf(llibre.getTitol())));
				libro.appendChild(titulo);
				Element autor = doc.createElement("autor");
				autor.appendChild(doc.createTextNode(String.valueOf(llibre.getAutor())));
				libro.appendChild(autor);
				Element anyo = doc.createElement("anyo");
				anyo.appendChild(doc.createTextNode(String.valueOf(llibre.getAnyoPublicacion())));
				libro.appendChild(anyo);
				Element editorial = doc.createElement("editorial");
				editorial.appendChild(doc.createTextNode(llibre.getEditorial()));
				libro.appendChild(editorial);
				Element numPaginas = doc.createElement("numpaginas");
				numPaginas.appendChild(doc.createTextNode(llibre.getNumeroPaginas()));
				libro.appendChild(numPaginas);

			}
			//Se escribe el contenido del XML en un archivo
			TransformerFactory tranFactory = TransformerFactory.newInstance(); // Crear serializador
			Transformer aTransformer = tranFactory.newTransformer();
			aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1"); // Darle formato al documento
			aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			try {
				FileWriter fw = new FileWriter(".\\src\\ae03\\Biblioteca.xml"); // Definir el nombre del fichero y guardar
				StreamResult result = new StreamResult(fw);
				aTransformer.transform(source, result);
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (TransformerException ex) {
			System.out.println("Error escribiendo el documento");
		} catch (ParserConfigurationException ex) {
			System.out.println("Error construyendo el documento");
		}
	}


	//main
	//Parametro de entrada: String[] args
	//Parametro de salida: void
	//Donde si inicializa todo el programa
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int opcion;
		do {
			System.out.println("Bienvenido al menu de la biblioteca");
			System.out.println("Pulse 1 si quiere mostrar todos los titulos de la biblioteca");
			System.out.println("Pulse 2 si quiere información detallada de un libro");
			System.out.println("Pulse 3 si quiere crear un nuevo libro");
			System.out.println("Pulse 4 si quiere actualizar un libro");
			System.out.println("Pulse 5 si quiere borrar un libro");
			System.out.println("Pulse 6 si quiere cerrar la biblioteca");
			opcion = sc.nextInt();

			switch(opcion) {
			case 1: 
				Llibre llibre = new Llibre();
				ArrayList<Llibre> lista = llibre.datosLibro();
				
				for (int i = 0; i < lista.size(); i++) {
					System.out.println("Titulo " + lista.get(i).getTitol());
				}
				break;
			case 2:
				int identificador;
				System.out.println("Dime el libro que quieres ver");
				identificador = sc.nextInt();
				mostrarLlibre(recuperarLlibre(identificador - 1));
				break;
			case 3:
				 llibre = new Llibre();
				crearLlibre(llibre);
				break;
			case 4:
				System.out.println("Dime el libro que quieres modificar");
				 identificador = sc.nextInt();
				actualitzaLlibre(identificador);

				break;
			case 5:
				System.out.println("Dime el libro que quieres borrar");
				identificador = sc.nextInt();
				borrarLlibre(identificador);
				break;
			}


		}
		while(opcion != 6);

	}

}
