package pl.pfranczak.springsecurityregistrationlogindemo.user;

import java.util.Optional;

import pl.pfranczak.springsecurityregistrationlogindemo.entity.User;

public interface UserRepository {

	Optional<User> findByEmail(String email);
	
}
