package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class program {

	public static void main(String[] args) {
		
		
		VendedorDao vendedorDao = DaoFactory.createVendedorDao();
		
		System.out.println("=== TESTE 1: vendedor findbyid ===");
		Vendedor vend = vendedorDao.findById(3);
		
		System.out.println(vend);
		System.out.println();
		
		System.out.println("=== TESTE 2: findbyDepartament ===");
		Departamento dep = new Departamento(2, null);
		List<Vendedor> lista = vendedorDao.findByDepartamento(dep);
		
		for(Vendedor vende : lista) {
			System.out.println(vende);
		}

	}

}
