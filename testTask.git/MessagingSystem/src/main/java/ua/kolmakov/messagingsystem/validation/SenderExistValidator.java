package ua.kolmakov.messagingsystem.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import ua.kolmakov.messagingsystem.service.UserService;

/**
 * Implementation of interface {@link ConstraintValidator} that will validate
 * custom annotation @{@link SenderExist}.
 */
public class SenderExistValidator implements ConstraintValidator<SenderExist, String> {

	/**
	 * Default error message of @SenderExist
	 */
	private String message = SenderExist.MESSAGE;

	/** User service to use in methods */
	@Autowired
	private UserService userService;

	@Override
	public void initialize(SenderExist constraintAnnotation) {
		this.message = constraintAnnotation.message();

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		boolean match = (userService.isNicknameExist(value));

		if (!match) {
			String msg = this.message;
			if (this.message == null || "".equals(this.message) || SenderExist.MESSAGE.equals(this.message)) {
				msg = "This field already exists.";
			}
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
		}
		return match;
	}

}
