package br.edu.infnet.pedido.model.persistencia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.pedido.model.entidade.Produto;

public class ProdutoDAO extends JdbcDAO<Produto> {
	
	public ProdutoDAO() {
	}

	@Override
	public Boolean salvar(Produto produto) {
		String sql = "insert into produto(codigo, descricao, preco) values (null, ?, ?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, produto.getDescricao());
			pstm.setDouble(2, produto.getPreco());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean atualizar(Produto produto) {
		String sql = "update produto set descricao = ?, preco = ? where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, produto.getDescricao());
			pstm.setDouble(2, produto.getPreco()); 
			pstm.setLong(3, produto.getCodigo()); 
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean deletar(Produto produto) {
		String sql = "delete from produto where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, produto.getCodigo()); 
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Produto obter(Long codigo) {
		String sql = "select * from produto where codigo = ?";
		Produto produto = new Produto();
		try {
			pstm = con.prepareStatement(sql); 
			pstm.setLong(1, codigo); 
			rs = pstm.executeQuery();
			if(rs.next()) {
				Long codigoDB = rs.getLong("codigo");
				String descricao = rs.getString("descricao");
				Double preco = rs.getDouble("preco");				
				produto = new Produto(codigoDB, descricao, preco);
			}
			return produto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Produto> listarTodos() {
		String sql = "select * from produto";
		List<Produto> produtos = new ArrayList<>();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				Long codigoDB = rs.getLong("codigo");
				String descricao = rs.getString("descricao");
				Double preco = rs.getDouble("preco");
				produtos.add(new Produto(codigoDB, descricao, preco));
			}
			return produtos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
