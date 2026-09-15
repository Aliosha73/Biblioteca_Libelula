package gestion;

import modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

public class GestionClientes {
	private final List<Cliente> clientes;

	public GestionClientes() {
		clientes = new ArrayList<>();
	}

	public void agregarCliente(Cliente cliente) {
		clientes.add(cliente);
	}

	public List<Cliente> getClientes() {
		return clientes;
	}
}
