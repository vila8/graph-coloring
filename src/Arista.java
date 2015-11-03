/**
 * Clase para representar las aristas del problema de coloraci&oacute;n de gr&aacute;ficas mediante optimizaci&oacute;
 * heur&iacute;stica de la colonia de hormigas
 * @author Jes&uacute;s Vila S&aacute;nchez
 * @version Mayo, 2015
 */
public class Arista {
	
	private Vertice vertice_1;
	private Vertice vertice_2;


	/**
	 * Construye una arista apartir de sus dos v&eacute;rtices incidentes
	 * @param vertice_1 primer v&eacute;rtice incidente a la arista
	 * @param vertice_2 segundo v&eacute;rtice incidente a la arista
	 */
	public Arista(Vertice vertice_1, Vertice vertice_2) {
		this.vertice_1 = vertice_1;
		this.vertice_2 = vertice_2;
	}

	/**
	 * Verifica si un v&eacute;rtice es incidente a la arista
	 * @param vertice v&eacute;rtice que se buscar&aacute; entre los v&eacute;rtices de la arista
	 * @return true si el v&eacute;rtice es incidente, false en otro caso
	 */
	public boolean incidente(Vertice vertice) {
		return this.vertice_1.equals(vertice) || this.vertice_2.equals(vertice);
	}

	/**
	 * Compara que dos aristas tengan la misma pareja no ordenada de v&eacute;rtices.
	 * @param arsita arsita que se comparar&aacute; con &eacute;sta arista.
	 * @return true si los arsitas tienen los mismos v&eacute;rtices, false en otro caso.
	 */
	public boolean equals(Arista arista) {
		return this.incidente(arista.vertice_1) && this.incidente(arista.vertice_2);
	}

	/**
	 * Obtiene el v&eacute;rtice adyacente al pasado en caso de que exista
	 * @param vertice v&eacute;rtice del que se quiere obtener el v&eacute;rtice adyacente sobre la arista
	 * @return v&eacute;rtice adyacente, null en caso de que el v&eacute;rtice no est&eacute; en la arista
	 */
	public Vertice obtenerAdyacente(Vertice vertice) {
		if (!this.incidente(vertice)) {
			return null;
		}
		if (this.vertice_1.equals(vertice))
			return this.vertice_2;
		return this.vertice_1;
	}

	/**
	 * Representaci&oacute;n como cadena de la arista
	 * @return representaci&oacute;n de la arista como pareja no ordenada
	 */
	@Override
	public String toString() {
		return "{" + this.vertice_1 + "," + this.vertice_2 + "}";
	}
}