package br.edu.infnet.pedido.model.persistencia;

import java.text.ParseException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.edu.infnet.pedido.model.entidade.Produto;

public class ProdutoDAOTest {
	
	@Test
	public void testSalvarProduto() {
		IDAO produtoDao = new ProdutoDAO();
		boolean validacao = produtoDao.salvar(new Produto(1l, "teste produto", 5.0));
		Assert.assertTrue(validacao);
	}
	
	@Test
	public void testObterProduto() throws ParseException {
		IDAO produtoDao = new ProdutoDAO();
		List<Produto> lista = produtoDao.listarTodos();
		Produto produto = (Produto) produtoDao.obter(lista.get(0).getCodigo());
		System.out.println(produto); 
		Assert.assertNotNull(produto);
	}
	
	@Test
	public void testListaProduto() {
		IDAO produtoDao = new ProdutoDAO();
		List<Produto> lista = produtoDao.listarTodos();
		System.out.println(lista); 
		Assert.assertTrue(lista.size() > 0);
	}
	
	@Test
	public void testUpdateProduto() {
		IDAO produtoDao = new ProdutoDAO();
		List<Produto> lista = produtoDao.listarTodos();
		Produto produto = lista.get(0);
		produto.setDescricao("nova descrição");
		boolean validacao = produtoDao.atualizar(lista.get(0));
		Assert.assertTrue(validacao);
	}
	
	@Test
	public void testDeleteProduto() throws ParseException {
		IDAO produtoDao = new ProdutoDAO();
		List<Produto> lista = produtoDao.listarTodos();
		Produto produto = lista.get(lista.size() -1);
		boolean validacao = produtoDao.deletar(produto);
		Assert.assertTrue(validacao);
	}

}
