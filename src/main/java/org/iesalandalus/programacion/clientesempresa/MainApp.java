package org.iesalandalus.programacion.clientesempresa;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.clientesempresa.modelo.negocio.Clientes;
import org.iesalandalus.programacion.clientesempresa.vista.Consola;
import org.iesalandalus.programacion.clientesempresa.vista.Opcion;
import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {

	public static final int NUM_MAX_CLIENTES = 10;
	static Clientes clientes = new Clientes(NUM_MAX_CLIENTES);
	
	public static void main(String[] args) {
		
		Opcion opcion;

		do 
		{
			Consola.mostrarMenu();
			opcion=Consola.elegirOpcionMenu();
			ejecutarOpcion(opcion);
		}while (opcion != Opcion.SALIR);
	}

	private static void ejecutarOpcion(Opcion opcion){
		switch (opcion) {
		case INSERTAR_CLIENTE:
			insertarCliente();
			break;

		case BUSCAR_CLIENTE:
			buscarCliente();
			break;

		case BORRAR_CLIENTE:
			borrarCliente();
			break;

		case MOSTRAR_CLIENTES:
			mostrarClientes();
			break;

		case MOSTRAR_CLIENTES_FECHA:
			mostrarClientesFecha();
			break;

		case SALIR:
			System.out.println("Usted ha salido del programa.");
		
		}		
	}
	
	private static void insertarCliente() {
		try {
			Cliente cliente = Consola.leerCliente();
			
			clientes.insertar(cliente);
			System.out.println("EXITO: El cliente ha sido insertado satisfactoriamente.");
			System.out.println("");
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		} catch(DateTimeParseException e) {
			System.out.println("ERROR: La fecha de nacimiento del cliente no tiene un formato válido.");
			System.out.println("");
		}
	}
	
	private static void buscarCliente(){
		try {
			if (clientes.getTamano() != 0) {
				Cliente cliente = Consola.leerClienteDni();
		
				for (int i = 0; i < clientes.getTamano(); i++) {
					if (clientes.get()[i].equals(cliente)) {
						System.out.println("Se ha encontrado un cliente con el DNI introducido: ");
						System.out.println(clientes.get()[i].toString());
						System.out.println("");
					}else {
						System.out.println("No se ha encontrado ningun cliente que coincida con el DNI introducido.");
						System.out.println("");
					}
				}
			}else {
				System.out.println("No hay ningun cliente introducido.");
				System.out.println("");
			}
		}catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}
	
	private static void borrarCliente() {
		try {
			if (clientes.getTamano() != 0) {
				Cliente cliente = Consola.leerClienteDni();
		
				for (int i = 0; i < clientes.getTamano(); i++) {
					if (clientes.get()[i].equals(cliente)) {
						System.out.println("Se ha encontrado al cliente con el DNI introducido. ");
						System.out.println("");
						clientes.borrar(cliente);
						System.out.println("EXITO: El cliente ha sido borrado satisfactoriamente.");
						System.out.println("");
					}else {
						System.out.println("No se ha encontrado ningun cliente que coincida con el DNI introducido.");
						System.out.println("");
						}
					}
			}else {
				System.out.println("No hay ningun cliente introducido.");
				System.out.println("");
			}
		} catch (NullPointerException | OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
			System.out.println("");
		}
	}
	
	private static void mostrarClientes() {
			if (clientes.getTamano() != 0) {
			for (int i = 0; i < clientes.getTamano(); i++) {
				System.out.println(clientes.get()[i].toString());
				System.out.println("");
				}
			}else {
			System.out.println("No hay ningun cliente introducido.");
			System.out.println("");
		}
	}
	
	private static void mostrarClientesFecha() {
		try {
			if(clientes.getTamano() != 0) {
				LocalDate fechaNacimiento = Consola.leerFechaNacimiento();
			
				for (int i = 0; i < clientes.getTamano(); i++) {
					if (clientes.get()[i].getFechaNacimiento().equals(fechaNacimiento)) {
						System.out.println("Clientes con la siguiente fecha de nacimiento introducida " + fechaNacimiento + ": ");
						System.out.println(clientes.get()[i].toString());
						System.out.println("");
					}else {
						System.out.println("No se ha encontrado ningun cliente que coincida con la fecha de nacimiento introducida.");
						System.out.println("");
					}
				}
			}else {
				System.out.println("No hay ningun cliente introducido.");
				System.out.println("");
			}
		}catch(DateTimeParseException e) {
			System.out.println("ERROR: La fecha de nacimiento del cliente no tiene un formato válido.");
		}
	}
}