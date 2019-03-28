package com.it.dao.impl;

import com.it.dao.HotelRoomHotelsDAO;
import com.it.model.HotelRoomHotels;

public class HotelRoomHotelsDAOImpl extends GenericDAOImpl<HotelRoomHotels, Long> implements HotelRoomHotelsDAO {
    private static HotelRoomHotelsDAOImpl instance;

    private HotelRoomHotelsDAOImpl() {
        super(HotelRoomHotels.class);
    }

    public static synchronized HotelRoomHotelsDAOImpl getInstance() {
        if (instance == null) {
            instance = new HotelRoomHotelsDAOImpl();
        }
        return instance;
    }
}
