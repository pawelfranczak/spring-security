package pl.pfranczak.springsecurityregistrationlogindemo.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pl.pfranczak.springsecurityregistrationlogindemo.entity.User;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
	
	private final ConfirmationTokenRepository confirmationTokenRepository;
	
	void saveConfirmationToken(ConfirmationToken confirmationToken) {
		confirmationTokenRepository.save(confirmationToken);
	}

	void deleteConfirmationToken(Long id) {
		confirmationTokenRepository.deleteById(id);
	}


	Optional<ConfirmationToken> findConfirmationTokenByToken(String token) {
		return confirmationTokenRepository.findConfirmationTokenByConfirmationToken(token);
	}
	
}
