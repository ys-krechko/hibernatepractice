package com.it.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "Order_List", schema = "projectdb")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "beginning_date_of_tour")
    private LocalDate beginningDateOfTour;

    @Column(name = "amount_of_days_of_tour")
    private Integer amountOfDaysOfTour;

    @Column(name = "number_of_tourists")
    private Integer numberOfTourists;

    @Column(name = "total_price")
    private Double totalPrice;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "operator_id")
    private Operators operator;

    @OneToOne
    @JoinColumn(name = "insurance_id", referencedColumnName = "insurance_id")
    private Insurance insurance;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_account_id")
    private PersonalAccount personalAccount;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customers_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private Set<HotelRoomHotels> hotelRoomHotels;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBeginningDateOfTour() {
        return beginningDateOfTour;
    }

    public void setBeginningDateOfTour(LocalDate beginningDateOfTour) {
        this.beginningDateOfTour = beginningDateOfTour;
    }

    public Integer getAmountOfDaysOfTour() {
        return amountOfDaysOfTour;
    }

    public void setAmountOfDaysOfTour(Integer amountOfDaysOfTour) {
        this.amountOfDaysOfTour = amountOfDaysOfTour;
    }

    public Integer getNumberOfTourists() {
        return numberOfTourists;
    }

    public void setNumberOfTourists(Integer numberOfTourists) {
        this.numberOfTourists = numberOfTourists;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Operators getOperator() {
        return operator;
    }

    public void setOperator(Operators operator) {
        this.operator = operator;
    }

    public PersonalAccount getPersonalAccount() {
        return personalAccount;
    }

    public void setPersonalAccount(PersonalAccount personalAccount) {
        this.personalAccount = personalAccount;
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<HotelRoomHotels> getHotelRoomHotels() {
        return hotelRoomHotels;
    }

    public void setHotelRoomHotels(Set<HotelRoomHotels> hotelRoomHotels) {
        this.hotelRoomHotels = hotelRoomHotels;
    }
}
