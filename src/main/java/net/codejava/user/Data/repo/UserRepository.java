package net.codejava.user.Data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import net.codejava.user.Data.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User getByEmail(String email);

	User findByEmailAndPassword(String email, String password);

	User findByToken(String token);
}

