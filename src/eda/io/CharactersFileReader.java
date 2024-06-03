package eda.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import graphsDSESIUCLM.Graph;
import campusvirtual.DecoratedElement;

import eda.characters.Character;

/**
 * Clase encargada de la lectura de los ficheros de personajes
 */
public class CharactersFileReader {
	
	/**
	 * Función para cambiar String a boolean
	 * 
	 * @param bool Representación en cadena del boolean
	 * @return resultado de la conversión
	 */
	private static boolean parseBoolean(String bool) {
		boolean result = false;
		
		if (bool.equals("VERDADERO")) {
			result = true;
		} else if (bool.equals("FALSO")) {
			result = false;
		}
		
		return result;
	}
	
	/**
	 * Lee todo el fichero con el formato que se ha indicado
	 * Añade personajes como nodos al grafo
	 * 
	 * @param filename nombre del fichero a abrir
	 * @param grafo Grafo donde añadir los personajes
	 * @throws FileNotFoundException Si no se encuentra el archivo
	 */
	public static void process_file(String filename, Graph<DecoratedElement<Character>, DecoratedElement<String>> grafo) throws FileNotFoundException {
		File f = new File(filename);
		Scanner s = new Scanner(f);
		
		DecoratedElement<Character> personaje_decorado;
		Character personaje;
		
		s.nextLine();
		
		while (s.hasNextLine()) {
			String[] datos = s.nextLine().split(";");
			
			String id = datos[0];

			personaje = new Character(
					datos[1],
					datos[2],
					datos[3],
					datos[4],
					datos[5],
					datos[6].equals("") ? - 1 : Integer.parseInt(datos[6]),
					parseBoolean(datos[7]),
					datos[8],
					datos[9],
					datos[10],
					datos[11],
					parseBoolean(datos[12])
			);
			
			personaje_decorado = new DecoratedElement<Character>(id, personaje);
			
			grafo.insertVertex(personaje_decorado);
		}

		s.close();
	}

}
