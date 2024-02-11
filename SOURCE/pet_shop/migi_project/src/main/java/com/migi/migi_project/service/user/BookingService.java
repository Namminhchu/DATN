package com.migi.migi_project.service.user;

import com.migi.migi_project.entity.Booking;
import com.migi.migi_project.model.dto.BookingDTO;
import com.migi.migi_project.model.dto.Revenue;
import com.migi.migi_project.model.response.ResponseNormal;

import java.util.List;

public interface BookingService {
    ResponseNormal bookService(BookingDTO booking);
    ResponseNormal updateStatus(BookingDTO booking);
    ResponseNormal deleteBooking (Integer id);
    List<BookingDTO> getAllByCurrentUser(Integer userId);

    List<Revenue> getAppointment(String year);
    List<BookingDTO> getAll();
}
