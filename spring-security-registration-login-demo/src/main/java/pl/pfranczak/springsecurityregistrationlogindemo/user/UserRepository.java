package pl.pfranczak.springsecurityregistrationlogindemo.user;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import pl.pfranczak.springsecurityregistrationlogindemo.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
}
