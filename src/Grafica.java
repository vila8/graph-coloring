import java.util.ArrayList;
/**
 * Clase para representar la gr&aacute;fica del problema de coloraci&oacute;n de gr&aacute;ficas mediante optimizaci&oacute;
 * heur&iacute;stica de la colonia de hormigas
 * @author Jes&uacute;s Vila S&aacute;nchez
 * @version Mayo, 2015
 */
public class Grafica {
	
	private ArrayList<Vertice> V;
	private ArrayList<Arista> E;

	/**
	 * Construye una gr&aacute;fica apartir de un conjunto de v&eacute;rtices y un conjunto de aristas
	 * @param V conjunto de v&eacute;rtices
	 * @param E conjunto de aristas
	 */
	public Grafica(ArrayList<Vertice> V, ArrayList<Arista> E) {
		this.V = V;
		this.E = E;
	}

	/**
	 * Obtiene el conjunto de v&eacute;rtices de la gr&aacute;fica
	 * @return conjunto de v&eacute;rtices de la gr&aacute;fica
	 */
	public ArrayList<Vertice> obtenerVertices() {
		return this.V;
	}

	/**
	 * Obtiene el v&eacute;rtice que se encuentra en el &iacute;ndice indicado
	 * @return v&eacute;rtice que se encuentra en la i-&eacute;sima posici&oacute;n
	 */
	public Vertice obtenerVertice(int indice) {
		return this.V.get(indice);
	}

	/**
	 * Obtiene el &iacute;ndice donde se encuentra el v&eacute;rtice indicado
	 * @param vertice v&eacute;rtice que se busca en el conjunto de v&eacute;rtices
	 * @return el &iacute;ndice donde se encuentra el v&eacute;rtice indicado, -1 si no est&aacute; en el conjunto de v&eacute;rtices
	 */
	public int indiceDeVertice(Vertice vertice) {
		return this.V.indexOf(vertice);
	}

	/**
	 * Obtiene el n&uacute;mero de elementos que tiene el conjunto de v&eacute;rtices
	 * @return cardinalidad del conjunto de v&eacute;rtices
	 */
	public int cardinalidadVertices() {
		return this.V.size();
	}

	/**
	 * Obtiene el conjunto de aristas de la gr&aacute;fica
	 * @return conjunto de aristas de la gr&aacute;fica
	 */
	public ArrayList<Arista> obtenerAristas() {
		return this.E;
	}

	/**
	 * Obtiene la arista que se encuentra en el &iacute;ndice indicado
	 * @return arista que se encuentra en la i-&eacute;sima posici&oacute;n
	 */
	public Arista obtenerArista(int indice) {
		return this.E.get(indice);
	}

	/**
	 * Obtiene el &iacute;ndice donde se encuentra la arista indicada
	 * @param arista que se busca en el conjunto de aristas
	 * @return el &iacute;ndice donde se encuentra arista indicada, -1 si no est&aacute; en el conjunto de aristas
	 */
	public int indiceDeArista(Arista arista) {
		return this.E.indexOf(arista);
	}

	/**
	 * Obtiene el n&uacute;mero de elementos que tiene el conjunto de aristas
	 * @return cardinalidad del conjunto de aristas
	 */
	public int cardinalidadAristas() {
		return this.E.size();
	}
}