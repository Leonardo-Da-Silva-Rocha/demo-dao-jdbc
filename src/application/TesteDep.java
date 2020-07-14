package application;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

public class TesteDep {

	public static void main(String[] args) {
		
		
		DepartamentoDao dep = DaoFactory.createDepartamentoDao();
		
		System.out.println("=== TESTE 1: Departamento findbyid ===");

		Departamento departamento = dep.findById(2 );
		System.out.println(departamento);
		
	}

}
