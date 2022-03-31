package me.dyatkokg.balancecachingapi.mapper;

import me.dyatkokg.balancecachingapi.dto.BalanceDTO;
import me.dyatkokg.balancecachingapi.entity.Balance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BalanceMapper {

    @Mapping(source = "subscriber.firstName",target = "firstName")
    @Mapping(source = "subscriber.lastName", target = "lastName")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "balance", target = "balance")
    BalanceDTO toDTO(Balance balance);

    @Mapping(source = "firstName",target = "subscriber.firstName")
    @Mapping(source = "lastName", target = "subscriber.lastName")
    @Mapping(source = "id", target = "id")
    @Mapping(source = "balance", target = "balance")
    Balance toEntity(BalanceDTO balanceDTO);


}
