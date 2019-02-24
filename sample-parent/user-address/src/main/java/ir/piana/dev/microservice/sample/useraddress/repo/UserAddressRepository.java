package ir.piana.dev.microservice.sample.useraddress.repo;

import ir.piana.dev.microservice.core.authenticate.QPPrincipal;
import ir.piana.dev.microservice.sample.useraddress.entity.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mohammad Rahmati, 2/21/2019
 */
@Repository
public interface UserAddressRepository extends JpaRepository<UserAddressEntity, Long> {
    UserAddressEntity findByPrincipalEntity(QPPrincipal principal);
}
