package principal;

import java.util.Scanner;

import clases.Ciudad;
import clases.Drone;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Introduce nº de filas y columnas de urbanizaciones: ");
		int n = scanner.nextInt(); //Introducimos el número por teclado
		
		Ciudad ciudad = new Ciudad(n); //Creamos la ciudad con el número filas y columnas introducido
		System.out.println(ciudad);
		
		Drone drone = new Drone(ciudad);
		
		//Introducimos coordenada X
		System.out.print("Introduce coordenada x: ");
		double coordenadaX = scanner.nextDouble();
		
		//Introducimos coordenada Y
		System.out.print("Introduce coordenada y: ");
		double coordenadaY = scanner.nextDouble();
		
		//Introducimos rango
		System.out.print("Introduce rango: ");
		int rango = scanner.nextInt();
		
		System.out.println("Lista de urbanizaciones que el Drone tiene que visitar:\n"
		+drone.obtenerUrbanizaciones(coordenadaX,coordenadaY,rango));
		
		scanner.close();		
	}

}


