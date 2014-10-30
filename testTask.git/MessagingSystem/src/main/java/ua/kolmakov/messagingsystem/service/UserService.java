package ua.kolmakov.messagingsystem.service;

import java.util.List;

import ua.kolmakov.messagingsystem.domain.User;

/**
 * This is interface that exposes methods used by User services. This methods
 * include add, remove, update operations, allow to get list of users, find user
 * by unique identifier or nickname. Also, service include access methods to the
 * authorized and current users of the aplication.
 */
public interface UserService {

	/**
	 * Get user that login.
	 * 
	 * @return User object
	 */
	public User getAuthorizedUser();

	/**
	 * Get Principal name from SecurityContextHolder, find corresponding User
	 * and set it as authorizedUser.
	 */
	public void setAuthorizedUser();

	/**
	 * Get user as object of current operation. It's mainly used to
	 * administration operations.
	 * 
	 * @return User object
	 */
	public User getUser();

	/**
	 * Set user as object of current operation.It's mainly used to
	 * administration operations.
	 * 
	 * @param user
	 *            User object to set.
	 */
	public void setUser(User user);

	/**
	 * Find user by his nickname and set user as object of current operation.
	 * 
	 * @param nickname
	 *            User's nickname to find and set user.
	 */
	public void setUser(String nickname);

	/**
	 * Get list of all users, existing in system.
	 * 
	 * @return listUser - The list of users.
	 */
	public List<User> listUser();

	/**
	 * Add new user to database.
	 * 
	 * @param user
	 *            User that will be added.
	 * @return boolean result of operation: true if success, otherwise return
	 *         false.
	 */
	public boolean addUser(User user);

	/**
	 * Remove user from database.
	 * 
	 * @param id
	 *            Unique identifier of the user, that will be removed.
	 */
	public void removeUser(Integer id);

	/**
	 * Get User object by unique identifier.
	 * 
	 * @param id
	 *            Unique identifier of the user.
	 * @return User object with corresponding id or null.
	 */
	public User getUserById(Integer id);

	/**
	 * Get User object by nickname.
	 * 
	 * @param nickname
	 *            Nickname of the user.
	 * @return User object with corresponding nickname or null.
	 */
	public User getUserByNickname(String nickname);

	/**
	 * Update user in database.
	 * 
	 * @param user
	 *            User that will be updated.
	 * @return boolean result of operation: true if success, otherwise return
	 *         false.
	 */
	public boolean updateUser(User user);

	/**
	 * Checking existence of user with specified nickname in database.
	 * 
	 * @param nickname
	 *            Nickname of user that will be checked.
	 * @return boolean result of operation: true if exist, otherwise return
	 *         false.
	 */
	public boolean isNicknameExist(String nickname);

}
