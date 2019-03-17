package com.it.dao.impl;

import com.it.dao.HotelRoomPriceDAO;
import com.it.model.HotelRoomPrice;

public class HotelRoomPriceDAOImpl extends GenericDAOImpl<HotelRoomPrice, Long> implements HotelRoomPriceDAO {
    private static HotelRoomPriceDAOImpl instance;

    private HotelRoomPriceDAOImpl() {
        super(HotelRoomPrice.class);
    }

    public static synchronized HotelRoomPriceDAOImpl getInstance() {
        if (instance == null) {
            instance = new HotelRoomPriceDAOImpl();
        }
        return instance;
    }
}
