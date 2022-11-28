package br.edu.infnet.pedido;

import java.util.List;

import br.edu.infnet.pedido.model.JdbcUtil;
import br.edu.infnet.pedido.model.entidade.Produto;
import br.edu.infnet.pedido.model.persistencia.ProdutoDAO;

public class ConsultaProduto {
	
	static ProdutoDAO produtoDao;
		
    public static void main(String[] args) {
    	JdbcUtil.obterConexao();
    	
		produtoDao = new ProdutoDAO();
		
		adicionarProduto();
		System.out.println("Produto incluído!");
		
		atualizarProduto();
		System.out.println("Produto atualizado!");
		
		listandoProduto();
		System.out.println("Produto listado!");
		
		listandoProdutoPorId(1l);
		System.out.println("Produto listado por ID!");
		
		deletandoProduto();
		System.out.println("Produto deletado!");
    }
    
    public static void adicionarProduto() {
    	produtoDao.salvar(new Produto("teste", 5.0));
    }
    
    public static void atualizarProduto() {
    	List<Produto> produtos = produtoDao.listarTodos();
    	Produto produto = produtos.get(produtos.size() -1);
    	produto.setDescricao("Nova descrição");
    	
    	produtoDao.atualizar(produto);
    }
    
    public static void listandoProduto() {
    	List<Produto> produtos = produtoDao.listarTodos();
    	
    	if (!produtos.isEmpty()) {
    		produtos.forEach(p -> {
	    		System.out.println(p.getCodigo() + " - " + p.getDescricao() + " - " + p.getPreco());
	    	});
    	}
    }
    
    public static void listandoProdutoPorId(Long id) {
    	Produto produto = produtoDao.obter(id);
    	
    	if (produto != null) {
    		System.out.println(produto.getCodigo() + " - " + produto.getDescricao() + " - " + produto.getPreco());
    	}
    }
    
    public static void deletandoProduto() {
    	List<Produto> produtos = produtoDao.listarTodos();
    	Produto produto = produtos.get(produtos.size() -1);
    	
    	produtoDao.deletar(produto);
    }
}
