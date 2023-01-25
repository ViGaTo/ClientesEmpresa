package org.iesalandalus.programacion.clientesempresa.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {

	private static final String ER_CORREO = "([A-Za-z0-9_.-]+[@]+[A-Za-z0-9]+[.]+[a-zA-Z]+$)";
	private static final String ER_DNI = "([0-9]{8})([A-Z]{1})";
	private static final String ER_TELEFONO = "([0-9]{9})";
	public static final String FORMATO_FECHA="dd/MM/yyyy";
	
	private String nombre;
	private String dni;
	private String correo;
	private String telefono;
	private LocalDate fechaNacimiento;
	
	public Cliente (String nombre, String dni, String correo, String telefono, LocalDate fechaNacimiento) {
		setNombre(nombre);
		setDni(dni);
		setCorreo(correo);
		setTelefono(telefono);
		setFechaNacimiento(fechaNacimiento);
	}
	
	public Cliente (Cliente cliente) {
		if (cliente==null)
			throw new NullPointerException("ERROR: No es posible copiar un cliente nulo.");
		
		setNombre(cliente.getNombre());
		setDni(cliente.getDni());
		setCorreo(cliente.getCorreo());
		setTelefono(cliente.getTelefono());
		setFechaNacimiento(cliente.getFechaNacimiento());
	}
	
	private String formateaNombre(String nombre) {
		String nombreCorrecto="";
		String [] nombrePalabra = nombre.trim().toLowerCase().split("\\s+");
		
		for(int i=0; i<nombrePalabra.length; i++) {
			nombrePalabra[i]=nombrePalabra[i].replace(nombrePalabra[i].substring(0, 1),
			nombrePalabra[i].substring(0, 1).toUpperCase()) + " ";
			
			nombreCorrecto = nombreCorrecto + nombrePalabra[i];
		}
		
		nombreCorrecto = nombreCorrecto.trim();
		return nombreCorrecto;
	}
	
	private String getIniciales() {
		
		String iniciales = "";
		
		this.nombre=formateaNombre(nombre);

		String nombrePalabraIni[] = nombre.split(" ");
		
		for (int i =0; i<nombrePalabraIni.length; i++) {
			iniciales = iniciales + nombrePalabraIni[i].charAt(0);
		}

		return iniciales;
		
	}
	
	private boolean comprobarLetraDni(String dni) {
		Boolean dniValido = false;
		
		Pattern p;
		Matcher m;
		
		p = Pattern.compile(ER_DNI);
		m = p.matcher(dni);
		
		if(m.matches()) {
			int dniNumero = Integer.parseInt(m.group(1));
			String letra = m.group(2);
			String[] letras = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
		
		if (letra.equals(letras[dniNumero % 23])) {
				dniValido = true;
			}
		}
		
		return dniValido;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		if (nombre==null)
			throw new NullPointerException("ERROR: El nombre de un cliente no puede ser nulo.");
		
		if (nombre.trim().isEmpty())
			throw new IllegalArgumentException("ERROR: El nombre de un cliente no puede estar vacío.");
		
		this.nombre = formateaNombre(nombre);
	}
	
	public String getDni() {
		return dni;
	}
	
	private void setDni(String dni) {
		if (dni==null)
			throw new NullPointerException("ERROR: El dni de un cliente no puede ser nulo.");
        
        if(!dni.matches(ER_DNI))
        	throw new IllegalArgumentException("ERROR: El dni del cliente no tiene un formato válido.");
        
        if(!comprobarLetraDni(dni))
        	throw new IllegalArgumentException("ERROR: La letra del dni del cliente no es correcta.");
        
		this.dni = dni;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		if (correo==null)
			throw new NullPointerException("ERROR: El correo de un cliente no puede ser nulo.");
        
        if(!correo.matches(ER_CORREO))
        	throw new IllegalArgumentException("ERROR: El correo del cliente no tiene un formato válido.");
		
		this.correo = correo;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		if (telefono==null)
			throw new NullPointerException("ERROR: El teléfono de un cliente no puede ser nulo.");
		
        if(!telefono.matches(ER_TELEFONO))
        	throw new IllegalArgumentException("ERROR: El teléfono del cliente no tiene un formato válido.");
		
		this.telefono = telefono;
	}
	
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		if(fechaNacimiento==null) {
			throw new NullPointerException("ERROR: La fecha de nacimiento de un cliente no puede ser nula.");
		}
        	
		this.fechaNacimiento = fechaNacimiento;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(dni, other.dni);
	}

	@Override
	public String toString() {
		return "nombre=" + nombre + " (" + getIniciales() + ")"+ ", DNI=" + dni + ", correo=" + correo + ", teléfono=" + telefono
				+ ", fecha nacimiento=" + fechaNacimiento.format(DateTimeFormatter.ofPattern(FORMATO_FECHA));
	}
}
