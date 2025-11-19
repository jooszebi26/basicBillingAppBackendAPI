package hu.Szebasztian.basicBillManagementApp.services;

import hu.Szebasztian.basicBillManagementApp.data.entities.Bill;
import hu.Szebasztian.basicBillManagementApp.data.repositories.BillRepo;
import hu.Szebasztian.basicBillManagementApp.services.businesslogicexpections.BillNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBillService {

    final BillRepo billRepo;

    public List<Bill> listMyBills(String username) {
        return billRepo.findAllByUsername(username);
    }

    public Bill listMyBillById(Long id, String username) {
        return billRepo.findByIdAndUsername(id, username)
                .orElseThrow(
                        () -> new BillNotFoundException(id)
                );
    }

}
