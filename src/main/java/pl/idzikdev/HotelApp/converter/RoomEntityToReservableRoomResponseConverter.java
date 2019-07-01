package pl.idzikdev.HotelApp.converter;

import org.springframework.core.convert.converter.Converter;
import pl.idzikdev.HotelApp.entity.RoomEntity;
import pl.idzikdev.HotelApp.model.Links;
import pl.idzikdev.HotelApp.model.Self;
import pl.idzikdev.HotelApp.model.response.ReservableRoomResponse;
import pl.idzikdev.HotelApp.rest.ResourceConstants;

public class RoomEntityToReservationResponseConverter implements Converter<RoomEntity, ReservableRoomResponse> {
    @Override
    public ReservableRoomResponse convert(RoomEntity roomEntity) {
        ReservableRoomResponse reservableRoomResponse =new ReservableRoomResponse();
        reservableRoomResponse.setRoomNumber(roomEntity.getRoomNumber());
        reservableRoomResponse.setPrice(Integer.valueOf(roomEntity.getPrice()));
        Links links=new Links();
        Self self=new Self();
        self.setRef(ResourceConstants.ROOM_RESERVATION_V1+"/"+roomEntity.getId());
        links.setSelf(self);
        reservableRoomResponse.setLinks(links);
        return reservableRoomResponse;
    }
}
