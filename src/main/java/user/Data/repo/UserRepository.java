package user.Data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user.Data.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User getByUsername(String username);
	User getByEmail(String email);
}
