package hu.Szebasztian.basicBillManagementApp.services.businesslogicexpections;

public class BillNotFoundException extends BusinessException {
    public BillNotFoundException(long billId) {
        super("BILL_NOT_FOUND", "Bill not found with id: " + billId);
    }
}
