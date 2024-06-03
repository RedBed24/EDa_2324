package eda.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

import graphsDSESIUCLM.Graph;
import graphsDSESIUCLM.Vertex;
import campusvirtual.DecoratedElement;

import eda.characters.Character;

/**
 * Clase para la lectura de archivos de aristas
 */
public class RelationsFileReader {
	
	/**
	 * Función que rellena con aristas un grafo a partir del fichero indicado
	 * 
	 * @param filename Nombre del fichero a leer
	 * @param grafo Grafo donde se añadirán las aristas
	 * @throws FileNotFoundException si no se encuentra el fichero especificado
	 */
	public static void process_file(String filename, Graph<DecoratedElement<Character>, DecoratedElement<String>> grafo) throws FileNotFoundException {
		File f = new File(filename);
		Scanner s = new Scanner(f);
		
		String id_source, id_target;
		
		s.nextLine();
		
		int cuenta = 0;
		
		while (s.hasNextLine()) {
			String[] datos = s.nextLine().split(";");

			id_source = datos[0];
			id_target = datos[1];

			Vertex<DecoratedElement<Character>> source = null, target = null;

			Iterator<Vertex<DecoratedElement<Character>>> vertices = grafo.getVertices(); 
			while (vertices.hasNext() && (source == null || target == null)) { 
				Vertex<DecoratedElement<Character>> vertice = vertices.next();
				
				source = id_source.equalsIgnoreCase(vertice.getElement().getID()) ? vertice : source;
				target = id_target.equalsIgnoreCase(vertice.getElement().getID()) ? vertice : target;
			}

			grafo.insertEdge(source, target, new DecoratedElement<String>(cuenta + "", datos[2]));
			cuenta++;
		}

		s.close();
	}

}
