package ua.kolmakov.messagingsystem.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import ua.kolmakov.messagingsystem.enums.Roles;

/**
 * Class User entity, that makes it possible to store user in DB.
 */
@Entity
@Table(name = "USERS")
public class User {

	/**
	 * Unique identifier.
	 */
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;

	/**
	 * Firstname of the user.
	 */
	@NotBlank(message = "Пожалуйста заполните поле")
	@Column(name = "FIRSTNAME")
	private String firstname;

	/**
	 * Lastname of the user.
	 */
	@NotBlank(message = "Пожалуйста заполните поле")
	@Column(name = "LASTNAME")
	private String lastname;

	/**
	 * Nickname of the user.
	 */
	@NotBlank(message = "Пожалуйста заполните поле")
	@Column(name = "NICKNAME")
	private String nickname;

	/**
	 * Password of the user.
	 */
	@NotEmpty(message = "Пожалуйста заполните поле")
	@Column(name = "PASSWORD")
	private String password;

	/**
	 * Role (access level) of the user.
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "ROLE")
	private Roles role;

	/**
	 * List of sent and received messages of the user.
	 */
	@OneToMany(fetch = FetchType.EAGER)
	@Cascade(value = { CascadeType.SAVE_UPDATE })
	@Column(name = "MESSAGES")
	@JoinTable(name = "USERS_MESSAGES", joinColumns = { @JoinColumn(name = "USERS_ID") }, inverseJoinColumns = { @JoinColumn(name = "MESSAGES_ID") })
	private List<Message> messages;

	/**
	 * @param message
	 *            Add message to the user messages.
	 */
	public void addMessage(Message message) {
		this.messages.add(message);
	}

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            Id of the user to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return firstname
	 */
	public String getFirstname() {
		return firstname;
	}

	/**
	 * @param firstname
	 *            Firstname of the user to set.
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 * @return lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname
	 *            Lastname of the user to set.
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            Nickname of the user to set.
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            Password of the user to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return role
	 */
	public Roles getRole() {
		return role;
	}

	/**
	 * @param role
	 *            Role of the user to set.
	 */
	public void setRole(Roles role) {
		this.role = role;
	}

	/**
	 * @return messages
	 */
	public List<Message> getMessages() {
		return messages;
	}

	/**
	 * @param messages
	 *            Messages of the user to set.
	 */
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}