package hu.Szebasztian.basicBillManagementApp.services.mappers;

import hu.Szebasztian.basicBillManagementApp.data.entities.Bill;
import hu.Szebasztian.basicBillManagementApp.dtos.responses.UserBillRes;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserBillMapper {

    UserBillRes entityToDto(Bill bill);
    List<UserBillRes> entitiesToDtos(List<Bill> bills);

}
