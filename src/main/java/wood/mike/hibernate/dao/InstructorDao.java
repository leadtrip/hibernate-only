package wood.mike.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import wood.mike.hibernate.entity.Instructor;
import wood.mike.hibernate.util.HibernateUtil;

import javax.persistence.EntityGraph;
import java.util.HashMap;
import java.util.Map;

public class InstructorDao {
	public void saveInstructor(Instructor instructor) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(instructor);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void updateInstructor(Instructor instructor) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(instructor);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteInstructor(int id) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Instructor instructor = session.get(Instructor.class, id);
			if (instructor != null) {
				session.delete(instructor);
				System.out.println("instructor is deleted");
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Instructor getInstructor(int id) {

		Transaction transaction = null;
		Instructor instructor = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			instructor = session.find(Instructor.class, id, getProperties(session));
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return instructor;
	}

	private Map<String, Object> getProperties(Session session) {
		EntityGraph<Instructor> entityGraph = session.createEntityGraph(Instructor.class);
		entityGraph.addAttributeNodes("courses");
		Map<String, Object> properties = new HashMap<>();
		properties.put("javax.persistence.fetchgraph", entityGraph);
		return properties;
	}
}
