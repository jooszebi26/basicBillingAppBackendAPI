package hu.Szebasztian.basicBillManagementApp.services;

import hu.Szebasztian.basicBillManagementApp.data.entities.Bill;
import hu.Szebasztian.basicBillManagementApp.data.repositories.BillRepo;
import hu.Szebasztian.basicBillManagementApp.services.businesslogicexpections.BillNotFoundException;
import hu.Szebasztian.basicBillManagementApp.services.businesslogicexpections.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminBillService {

    private final BillRepo billRepo;
    private final AdminUserService adminUserService;

    public List<Bill> listAllBills(){
        return billRepo.findAll();
    }

    public List<Bill> listAllBillsByUsername(String username){
        return billRepo.findAllByUsername(adminUserService.listUserByUsername(username).getUsername());
    }

    public Bill listBillById(long id){
        return billRepo.findById(id).orElseThrow(
                () -> new BillNotFoundException(id)
        );
    }

    public Bill listBillByIdAndUsername(long id, String username) {
        return billRepo.findByIdAndUsername(
                id, adminUserService.listUserByUsername(username).getUsername())
                .orElseThrow(() -> new BillNotFoundException(id));
    }

    public Bill createBill(String username, LocalDate exhibitionDate,
                           LocalDate dueDate, String comment,
                           String name, int amount) throws UserNotFoundException
    {

        Bill bill = Bill.builder()
                .username(adminUserService.listUserByUsername(username).getUsername())
                .exhibitionDate(exhibitionDate)
                .dueDate(dueDate)
                .name(name)
                .comment(comment)
                .amount(amount)
                .build();

        return billRepo.save(bill);

    }

    public void deleteBillById(long id){
        billRepo.delete(listBillById(id));
    }

    public void deleteBillByIdAndUsername(long id, String username){
        billRepo.delete(listBillByIdAndUsername(id, username));
    }

}
