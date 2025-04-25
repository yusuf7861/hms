package com.hostelpro.hms.mapper;

/**
 * Projection for {@link com.hostelpro.hms.entities.StudentDetails}
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