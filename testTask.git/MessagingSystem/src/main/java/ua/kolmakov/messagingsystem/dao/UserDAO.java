package ua.kolmakov.messagingsystem.dao;

import java.util.List;

import ua.kolmakov.messagingsystem.domain.User;

/**
 * This is interface that exposes methods of directly access to the data base.
 * This methods include add, remove, update operations, allow to get list of
 * users, find user by unique identifier or nickname.
 */
public interface UserDAO {

	/**
	 * Load list of all users, from database.
	 * 
	 * @return listUser - The list of users.
	 */
	public List<User> listUser();

	/**
	 * Add new user to database.
	 * 
	 * @param user
	 *            User that will be added.
	 */
	public void addUser(User user);

	/**
	 * Remove user from database.
	 * 
	 * @param id
	 *            Unique identifier of the user, that will be removed.
	 */
	public void removeUser(Integer id);

	/**
	 * Load User object by nickname from database.
	 * 
	 * @param nickname
	 *            Nickname of the user.
	 */
	public User getUserByNickname(String nickname);

	/**
	 * Load User object by unique identifier from database.
	 * 
	 * @param id
	 *            Unique identifier of the user.
	 */
	public User getUserById(Integer id);

	/**
	 * Update user in database.
	 * 
	 * @param user
	 *            User that will be updated.
	 */
	public void updateUser(User user);

}
