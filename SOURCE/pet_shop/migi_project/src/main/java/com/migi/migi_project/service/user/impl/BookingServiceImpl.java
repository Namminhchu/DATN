package com.migi.migi_project.service.user.impl;

import com.migi.migi_project.entity.Booking;
import com.migi.migi_project.entity.ServiceWorker;
import com.migi.migi_project.model.dto.BookingDTO;
import com.migi.migi_project.model.dto.Revenue;
import com.migi.migi_project.model.response.ResponseNormal;
import com.migi.migi_project.repository.user.BookingRepository;
import com.migi.migi_project.repository.user.ServiceRepository;
import com.migi.migi_project.repository.user.ServiceWorkerRepository;
import com.migi.migi_project.service.user.BookingService;
import com.migi.migi_project.utils.DataUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceWorkerRepository serviceWorkerRepository;

    @Autowired
    private ModelMapper mapper;

    private static final Map<Integer, String> statusBooking = new HashMap<>();

    static {
        statusBooking.put(0, "Đặt lịch thành công & chờ nhận thú cưng");
        statusBooking.put(1, "Đã nhận thú cưng & tiến hành dịch vụ");
        statusBooking.put(2, "Đã hoàn thành");
        statusBooking.put(3, "Đã thanh toán");
    }

    @Override
    public ResponseNormal bookService(BookingDTO bookingDTO) {
        try {
            Booking booking = mapper.map(bookingDTO, Booking.class);
            booking.setCreateTime(Instant.now());
            booking.setStatus(0);

            bookingRepository.save(booking);
            return new ResponseNormal("Đặt lịch hẹn thành công", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseNormal("", HttpStatus.OK);
        }
    }

    @Override
    public ResponseNormal updateStatus(BookingDTO bookingDTO) {
        Booking booking = bookingRepository.findById(bookingDTO.getId()).orElse(null);
        if (booking == null) {
            return new ResponseNormal("", HttpStatus.OK);
        }

        booking.setStatus(bookingDTO.getStatus());
        bookingRepository.save(booking);
        return new ResponseNormal("", HttpStatus.OK);
    }

    @Override
    public ResponseNormal deleteBooking(Integer id) {
        try {
            bookingRepository.deleteById(id);
            return new ResponseNormal("Xóa lịch hẹn thành công", HttpStatus.OK);
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu cần thiết
            return new ResponseNormal("Lỗi khi xóa lịch hẹn", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<BookingDTO> getAllByCurrentUser(Integer userId) {
        List<com.migi.migi_project.entity.Service> services = serviceRepository.findAll();
        List<ServiceWorker> serviceWorkers = serviceWorkerRepository.findAll();

        return bookingRepository.findAllByClientId(userId)
                .stream().map(o -> {
                    BookingDTO bookingDTO = mapper.map(o, BookingDTO.class);
                    com.migi.migi_project.entity.Service service = services.stream().filter(s -> s.getId().equals(o.getServiceId()))
                            .findAny().orElse(new com.migi.migi_project.entity.Service());
                    if (service.getId() == null) {
                        return bookingDTO;
                    }
                    bookingDTO.setServiceName(service.getName());
                    bookingDTO.setPrice(service.getPrice());

                    ServiceWorker serviceWorker = serviceWorkers.stream().filter(s -> s.getId().equals(o.getServiceWorkerId()))
                            .findAny().orElse(new ServiceWorker());
                    if (serviceWorker.getId() == null) {
                        return bookingDTO;
                    }
                    bookingDTO.setWorkerName(serviceWorker.getFullname());
                    bookingDTO.setWorkerPhone(serviceWorker.getPhoneNumber());
                    bookingDTO.setStatusDisplay(statusBooking.get(bookingDTO.getStatus()));
                    bookingDTO.setAppointmentDateStr(DataUtils.toString(bookingDTO.getAppointmentDate()));

                    return bookingDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Revenue> getAppointment(String year) {
        String sub = year.substring(2, 4);
        List<Object[]> objects = bookingRepository.getAppointment(sub);
        List<Revenue> revenues1 = new ArrayList<>();
        for(Object[] o: objects){
            Revenue revenue = new Revenue();
            revenue.setName("Tháng "+ DataUtils.safeToString(o[0]));
            revenue.setValue(DataUtils.safeToDouble(o[1]));
            revenues1.add(revenue);
        }
        return revenues1;
    }

    @Override
    public List<BookingDTO> getAll() {
        List<com.migi.migi_project.entity.Service> services = serviceRepository.findAll();
        List<ServiceWorker> serviceWorkers = serviceWorkerRepository.findAll();

        return bookingRepository.findAll()
                .stream().map(o -> {
                    BookingDTO bookingDTO = mapper.map(o, BookingDTO.class);
                    com.migi.migi_project.entity.Service service = services.stream().filter(s -> s.getId().equals(o.getServiceId()))
                            .findAny().orElse(new com.migi.migi_project.entity.Service());
                    if (service.getId() == null) {
                        return bookingDTO;
                    }
                    bookingDTO.setServiceName(service.getName());
                    bookingDTO.setPrice(service.getPrice());

                    ServiceWorker serviceWorker = serviceWorkers.stream().filter(s -> s.getId().equals(o.getServiceWorkerId()))
                            .findAny().orElse(new ServiceWorker());
                    if (serviceWorker.getId() == null) {
                        return bookingDTO;
                    }
                    bookingDTO.setWorkerName(serviceWorker.getFullname());
                    bookingDTO.setWorkerPhone(serviceWorker.getPhoneNumber());

                    return bookingDTO;
                })
                .collect(Collectors.toList());
    }

}