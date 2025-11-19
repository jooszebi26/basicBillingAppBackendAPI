package hu.Szebasztian.basicBillManagementApp.data.repositories;

import hu.Szebasztian.basicBillManagementApp.data.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BillRepo extends JpaRepository<Bill, Long> {

    Optional<Bill> findByIdAndUsername(long id, String username);
    List<Bill> findAllByUsername(String username);

}
