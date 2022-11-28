package br.edu.infnet.pedido.model.persistencia;

import java.text.ParseException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.edu.infnet.pedido.model.entidade.Cliente;

public class ClienteDAOTest {

	
	@Before
	public void inicializar() {
		IDAO clienteDAO = new ClienteDAO();
		Cliente cliente = new Cliente("Jose das Couves"); 
		clienteDAO.salvar(cliente);
	}	
	
	@Test
	public void test() {
		IDAO clienteDAO = new ClienteDAO();
		Cliente cliente = new Cliente("Jose das Couves"); 
		boolean validacao = clienteDAO.salvar(cliente);
		Assert.assertTrue(validacao);
	}	
	
	@Test
	public void testUpdate() {
		IDAO clienteDAO = new ClienteDAO();
		List<Cliente> lista = clienteDAO.listarTodos();
		Cliente cliente = new Cliente("Maria das Couves", lista.get(0).getCodigo()); 
		boolean validacao = clienteDAO.atualizar(cliente);
		Assert.assertTrue(validacao);
	}
	
	@Test
	public void testDelete() {
		IDAO clienteDAO = new ClienteDAO();
		List<Cliente> lista = clienteDAO.listarTodos();
		Cliente cliente = new Cliente("Maria das Couves", lista.get(lista.size()-1).getCodigo()); 
		boolean validacao = clienteDAO.deletar(cliente);
		Assert.assertTrue(validacao);
	}	
	
	@Test
	public void testListaClientes() {
		IDAO clienteDAO = new ClienteDAO();
		List<Cliente> lista = clienteDAO.listarTodos();
		Assert.assertTrue(lista.size() > 0);
	}	

	@Test
	public void testObterCliente() throws ParseException {
		IDAO clienteDAO = new ClienteDAO();
		List<Cliente> lista = clienteDAO.listarTodos();
		Cliente cliente = (Cliente) clienteDAO.obter(lista.get(0).getCodigo());
		Assert.assertNotNull(cliente);;
	}	

}
