package com.hostelpro.hms.mapper;

import com.hostelpro.hms.dto.StudentDetailsDto;
import com.hostelpro.hms.dto.StudentSummaryDto;
import com.hostelpro.hms.entities.StudentDetails;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    StudentDetails toEntity(StudentSummaryDto studentSummaryDto);

    StudentSummaryDto toDto(StudentDetails studentDetails);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StudentDetails partialUpdate(StudentSummaryDto studentSummaryDto, @MappingTarget StudentDetails studentDetails);

    StudentDetailsDto toDto1(StudentDetails studentDetails);
}