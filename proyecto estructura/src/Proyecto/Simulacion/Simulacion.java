package Proyecto.Simulacion;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;

import Nodo.persona.Nodo;

public class Simulacion extends SimulacionProceso {
	public void Dato() {

		try {
			Scanner lector = new Scanner(new File("src/simulacion.txt"));
			while (lector.hasNextLine()) {
				String[] datos = lector.nextLine().split(",");
				super.persona += ((Integer.parseInt(datos[0]) + (Integer.parseInt(datos[1]))));
				super.ColaPrioridad(Integer.parseInt(datos[1]), 0, Integer.parseInt(datos[1]) == 0 ? true : false);

				super.ColaOrdinaria(Integer.parseInt(datos[0]),
						super.raiz == null && super.mostrador[0] == 0 ? 0 : 1,
						Integer.parseInt(datos[0]) == 0 ? true : false);
			}
			lector.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ImprimirDatos();

	}

	private void ImprimirDatos() {
		System.out.println("Personas atendidas: " + contadopersonas);
		System.out.println(("El promedio de tiempo de esperar en la cola es : " + LocalTime.MIN.plus(Duration.ofMinutes(
				(long) (Math.round(((super.TEC) / (double) contadopersonas) * Math.pow(10, 2)) / Math.pow(10, 2))))
				.toString() + " Horas"));
		System.out.println("promedio de tramites : " + (int) (comprobar / contadopersonas) + " Minutos");
		System.out.println("timepo total de promedio :" + LocalTime.MIN
				.plus(Duration.ofMinutes(
						(long) (Math.round((comprobar + TEC) / (contadopersonas) * Math.pow(10, 2)) / Math.pow(10, 2))))
				.toString() + " Horas");
		System.out.println("las personas que no pudieron hacer su tramite son: " + personasCola
				+ "\n Y el tiempo promedio de espera de esas personas fueron: "
				+ LocalTime.MIN.plus(Duration.ofMinutes((long) (Math
						.round(((double) (tiempodeTEC(raiz, raizCO)) / (double) personasCola) * Math.pow(10, 2))
						/ Math.pow(10, 2)))).toString()
				+ " Horas");
		System.out
				.println("tiempo promedio de espera total: "
						+ LocalTime.MIN.plus(Duration.ofMinutes(
								(long) (Math.round(((double) (tiempodeTEC(raiz, raizCO) + (super.TEC / contadopersonas))
										/ (double) persona) * Math.pow(10, 2)) / Math.pow(10, 2))))
								.toString()
						+ " Horas");
		System.out.println();
		
	}

	private int tiempodeTEC(Nodo raiz, Nodo raizCO) {
		if (raiz != null) {
			super.TPC += raiz.dato.getTEC();
			tiempodeTEC(raiz.siguiente, raizCO);

		} else if (raizCO != null) {
			super.TPC += raizCO.dato.getTEC();
			tiempodeTEC(raiz, raizCO.siguiente);
		}
		return TPC;
	}
}
