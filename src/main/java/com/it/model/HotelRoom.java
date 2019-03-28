package com.it.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Hotel_Room", schema = "projectdb")
public class HotelRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_room_id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "number_of_guests")
    private Integer numberOfGuests;

    @Column(name = "food_type")
    private String foodType;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "Hotel_room_Hotel_room_price",
            joinColumns = {@JoinColumn(name = "hotel_room_id")},
            inverseJoinColumns = {@JoinColumn(name = "hotel_room_price_id")})
    private Set<HotelRoomPrice> hotelRoomPrices;

    @OneToMany(mappedBy = "hotelRooms")
    private Set<HotelRoomHotels> hotelRoom_hotels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public Set<HotelRoomPrice> getHotelRoomPrices() {
        return hotelRoomPrices;
    }

    public void setHotelRoomPrices(Set<HotelRoomPrice> hotelRoomPrices) {
        this.hotelRoomPrices = hotelRoomPrices;
    }

    public Set<HotelRoomHotels> getHotelRoom_hotels() {
        return hotelRoom_hotels;
    }

    public void setHotelRoom_hotels(Set<HotelRoomHotels> hotelRoom_hotels) {
        this.hotelRoom_hotels = hotelRoom_hotels;
    }
}
