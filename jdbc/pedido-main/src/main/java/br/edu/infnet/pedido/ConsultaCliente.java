package br.edu.infnet.pedido;

import java.util.List;

import br.edu.infnet.pedido.model.JdbcUtil;
import br.edu.infnet.pedido.model.entidade.Cliente;
import br.edu.infnet.pedido.model.persistencia.ClienteDAO;

public class ConsultaCliente {
	
	static ClienteDAO clienteDao;
		
    public static void main(String[] args) {
    	JdbcUtil.obterConexao();
    	
		clienteDao = new ClienteDAO();
		
		adicionarCliente();
		System.out.println("Cliente incluído!");
		
		atualizarCliente();
		System.out.println("Cliente atualizado!");
		
		listandoCliente();
		System.out.println("Cliente listado!");
		
		listandoClientePorId(1l);
		System.out.println("Cliente listado por ID!");
		
		deletandoCliente();
		System.out.println("Cliente deletado!");
    }
    
    public static void adicionarCliente() {
    	clienteDao.salvar(new Cliente("Incluindo Cliente"));
    }
    
    public static void atualizarCliente() {
    	Cliente cliente = clienteDao.obter(1l);
    	cliente.setNome("Novo nome");
    	
    	clienteDao.atualizar(cliente);
    }
    
    public static void listandoCliente() {
    	List<Cliente> clientes = clienteDao.listarTodos();
    	
    	if (!clientes.isEmpty()) {
	    	clientes.forEach(c -> {
	    		System.out.println(c.getCodigo() + " - " + c.getNome());
	    	});
    	}
    }
    
    public static void listandoClientePorId(Long id) {
    	Cliente cliente = clienteDao.obter(id);
    	
    	if (cliente != null) {
    		System.out.println(cliente.getCodigo() + " - " + cliente.getNome());
    	}
    }
    
    public static void deletandoCliente() {
    	Cliente cliente = clienteDao.obter(5l);
    	
    	clienteDao.deletar(cliente);
    }
}
