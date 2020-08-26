package Variables;

import Nodo.persona.Nodo;

public class VariableYMetodos {
	protected double TEC;
	protected double persona;
	protected double TDS;
	protected int mostrador[];
	protected Nodo raiz = null;
	protected Nodo raizCO = null;
	protected double comprobar;
	protected int contadopersonas;
	protected int personasCola;
	protected int TPC;


	protected int TiempoPersona() {
		double persona = Math.random();

		if (persona >= 0 && persona <= 0.30) {
			comprobar += 1;
			return 1;
		} else if (persona >= 0.20 && persona <= 0.4) {
			comprobar += 2;
			return 2;
		} else if (persona >= 0.4 && persona <= 0.6) {
			comprobar += 3;
			return 3;
		} else if (persona >= 0.6 && persona <= 0.8) {
			comprobar += 5;
			return 5;
		} else if (persona >= 0.8 && persona <= 0.9) {
			comprobar += 8;
			return 8;
		} else if (persona >= 0.9 && persona <= 0.95) {
			comprobar += 13;
			return 13;
		}
		int paso = (int) (13 + (13 * Math.random()));
		comprobar += paso;
		return paso;
	}

	public void setMostrador(int i) {
		this.mostrador = new int[i];
	}
}
