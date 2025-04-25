package com.hostelpro.hms.mapper;

import com.hostelpro.hms.dto.RoomDto;
import com.hostelpro.hms.entities.Room;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomMapper {
    Room toEntity(RoomDto roomDto);

    RoomDto toDto(Room room);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Room partialUpdate(RoomDto roomDto, @MappingTarget Room room);
}