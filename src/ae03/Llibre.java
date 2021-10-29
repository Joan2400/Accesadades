package ae03;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Llibre {

	private String identificador;
	private String titol;
	private String autor;
	private String anyoPublicacion;
	private String editorial;
	private String numeroPaginas;

	//Constructor por defecto
	public Llibre() {

	}

	//Constructor con parametros
	public Llibre(String identificador, String titol, String autor, String anyoPublicacion, String editorial,
			String numeroPaginas) {

		this.identificador = identificador;
		this.titol = titol;
		this.autor = autor;
		this.anyoPublicacion = anyoPublicacion;
		this.editorial = editorial;
		this.numeroPaginas = numeroPaginas;
	}

	//getIdentificador
	//Parametro de entrada: ninguno
	//Parametro de salida: String
	public String getIdentificador() {
		return identificador;
	}

	//setIdentificador
	//Parametro de entrada: String identificador
	//Parametro de salida: void
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	//getTitol
	//Parametro de entrada: ninguno
	//Parametro de salida: String
	public String getTitol() {
		return titol;
	}
	//setTitol
	//Parametro de entrada: String titol
	//Parametro de salida: void
	public void setTitol(String titol) {
		this.titol = titol;
	}
	//getAutor
	//Parametro de entrada: ninguno
	//Parametro de salida: String
	public String getAutor() {
		return autor;
	}

	//setAutor
	//Parametro de entrada: String autor
	//Parametro de salida: void
	public void setAutor(String autor) {
		this.autor = autor;
	}

	//getAnyoPublicacion
	//Parametro de entrada: ninguno
	//Parametro de salida:String
	public String getAnyoPublicacion() {
		return anyoPublicacion;
	}

	//setAnyoPublicacion
	//Parametro de entrada: String anyoPublicacion
	//Parametro de salida: void
	public void setAnyoPublicacion(String anyoPublicacion) {
		this.anyoPublicacion = anyoPublicacion;
	}

	//getEditorial
	//Parametro de entrada: ninguno
	//Parametro de salida: String
	public String getEditorial() {
		return editorial;
	}

	//setEditorial
	//Parametro de entrada: String editorial
	//Parametro de salida: void
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	//getNumeroPaginas
	//Parametro de entrada: ninguno
	//Parametro de salida: String
	public String getNumeroPaginas() {
		return numeroPaginas;
	}

	//setNumeroPaginas
	//Parametro de entrada: String numeroPaginas
	//Parametro de salida: void
	public void setNumeroPaginas(String numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	//datosLibro
	//Parametro de entrada: ninguno
	//Parametro de salida: ArrayList<Llibre>
	//Proporciona los datos de la lista
	public ArrayList<Llibre> datosLibro() throws Exception {

		Scanner sc = new Scanner(System.in);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(new File(".\\src\\ae03\\Biblioteca.xml"));


		ArrayList<Llibre> lista = new ArrayList<>();
		Element raiz = doc.getDocumentElement();
		NodeList nodeList = doc.getElementsByTagName("libro");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			System.out.println("");
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				String identificador = eElement.getAttribute("identificador");
				String titulo = eElement.getElementsByTagName("titulo").item(0).getTextContent();
				String autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
				String fechaPublicacion = eElement.getElementsByTagName("anyo").item(0).getTextContent();
				String editorial = eElement.getElementsByTagName("editorial").item(0).getTextContent();
				String numPaginas = eElement.getElementsByTagName("numpaginas").item(0).getTextContent();

				Llibre llibre= new Llibre(identificador,titulo, autor, fechaPublicacion, editorial, numPaginas);
				lista.add(llibre);


			}

		}
		return lista;
	}







}
