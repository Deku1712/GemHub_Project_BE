package exeGemHub.gemhub.Repository;

import exeGemHub.gemhub.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User ,Integer> {
}
