package org.iesalandalus.programacion.clientesempresa.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.clientesempresa.modelo.dominio.Cliente;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

	private Consola() {

	}
	
	public static void mostrarMenu() {
		System.out.println("Menú del programa");
		System.out.println("-----------------------------------------------------------------");
		System.out.println("");
		System.out.println("1.- Insertar un nuevo cliente.");
		System.out.println("2.- Buscar un cliente creado.");
		System.out.println("3.- Borrar un cliente creado.");
		System.out.println("4.- Mostrar todos los clientes.");
		System.out.println("5.- Mostrar los clientes que hayan nacido en una fecha concreta.");
		System.out.println("0.- Salir.");
		System.out.println("");
	}
	
	public static Opcion elegirOpcionMenu(){
		int opcionElegida=0;
		Opcion[] opcion=Opcion.values();
		
		do
		{
			System.out.print("Introduce la opción a realizar (0-5): ");
			opcionElegida=Entrada.entero();
			
		}while (opcionElegida<0 || opcionElegida>5);
		
		return opcion[opcionElegida];
	}
	
	public static Cliente leerCliente() {
		Cliente cliente;
		String nombre, dni, correo, telefono;
		LocalDate fechaNacimiento=null;
		
		System.out.print("Introduzca el nombre completo del cliente: ");
		nombre = Entrada.cadena();
		
		System.out.print("Introduzca el DNI completo del cliente: ");
		dni = Entrada.cadena();
		
		System.out.print("Introduzca el correo del cliente: ");
		correo = Entrada.cadena();

		System.out.print("Introduzca el teléfono del cliente: ");
		telefono = Entrada.cadena();

		System.out.print("Introduzca la fecha de nacimiento del cliente (Ejemplo: 17/01/2000): ");
		fechaNacimiento = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(Cliente.FORMATO_FECHA));
		
		cliente = new Cliente(nombre, dni, correo, telefono, fechaNacimiento);
		
		return cliente;
	}
	
	public static Cliente leerClienteDni() {
		String dni;
		
		System.out.print("Introduzca el DNI completo del cliente que quiere buscar: ");
		dni = Entrada.cadena();
			
		Cliente clienteDni = new Cliente("Juan", dni, "jester@gmail.org", "672548712", LocalDate.of(2022, 2, 1));
			
		return clienteDni;
	}
	
	public static LocalDate leerFechaNacimiento() {
		LocalDate fechaNacimiento = null;

		do {
			System.out.print("Introduzca la fecha de nacimiento del cliente que quiere buscar (Ejemplo: 17/01/2000):");
			fechaNacimiento = LocalDate.parse(Entrada.cadena(), DateTimeFormatter.ofPattern(Cliente.FORMATO_FECHA));
		} while (fechaNacimiento==null);

		return fechaNacimiento;
	}
}
