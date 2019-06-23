package pl.idzikdev.HotelApp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.idzikdev.HotelApp.entity.RoomEntity;


public interface PageableRoomRepository extends PagingAndSortingRepository<RoomEntity, Long> {
    Page<RoomEntity> findById(Long id, Pageable page);
}
