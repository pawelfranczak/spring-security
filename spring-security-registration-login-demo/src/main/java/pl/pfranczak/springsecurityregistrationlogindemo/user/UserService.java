package pl.pfranczak.springsecurityregistrationlogindemo.user;

import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AllArgsConstructor;
import pl.pfranczak.springsecurityregistrationlogindemo.entity.User;

@AllArgsConstructor
public class UserService implements UserDetailsService {

	private final UserRepository userRepository = null;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final ConfirmationTokenService confirmationTokenService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		final Optional<User> optionalUser = userRepository.findByEmail(email);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		}
		else {
			throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found.", email));
		}
	}
	
	void signUpUser(User user) {
		final String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		final User createdUser = userRepository.save(user);
		final ConfirmationToken confirmationToken = new ConfirmationToken(user);
		confirmationTokenService.saveConfirmationToken(confirmationToken);
	}
	
	void confirmUser(ConfirmationToken confirmationToken) {
		final User user = confirmationToken.getUser();
		user.setEnabled(true);
		userRepository.save(user);
		confirmationTokenService.deleteConfirmationToken(confirmationToken.getId());
	}


}
