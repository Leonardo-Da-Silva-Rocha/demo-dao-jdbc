package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class VendedorDaoImpJDBC implements VendedorDao {

	private Connection conn;

	public VendedorDaoImpJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Vendedor obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Vendedor obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vendedor findById(Integer id) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT vendedor.*,departamento.Nome " + "as DepNome " + "FROM vendedor INNER JOIN departamento "
							+ "ON vendedor.DepartmentId =  departamento.Id " + "WHERE vendedor.Id = ?");

			st.setInt(1, id);

			rs = st.executeQuery();

			if (rs.next()) {

				Departamento dep = instanciaDepartamento(rs);

				Vendedor obj = instanciaVendedor(rs, dep);

				return obj;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	private Vendedor instanciaVendedor(ResultSet rs, Departamento dep) throws SQLException {

		Vendedor obj = new Vendedor();

		obj.setId(rs.getInt("Id"));
		obj.setNome(rs.getString("Nome"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setDataNascimento(rs.getDate("dataNascimento"));
		obj.setDepartament(dep);

		return obj;
	}

	private Departamento instanciaDepartamento(ResultSet rs) throws SQLException {

		Departamento dep = new Departamento();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNome(rs.getString("DepNome"));

		return dep;

	}

	@Override
	public List<Vendedor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vendedor> findByDepartamento(Departamento dep) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT Vendedor.*,departamento.Nome as DepNome "
					+"FROM Vendedor INNER JOIN departamento "
					+"ON vendedor.DepartmentId = departamento.Id "
					+"WHERE DepartmentId = ? "
					+"ORDER BY Nome ");
			
			st.setInt(1, dep.getId());
			rs = st.executeQuery();
			
			List<Vendedor> lista = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			while (rs.next()) {
				
				Departamento d = map.get(rs.getInt("DepartmentId"));
				
				if(d == null) {
					
					d = instanciaDepartamento(rs);
					map.put(rs.getInt("DepartmentId"), d);
				}
				
				
				Vendedor vendedor = instanciaVendedor(rs, d);
				lista.add(vendedor);

			}
			return lista;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

}
