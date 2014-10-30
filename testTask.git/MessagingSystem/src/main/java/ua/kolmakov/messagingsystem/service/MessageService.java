package ua.kolmakov.messagingsystem.service;

import java.util.List;

import ua.kolmakov.messagingsystem.domain.Message;
import ua.kolmakov.messagingsystem.enums.MessageType;

/**
 * This is interface that exposes methods used by Message services. This methods
 * include add, remove operations and allow to get list of messages.
 */
public interface MessageService {

	/**
	 * Adds message to lists of messages those users, that are sender or
	 * receiver of this message.
	 * 
	 * @param messege
	 *            Message that will be added.
	 */
	public void addMessage(Message message);

	/**
	 * Gets all user's messages specified by messagetype
	 * 
	 * @param messagetype
	 *            Type of the required message {@link MessageType}.
	 * 
	 * @param nickname
	 *            Nickname of the user
	 * 
	 * @return List of messages or empty list if there are no messages. Return
	 *         null if error.
	 */
	public List<Message> listMessage(MessageType messagetype, String nickname);

	/**
	 * Remove message from user's list of messages.
	 * 
	 * @param id
	 *            Unique identifier of the message that will be removed.
	 */
	public void removeMessage(Integer id);
}
