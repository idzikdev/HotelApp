package pl.idzikdev.HotelApp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.idzikdev.HotelApp.converter.RoomEntityToReservableRoomResponseConverter;
import pl.idzikdev.HotelApp.entity.ReservationEntity;
import pl.idzikdev.HotelApp.entity.RoomEntity;
import pl.idzikdev.HotelApp.model.request.ReservationRequest;
import pl.idzikdev.HotelApp.model.response.ReservableRoomResponse;
import pl.idzikdev.HotelApp.model.response.ReservationResponse;
import pl.idzikdev.HotelApp.repository.PageableRoomRepository;
import pl.idzikdev.HotelApp.repository.ReservationRepository;
import pl.idzikdev.HotelApp.repository.RoomRepository;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping(ResourceConstants.ROOM_RESERVATION_V1)
@CrossOrigin
public class ReservationResource {
    @Autowired
    PageableRoomRepository pageableRoomRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ConversionService conversionService;

    @RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<ReservableRoomResponse> getAvailableRooms(
            @RequestParam(value = "checkin")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate checkin,
            @RequestParam(value = "checkout")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate checkout, Pageable pageable) {
        Page<RoomEntity> roomEntryList=pageableRoomRepository.findAll(pageable);
//        return new ResponseEntity<>(new ReservableRoomResponse(), HttpStatus.OK);
        return roomEntryList.map(a->new RoomEntityToReservableRoomResponseConverter().convert(a));
    }

    @RequestMapping(path = "/{roomId}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RoomEntity> getRoomById(
            @PathVariable Long roomId){
        Optional<RoomEntity> roomEntity=roomRepository.findById(roomId);
        //TODO Optional
        return new ResponseEntity<>(roomEntity.get(), HttpStatus.OK);
    }

    @RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ReservationResponse> createReservation(
            @RequestBody
                    ReservationRequest reservationRequest) {

        ReservationEntity reservationEntity=conversionService.convert(reservationRequest, ReservationEntity.class);
        reservationRepository.save(reservationEntity);
        //TODO Optional
        RoomEntity roomEntity=roomRepository.findById(reservationRequest.getRoomId()).get();
        roomEntity.addReservationEntity(reservationEntity);
        roomRepository.save(roomEntity);
        reservationEntity.setRoomEntity(roomEntity);
        ReservationResponse reservationResponse=conversionService.convert(reservationEntity,ReservationResponse.class);

        return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
    }

    @RequestMapping(path = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ReservableRoomResponse> updateReservation(
            @RequestBody
                    ReservationRequest reservationRequest) {
        return new ResponseEntity<>(new ReservableRoomResponse(), HttpStatus.OK);
    }

    @RequestMapping(path = "/{reservationId}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteReservation(
            @PathVariable("reservationId")
            long reservationId){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
