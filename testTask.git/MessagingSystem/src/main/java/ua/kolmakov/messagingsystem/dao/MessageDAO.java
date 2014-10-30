package ua.kolmakov.messagingsystem.dao;

/**
 * This is interface that exposes method of directly access to message table of
 * the data base.
 * 
 * Method of this interface was never used by this application. It can be used
 * to delete messages, that was removed from sender's and receiver's message
 * lists.
 * 
 */
public interface MessageDAO {

	/**
	 * Remove message object directly from database.
	 * 
	 * @param id
	 *            Unique identifier of the message, that will be removed.
	 */
	public void removeMessage(Integer id);
}
