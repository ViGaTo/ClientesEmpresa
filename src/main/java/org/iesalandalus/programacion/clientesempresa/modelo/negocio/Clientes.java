package org.iesalandalus.programacion.clientesempresa.modelo.negocio;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;

public class Clientes {

		private int capacidad;
		private int tamano;
		
		Cliente[] coleccionClientes;
		
		public Clientes(int capacidad) {
			if (capacidad<=0) {
				throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
			}
			
			this.capacidad=capacidad;
			coleccionClientes = new Cliente[capacidad];
		}
		
		private int buscarIndice(Cliente cliente) {
			int indice=0;
			boolean clienteEncontrado = false;
			
			while (!tamanoSuperado(indice) && !clienteEncontrado) { 		
				if (coleccionClientes[indice].equals(cliente)) {
					clienteEncontrado = true;
				} else {
					indice++;
				}
			}

			return indice;
		}
		
		public void insertar(Cliente cliente) throws OperationNotSupportedException {
			if(cliente == null) {
				throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
			}
			
			if(capacidadSuperada(buscarIndice(cliente))) {	
				throw new OperationNotSupportedException("ERROR: No se aceptan más clientes.");
			}
			
			if(!tamanoSuperado(buscarIndice(cliente))) {
				throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese dni.");
			}
			
			coleccionClientes[buscarIndice(cliente)] = new Cliente(cliente);
			tamano++;
		}
		
		public Cliente buscar(Cliente cliente)  {
			if (cliente == null) {
				throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
			}
			
			for (int i = 0; i < getTamano(); i++) {
				if (get()[i].equals(cliente)) {
					cliente = get()[i];		
				} 
			}
			
			if (tamanoSuperado(buscarIndice(cliente))) {
				return null;
			} else {
				return cliente;
			}
		}
		
		public void borrar(Cliente cliente) throws OperationNotSupportedException {
			if (cliente == null) {
				throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
			}
			
			if (tamanoSuperado(buscarIndice(cliente))) {
				throw new OperationNotSupportedException("ERROR: No existe ningún cliente como el indicado.");
			}
			
			desplazarUnaPosicionHaciaIzquierda(buscarIndice(cliente));
		}
		
		private void desplazarUnaPosicionHaciaIzquierda(int indice) {
			for (int i = indice; i < tamano; i++) {
				coleccionClientes[i] = coleccionClientes[i + 1];
			}
			
			tamano--;
		}
		
		private Cliente[] copiaProfundaClientes() {
			Cliente[] copiaProfundaClientes = new Cliente[capacidad];
			
			for (int i = 0; i < tamano; i++) {
				copiaProfundaClientes[i] = coleccionClientes[i];
			}
			
			return copiaProfundaClientes;
		}
		
		private boolean capacidadSuperada(int indice) {
			if (indice >= capacidad) {
				return true;
			} else {
				return false;
			}
		}

		private boolean tamanoSuperado(int indice) {
			if (indice >= tamano) {
				return true;
			} else {
				return false;
			}
		}
		
		public int getCapacidad() {
			return capacidad;
		}
		public int getTamano() {
			return tamano;
		}
		
		public Cliente[] get() {
			return copiaProfundaClientes();
		}
}
