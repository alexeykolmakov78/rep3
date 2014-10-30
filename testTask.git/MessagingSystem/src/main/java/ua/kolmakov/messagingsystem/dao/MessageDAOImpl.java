package ua.kolmakov.messagingsystem.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ua.kolmakov.messagingsystem.domain.Message;

/**
 * Implementation of the message data access interface {@link MessageDAO} This
 * implementation use Hibernate framework for database access.
 * 
 */

@Repository
public class MessageDAOImpl implements MessageDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void removeMessage(Integer id) {
		Message message = (Message) sessionFactory.getCurrentSession().load(Message.class, id);
		if (null != message) {
			sessionFactory.getCurrentSession().delete(message);
		}
	}
}
