package clases;

public class Ciudad {
	//Atributo
	private Urbanizacion[][] urbanizaciones; //Una ciudad tendrá una matriz bidimensional de urbanizaciones.
	
	//El tamaño de dicha ciudad se definirá en el constructor, que recibirá un tipo entero como parámetro, que será el ancho de la ciudad
	//El area de esta ciudad se definirá como "ancho x ancho", obteniendose así un cuadrado.
	public Ciudad(int anchoCiudad){
		this.urbanizaciones = urbanizacionesAleatorias(anchoCiudad);
	}
	

	//Este método devolverá el conjunto de urbanizaciones que tiene la ciudad en una madrid bidimensional de tipo Urbanizacion,
	//de tal forma que la primera urbanización tendrá el índice 1 y coordenadas (0,0) y la ultima urbanización tendrá índice resultante de la multiplicacion ancho por ancho y 
	//coordenadas(ancho-1,ancho-1)
	public Urbanizacion[][] urbanizacionesAleatorias(int anchoCiudad){
		Urbanizacion[][] ciudad = new Urbanizacion[anchoCiudad][anchoCiudad];
		int k=1;
		for (int i = 0; i < anchoCiudad; i++) {	
			for (int j = 0; j < anchoCiudad; j++) {	
				int id = k;
				double coordenadaX =i; 
				double coordenadaY =j; 
				
				Urbanizacion u = new Urbanizacion(id,coordenadaX,coordenadaY);
				
				ciudad[i][j] = u;
				k++;
			}
		}
		
		return ciudad;
	}
	
	public Urbanizacion[][] getUrbanizaciones() {
		return urbanizaciones;
	}
	
	//Este método devolverá la urbanización correspondiente que tenga como id el número entero introducido por parámetro.
	//En caso de no existir tal urbanización, devolverá null
	public Urbanizacion getUrbanizacion(int id){
		Urbanizacion u = null;
		for (int i = 0; i < this.urbanizaciones.length; i++) {
			for (int j= 0; j < this.urbanizaciones.length; j++) {
				if(this.urbanizaciones[i][j].getId() == id){
					u= new Urbanizacion(id,i,j);
				}
			}	
		}
		return u;
	}
	
	@Override
	public String toString() {
		String res="";
		int n = urbanizaciones.length;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				res+="\t"+this.urbanizaciones[i][j];
			}
			res+="\n";
		}
		return res;
	}
	
}
