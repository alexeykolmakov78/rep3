package ua.kolmakov.messagingsystem.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import ua.kolmakov.messagingsystem.validation.SenderExist;

/**
 * Class Message entity, that makes it possible to store message in DB.
 */
@Entity
@Table(name = "MESSAGES")
public class Message {

	/**
	 * Unique identifier.
	 */
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Integer id;

	/**
	 * Theme of the message.
	 */
	@NotBlank(message = "Пожалуйста заполните поле")
	@Column(name = "THEME")
	private String theme;

	/**
	 * Text of the message.
	 */
	@Lob
	@Column(name = "TEXT")
	private String text;

	/**
	 * Message receiver nickname.
	 */
	@SenderExist(message = "Пользователь не существует")
	@Column(name = "RECEIVER")
	private String receiver;

	/**
	 * Message sender nickname.
	 */
	@Column(name = "SENDER")
	private String sender;

	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            Id of the message to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the theme
	 */
	public String getTheme() {
		return theme;
	}

	/**
	 * @param theme
	 *            the theme to set
	 */
	public void setTheme(String theme) {
		this.theme = theme;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @return the receiver
	 */
	public String getReceiver() {
		return receiver;
	}

	/**
	 * @param receiver
	 *            the receiver to set
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * @param sender
	 *            the sender to set
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

}
