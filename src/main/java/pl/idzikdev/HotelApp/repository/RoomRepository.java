package pl.idzikdev.HotelApp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.idzikdev.HotelApp.entity.RoomEntity;

import java.util.Optional;

public interface RoomRepository extends CrudRepository<RoomEntity, Long> {
    @Override
    Optional<RoomEntity> findById(Long aLong);
}
