package ua.kolmakov.messagingsystem.security;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ua.kolmakov.messagingsystem.domain.User;

/**
 * Implementation of the Spring Security interface UserDetails. It is an adapter
 * class from User object to UserDetails object, that used by Spring Security
 * UserDetailsService for user authentication.
 */
public class SecurityUser implements UserDetails {

	/**
	 * Default serial version Id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Nickname of the authenticated user.
	 */
	private String username;

	/**
	 * Password of the authenticated user.
	 */
	private String password;

	/**
	 * Authority list of the authenticated user
	 */
	private Collection<GrantedAuthority> authorities;

	/**
	 * Constructor that initialized SecurityUser object with values of the User
	 * object
	 * 
	 * @param User
	 *            user
	 */
	public SecurityUser(final User user) {
		super();

		this.username = user.getNickname();
		this.password = user.getPassword();
		this.authorities = new HashSet<GrantedAuthority>();
		this.authorities.add(new GrantedAuthority() {

			private static final long serialVersionUID = 2L;

			@Override
			public String getAuthority() {

				return user.getRole().name();
			}
		});
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
