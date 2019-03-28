package com.it.dao;

import com.it.model.HotelRoom;

import java.util.List;

public interface HotelRoomDAO extends GenericDAO<HotelRoom, Long> {
    List<HotelRoom> findWithPriceById(Long id);
}
