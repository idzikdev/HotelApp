package pl.idzikdev.HotelApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.idzikdev.HotelApp.entity.RoomEntity;
import pl.idzikdev.HotelApp.repository.RoomRepository;
@Component
public class BaseBootstrap implements CommandLineRunner {
    @Autowired
    RoomRepository roomRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Bootstraping data...");

//        roomRepository.save(new RoomEntity(15, "127"));
//        roomRepository.save(new RoomEntity(11, "12"));
//        roomRepository.save(new RoomEntity(12, "60"));
//        roomRepository.save(new RoomEntity(45, "5"));
//        roomRepository.save(new RoomEntity(34, "45"));

        System.out.println("Printing data...");
        Iterable<RoomEntity> it=roomRepository.findAll();
        for (RoomEntity room:it
             ) {
            System.out.println(room.getRoomNumber()+" | "+room.getPrice());
        }
    }
}
