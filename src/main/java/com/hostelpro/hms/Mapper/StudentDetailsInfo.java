package com.hostelpro.hms.Mapper;

/**
 * Projection for {@link com.hostelpro.hms.Entities.StudentDetails}
 */
public interface StudentDetailsInfo {
//    Long getId();

    String getName();

    String getGuardianName();

    String getGuardianContactNumber();

    String getGender();

    String getPhone();

    String getDepartment();

    String getAddress();

    String getCollegeName();
}