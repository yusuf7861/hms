package com.hostelpro.hms.mapper;

import com.hostelpro.hms.dto.HostelDtoInfo;
import com.hostelpro.hms.entities.Hostel;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface HostelMapper {
    Hostel toEntity(HostelDtoInfo hostelDtoInfo);

    @AfterMapping
    default void linkRooms(@MappingTarget Hostel hostel) {
        hostel.getRooms().forEach(room -> room.setHostel(hostel));
    }

    HostelDtoInfo toDto(Hostel hostel);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Hostel partialUpdate(HostelDtoInfo hostelDtoInfo, @MappingTarget Hostel hostel);
}