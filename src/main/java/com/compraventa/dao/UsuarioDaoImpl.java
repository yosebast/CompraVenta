package com.compraventa.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.compraventa.entidades.Usuarios;
import com.compraventa.util.HibernateUtil;


@Repository
public class UsuarioDaoImpl implements UsuarioDao {

	public void insertaUsuario(Usuarios user) {
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();		
		
		//1º forma   solo esta
		//sesion.save(user);
		
		//segunda forma mas compacta y robusta   se obtiene el id    pero faltaria que el metodi sea de devolver un tipo de dato Integer
		int id = 0;
        try {
           
            id = (Integer) sesion.save(user);
        } catch (HibernateException he) {
            throw he;
        } 

        
		
		
		
		/*try {
            trns = sesion.beginTransaction();
            sesion.save(user);
            sesion.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
        } finally {
            sesion.flush();
            sesion.close();
        }*/
		
	
	}

	
	public List<Usuarios> cargaUsuariosDao() {
		// TODO Auto-generated method stub
		
		List<Usuarios> listaUsuarios = null;
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		
		//usando HQL
		//listaUsuarios = sesion.createQuery("from Usuarios").list();
		
		//return listaUsuarios;
		
		
		
		//usando criteria
		Criteria crite =  sesion.createCriteria(Usuarios.class);
		
		
		
		listaUsuarios = (List<Usuarios>) crite.list();
		
		return listaUsuarios;
	}
	
	
	public Usuarios getById(Integer id){
		
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();		
		Usuarios usuario = (Usuarios) sesion.get(Usuarios.class, id);		
		return usuario;
		
	}

	@Transactional
	public void updateUsuarioDao(Usuarios usu, int idusu) {
		// TODO Auto-generated method stub
		
		Session sesion = HibernateUtil.getSessionFactory().openSession();
		
		//sesion.update(usu);
		
		
		/* try {
	           
	            sesion.update(usu);
	        } catch (HibernateException he) {
	            throw he;
	        } */
		
		
		/*	try {
			
			Usuarios Us = (Usuarios) sesion.get(Usuarios.class, usu.getIdusuario());		
			Us.setNombre(usu.getNombre());
			Us.setApellidos(usu.getApellidos());
			Us.setEdad(usu.getEdad());
			Us.setEmail(usu.getEmail());
			Us.setTlf(usu.getTlf());		
			
			sesion.update(Us);
			
		} catch (Exception e) {
			// TODO: handle exception
		}	*/
			
			
			try {
			/*	
				Usuarios Us = (Usuarios) sesion.get(Usuarios.class, idusu);	
				Us.setIdusuario(usu.getIdusuario());
				Us.setNombre(usu.getNombre());
				Us.setApellidos(usu.getApellidos());
				Us.setEdad(usu.getEdad());
				Us.setEmail(usu.getEmail());
				Us.setTlf(usu.getTlf());
				Us.setClave(usu.getClave());
				Us.setFotoUrl(usu.getFotoUrl());
				Us.setReg_Date(usu.getReg_Date());
				Us.setRegistrado(usu.getRegistrado());
				
				sesion.saveOrUpdate(Us);*/
				
				Query query = sesion.createQuery("update Usuarios set nombre = :nombre," +" apellidos = :apellidos," + " email = :email," + " edad = :edad," + " tlf = :tlf,"
						+ " fotoUrl = :fotoUrl," + " registrado = :registrado," + " clave = :clave," + " reg_Date = :reg_Date" +
			    				" where idusuario = :idusuario");
				query.setParameter("nombre", usu.getNombre());
				query.setParameter("apellidos", usu.getApellidos());
				query.setParameter("email", usu.getEmail());
				query.setParameter("edad", usu.getEdad());
				query.setParameter("tlf", usu.getTlf());
				query.setParameter("fotoUrl", usu.getFotoUrl());
				query.setParameter("registrado", usu.getRegistrado());
				query.setParameter("clave", usu.getClave());
				query.setParameter("reg_Date", usu.getReg_Date() );
				query.setParameter("idusuario", usu.getIdusuario());
			
			 query.executeUpdate();
				
		
				
			} catch (RuntimeException e) {
				  
			    throw e;
			}
			
			
			
			
			
			
			
			
		
		
	}
	
	@Transactional
	public void deleteUsuarioDao(Integer idUsuario){
		
		try {
		Session sesion = HibernateUtil.getSessionFactory().openSession();		
		/*Criteria crit =  sesion.createCriteria(Usuarios.class);			 
		crit.add(Restrictions.eq("idusuario", idUsuario));
		Usuarios usu = (Usuarios)crit.uniqueResult();	
		
		sesion.delete(usu);		*/
		
		Query query = sesion.createSQLQuery("delete from Usuarios where idusuario = :idUsuario");
        query.setInteger("idUsuario", idUsuario);
        query.executeUpdate();
		
		} catch (RuntimeException e) {
		  
		    throw e;
		}

}
	
}
