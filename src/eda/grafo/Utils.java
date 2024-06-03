package eda.grafo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import campusvirtual.DecoratedElement;
import graphsDSESIUCLM.Edge;
import graphsDSESIUCLM.Graph;
import graphsDSESIUCLM.Vertex;

import eda.characters.Character;

/**
 * Clase de utilidades varias
 */
public class Utils {

	/**
	 * Obtiene el nodo correspondiente a aquel con menos, mas y mas relaciones positivas
	 * @param grafo Grafo del cual leeremos los datos
	 * @return [min, max, max_positive] Lista de vértices, donde las posiciones son los vértices con menos, mas relaciones positivas respectivamente
	 */
	public static List<Vertex<DecoratedElement<Character>>> apartadoA(Graph<DecoratedElement<Character>, DecoratedElement<String>> grafo) {
		Vertex<DecoratedElement<Character>> min, max, max_positive;
		int min_v, max_v, max_positive_v;

		Iterator<Vertex<DecoratedElement<Character>>> it = grafo.getVertices();
		
		min = max = max_positive = it.next();

		int[] valores = calcularRelaciones(max_positive, grafo);
		
		min_v = max_v = valores[0];
		max_positive_v = valores[1];
		
		while (it.hasNext()) {
			Vertex<DecoratedElement<Character>> actual = it.next();
			
			valores = calcularRelaciones(actual, grafo);

			if (valores[0] < min_v) {
				min = actual;
				min_v = valores[0];
			} else if (valores[0] > max_v) {
				max = actual;
				max_v = valores[0];
			}
			
			if (valores[1] > max_positive_v) {
				max_positive = actual;
				max_positive_v = valores[1];
			}
		}

		List<Vertex<DecoratedElement<Character>>> resultado = new ArrayList<Vertex<DecoratedElement<Character>>>();
		resultado.add(min);
		resultado.add(max);
		resultado.add(max_positive);
		return resultado;
	}
	
	/**
	 * Calcula las relaciones y las relaciones positivas de un vertice
	 * @param nodo nodo del cual se cuentas las relaciones
	 * @param grafo grafo que nos da las aristas
	 * @return [cuenta, cuenta_positivas] Array de enteros que representan la cuenta de relaciones y la cuenta de relaciones positivas respectivamente
	 */
	public static int[] calcularRelaciones(Vertex<DecoratedElement<Character>> nodo, Graph<DecoratedElement<Character>, DecoratedElement<String>> grafo) {
		int cuenta = 0;
		int cuenta_positivas = 0;
		
		Iterator<Edge<DecoratedElement<String>>> it = grafo.incidentEdges(nodo);
		
		while (it.hasNext()) {
			String tipo = it.next().getElement().getElement();
			cuenta++;
			if (tipo.equals("+")) {
				cuenta_positivas++;
			}
		}
		
		return new int[]{cuenta, cuenta_positivas};
	}

	/**
	 * Función para mostrar los padres de un elemento
	 * 
	 * @param elemento a partir del cual se empieza a deshacer el camino
	 * @return Cadena con la representación de cada elemento por línea en orden inicio a fin de aquellos elementos que pertenecen al camino
	 */
	public static String deshacerCamino(DecoratedElement<Character> elemento) {
		String resultado = "";
		if (elemento.getParent() != null) {
			resultado = deshacerCamino(elemento.getParent());
		}
		resultado += "\n" + elemento.getID() + elemento.getElement();
		return resultado;
	}
	
}
