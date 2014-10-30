package ua.kolmakov.messagingsystem.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.kolmakov.messagingsystem.dao.MessageDAO;
import ua.kolmakov.messagingsystem.dao.UserDAO;
import ua.kolmakov.messagingsystem.domain.Message;
import ua.kolmakov.messagingsystem.domain.User;
import ua.kolmakov.messagingsystem.enums.MessageType;

/**
 * Implementation of the message service {@link MessageService}
 */
@Service
public class MessageServiceImpl implements MessageService {

	/** MessageDAO object to use in methods */
	@Autowired
	private MessageDAO messageDAO;

	/** UserDAO object to use in methods */
	@Autowired
	private UserDAO userDAO;

	/** User service to use in methods */
	@Autowired
	private UserService userService;

	@Transactional
	public void addMessage(Message message) {

		message.setSender(userService.getAuthorizedUser().getNickname());

		User sender = userService.getAuthorizedUser();
		sender.addMessage(message);
		userDAO.updateUser(sender);

		User receiver = userService.getUserByNickname(message.getReceiver());
		receiver.addMessage(message);
		userDAO.updateUser(receiver);
	}

	@Transactional
	public List<Message> listMessage(MessageType messagetype, String nickname) {

		if (messagetype.equals(MessageType.SENT))
			return getSent(nickname);
		else if (messagetype.equals(MessageType.RECEIVED))
			return getReceived(nickname);
		else
			return null;
	}

	/**
	 * Get all received messages from user's message list.
	 * 
	 * @param nickname
	 *            Nickname of the user
	 * 
	 * @return List of messages or empty list if there are no messages.
	 */
	private List<Message> getReceived(String nickname) {

		List<Message> tmp = userService.getUserByNickname(nickname).getMessages();
		List<Message> received = new ArrayList<Message>();
		for (Message m : tmp) {
			if (m.getReceiver().equals(nickname))
				received.add(m);
		}
		return received;
	}

	/**
	 * Get all sent messages from user's message list.
	 * 
	 * @param nickname
	 *            Nickname of the user
	 * 
	 * @return List of messages or empty list if there are no messages.
	 */
	private List<Message> getSent(String name) {

		List<Message> tmp = userService.getUserByNickname(name).getMessages();
		List<Message> sent = new ArrayList<Message>();
		for (Message m : tmp) {
			if (m.getSender().equals(name))
				sent.add(m);
		}
		return sent;
	}

	@Transactional
	public void removeMessage(Integer id) {

		User user = userService.getUser();

		Iterator<Message> i = user.getMessages().iterator();

		while (i.hasNext()) {
			Message msg = i.next();
			if (msg.getId().equals(id)) {
				i.remove();
			}
		}
		userDAO.updateUser(user);
	}

}
