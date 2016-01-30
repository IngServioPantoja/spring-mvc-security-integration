package co.com.sp.dto;


import co.com.sp.capadominio.Persona;

public class PersonaDTO extends Persona {

	private static final long serialVersionUID = -1971816001641262269L;
	
	private String accion;

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}
	
}
