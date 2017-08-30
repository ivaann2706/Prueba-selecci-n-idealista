package clases;
import java.util.ArrayList;

public class Drone {
	//Atributo
	private Ciudad ciudad;
	
	//Constructor
	public Drone(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	
	//Este m�todo devuelve la id correspondiente a las coordenadas x e y introducidas.
	//En el caso que estas coordenadas no correspondan a ninguna id devolver� un -1.
	public int obtenerIdentificadorUrbanizaci�n(double x, double y){
		int id=-1;
		int n =this.ciudad.getUrbanizaciones().length;
		
		for (int i = 0; i < n; i++) {	
			for (int j = 0; j < n; j++) {	
				if(this.ciudad.getUrbanizaciones()[i][j].getCoordenadaX() == x && this.ciudad.getUrbanizaciones()[i][j].getCoordenadaY() == y){
					id=this.ciudad.getUrbanizaciones()[i][j].getId();
				}
			}
		}	
		return id;
	}

	//Este m�todo devuelve la id adyacente a la introducida por par�metro con la direcci�n correspondiente
	public int obtenerAdyacente(int id, String direccion){
		int idAdyacete = 0;
		int n = this.ciudad.getUrbanizaciones().length;
		
		int dir = 0;
		switch(direccion){
			case "arriba": 
					dir=-n;
				break;
			case "abajo": 
					dir=n;
				break;
			case "derecha": 
					dir=1;
				break;
			case "izquierda":
					dir=-1;
				break;
		}
		idAdyacete = id+dir;		
		return idAdyacete;
	}
	
	
	public ArrayList<Integer> obtenerUrbanizaciones(double x, double y, int rango){
		//Lista de n�meros enteros donde se almacenar�n los ids de las distintas urbanizaciones 
		//que el drone tendr� que recorrer.
		ArrayList<Integer> ids = new ArrayList<>(); 
		//id de la urbanizaci�n que se obtiene a trav�s de las coordenadas introducidas por par�metro.
		int idUrbanizacion = this.obtenerIdentificadorUrbanizaci�n(x,y);
		
		//si la id Urbanizaci�n obtenida es -1, significar� que no existe urbanizaci�n con tal coordenadas,
		//por lo que se mandar� un mensaje indicativo
		if(idUrbanizacion>0){
			System.out.println("Las coordenadas ("+x+","+y+") corresponden a la "
					+ "urbanizaci�n con id="+idUrbanizacion);
			
			//N� de filas y columnas que posee la matriz de urbanizaciones
			int n = this.ciudad.getUrbanizaciones().length;
			
			//Las siguientes l�neas de c�digo sirven para calcular el n�mero de veces que podemos desplazar
			//el drone desde la id obtenida hacia la derecha, izquierda, arriba y abajo, dependiendo del
			//rango indicado. El n�mero de desplazamientos se almacenar�n en las variables:
			//desplazamientosDerecha, desplazamientosIzquierda, desplazamientosArriba y desplazamientosAbajo.
			
			//Desplazamientos hacia la derecha
			int suma = n;
			while(idUrbanizacion>suma){
				suma += n;
			}

			int hastaExtremoDerecho = suma - idUrbanizacion; 
			int hastaExtremoIzquierdo = idUrbanizacion - (suma-n+1);
			
			int desplazamientosDerecha = rango;
			if(hastaExtremoDerecho <= rango){
				desplazamientosDerecha=hastaExtremoDerecho; 
			}
			//Desplazamientos hacia la izquierda
			int desplazamientosIzquierda = rango;
			if(hastaExtremoIzquierdo <= rango){
				desplazamientosIzquierda=hastaExtremoIzquierdo;// 
			}
			//Desplazamientos hacia abajo
			suma = n;
			while(idUrbanizacion>suma){
				suma += n;
			}
			int hastaExtremoAbajo = (n*n -suma)/n;
			int hastaExtremoArriba = (suma-n)/n;
			
			int desplazamientosAbajo = rango;
			if(hastaExtremoAbajo <= rango){
				desplazamientosAbajo=hastaExtremoAbajo;
			}
			
			//Desplazamientos hacia arriba
			int desplazamientosArriba = rango;
			if(hastaExtremoArriba <= rango){
				desplazamientosArriba=hastaExtremoArriba;
			}

			/*
			 * Para obtener la lista de ids se ha optado por realizar el siguiente procedimiento:
			 * En un primer momento colocamos el drone en la esquina superior izquierda.
			 * Para ello hacemos uso de la funci�n "obtenerAdyacente(int,String)" anteriormente creada,
			 * tantas veces como los desplazamientos anteriormente calculados indiquen. Una vez en ese
			 * punto, recorremos la fila mediante un for tantas veces como indique la suma entre los 
			 * desplazamientos derechos e izquierdos. En cada vuelta a�adimos la id de la urbanizaci�n
			 * en la que se encuentra el drone y seguidamente lo desplazamos hacia la derecha.Una vez
			 * acabe el bucle for, desplazamos el drone hacia la fila de abajo y al extremo izquierdo, 
			 * para volver a recorrer la fila de nuevo. As� sucesivamente, mientras lo indique las 
			 * variables desplazamientos arriba y abajo.
			 */
			
			//Situamos el drone en la esquina superior izquierda deseada.
			int i = 0;
			int id=idUrbanizacion;
			for(i=0; i<desplazamientosIzquierda; i++){
				id = obtenerAdyacente(id, "izquierda");
			}
			
			for(i=0; i<desplazamientosArriba; i++){
				id = obtenerAdyacente(id, "arriba");
			}
			
			//recorremos arriba abajo las filas
			int idInicioIzquierda=id;
			for(i=0; i<desplazamientosAbajo+desplazamientosArriba+1; i++){
				//recorremos de izquierda a derecha
				for(int j=0; j<desplazamientosDerecha+desplazamientosIzquierda+1; j++){
					ids.add(id); //a�adimos las ids en la lista.
					id=obtenerAdyacente(id, "derecha");
				}
				//situamos el drone en la fila de abajo y en el extremo izquierdo
				id = idInicioIzquierda;
				id=obtenerAdyacente(id, "abajo");
				idInicioIzquierda = id;
			}
		}else{
			//si la urbanizaci�n no existe, se devuelve el siguiente mensaje por la consola.
			System.err.println("No existe urbanizaci�n con coordenadas ("+x+","+y+")"); 
		}
		return ids;
	}
}