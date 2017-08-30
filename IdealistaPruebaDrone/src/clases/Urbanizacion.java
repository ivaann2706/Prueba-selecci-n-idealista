package clases;

public class Urbanizacion {
	//Atributos
	private int id;
	private double coordenadaX;
	private double coordenadaY;
	
	//Constructor
	public Urbanizacion(int id, double coordenadaX, double coordenadaY) {
		this.id = id;
		this.coordenadaX = coordenadaX;
		this.coordenadaY = coordenadaY;
	}	
	
	//Métodos
	public int getId() {
		return id;
	}
	
	public double getCoordenadaX() {
		return coordenadaX;
	}


	public double getCoordenadaY() {
		return coordenadaY;
	}

	@Override
	public String toString() {
		return "" + this.id + "(" + this.coordenadaX + "," + this.coordenadaY + ")";
	}
	
}
