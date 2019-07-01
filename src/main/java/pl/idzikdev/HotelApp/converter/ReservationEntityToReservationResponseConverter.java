package pl.idzikdev.HotelApp.converter;

import org.springframework.core.convert.converter.Converter;
import pl.idzikdev.HotelApp.entity.ReservationEntity;
import pl.idzikdev.HotelApp.model.response.ReservationResponse;

public class ReservationEntityToReservationResponseConverter implements Converter<ReservationEntity, ReservationResponse> {
    @Override
    public ReservationResponse convert(ReservationEntity reservationEntity) {
        ReservationResponse reservationResponse=new ReservationResponse();
        reservationResponse.setCheckin(reservationEntity.getCheckin());
        reservationResponse.setCheckout(reservationEntity.getCheckout());
        if (reservationEntity!=null){
            reservationResponse.setId(reservationEntity.getId());
        }
        return reservationResponse;
    }
}
