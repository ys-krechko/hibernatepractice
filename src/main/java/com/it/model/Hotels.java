package com.it.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Hotels", schema = "projectdb")
public class Hotels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotels_id")
    private Long id;

    @Column(name = "hotels_name")
    private String hotelsName;

    @Column(name = "stars")
    private Integer stars;

    @Column(name = "hotels_address")
    private String hotelsAddress;

    @OneToMany(mappedBy = "hotels")
    private Set<HotelRoomHotels> hotelRoomHotels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHotelsName() {
        return hotelsName;
    }

    public void setHotelsName(String hotelsName) {
        this.hotelsName = hotelsName;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getHotelsAddress() {
        return hotelsAddress;
    }

    public void setHotelsAddress(String hotelsAddress) {
        this.hotelsAddress = hotelsAddress;
    }

    public Set<HotelRoomHotels> getHotelRoomHotels() {
        return hotelRoomHotels;
    }

    public void setHotelRoomHotels(Set<HotelRoomHotels> hotelRoomHotels) {
        this.hotelRoomHotels = hotelRoomHotels;
    }
}
