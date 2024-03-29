package pl.idzikdev.HotelApp.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate checkin;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate checkout;
    @ManyToOne
    private RoomEntity roomEntity;

    public ReservationEntity(@NotNull LocalDate checkin, @NotNull LocalDate checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public ReservationEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public RoomEntity getRoomEntity() {
        return roomEntity;
    }

    public void setRoomEntity(RoomEntity roomEntity) {
        this.roomEntity = roomEntity;
    }
}
