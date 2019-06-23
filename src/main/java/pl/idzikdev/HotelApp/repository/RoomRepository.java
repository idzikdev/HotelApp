package pl.idzikdev.HotelApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.idzikdev.HotelApp.entity.RoomEntity;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    @Override
    Optional<RoomEntity> findById(Long aLong);
}
