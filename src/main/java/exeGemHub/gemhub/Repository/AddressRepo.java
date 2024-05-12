package exeGemHub.gemhub.Repository;

import exeGemHub.gemhub.Entity.Address;
import exeGemHub.gemhub.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {

    List<Address> findAddressByUser(User user);

    Address findAddressByStatus(Boolean status);

}
