import java.util.ArrayList;
import java.util.Random;

public class ColoniaHormigas {
	
	private Grafica G;
	private ArrayList<Vertice> X;
	private ArrayList<ArrayList<Vertice>> C;
	private ArrayList<Vertice> F;

	private int NCICLOS = 50;
	private int NANTS = 100;

	private final double ALPHA = 2;
	private final double BETA = 3;
	private final double RHO = 0.1;
	private double eta;

	private Random rnd;

	private double [][] rastro;
	private int mejorColoracion;

	private int cardinalidad;

	public ColoniaHormigas(Grafica G) {
		this.G = G;
		this.rnd = new Random();
		
		this.cardinalidad = this.G.cardinalidadVertices();
		this.rastro = new double [this.cardinalidad][this.cardinalidad];
		this.mejorColoracion = Integer.MAX_VALUE;
	}

	public ColoniaHormigas(Grafica G, int ciclos) {
		this.G = G;
		this.rnd = new Random();
		
		this.cardinalidad = this.G.cardinalidadVertices();
		this.rastro = new double [this.cardinalidad][this.cardinalidad];
		this.mejorColoracion = Integer.MAX_VALUE;
		this.NCICLOS = ciclos;

	}

	public ColoniaHormigas(Grafica G, int ciclos, int hormigas) {
		this.G = G;
		this.rnd = new Random();
		
		this.cardinalidad = this.G.cardinalidadVertices();
		this.rastro = new double [this.cardinalidad][this.cardinalidad];
		this.mejorColoracion = Integer.MAX_VALUE;
		this.NCICLOS = ciclos;
		this.NANTS = hormigas;
	}

	private void color_vertice(Vertice i, int k) {
		this.X.remove(i);
		ArrayList<Vertice> temp;
		if (!this.C.isEmpty()) {
			temp = this.C.get(k);
			temp.add(i);
			this.C.set(k, temp);
		} else {
			temp = new ArrayList<Vertice>();
			temp.add(i);
			this.C.add(temp);
		}

		this.F.removeAll(vecinosEnF(i));
	}

	private ArrayList<Vertice> vecinosEnF(Vertice i) {
		ArrayList<Vertice> vecinos = new ArrayList<Vertice>();
		Vertice temp;
		for (Arista a : this.G.obtenerAristas()) {
			temp = a.obtenerAdyacente(i);
			if (temp != null && this.F.contains(temp)) {
				vecinos.add(temp);
			}
		}

		eta = vecinos.size();
		vecinos.add(i);
		return vecinos;
	}

	private Vertice seleccionEnF() {
		if (this.F.size() == 1) {
			return this.F.get(0);
		}
		double prob = 1.0 / this.F.size();
		int seleccion = 0;
		while (Math.random() < prob) {
			seleccion = rnd.nextInt(this.F.size());
		}

		return this.F.get(seleccion);
	}

	private Vertice seleccionEnPik(Vertice i, int k) {
		double tau = obtenerTau(i, k);
		tau = Math.pow(tau, ALPHA);
		eta = Math.pow(eta, BETA);
		double P = tau * eta;
		double divisor = 0;
		for (Vertice j : this.F) {
			tau = obtenerTau(j, k);
			tau = Math.pow(tau, ALPHA);
			eta = Math.pow(eta, BETA);
			divisor += tau * eta;
		}
		if (divisor != 0)
			P /= divisor;
		while (P > 1)
			P /= 10;
		int seleccion = 0;
		while (Math.random() < P) {
			seleccion = rnd.nextInt(this.F.size());
		}
		return this.F.get(seleccion);
	}

	private double obtenerTau(Vertice i, int k) {
		double total = 0.0;
		int indice_i = this.G.indiceDeVertice(i);

		for (Vertice j : this.C.get(k)) {
			total += rastro[indice_i][this.G.indiceDeVertice(j)];
		}
		return total / this.C.get(k).size();
	}

	public void antcol() {
		
		System.out.print("Número de ciclos " + NCICLOS);
		System.out.println(", Número de hormigas " + NANTS);

		double [][] rastro_actualizado = new double [this.cardinalidad][this.cardinalidad];
		int k;


		for (int i = 0; i < this.cardinalidad; i++)
			for (int j = 0; j < this.cardinalidad; j++)
				if (i != j)
					rastro[i][j] = 1;

		for (int ciclo = 0; ciclo < NCICLOS; ciclo++) {

			System.out.println("iteracion " + ciclo);
			for (int ant = 0; ant < NANTS; ant++) {
				this.X = new ArrayList<Vertice>(this.G.obtenerVertices());
				k = 0;
				this.C = new ArrayList<ArrayList<Vertice>>();
				while (!this.X.isEmpty()) {
					this.C = new ArrayList<ArrayList<Vertice>>(C);
					this.C.add(new ArrayList<Vertice>());
					this.F = new ArrayList<Vertice>(this.X);
					Vertice i = seleccionEnF();
					color_vertice(i, k);
					while (!this.F.isEmpty()) {
						i = seleccionEnPik(i, k);
						color_vertice(i, k);
					}
					k++;
				}
				mejorColoracion = k;
				for (int a = 0; a < this.cardinalidad; a++) {
					Vertice i = this.G.obtenerVertice(a);
					for (int b = 0; b < this.cardinalidad && a != b; b++) {
						Vertice j = this.G.obtenerVertice(b);
						for (int m = 0; m < k; m++)
							if (this.C.get(m).contains(i) && this.C.get(m).contains(j))
								rastro_actualizado[a][b] += 1.0 / k;
					}
				}
			}
			for (int i = 0; i < this.cardinalidad; i++) {
				for (int j = 0; j < this.cardinalidad && i != j; j++) {
						rastro[i][j] = RHO * rastro[i][j] + rastro_actualizado[i][j];
						rastro_actualizado[i][j] = 0;
				}
			}
		}
	}

	public void mejorColoracion() {
		System.out.println("Número de colores usados " + this.mejorColoracion);
	}
	
	public static void main(String [] args) {
		Parser parser;
		int ciclos = -1;
		int hormigas = -1;
		switch (args.length) {
			case 1:
				parser = new Parser(args[0]);
				break;
			case 2:
				parser = new Parser(args[0]);
				ciclos = Integer.parseInt(args[1]);
				break;
			case 3:
				parser = new Parser(args[0]);
				ciclos = Integer.parseInt(args[1]);
				hormigas = Integer.parseInt(args[2]);
				break;
			default:
				parser = new Parser("grafica.graf");
		}
		Grafica G = parser.obtenerGrafica();
		System.out.println("Entrada " + G.cardinalidadVertices() + " vértices, " + G.cardinalidadAristas() + " aristas.");
		ColoniaHormigas ch;
		if (ciclos > -1) {
			if (hormigas > -1) {
		 		ch = new ColoniaHormigas(G, ciclos, hormigas);
		 	} else {
		 		ch = new ColoniaHormigas(G, ciclos);
		 	}
		} else {
			ch = new ColoniaHormigas(G);
		}
		ch.antcol();
		ch.mejorColoracion();
	}
}