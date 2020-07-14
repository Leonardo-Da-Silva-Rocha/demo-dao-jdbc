package model.dao;

import model.dao.impl.VendedorDaoImpJDBC;

public class DaoFactory {
	
	public static VendedorDao createVendedorDao() {
		return new VendedorDaoImpJDBC();
	}
	
}
