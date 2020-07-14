package application;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Vendedor;

public class program {

	public static void main(String[] args) {
		
		
		
		VendedorDao vendedorDao = DaoFactory.createVendedorDao();
		
		
		System.out.println("=== TESTE 1: vendedor findbyid ===");
		Vendedor vend = vendedorDao.findById(3);
		
		System.out.println(vend);
		
		
		

	}

}
