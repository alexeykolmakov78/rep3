package ua.kolmakov.messagingsystem.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ua.kolmakov.messagingsystem.domain.User;

/**
 * Implementation of the user data access interface {@link UserDAO} This
 * implementation use Hibernate framework for database access.
 */
@Repository
public class UserDAOImpl implements UserDAO {

	/** SessionFactory object to use in methods */
	@Autowired
	private SessionFactory sessionFactory;

	public void addUser(User user) {

		sessionFactory.getCurrentSession().save(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> listUser() {

		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	public void removeUser(Integer id) {

		User user = (User) sessionFactory.getCurrentSession().load(User.class, id);
		if (null != user) {
			sessionFactory.getCurrentSession().delete(user);

		}
	}

	public User getUserByNickname(String nickname) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User WHERE NICKNAME= :nickname");
		query.setParameter("nickname", nickname);
		User user = (User) query.uniqueResult();
		session.evict(user);
		return user;

	}

	public User getUserById(Integer id) {

		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	public void updateUser(User user) {

		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

}
