package eda.grafo;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import campusvirtual.DecoratedElement;
import graphsDSESIUCLM.Edge;
import graphsDSESIUCLM.Graph;
import graphsDSESIUCLM.Vertex;

import eda.characters.Character;

/**
 * Clase para realizar los recorridos de los grafos
 */
public class Algorithms {

	/**
	 * Algoritmo en profundidad para buscar un camino entre dos nodos asegurando que estos cumplan unos requisitos
	 * 
	 * @param grafo Grafo que nos indica las relaciones entre los nodos
	 * @param inicio Nodo del que se parte
	 * @param fin Nodo al que se desea llegar
	 * @return Si se ha llegado al fin o no
	 */
	public static boolean DFS(Graph<DecoratedElement<Character>, DecoratedElement<String>> grafo, Vertex<DecoratedElement<Character>> inicio, Vertex<DecoratedElement<Character>> fin) {
		boolean encontrado = fin.getElement().equals(inicio.getElement());

		Iterator<Edge<DecoratedElement<String>>> aristas_incidentes = grafo.incidentEdges(inicio);
		while (!encontrado && aristas_incidentes.hasNext()) {
			Edge<DecoratedElement<String>> arista_actual = aristas_incidentes.next();
			Vertex<DecoratedElement<Character>> nodo_siguiente = grafo.opposite(inicio, arista_actual);
			
			// si no está visitado
			if (!nodo_siguiente.getElement().getVisited()) {
				// lo marcamos a visitar, con su padre correspondiente
				nodo_siguiente.getElement().setVisited(true);
				nodo_siguiente.getElement().setParent(inicio.getElement());
				Character personaje_elegible = nodo_siguiente.getElement().getElement();

				encontrado = nodo_siguiente.getElement().equals(fin.getElement());
				
				if (!encontrado &&
				// si se cumplen las condiciones, se incluye en el camino
					arista_actual.getElement().getElement().equals("+") &&
					personaje_elegible.isWizard() &&
					personaje_elegible.getYearOfBirth() > 1975 &&
					(personaje_elegible.getSpecies().equals("human") || personaje_elegible.getSpecies().equals("half-giant"))
				) {
					encontrado = DFS(grafo, nodo_siguiente, fin);
				}
			}
		}
		
		return encontrado;
	}

	/**
	 * Algoritmo en anchura para buscar un camino entre dos nodos asegurando que estos cumplan unos requisitos
	 * 
	 * @param grafo Grafo que nos indica las relaciones entre los nodos
	 * @param inicio Nodo del que se parte
	 * @param fin Nodo al que se desea llegar
	 * @return Si se ha llegado al fin o no
	 */
	public static boolean BFS(Graph<DecoratedElement<Character>, DecoratedElement<String>> grafo, Vertex<DecoratedElement<Character>> inicio, Vertex<DecoratedElement<Character>> fin) {
		boolean encontrado = fin.getElement().equals(inicio.getElement());
		
		Queue<Vertex<DecoratedElement<Character>>> vertices = new LinkedBlockingQueue<Vertex<DecoratedElement<Character>>>();
		vertices.add(inicio);
		
		while (!vertices.isEmpty() && !encontrado) {
			Vertex<DecoratedElement<Character>> nodo_actual = vertices.poll();
			
			Iterator<Edge<DecoratedElement<String>>> aristas_incidentes = grafo.incidentEdges(nodo_actual);
			while (!encontrado && aristas_incidentes.hasNext()) {
				Edge<DecoratedElement<String>> aritsta_actual = aristas_incidentes.next();
				Vertex<DecoratedElement<Character>> nodo_siguiente = grafo.opposite(nodo_actual, aritsta_actual);

				// si no está visitado
				if (!nodo_siguiente.getElement().getVisited()) {
					// lo marcamos a visitar, con su padre correspondiente
					nodo_siguiente.getElement().setVisited(true);
					nodo_siguiente.getElement().setParent(nodo_actual.getElement());
					Character personaje_elegible = nodo_siguiente.getElement().getElement();

					encontrado = nodo_siguiente.getElement().equals(fin.getElement());

					if (!encontrado &&
					// si se cumplen las condiciones, se incluye en el camino
						personaje_elegible.isAlive() &&
						!personaje_elegible.getHouse().equals("") &&
						!personaje_elegible.getAncestry().equals("muggle") &&
						!personaje_elegible.getHairColour().equals("black")
					) {
						vertices.add(nodo_siguiente);
					}
				}
			}
		}
		return encontrado;
	}

}
