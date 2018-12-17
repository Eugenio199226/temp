package com.ricardo.persistence;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ricardo.models.Pedido;

public class PedidosManager {

	private static PedidosManager instance = null;

	private static SessionFactory sfactory;

	public static PedidosManager getInstance() {
		if (instance == null) instance = new PedidosManager();
		return instance;
	}

	private PedidosManager() {
		sfactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	public List<Pedido> getPedidos() {
		Session sess = sfactory.openSession();

		List<Pedido> listaPedidos = sess.createQuery("from Pedido").list();

		sess.close();
		return listaPedidos;
	}
	
	public boolean deletePedido(int pid) throws Exception
	{
		boolean borrado = false;
		
		Session session = sfactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.delete(session.find(Pedido.class, pid));
		
		tx.commit();
		borrado=true;
		session.close();

		return borrado;
	}

}
