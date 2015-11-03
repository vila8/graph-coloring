/**
 * Clase para representar los v&eacute;rtices del problema de coloraci&oacute;n de gr&aacute;ficas mediante optimizaci&oacute;
 * heur&iacute;stica de la colonia de hormigas
 * @author Jes&uacute;s Vila S&aacute;nchez
 * @version Mayo, 2015
 */
public class Vertice {

	private int [] coordenadas;

	/**
	 * Construye una v&eacute;rtice colocado en un punto aleatorio
	 */
	public Vertice() {
		this((int)(Math.random() * 1000), (int)(Math.random() * 1000));
	}

	/**
	 * Construye un v&eacute;rtice colocado en las coordenadas indicadas
	 * @param coordenadas coordenadas donde se colocar&aacute; el v&eacute;rtice
	 */
	public Vertice(int ... coordenadas) {
		this.coordenadas = new int [coordenadas.length];
		for (int i = 0; i < coordenadas.length; i++) {
			this.coordenadas[i] = coordenadas[i];
		}
	}

	/**
	 * Obtiene la distancia euclidiana entre el v&eacute;rtice actual y el v&eacute;rtice especificado
	 * @param vertice v&eacute;rtice de el que se sacar&aacute; la distancia a &eacute;ste v&eacute;rtice
	 * @return distancia entre las v&eacute;rtices
	 */
	public double distanciaEuclidiana(Vertice vertice) {
		int distanciaCoordenada, distanciaTotal;
		distanciaCoordenada = vertice.coordenadas[0] - this.coordenadas[0];
		distanciaTotal = distanciaCoordenada * distanciaCoordenada;
		for (int i = 1; i < this.coordenadas.length; i++) {
			distanciaCoordenada = vertice.coordenadas[i] - this.coordenadas[i];
			distanciaTotal += distanciaCoordenada * distanciaCoordenada;
		}
		return Math.sqrt(distanciaTotal);
	}

	/**
	 * Compara que dos v&eacute;rtices tengan las mismas coordenadas
	 * @param vertice v&eacute;rtice que se comparar&aacute; con &eacute;ste v&eacute;rtice
	 * @return true si la v&eacute;rtice especificada es igual a &eacute;ste v&eacute;rtice
	 */
	public boolean equals(Vertice vertice) {
		if (this.coordenadas.length != vertice.coordenadas.length) {
			return false;
		}
		for (int i = 0; i < this.coordenadas.length; i++) {
			if (this.coordenadas[i] != vertice.coordenadas[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Obtiene la coordenada que se encuentra en el &iacute;ndice indicado
	 * @param indice &iacute;ndice de la coordenada que se pide
	 * @return i-&eacute;sima coordenada de el v&eacute;rtice
	 */
	public int obtenerCoordenada(int indice) {
		return this.coordenadas[indice];
	}

	/**
	 * Representaci&oacute;n como cadena de el v&eacute;rtice
	 * @return representaci&oacute;n de las coordenadas de el v&eacute;rtice
	 */
	@Override
	public String toString() {
		String cadena = "(";
		for (int i = 0; i < this.coordenadas.length; i++) {
			cadena += this.coordenadas[i] + ",";
		}
		return cadena.substring(0, cadena.length() - 1) + ")";
	}
}