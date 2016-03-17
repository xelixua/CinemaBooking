package xyz.maksimenko.iqbuzztt.DAO.Impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xyz.maksimenko.iqbuzztt.Visitor;
import xyz.maksimenko.iqbuzztt.DAO.VisitorDAO;

@Repository
public class VisitorDAOImpl implements VisitorDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void addVisitor(Visitor visitor) {
		Session ses = sessionFactory.openSession();
		ses.save(visitor);
		ses.flush();
		ses.close();
	}

	public Visitor getVisitor(Long visitorId) {
		Session ses = sessionFactory.openSession();
		try{
			return (Visitor) ses.createQuery("from Visitor where visitorId = :userId").setLong("visitorId", visitorId).list().get(0);
		} catch (NullPointerException e){
			return null;
		} finally {
			ses.close();
		}

	}
	
	public Visitor getVisitorByUserName(String fullName) {
		Session ses = sessionFactory.openSession();
		try{
			return (Visitor) ses.createQuery("from Visitor where fullname = :fullname").setString("fullname", fullName).list().get(0);
		} catch (NullPointerException | IndexOutOfBoundsException e){
			return null;
		} finally {
			ses.close();
		}
	}

	public void updateVisitor(Visitor visitor) {
		Session ses = sessionFactory.openSession();
		ses.update(visitor);
		ses.flush();
		ses.close();
	}
	
	public void removeVisitor(Long visitorId) {
		Session ses = sessionFactory.openSession();
		Visitor visitor = (Visitor) ses.load(
				Visitor.class, visitorId);
		if (null != visitor) {
			ses.delete(visitor);
		}
		ses.flush();
		ses.close();
	}
}
