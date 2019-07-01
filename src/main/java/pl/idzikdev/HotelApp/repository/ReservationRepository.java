package pl.idzikdev.HotelApp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.idzikdev.HotelApp.entity.ReservationEntity;

public interface ReservationRepository extends CrudRepository<ReservationEntity,Long> {
}
