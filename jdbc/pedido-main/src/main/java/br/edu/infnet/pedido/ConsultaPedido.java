package br.edu.infnet.pedido;

import java.time.LocalDate;
import java.util.List;

import br.edu.infnet.pedido.model.JdbcUtil;
import br.edu.infnet.pedido.model.entidade.Cliente;
import br.edu.infnet.pedido.model.entidade.Pedido;
import br.edu.infnet.pedido.model.persistencia.ClienteDAO;
import br.edu.infnet.pedido.model.persistencia.PedidoDAO;

public class ConsultaPedido {

	static PedidoDAO pedidoDao;
	static ClienteDAO clienteDao;
	
    public static void main(String[] args) {
    	JdbcUtil.obterConexao();
    	
    	pedidoDao = new PedidoDAO();
    	clienteDao = new ClienteDAO();
		
		adicionarPedido();
		System.out.println("Pedido incluído!");
		
		atualizarPedido();
		System.out.println("Pedido atualizado!");
		
		listandoPedido();
		System.out.println("Pedido listado!");
		
		listandoPedidoPorId(1l);
		System.out.println("Pedido listado por ID!");
		
		deletandoPedido();
		System.out.println("Pedido deletado!");
    }
    
    public static void adicionarPedido() {
    	Cliente cliente = clienteDao.obter(10l);
    	pedidoDao.salvar(new Pedido(LocalDate.now(), cliente));
    }
    
    public static void atualizarPedido() {
    	Pedido pedido = pedidoDao.obter(3l);
    	
    	Cliente cliente = clienteDao.obter(9l);
    	
    	pedido.setCliente(cliente);
    	
    	pedidoDao.atualizar(pedido);
    }
    
    public static void listandoPedido() {
    	List<Pedido> pedidos = pedidoDao.listarTodos();
    	
    	if (!pedidos.isEmpty()) {
	    	pedidos.forEach(p -> {
	    		System.out.println(" - " + p.getData());
	    		System.out.println(" -- " + p.getCliente().getNome());
	    		p.getProdutos().forEach(prod -> {
	    			System.out.println(" --- " + prod.getDescricao() + " - " + prod.getPreco());
	    		});
	    	});
    	}
    }
    
    public static void listandoPedidoPorId(Long ID) {
    	Pedido pedido = pedidoDao.obter(3l);
    	
    	if (pedido != null) {
			System.out.println(" - " + pedido.getData());
			System.out.println(" -- " + pedido.getCliente().getNome());
    	}
    }
    
    public static void deletandoPedido() {
    	Pedido pedido = pedidoDao.obter(4l);
    	
    	pedidoDao.deletar(pedido);
    }
	
}
