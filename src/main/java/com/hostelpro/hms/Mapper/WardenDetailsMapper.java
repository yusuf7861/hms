package com.hostelpro.hms.mapper;

import com.hostelpro.hms.dto.CreateWardenDto;
import com.hostelpro.hms.dto.WardenDetailsDto;
import com.hostelpro.hms.dto.WardenProfileDto;
import com.hostelpro.hms.entities.WardenDetails;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)public interface WardenDetailsMapper {
    WardenDetails toEntity(WardenDetailsDto wardenDetailsDto);

    WardenDetailsDto toDto(WardenDetails wardenDetails);

    WardenProfileDto toProfileDto(WardenDetails wardenDetails);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)WardenDetails partialUpdate(WardenDetailsDto wardenDetailsDto, @MappingTarget WardenDetails wardenDetails);

    // Create DTO -> Entity (without setting hostel)
    @Mapping(target = "hostel", ignore = true)
    WardenDetails toEntity(CreateWardenDto createWardenDto);
}