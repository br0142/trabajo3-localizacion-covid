package com.practica.lista;

import com.practica.genericas.FechaHora;
import com.practica.genericas.PosicionPersona;

public class ListaContactos {
	private NodoTemporal lista;
	private int size;
	
	/**
	 * Insertamos en la lista de nodos temporales, y a la vez inserto en la lista de nodos de coordenadas. 
	 * En la lista de coordenadas metemos el documento de la persona que está en esa coordenada 
	 * en un instante 
	 */



	public int personasEnCoordenadas () {
		NodoPosicion aux = this.lista.getListaCoordenadas();
		if(aux==null)
			return 0;
		else {
			int cont;
			for(cont=0;aux!=null;) {
				cont += aux.getNumPersonas();
				aux=aux.getSiguiente();
			}
			return cont;
		}
	}
	
	public int tamanioLista () {
		return this.size;
	}

	public String getPrimerNodo() {
		NodoTemporal aux = lista;
		String cadena = aux.getFecha().getFecha().toString();
		cadena+= ";" +  aux.getFecha().getHora().toString();
		return cadena;
	}

	/**
	 * Métodos para comprobar que insertamos de manera correcta en las listas de 
	 * coordenadas, no tienen una utilidad en sí misma, más allá de comprobar que
	 * nuestra lista funciona de manera correcta.
	 */
	public int numPersonasEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		if(this.size==0)
			return 0;
		NodoTemporal aux = lista;
		int cont = 0;
		cont = 0;
		while(aux!=null) {
			if(aux.getFecha().compareTo(inicio)>=0 && aux.getFecha().compareTo(fin)<=0) {
				NodoPosicion nodo = aux.getListaCoordenadas();
				while(nodo!=null) {
					cont = cont + nodo.getNumPersonas();
					nodo = nodo.getSiguiente();
				}				
				aux = aux.getSiguiente();
			}else {
				aux=aux.getSiguiente();
			}
		}
		return cont;
	}
	
	
	
	public int numNodosCoordenadaEntreDosInstantes(FechaHora inicio, FechaHora fin) {
		if(this.size==0)
			return 0;
		NodoTemporal aux = lista;
		int cont = 0;
		cont = 0;
		while(aux!=null) {
			if(aux.getFecha().compareTo(inicio)>=0 && aux.getFecha().compareTo(fin)<=0) {
				NodoPosicion nodo = aux.getListaCoordenadas();
				while(nodo!=null) {
					cont = cont + 1;
					nodo = nodo.getSiguiente();
				}				
				aux = aux.getSiguiente();
			}else {
				aux=aux.getSiguiente();
			}
		}
		return cont;
	}

	public void insertarNodoTemporal(PosicionPersona p) {
		NodoTemporal aux = lista;
		NodoTemporal ant = null;
		boolean encontrado = false;

		// Find the appropriate position in the list
		while (aux != null && !encontrado) {
			int comparisonResult = aux.getFecha().compareTo(p.getFechaPosicion());
			if (comparisonResult == 0) {
				encontrado = true;
			} else if (comparisonResult < 0) {
				ant = aux;
				aux = aux.getSiguiente();
			} else {
				break;
			}
		}

		// Handle insertion
		if (encontrado) {
			insertIntoCoordinatesList(aux, p);
		} else {
			insertNewNode(ant, p);
			this.size++;
		}
	}

	private void insertIntoCoordinatesList(NodoTemporal aux, PosicionPersona p) {
		NodoPosicion npActual = aux.getListaCoordenadas();
		NodoPosicion npAnt = null;
		boolean npEncontrado = false;

		// Find the appropriate position in the coordinates list
		while (npActual != null && !npEncontrado) {
			if (npActual.getCoordenada().equals(p.getCoordenada())) {
				npEncontrado = true;
				npActual.setNumPersonas(npActual.getNumPersonas() + 1);
			} else {
				npAnt = npActual;
				npActual = npActual.getSiguiente();
			}
		}

		// Insert into the coordinates list
		if (!npEncontrado) {
			NodoPosicion npNuevo = new NodoPosicion(p.getCoordenada(), 1, null);
			if (aux.getListaCoordenadas() == null) {
				aux.setListaCoordenadas(npNuevo);
			} else {
				npAnt.setSiguiente(npNuevo);
			}
		}
	}

	private void insertNewNode(NodoTemporal ant, PosicionPersona p) {
		NodoTemporal nuevo = new NodoTemporal();
		nuevo.setFecha(p.getFechaPosicion());

		NodoPosicion npActual = nuevo.getListaCoordenadas();
		NodoPosicion npAnt = null;
		boolean npEncontrado = false;

		// Find the appropriate position in the coordinates list
		while (npActual != null && !npEncontrado) {
			if (npActual.getCoordenada().equals(p.getCoordenada())) {
				npEncontrado = true;
				npActual.setNumPersonas(npActual.getNumPersonas() + 1);
			} else {
				npAnt = npActual;
				npActual = npActual.getSiguiente();
			}
		}

		// Insert into the coordinates list
		if (!npEncontrado) {
			NodoPosicion npNuevo = new NodoPosicion(p.getCoordenada(), 1, null);
			if (nuevo.getListaCoordenadas() == null) {
				nuevo.setListaCoordenadas(npNuevo);
			} else {
				npAnt.setSiguiente(npNuevo);
			}
		}

		// Insert the new node into the main list
		if (ant != null) {
			nuevo.setSiguiente(ant.getSiguiente());
			ant.setSiguiente(nuevo);
		} else {
			nuevo.setSiguiente(lista);
			lista = nuevo;
		}
	}


	@Override
	public String toString() {
		String cadena="";
		int cont=0;
		NodoTemporal aux = lista;
		for(cont=1; cont<size; cont++) {
			cadena += aux.getFecha().getFecha().toString();
			cadena += ";" +  aux.getFecha().getHora().toString() + " ";
			aux=aux.getSiguiente();
		}
		cadena += aux.getFecha().getFecha().toString();
		cadena += ";" +  aux.getFecha().getHora().toString();
		return cadena;
	}
	
	
	
}
