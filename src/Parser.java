import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Clase para leer archivos con los v&eacute;rtices y aristas de la gr&aacute;fica;
 * heur&iacute;stica de la colonia de hormigas
 * @author Jes&uacute;s Vila S&aacute;nchez
 * @version Mayo, 2015
 */
public class Parser {

	private final int VERTICES = 200;
	
	private String archivo;
	private ArrayList<Vertice> V;
	private ArrayList<Arista> E;

	public Parser(String archivo) {
		this.archivo = archivo;
		V = new ArrayList<Vertice>();
		E = new ArrayList<Arista>();
	}

	private void leer() {
		Vertice temp;
		for (int i = 0; i < VERTICES; i++) {
			temp = new Vertice();
			while (V.contains(temp)) {
				temp = new Vertice();
			}
			V.add(temp);
		}
		try {
			BufferedReader lector = new BufferedReader(new FileReader(this.archivo));
			String linea;
			Arista a;
			while ((linea = lector.readLine()) != null) {
	  			String [] vertices = linea.split(" ");
	  			for (int i = 1; i < vertices.length; i++) {
	  				a = new Arista(V.get(Integer.parseInt(vertices[0])), V.get(Integer.parseInt(vertices[i])));
	  				if (!E.contains(a)) {
	  					E.add(a);
	  				}
	  			}
			}
			lector.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Grafica obtenerGrafica() {
		leer();
		return new Grafica(V, E);
	}
}