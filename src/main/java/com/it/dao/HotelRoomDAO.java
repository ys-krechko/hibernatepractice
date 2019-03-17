package com.it.dao;

import com.it.model.HotelRoom;

public interface HotelRoomDAO extends GenericDAO<HotelRoom, Long> {
    HotelRoom findWithPriceById(Long id);
}
