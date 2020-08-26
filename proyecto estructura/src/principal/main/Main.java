package principal.main;

import Proyecto.Simulacion.Simulacion;

public class Main {

	public static void main(String[] args) {
		System.out.println("****************Bienvenido************");
		for (int i = 3; i < 9; i++) {
			Simulacion S = new Simulacion();
			S.setMostrador(i);
			System.out.println("Simulacion: " + (i - 2)+ " con "+ i +" cajas");
			S.Dato();
		}
	}

}
