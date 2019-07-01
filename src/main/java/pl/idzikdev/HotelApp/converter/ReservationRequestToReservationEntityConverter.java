package pl.idzikdev.HotelApp.converter;

import org.springframework.core.convert.converter.Converter;
import pl.idzikdev.HotelApp.entity.ReservationEntity;
import pl.idzikdev.HotelApp.model.request.ReservationRequest;

public class ReservationRequestToReservationEntityConverter implements Converter<ReservationRequest, ReservationEntity> {
    @Override
    public ReservationEntity convert(ReservationRequest reservationRequest) {
        ReservationEntity reservationEntity=new ReservationEntity();
        reservationEntity.setCheckin(reservationRequest.getCheckin());
        reservationEntity.setCheckout(reservationRequest.getCheckout());
        if (reservationRequest!=null){
            reservationEntity.setId(reservationRequest.getId());
        }
        return reservationEntity;
    }
}
