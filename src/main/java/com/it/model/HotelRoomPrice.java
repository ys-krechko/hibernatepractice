package com.it.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Hotel_room_price", schema = "projectdb")
public class HotelRoomPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_room_price_id")
    private Long id;

    @Column(name = "date_of_month")
    private LocalDate date;

    @Column(name = "price_per_night")
    private Double pricePerNight;

    @ManyToMany(mappedBy = "hotelRoomPrices", fetch = FetchType.LAZY)
    private Set<HotelRoom> hotelRooms;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Set<HotelRoom> getHotelRooms() {
        return hotelRooms;
    }

    public void setHotelRooms(Set<HotelRoom> hotelRooms) {
        this.hotelRooms = hotelRooms;
    }
}
