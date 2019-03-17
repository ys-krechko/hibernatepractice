package com.it.dao.impl;

import com.it.dao.HotelRoomDAO;
import com.it.model.HotelRoom;
import com.it.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class HotelRoomDAOImpl extends GenericDAOImpl<HotelRoom, Long> implements HotelRoomDAO {
    private static HotelRoomDAOImpl instance;

    public HotelRoomDAOImpl() {
        super(HotelRoom.class);
    }

    public static synchronized HotelRoomDAOImpl getInstance() {
        if (instance == null) {
            instance = new HotelRoomDAOImpl();
        }
        return instance;
    }


    /**
     * Find HotelRoom with fetching HotelRoomPrice by HotelRoom's Id
     *
     * @param id - id of hotel room
     * @return
     */
    @Override
    public HotelRoom findWithPriceById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Hotel Room hr JOIN FETCH hr.Hotel room price hrp WHERE (hrp.hotel_room_price_id = :hr.hotel_room_id)";
            Query query = session.createQuery(hql);
            query.setParameter("hr.hotel_room_id", id);
            return (HotelRoom) query.getSingleResult();
        }
    }
}