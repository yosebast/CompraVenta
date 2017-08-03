package com.compraventa.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.compraventa.entidades.Seccion;
import com.compraventa.util.HibernateUtil;

@Repository
public class SeccionDaoImpl implements SeccionDao {

	@SuppressWarnings("unchecked")
	public List<Seccion> listaSeccion() {
		// TODO Auto-generated method stub

		List<Seccion> listaSecciones = null;

		Session sesion = HibernateUtil.getSessionFactory().openSession();

		listaSecciones = sesion.createQuery("from Seccion").list();

		return listaSecciones;

	}

	public void insertaSeccionDao(Seccion seccion) {
		// TODO Auto-generated method stub

		Session sesion = HibernateUtil.getSessionFactory().openSession();

		@SuppressWarnings("unused")
		int id = 0;

		try {

			id = (Integer) sesion.save(seccion);

		} catch (HibernateException he) {
			throw he;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Seccion> cargaSeccionServicio() {
		// TODO Auto-generated method stub

		Session sesion = HibernateUtil.getSessionFactory().openSession();

		return sesion.createQuery("from Seccion").list();
	}

	public Seccion cargaSeccionById(int idseccion) {
		// TODO Auto-generated method stub

		Session sesion = HibernateUtil.getSessionFactory().openSession();

		return (Seccion) sesion.get(Seccion.class, idseccion);
	}

	public void borrarSeccion(Integer idseccion) {
		// TODO Auto-generated method stub

		Session session = HibernateUtil.getSessionFactory().openSession();

		Query query = session.createSQLQuery("delete from Seccion where idseccion = :idseccion");

		query.setInteger("idseccion", idseccion);
		query.executeUpdate();

	}

	public void actualizaSeccion(Integer idseccion, Seccion seccion) {
		// TODO Auto-generated method stub

		Session session = HibernateUtil.getSessionFactory().openSession();

		Query query = session
				.createQuery("update Seccion set nomseccion = :nomseccion" + " where idseccion = :idseccion");
		query.setParameter("nomseccion", seccion.getNomseccion());
		query.setParameter("idseccion", seccion.getIdseccion());

		query.executeUpdate();

	}

}
