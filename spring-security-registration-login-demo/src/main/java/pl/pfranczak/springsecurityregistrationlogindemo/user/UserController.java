package pl.pfranczak.springsecurityregistrationlogindemo.user;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import pl.pfranczak.springsecurityregistrationlogindemo.entity.User;

@Controller
@AllArgsConstructor
public class UserController {
	private final UserService userService;

	private final ConfirmationTokenService confirmationTokenService;

	@GetMapping("/sign-in")
	String signIn() {
		return "sign-in";
	}
	
	@PostMapping("/sign-in")
	String signInPost() {
		return "success";
	}

	@GetMapping("/sign-up")
	String signUpPage(User user) {
		return "sign-up";
	}

	@PostMapping("/sign-up")
	String signUp(User user) {
		userService.signUpUser(user);
		return "redirect:/sign-in";
	}

	@GetMapping("/sign-up/confirm")
	String confirmMail(@RequestParam("token") String token) {
		Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);
		optionalConfirmationToken.ifPresent(userService::confirmUser);
		return "/sign-in";
	}
}
