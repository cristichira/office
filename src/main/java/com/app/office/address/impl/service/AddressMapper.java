package com.app.office.address.impl.service;

import com.app.office.address.api.dto.AddressDTO;
import com.app.office.address.domain.Address;
import com.app.office.shared.api.dto.NameIdDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class AddressMapper {

    abstract AddressDTO toDTO(Address address);

    @Mapping(target = "name", expression = "java(toDisplayName(address))")
    abstract NameIdDTO toNameIdDTO(Address address);

    String toDisplayName(Address address) {
        return address.getAddressLine() + ", " +
                address.getCity() + ", " +
                address.getPostalCode() + ", " +
                address.getRegion() + ", " +
                address.getCountry();
    }
}
