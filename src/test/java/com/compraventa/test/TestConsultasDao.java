package com.compraventa.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.compraventa.dao.UsuarioDao;
import com.compraventa.entidades.Usuarios;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/application-config.xml")

public class TestConsultasDao { 
	
@Test	
public void TestOne(){
	


	/*Criteria criteria = currentSession().createCriteria(Seccion.class);
	criteria.add(Restrictions.eq("nomseccion", "Hombre"));
	
	List<Seccion> seccion = (List<Seccion>) criteria.list();*/
	
	//List<Usuarios> user = UsuarioPersisten.getAll();		
	
	assertEquals(5, 5);
	
	
}

	
	
	
	
}
