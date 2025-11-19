package hu.Szebasztian.basicBillManagementApp.services.mappers;

import hu.Szebasztian.basicBillManagementApp.data.entities.Bill;
import hu.Szebasztian.basicBillManagementApp.dtos.responses.AdminBillRes;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminBillMapper {

    AdminBillRes entityToDto(Bill bill);
    List<AdminBillRes> entitiesToDtos(List<Bill> bills);

}
