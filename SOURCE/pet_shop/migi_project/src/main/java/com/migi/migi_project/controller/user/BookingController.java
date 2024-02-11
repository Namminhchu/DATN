package com.migi.migi_project.controller.user;

import com.migi.migi_project.entity.Booking;
import com.migi.migi_project.model.dto.BookingDTO;
import com.migi.migi_project.model.dto.OrdersDTO;
import com.migi.migi_project.service.user.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.migi.migi_project.model.dto.Revenue;
import java.util.List;
import com.migi.migi_project.model.dto.Revenue;


@RestController
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @PostMapping(value = "booking")
    public ResponseEntity<?> bookService(@RequestBody BookingDTO booking){
        return ResponseEntity.ok(bookingService.bookService(booking));
    }

    @PutMapping(value = "booking/status")
    public ResponseEntity<?> updateStatus(@RequestBody BookingDTO booking){
        return ResponseEntity.ok(bookingService.updateStatus(booking));
    }

    @GetMapping(value = "booking/user/{user-id}")
    public ResponseEntity<?> getAllByCurrentUser(@PathVariable(value = "user-id") Integer userId){
        return ResponseEntity.ok(bookingService.getAllByCurrentUser(userId));
    }

    @DeleteMapping(value = "booking/{id}")
    public  ResponseEntity<?> deleteBooking(@PathVariable(value = "id") Integer id){
        return ResponseEntity.ok(bookingService.deleteBooking(id));
    }


    @GetMapping(value = "booking")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(bookingService.getAll());
    }

    @GetMapping(value = "/admin/revenue/service")
    public ResponseEntity<List<Revenue>> getAppointment(@RequestParam(value = "year") String year) {
        List<Revenue> revenues = bookingService.getAppointment(year);
        return ResponseEntity.ok(revenues);
    }
}
