package eda.main;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import graphsDSESIUCLM.*;

import campusvirtual.DecoratedElement;
import eda.characters.Character;
import eda.grafo.*;
import eda.io.*;

public class Main {

	private static Scanner TECLADO = new Scanner(System.in);
	
	public static void main(String[] args) {
		try {
			boolean encontrado;
			int opcion;
			List<Vertex<DecoratedElement<Character>>> vertices;

			Graph<DecoratedElement<Character>, DecoratedElement<String>> grafo = new TreeMapGraph<DecoratedElement<Character>, DecoratedElement<String>>();
			CharactersFileReader.process_file("data/characters.csv", grafo);
			RelationsFileReader.process_file("data/relations.csv", grafo);

			System.out.printf("AparatadoA\nPersonajes: %d\nRelaciones: %d\n", grafo.getN(), grafo.getM());
			vertices = Utils.apartadoA(grafo);
			System.out.printf("Personaje con más relaciones: %s\nPersonaje con menos relaciones: %s\nPersonaje con más relaciones positivas (%d): %s\n", vertices.get(1).getElement().getElement().getName(), vertices.get(0).getElement().getElement().getName(), Utils.calcularRelaciones(vertices.get(2), grafo)[1], vertices.get(2).getElement().getElement().getName());

			do {
				System.out.printf("\nSelecciona una opción:\n0. Salir\n1. Apartado B\n2. Apartado C\n");
				opcion = Integer.parseInt(TECLADO.nextLine());
				
				switch (opcion) {
				case 0: { break; }
				case 1: case 2: {
					vertices = pedirCharacters(grafo);
					vertices.get(0).getElement().setParent(null);
					vertices.get(0).getElement().setVisited(true);

					encontrado = opcion == 1 ? Algorithms.DFS(grafo, vertices.get(0), vertices.get(1)) : Algorithms.BFS(grafo, vertices.get(0), vertices.get(1));
				
					if (encontrado) {
						System.out.println(Utils.deshacerCamino(vertices.get(1).getElement()));
					} else {
						System.out.println("No se ha encontrado camino");
					}
						
					break;
				}
				default:
					System.out.println("Se introdujo una opción inesperada");
				}
				
				Iterator<Vertex<DecoratedElement<Character>>> nodos = grafo.getVertices();
				while (nodos.hasNext()) {
					nodos.next().getElement().setVisited(false);
				}
			} while (opcion != 0);
		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado un fichero");
		} catch (NumberFormatException e) {
			System.err.println("Se introdujo un dato indebido");
		} catch (Exception e) {
			System.err.println("Ocurrió un error inesperado" + e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Pide al usuario dos personajes diferentes contenidos en el grafo, el primero como inicio y el segundo como fin
	 * @param grafo donde se buscan los personajes
	 * @return [inicio, fin] Lista de Vertices donde el primer vertice representa el vertice de inicio, y el siguiente, el fin
	 */
	public static List<Vertex<DecoratedElement<Character>>> pedirCharacters(Graph<DecoratedElement<Character>, DecoratedElement<String>> grafo) {
		List<Vertex<DecoratedElement<Character>>> resultado = new ArrayList<Vertex<DecoratedElement<Character>>>();
		Vertex<DecoratedElement<Character>> source = null, target = null;
		boolean continuar = false;
		do {
			System.out.print("Introduce el personaje inicial: ");
			String p1 = TECLADO.nextLine();
			System.out.print("Introduce el personaje final: ");
			String p2 = TECLADO.nextLine();

			if (p1.equals(p2)) {
				System.out.println("Los personajes no pueden ser iguales");
			} else {

				Iterator<Vertex<DecoratedElement<Character>>> vertices = grafo.getVertices(); 
				while (vertices.hasNext() && (source == null || target == null)) { 
					Vertex<DecoratedElement<Character>> vertice = vertices.next();
					
					source = p1.equalsIgnoreCase(vertice.getElement().getElement().getName()) ? vertice : source;
					target = p2.equalsIgnoreCase(vertice.getElement().getElement().getName()) ? vertice : target;
				}

				if (source == null || target == null) {
					System.out.println("Algun personaje no se ha encontrado");
				} else {
					continuar = true;
				}
			}
		} while (!continuar);
		
		resultado.add(source);
		resultado.add(target);

		return resultado;
	}
	
}
