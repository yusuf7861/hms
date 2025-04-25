package com.hostelpro.hms.repositories;

import com.hostelpro.hms.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {
    Room getRoomByRoomNumber(String roomNumber);
    Room getRoomById(Long id);

    List<Room> getRoomByAvailable(boolean available);
}