package br.edu.infnet.pedido.model.persistencia;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.edu.infnet.pedido.model.entidade.Cliente;
import br.edu.infnet.pedido.model.entidade.Pedido;

public class PedidoDAOTest {
	
	@Test
	public void testSalvarPedido() throws ParseException {
		IDAO clienteDAO = new ClienteDAO();
		List<Cliente> clientes = clienteDAO.listarTodos();
		
		IDAO pedidoDao = new PedidoDAO();
		boolean validacao = pedidoDao.salvar(new Pedido(LocalDate.now(), clientes.get(0)));
		Assert.assertTrue(validacao);
	}
	
	@Test
	public void testObterPedido() throws ParseException {
		IDAO pedidoDao = new PedidoDAO();
		List<Pedido> lista = pedidoDao.listarTodos();
		Pedido pedido = (Pedido) pedidoDao.obter(lista.get(0).getCodigo());
		System.out.println(pedido); 
		Assert.assertNotNull(pedido);
	}
	
	@Test
	public void testListaPedidos() {
		IDAO pedidoDao = new PedidoDAO();
		List<Pedido> lista = pedidoDao.listarTodos();
		System.out.println(lista); 
		Assert.assertTrue(lista.size() > 0);
	}
	
	@Test
	public void testUpdatePedido() {
		IDAO clienteDAO = new ClienteDAO();
		List<Cliente> clientes = clienteDAO.listarTodos();
		
		IDAO pedidoDao = new PedidoDAO();
		List<Pedido> lista = pedidoDao.listarTodos();
		Pedido pedido = lista.get(0);
		pedido.setCliente(clientes.get(0));
		boolean validacao = pedidoDao.atualizar(lista.get(0));
		Assert.assertTrue(validacao);
	}
	
	@Test
	public void testDeletePedido() throws ParseException {
		IDAO pedidoDao = new PedidoDAO();
		Pedido pedido = (Pedido) pedidoDao.obter(5l);
		boolean validacao = pedidoDao.deletar(pedido);
		Assert.assertTrue(validacao);
	}

}
