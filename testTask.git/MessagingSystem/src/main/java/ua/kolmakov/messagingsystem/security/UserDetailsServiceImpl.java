package ua.kolmakov.messagingsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.kolmakov.messagingsystem.dao.UserDAO;
import ua.kolmakov.messagingsystem.domain.User;

/**
 * Implementation of the Spring Security interface UserDetailsService.
 * 
 * {@link UserDetailsService}
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;

	/**
	 * Override {@link UserDetailsService} interface loadUserByUsername(...)
	 * method, that locates the user based on the username.
	 * 
	 * @param username
	 *            The username identifying the user whose data is required.
	 * 
	 * @return SecurityUser object.
	 */
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		User user = userDAO.getUserByNickname(username);

		return new SecurityUser(user);
	}

}
