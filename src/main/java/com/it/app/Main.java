package com.it.app;

import com.it.dao.*;
import com.it.dao.impl.*;
import com.it.model.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
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

        createRole("manager");
        Roles persistentRole = rolesDAO.getOne(1L);
        createOperator(persistentRole, "someone", "65s4fs");
        Operators persistentOperator = operatorDAO.getOne(1L);

        createRole("director");
        Roles persistentRole2 = rolesDAO.getOne(2L);
        persistentRole2.setRole("new director");
        rolesDAO.update(persistentRole2);

        createPersonalAccount(persistentOperator);
        PersonalAccount persistentPersonalAccount = personalAccountDAO.getOne(1L);

        createHotelRoomPrice(LocalDate.of(2019, 12, 01), 2.50);

        HotelRoomPrice persistentHotelRoomPrice = hotelRoomPriceDAO.getOne(1L);
        Set<HotelRoomPrice> hotelRoomPrices = new HashSet<>();
        hotelRoomPrices.add(persistentHotelRoomPrice);
        createHotelRoom("luxury", 2, "all inclusive", hotelRoomPrices);
        HotelRoom persistentHotelRoom = hotelRoomDAO.getOne(1L);

        createHotels("something", 2, "somewhere");
        Hotels persistentHotel = hotelsDAO.getOne(1L);

        createCustomer("someone", "Targariane", "s5dd4fs6", "54sdf-ds/1", LocalDate.of(2019, 01, 02));
        Customer persistentCustomer = customerDAO.getOne(1L);

        createInsurance("cool", 15.03);
        Insurance persistentInsurance = insuranceDAO.getOne(1L);

        createOrder(persistentInsurance, persistentCustomer, persistentPersonalAccount, persistentOperator, LocalDate.of(2019, 03, 25), 15, 2, 501.95);
        Order persistentOrder = orderDAO.getOne(1L);

        createHotelRoomHotels(persistentHotelRoom, persistentOrder, persistentHotel);

        rolesDAO.delete(2L);

        hotelRoomHQL(1L);

    }

    private static void createRole(String role) {
        Roles transientRole = new Roles();
        transientRole.setRole(role);
        rolesDAO.save(transientRole);
    }

    private static void createOperator(Roles role, String operatorLogin, String operatorPassword) {
        Operators transientOperator = new Operators();
        transientOperator.setOperatorLogin(operatorLogin);
        transientOperator.setOperatorPassword(operatorPassword);
        transientOperator.setRoles(role);
        operatorDAO.save(transientOperator);
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

    private static void createHotelRoom(String type, Integer numberOfGuests, String foodType, Set<HotelRoomPrice> hotelRoomPrices) {
        HotelRoom transientHotelRoom = new HotelRoom();
        transientHotelRoom.setType(type);
        transientHotelRoom.setNumberOfGuests(numberOfGuests);
        transientHotelRoom.setFoodType(foodType);
        transientHotelRoom.setHotelRoomPrices(hotelRoomPrices);
        hotelRoomDAO.save(transientHotelRoom);
    }

    private static void createHotels(String hotelsName, Integer stars, String hotelsAddress) {
        Hotels transientHotel = new Hotels();
        transientHotel.setHotelsName(hotelsName);
        transientHotel.setStars(stars);
        transientHotel.setHotelsAddress(hotelsAddress);
        hotelsDAO.save(transientHotel);
    }

    private static void createCustomer(String customersFirstName, String customersLastName, String customersPassportNumber, String customersContractNumber, LocalDate customersContractDateOfSigning) {
        Customer transientCustomer = new Customer();
        transientCustomer.setCustomersFirstName(customersFirstName);
        transientCustomer.setCustomersLastName(customersLastName);
        transientCustomer.setCustomersPassportNumber(customersPassportNumber);
        transientCustomer.setCustomersContractNumber(customersContractNumber);
        transientCustomer.setCustomersContractDateOfSigning(customersContractDateOfSigning);
        customerDAO.save(transientCustomer);
    }

    private static void createInsurance(String insuranceType, Double insurancePrice) {
        Insurance transientInsurance = new Insurance();
        transientInsurance.setInsuranceType(insuranceType);
        transientInsurance.setInsurancePrice(insurancePrice);
        insuranceDAO.save(transientInsurance);
    }

    private static void createOrder(Insurance insurance, Customer customer, PersonalAccount personalAccount, Operators operators, LocalDate beginningDateOfTour, Integer amountOfDaysOfTour, Integer numberOfTourists, Double totalPrice) {
        Order transientOrder = new Order();
        transientOrder.setInsurance(insurance);
        transientOrder.setCustomer(customer);
        transientOrder.setPersonalAccount(personalAccount);
        transientOrder.setOperator(operators);
        transientOrder.setBeginningDateOfTour(beginningDateOfTour);
        transientOrder.setAmountOfDaysOfTour(amountOfDaysOfTour);
        transientOrder.setNumberOfTourists(numberOfTourists);
        transientOrder.setTotalPrice(totalPrice);
        orderDAO.save(transientOrder);
    }

    private static void createHotelRoomHotels(HotelRoom hotelRooms, Order order, Hotels hotels) {
        HotelRoomHotels transientHotelRoomHotels = new HotelRoomHotels();
        transientHotelRoomHotels.setHotelRooms(hotelRooms);
        transientHotelRoomHotels.setOrder(order);
        transientHotelRoomHotels.setHotels(hotels);
        hotelRoomHotelsDAO.save(transientHotelRoomHotels);
    }

    private static void hotelRoomHQL(Long id) {
        List<HotelRoom> hotelRoom = hotelRoomDAO.findWithPriceById(id);
        System.out.println(hotelRoom.get(0).getType());
        System.out.println(hotelRoom.get(0).getHotelRoomPrices().size());
    }
}
