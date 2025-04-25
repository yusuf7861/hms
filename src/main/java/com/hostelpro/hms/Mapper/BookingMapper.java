package com.hostelpro.hms.mapper;

import com.hostelpro.hms.dto.BookingDto;
import com.hostelpro.hms.entities.Booking;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {
    @Mapping(source = "studentDetails.studentName", target = "studentDetails.name")
    Booking toEntity(BookingDto bookingDto);

    @Mapping(source = "studentDetails.name", target = "studentDetails.studentName")
    BookingDto toDto(Booking booking);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "studentDetails.studentName", target = "studentDetails.name")
    Booking partialUpdate(BookingDto bookingDto, @MappingTarget Booking booking);
}