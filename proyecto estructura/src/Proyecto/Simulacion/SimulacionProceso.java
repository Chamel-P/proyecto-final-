package Proyecto.Simulacion;

import Nodo.persona.Nodo;
import Variables.VariableYMetodos;
import personas.Personas;

public class SimulacionProceso extends VariableYMetodos {

	protected void ColaPrioridad(int personas, int i, boolean vacio) {

		if (personas != 0 && super.raiz == null) {

			if (super.mostrador[0] == 0) {
				VacioPrimeraOpcion(personas, i, mostrador);
				return;
			} else {
				VacioSegundoOpcion(i, mostrador);
				if (mostrador[0] == 0) {
					VacioPrimeraOpcion(personas, i, mostrador);
					return;
				} else {
					MeterColaP(personas, new Personas(0));
					return;
				}
			}
		} else if (personas != 0 && super.raiz != null) {
			if (mostrador[0] == 0) {
				LlenoPrimeraOpcion(personas, i, mostrador);
				return;
			} else if (mostrador[0] != 0) {
				VacioSegundoOpcion(i, mostrador);
				if (mostrador[0] == 0) {
					LlenoPrimeraOpcion(personas, i, mostrador);
					return;
				} else {
					TEC(raiz);
					MeterColaP(personas, new Personas(0));
					return;
				}
			}
		} else if (vacio == true && mostrador[0] != 0) {
			VacioSegundoOpcion(i, mostrador);
			if (mostrador[0] == 0 && raiz != null) {
				LlenoPrimeraOpcion(personas, i, mostrador);
			} else if (mostrador[0] != 0 && raiz != null)
				TEC(raiz);
		}
	}

	protected void ColaOrdinaria(int personas, int i, boolean b) {

		if (personas != 0 && super.raizCO == null) {
			if (i < mostrador.length) {
				if (super.mostrador[i] == 0) {

					COPrimeraOpcion(i, personas, b);
					return;
				} else {
					VacioSegundoOpcion(i, mostrador);
					if (mostrador[i] == 0) {
						COPrimeraOpcion(i, personas, b);
						return;
					} else {
						ColaOrdinaria(personas, ++i, b);
						return;
					}
				}
			} else if (i >= mostrador.length && personas != 0) {
				MeterCola(personas, new Personas(0));
				return;
			}

		} else if (personas != 0 && super.raizCO != null) {
			if (i < mostrador.length) {
				if (super.mostrador[i] == 0) {
					LlenoPrimeraOpcionNormal(personas, i, mostrador, b);
					b = personas == 0 ? true : false;
					ColaOrdinaria(personas, ++i, b);
				} else {
					VacioSegundoOpcion(i, mostrador);
					if (mostrador[i] == 0) {

						LlenoPrimeraOpcionNormal(personas, i, mostrador, b);
						return;
					} else {
						ColaOrdinaria(personas, ++i, b);
						return;
					}
				}
			} else if (i >= mostrador.length && personas != 0) {
				TEC(raizCO);
				MeterCola(personas, new Personas(0));
				return;
			}
		} else if (b == true) {
			if (i < mostrador.length) {

				if (super.mostrador[i] == 0) {
					if (super.raizCO != null) {
						LlenoPrimeraOpcionNormal(personas, i, mostrador, b);
						return;
					} else {
						ColaOrdinaria(personas, ++i, b);
						return;
					}
				} else {
					VacioSegundoOpcion(i, mostrador);
					if (super.mostrador[i] == 0) {
						if (super.raizCO != null) {
							LlenoPrimeraOpcionNormal(personas, i, mostrador, b);
							return;
						} else {
							ColaOrdinaria(personas, ++i, b);
							return;
						}
					} else {
						ColaOrdinaria(personas, ++i, b);
						return;
					}
				}
			} else {
				TEC(raizCO);
			}
		}
	}

//////////Metodos de cola de Normal////////////
	private void COPrimeraOpcion(int i, int personas, boolean b) {
		mostrador[i] = super.TiempoPersona();
		contadopersonas++;
		ColaOrdinaria(--personas, ++i, b = personas == 0 ? true : false);

	}

	private void LlenoPrimeraOpcionNormal(int personas, int i, int[] mostrador, boolean b) {
		super.TEC += raizCO.dato.getTEC();
		raizCO = raizCO.siguiente;
		personasCola -= 1;
		mostrador[i] = super.TiempoPersona();
		contadopersonas++;
		ColaOrdinaria(personas, ++i, b);

	}

	private void MeterCola(int personas, Personas personas2) {
		if (personas != 0) {
			Nodo nuevo = new Nodo();
			nuevo.dato = personas2;
			if (super.raizCO == null) {
				super.raizCO = nuevo;
				personasCola += 1;
			} else {
				recorrer(nuevo, super.raizCO);
			}
			MeterCola(--personas, new Personas(0));
		}
	}

	////////////// Metodos de cola
	////////////// privilegios//////////////////////////////////////////////
	private void VacioPrimeraOpcion(int personas, int i, int[] mostrador) {
		mostrador[i] = super.TiempoPersona();
		contadopersonas++;
		MeterColaP(--personas, new Personas(0));
	}

	private void VacioSegundoOpcion(int i, int[] mostrador) {
		mostrador[i] -= 1;
		super.TDS += 1;
	}

	private void LlenoPrimeraOpcion(int personas, int i, int[] mostrador) {
		super.TEC += raiz.dato.getTEC();
		raiz = raiz.siguiente;
		personasCola -= 1;
		mostrador[i] = super.TiempoPersona();
		contadopersonas++;
		if (raiz == null) {
			MeterColaP(personas, new Personas(0));
			return;
		} else {
			if (raiz != null) {
				sumar(raiz);
			}
			MeterColaP(personas, new Personas(0));
			return;
		}
	}

	private void TEC(Nodo raiz) {
		if (raiz != null) {
			sumar(raiz);
		}
	}

	private void sumar(Nodo raiz) {
		if (raiz != null) {
			raiz.dato.setTEC(raiz.dato.getTEC() + 1);
			sumar(raiz.siguiente);
		}
	}

	private void MeterColaP(int personas, Personas personas2) {
		if (personas != 0) {
			Nodo nuevo = new Nodo();
			nuevo.dato = personas2;
			if (raiz == null) {
				raiz = nuevo;
				personasCola += 1;
			} else {
				recorrer(nuevo, raiz);
			}
			MeterColaP(--personas, new Personas(0));
		}
	}

	private void recorrer(Nodo nuevo, Nodo raiz) {
		if (raiz.siguiente == null) {
			raiz.siguiente = nuevo;
			super.personasCola++;
		} else {
			recorrer(nuevo, raiz.siguiente);
		}

	}

}
