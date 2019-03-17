package com.it.app;

import com.it.dao.*;
import com.it.dao.impl.*;
import com.it.model.*;
import com.it.util.HibernateUtil;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static final CustomerDAO customerDAO = CustomerDAOImpl.getInstance();
    private static final HotelRoomDAO hotelRoomDAO = HotelRoomDAOImpl.getInstance();
    private static final HotelRoomHotelsDAO hotelRoomHotelsDAO = HotelRoomHotelsDAOImpl.getInstance();
    private static final HotelRoomPriceDAO hotelRoomPriceDAO = HotelRoomPriceDAOImpl.getInstance();
    private static final HotelsDAO hotelsDAO = HotelsDAOImpl.getInstance();
    private static final InsuranceDAO insuranceDAO = InsuranceDAOImpl.getInstance();
    private static final OrderDAO orderDAO = OrderDAOImpl.getInstance();
    private static final PersonalAccountDAO personalAccountDAO = PersonalAccountDAOImpl.getInstance();
    private static final RolesDAO rolesDAO = RolesDAOImlp.getInstance();
    private static final OperatorDAO operatorDAO = OperatorDAOImpl.getInstance();

    public static void main(String[] args) {
        /*try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println(session.toString());
        }*/

        createOperator("mg1", "7534");
        Operators persistOperator = operatorDAO.getOne(1L);
        Set<Operators> operator = new HashSet<>();
        operator.add(persistOperator);
        createRole(operator, "manager");
        Roles persistRole = rolesDAO.getOne(1L);
        persistOperator.setRoles(persistRole);

        createPersonalAccount(persistOperator);
        PersonalAccount persistPersonalAccount = personalAccountDAO.getOne(1L);
        persistOperator.setPersonalAccount(persistPersonalAccount);
        operatorDAO.update(persistOperator);

        createHotelRoomPrice(LocalDate.of(2018, 01, 20), 2.50);
        HotelRoomPrice persistHotelRoomPrice = hotelRoomPriceDAO.getOne(1L);
        Set<HotelRoomPrice> hotelRoomPrices = new HashSet<>();
        Set<HotelRoom> hotelRooms = new HashSet<>();
        hotelRoomPrices.add(persistHotelRoomPrice);
        createHotelRoom(hotelRoomPrices, "luxury", 3, "all inclusive");
        HotelRoom persistHotelRoom = hotelRoomDAO.getOne(1L);
        hotelRooms.add(persistHotelRoom);
        persistHotelRoomPrice.setHotelRooms(hotelRooms);
        hotelRoomPriceDAO.update(persistHotelRoomPrice);

        createHotelRoomHotels(persistHotelRoom);
        HotelRoomHotels persistHotelRoomHotels = hotelRoomHotelsDAO.getOne(1L);
        Set<HotelRoomHotels> hotelRoomHotels = new HashSet<>();
        hotelRoomHotels.add(persistHotelRoomHotels);
        createHotels(hotelRoomHotels, "something", 5, "somewhere");
        Hotels persistHotel = hotelsDAO.getOne(1L);
        persistHotelRoomHotels.setHotels(persistHotel);
        hotelRoomHotelsDAO.update(persistHotelRoomHotels);
        persistHotelRoom.setHotelRoom_hotels(hotelRoomHotels);

        createCustomer("vasili", "vasilev", "34340ff64", "23133-ff/01", LocalDate.of(2019, 03, 13));
        Customer persistCustomer = customerDAO.getOne(1L);
        createOrder(hotelRoomHotels, persistCustomer, persistPersonalAccount, persistOperator, LocalDate.of(2019,3, 17), 5, 1, 1354.00);
        Set<Order> orders = new HashSet<>();
        Order persistOrder = new Order();
        orders.add(persistOrder);
        persistCustomer.setOrder(persistOrder);
        customerDAO.update(persistCustomer);
        persistOperator.setOrders(orders);
        operatorDAO.update(persistOperator);
        persistPersonalAccount.setOrders(orders);
        personalAccountDAO.update(persistPersonalAccount);
        persistHotelRoomHotels.setOrder(persistOrder);
        hotelRoomHotelsDAO.update(persistHotelRoomHotels);

        createInsurance(persistOrder, "some type", 3.50);
        Insurance persistInsurance = insuranceDAO.getOne(1L);
        persistOrder.setInsurance(persistInsurance);
        orderDAO.update(persistOrder);

        hotelRoomHQL(1L);
    }

    private static void createOperator(String operatorLogin, String operatorPassword) {
        Operators transientOperator = new Operators();
        transientOperator.setOperatorLogin(operatorLogin);
        transientOperator.setOperatorPassword(operatorPassword);
        operatorDAO.save(transientOperator);
    }

    private static void createRole(Set<Operators> operators, String role) {
        Roles transientRole = new Roles();
        transientRole.setRole(role);
        transientRole.setOperator(operators);
        rolesDAO.save(transientRole);
    }

    private static void createPersonalAccount(Operators operators) {
        PersonalAccount transientPersonalAccount = new PersonalAccount();
        transientPersonalAccount.setOperator(operators);
        personalAccountDAO.save(transientPersonalAccount);
    }

    private static void createHotelRoomPrice(LocalDate date, Double pricePerNight) {
        HotelRoomPrice transientHotelRoomPrice = new HotelRoomPrice();
        transientHotelRoomPrice.setDate(date);
        transientHotelRoomPrice.setPricePerNight(pricePerNight);
        hotelRoomPriceDAO.save(transientHotelRoomPrice);
    }

    private static void createHotelRoom(Set<HotelRoomPrice> hotelRoomPrices, String type, Integer numberOfGuests, String foodType) {
        HotelRoom transientHotelRoom = new HotelRoom();
        transientHotelRoom.setHotelRoomPrices(hotelRoomPrices);
        transientHotelRoom.setType(type);
        transientHotelRoom.setNumberOfGuests(numberOfGuests);
        transientHotelRoom.setFoodType(foodType);
        hotelRoomDAO.save(transientHotelRoom);
    }

    private static void createHotelRoomHotels(HotelRoom hotelRooms) {
        HotelRoomHotels transientHotelRoomHotels = new HotelRoomHotels();
        transientHotelRoomHotels.setHotelRooms(hotelRooms);
        hotelRoomHotelsDAO.save(transientHotelRoomHotels);
    }

    private static void createHotels(Set<HotelRoomHotels> hotelRoomHotels, String hotelsName, Integer stars, String hotelsAddress) {
        Hotels transientHotel = new Hotels();
        transientHotel.setHotelRoomHotels(hotelRoomHotels);
        transientHotel.setHotelsName(hotelsName);
        transientHotel.setStars(stars);
        transientHotel.setHotelsAddress(hotelsAddress);
        hotelsDAO.save(transientHotel);
    }

    private static void createCustomer(String customersFirstName, String customersLastName, String customersPassportNumber, String customersContractNumber, LocalDate customersContractDateOfSigning) {
        Customer transientCustomer = new Customer();
        transientCustomer.setCustomersFirstName(customersFirstName);
        transientCustomer.setCustomersLastName(customersLastName);
        transientCustomer.setCustomersLastName(customersPassportNumber);
        transientCustomer.setCustomersContractNumber(customersContractNumber);
        transientCustomer.setCustomersContractDateOfSigning(customersContractDateOfSigning);
        customerDAO.save(transientCustomer);
    }

    private static void createOrder(Set<HotelRoomHotels> hotelRoomHotels, Customer customer, PersonalAccount personalAccount, Operators operators, LocalDate beginningDateOfTour, Integer amountOfDaysOfTour, Integer numberOfTourists, Double totalPrice) {
        Order transientOrder = new Order();
        transientOrder.setHotelRoomHotels(hotelRoomHotels);
        transientOrder.setCustomer(customer);
        transientOrder.setPersonalAccount(personalAccount);
        transientOrder.setOperator(operators);
        transientOrder.setBeginningDateOfTour(beginningDateOfTour);
        transientOrder.setAmountOfDaysOfTour(amountOfDaysOfTour);
        transientOrder.setNumberOfTourists(numberOfTourists);
        transientOrder.setTotalPrice(totalPrice);
        orderDAO.save(transientOrder);
    }

    private static void createInsurance(Order order, String insuranceType, Double insurancePrice) {
        Insurance transientInsurance = new Insurance();
        transientInsurance.setOrder(order);
        transientInsurance.setInsuranceType(insuranceType);
        transientInsurance.setInsurancePrice(insurancePrice);
        insuranceDAO.save(transientInsurance);
    }

    private static void hotelRoomHQL (Long id){
        HotelRoom hotelRoom = hotelRoomDAO.findWithPriceById(id);
        System.out.println(hotelRoom.getType());
    }
}
