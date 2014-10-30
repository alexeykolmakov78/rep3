package ua.kolmakov.messagingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.kolmakov.messagingsystem.dao.UserDAO;
import ua.kolmakov.messagingsystem.domain.User;

/**
 * Implementation of the user service {@link UserService}
 */
@Service
public class UserServiceImpl implements UserService {

	/** UserDAO object to use in methods */
	@Autowired
	private UserDAO userDAO;

	/**
	 * User, authorized in session (user that login).
	 */
	private User authorizedUser;

	/**
	 * User as an object of current operation.
	 */
	private User user;

	public User getAuthorizedUser() {
		return authorizedUser;
	}

	@Transactional
	public void setAuthorizedUser() {
		this.authorizedUser = getUserByNickname(SecurityContextHolder.getContext().getAuthentication().getName());
	}

	public User getUser() {

		return user;
	}

	public void setUser(User user) {

		this.user = user;
	}

	@Transactional
	public void setUser(String nickname) {

		this.user = userDAO.getUserByNickname(nickname);
	}

	@Transactional
	public boolean addUser(User user) {

		if (!isNicknameExist(user.getNickname())) {
			userDAO.addUser(user);
			return true;
		}
		return false;
	}

	@Transactional
	public List<User> listUser() {

		return userDAO.listUser();
	}

	@Transactional
	public void removeUser(Integer id) {

		userDAO.removeUser(id);
	}

	@Transactional
	public User getUserByNickname(String nickname) {

		return userDAO.getUserByNickname(nickname);
	}

	@Transactional
	public User getUserById(Integer id) {

		return userDAO.getUserById(id);
	}

	@Transactional
	public boolean updateUser(User user) {

		User etalon;

		if (isNicknameExist(user.getNickname())) {

			etalon = getUserByNickname(user.getNickname());

			if (user.getId().equals(etalon.getId())) {
				user.setMessages(etalon.getMessages());
				userDAO.updateUser(user);
				return true;
			} else
				return false;
		} else
			userDAO.updateUser(user);
		return true;
	}

	@Transactional
	public boolean isNicknameExist(String nickname) {

		if (null == userDAO.getUserByNickname(nickname))
			return false;
		else
			return true;
	}
}
