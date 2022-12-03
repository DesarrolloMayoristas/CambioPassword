package cdo.Datos;

import java.io.Serializable;

public class Log  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int tipo_acceso;
	private int cve_empresa;
	private int cve_cliente;
	private int cve_pedido;
	String accion;
	
	public Log() {
		super();
	}

	public Log(int tipo_acceso, int cve_empresa, int cve_cliente, int cve_pedido, String accion) {
		super();
		this.tipo_acceso = tipo_acceso;
		this.cve_empresa = cve_empresa;
		this.cve_cliente = cve_cliente;
		this.cve_pedido = cve_pedido;
		this.accion = accion;
	}

	public int getTipo_acceso() {
		return tipo_acceso;
	}

	public void setTipo_acceso(int tipo_acceso) {
		this.tipo_acceso = tipo_acceso;
	}

	public int getCve_empresa() {
		return cve_empresa;
	}

	public void setCve_empresa(int cve_empresa) {
		this.cve_empresa = cve_empresa;
	}

	public int getCve_cliente() {
		return cve_cliente;
	}

	public void setCve_cliente(int cve_cliente) {
		this.cve_cliente = cve_cliente;
	}

	public int getCve_pedido() {
		return cve_pedido;
	}

	public void setCve_pedido(int cve_pedido) {
		this.cve_pedido = cve_pedido;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Log [tipo_acceso=" + tipo_acceso + ", cve_empresa=" + cve_empresa + ", cve_cliente=" + cve_cliente
				+ ", cve_pedido=" + cve_pedido + ", accion=" + accion + "]";
	}

}
