package ua.kolmakov.messagingsystem.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ua.kolmakov.messagingsystem.domain.Message;
import ua.kolmakov.messagingsystem.domain.User;
import ua.kolmakov.messagingsystem.enums.MessageType;
import ua.kolmakov.messagingsystem.service.MessageService;
import ua.kolmakov.messagingsystem.service.UserService;

/**
 * This class include controller methods for all pages and references of the
 * application.
 * 
 */
@Controller
public class AllController {

	/** Message service to use in methods */
	@Autowired
	private MessageService messageService;

	/** User service to use in methods */
	@Autowired
	private UserService userService;

	/*-------------------------------------home----------------------------------------------*/

	/**
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String toHomePage() {

		return "redirect:/home";
	}

	/**
	 * Home page controller method. It handles request "/home", sets values of
	 * authorized user and user for current operation, and also return the name
	 * of jsp that being called.
	 * 
	 * @return name of jsp that being called ("home").
	 */
	@RequestMapping("/home")
	public String home() {

		userService.setAuthorizedUser();
		userService.setUser(userService.getAuthorizedUser());

		return "home";
	}

	/*--------------------------------------send---------------------------------------------*/

	/**
	 * Send page controller method. It handles request "/send" method GET.
	 * Creates the model for send.jsp (page for sending messages) and adds to it
	 * new Message object, that will be initialized when message will sent.
	 * 
	 * Finally return the name of jsp that being called.
	 * 
	 * @param model
	 *            Model of the sehd.jsp.
	 * @return name of jsp that being called ("send").
	 */
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public String toSendPage(Model model) {

		model.addAttribute("message", new Message());

		return "send";
	}

	/**
	 * Send page controller method. It handles request "/send" method POST. The
	 * data, that was entered by the user are validate. If it has no errors,
	 * message added and displays success message, otherwise validator displays
	 * error messages.
	 * 
	 * Finally return the name of jsp that being called.
	 * 
	 * @param message
	 *            Message object from send.jsp.
	 * @param result
	 *            BindingResult object.
	 * @return name of jsp that being called ("send").
	 */
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String sendSubmit(@Valid @ModelAttribute("message") Message message, @Valid BindingResult result) {

		if (!result.hasErrors()) {
			messageService.addMessage(message);

			result.addError(new ObjectError("message", "Сообщение отправлено"));
		}
		return "send";
	}

	/**
	 * Message page controller method. It handles request
	 * "/message/{messagetype}" method GET. Depending on the message type (sent
	 * or received), creates the model for message.jsp (page to view the
	 * messages) and adds user's nickname, type of message and list of
	 * corresponding messages to it.
	 * 
	 * Finally return the name of jsp that being called.
	 * 
	 * @param messagetype
	 * 
	 * @param model
	 *            Model of the message.jsp.
	 * @return @return name of jsp that being called ("message").
	 */
	@RequestMapping(value = "/message/{messagetype}", method = RequestMethod.GET)
	public String toMessagePage(@PathVariable("messagetype") MessageType messagetype, Model model) {

		String nickname = userService.getUser().getNickname();

		model.addAttribute("messagetype", messagetype);
		model.addAttribute("nickname", nickname);
		model.addAttribute("messageList", messageService.listMessage(messagetype, nickname));

		return "message";
	}

	/**
	 * Handles request "/message/{messagetype}/{nickname}" from user.jsp, sets
	 * user for current operation in accordance to nickname, and redirect
	 * request to message.jsp with messagetype parameter.
	 * 
	 * This is an administrator option.
	 * 
	 * @param nickname
	 *            Nickame of current user.
	 * @param messagetype
	 *            Type of messages to view.
	 * @return redirect url
	 */
	@RequestMapping("/message/{messagetype}/{nickname}")
	public String configMessagePage(@PathVariable("nickname") String nickname, @PathVariable("messagetype") MessageType messagetype) {

		userService.setUser(nickname);

		return "redirect:/message/" + messagetype;
	}

	/**
	 * Handles request "/deletemessage/{messagetype}/{nickname}/{id}" from
	 * user.jsp or message.jsp, sets user for current operation in accordance to
	 * nickname, remove message with given id from current user's message list,
	 * and redirect request to message.jsp with messagetype and nickname
	 * parameters.
	 * 
	 * @param messagetype
	 *            Type of message (sent or received).
	 * @param nickname
	 *            Nickname of user, which are sender or receiver of message
	 *            according to message type.
	 * @param id
	 *            Message unique identifier to delete
	 * @return redirect url
	 */
	@RequestMapping("/deletemessage/{messagetype}/{nickname}/{id}")
	public String deleteMessage(@PathVariable("messagetype") MessageType messagetype, @PathVariable("nickname") String nickname, @PathVariable("id") Integer id) {

		userService.setUser(nickname);
		messageService.removeMessage(id);

		return "redirect:/message/" + messagetype.name() + "/" + nickname;
	}

	/*-----------------------createuser, edit, deleteuser------------------------------------*/

	/**
	 * User page controller method. It handles request "/user". Creates the
	 * model for user.jsp (page to view all users) and adds authorized user's
	 * role and list of all users to it. Authorized user's role is used on
	 * user.jsp to configure it.
	 * 
	 * Finally return the name of jsp that being called.
	 * 
	 * 
	 * @param model
	 *            Model of the user.jsp.
	 * @return name of jsp that being called ("user").
	 */
	@RequestMapping("/user")
	public String toUserPage(Model model) {

		model.addAttribute("role", userService.getAuthorizedUser().getRole());
		model.addAttribute("userList", userService.listUser());

		return "user";
	}

	/**
	 * Createuser page controller method. It handles request "/createuser"
	 * method GET. Model of createuser.jsp include: current user object, binding
	 * result, success/failed message. The data, that was entered by the user
	 * are validate. If it has no errors, depending to values of arole and
	 * user.id, there are added new or updated existing user. Also,
	 * corresponding result messages are added.
	 * 
	 * @param model
	 *            Model of the createuser.jsp.
	 * @param user
	 *            User object for current operation.
	 * @param result
	 *            BindingResult object.
	 * @return name of jsp that being called ("createuser").
	 */
	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	public String createUserSubmit(Model model, @Valid @ModelAttribute("user") User user, BindingResult result) {

		model.addAttribute("arole", userService.getAuthorizedUser().getRole());

		if (!result.hasErrors()) {

			user.setId(userService.getUser().getId());

			if (null != user.getId()) {

				if (userService.updateUser(user)) {
					model.addAttribute("user", user);
					model.addAttribute("success", "Изменения сохранены");
				} else
					model.addAttribute("failed", "Никнейм уже занят");

			} else {

				if (userService.addUser(user)) {
					model.addAttribute("user", new User());
					model.addAttribute("success", "Добавлен новый пользователь");
				} else
					model.addAttribute("failed", "Никнейм уже занят");
			}
		}
		return "createuser";
	}

	/**
	 * Createuser page controller method. It handles request "/createuser"
	 * method GET. Creates the model for createuser.jsp (page to create new and
	 * edit exist users), adds current user and role of authorized user to it.
	 * Authorized user's arole and user.id are used on createuser.jsp to
	 * configure it. Depending to values of arole and user.id, will be create
	 * new or edited existing user on createuser.jsp.
	 * 
	 * @param model
	 *            Model of the createuser.jsp.
	 * @return name of jsp that being called ("createuser").
	 */
	@RequestMapping(value = "/createuser", method = RequestMethod.GET)
	public String toCreateUserPage(Model model) {

		User user = userService.getUser();
		model.addAttribute("user", user);
		model.addAttribute("arole", userService.getAuthorizedUser().getRole());

		return "createuser";
	}

	/**
	 * Handles request "/edituser/{id}" from user.jsp, sets user for current
	 * operation in accordance to given id, and redirect to createuser.jsp
	 * 
	 * @param id
	 *            Unique identifier of user to edit.
	 * @return redirect url
	 */
	@RequestMapping("/edituser/{id}")
	public String editUser(@PathVariable("id") Integer id) {

		userService.setUser(userService.getUserById(id));

		return "redirect:/createuser";
	}

	/**
	 * Handles request "/newuser" from user.jsp, sets new user for current
	 * operation and redirect to createuser.jsp
	 * 
	 * @return redirect url
	 */
	@RequestMapping("/newuser")
	public String newUser() {

		userService.setUser(new User());

		return "redirect:/createuser";
	}

	/**
	 * Handles request "/deleteuser/{id}" from user.jsp, remove user with given
	 * id from database, and redirect request to user.jsp.
	 * 
	 * @param id
	 *            Unique identifier of user.
	 * @return redirect url
	 */
	@RequestMapping("/deleteuser/{id}")
	public String deleteUser(@PathVariable("id") Integer id) {

		userService.removeUser(id);

		return "redirect:/user";
	}

	/*--------------------------       Exception      ---------------------------------------*/

	/**
	 * Catching all exceptions, that are possible in controller. If you delete
	 * your account, you'll see error message. Return exception.jsp contains
	 * error message.
	 * 
	 * @param e
	 *            Exception e
	 * @return modelAndView object
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {

		ModelAndView modelAndView = new ModelAndView("/exception");
		modelAndView.addObject("message", "ПРОИЗОШЛА ОШИБКА");

		return modelAndView;
	}
}
