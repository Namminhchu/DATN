package com.migi.migi_project.repository.user;

import com.migi.migi_project.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findAllByClientId(Integer clientId);

    @Query(value = "SELECT date_format(b.appointment_date,'%m') as name, sum(s.price) as value \n" +
            "from booking as b inner join service as s on s.id = b.service_id\n" +
            "WHERE date_format(b.appointment_date,'%y') = ?1 AND status = 3 GROUP BY date_format(b.appointment_date,'%m')",
            nativeQuery = true)
    List<Object[]> getAppointment(String year);
}
