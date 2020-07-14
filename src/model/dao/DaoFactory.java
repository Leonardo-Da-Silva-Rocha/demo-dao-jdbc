package model.dao;

import db.DB;
import model.dao.impl.DepartamentoDaoImpJDBC;
import model.dao.impl.VendedorDaoImpJDBC;

public class DaoFactory {
	
	public static VendedorDao createVendedorDao() {
		return new VendedorDaoImpJDBC(DB.getConnection());
	}
	
	public static DepartamentoDao createDepartamentoDao() {
		return new DepartamentoDaoImpJDBC(DB.getConnection());
	}
	
}
