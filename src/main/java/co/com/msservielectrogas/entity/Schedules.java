package co.com.msservielectrogas.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "schedules")
public class Schedules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSchedule;

    @Column(name = "date")
    private Date date;

    @Column(name = "hour")
    private LocalTime hour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Users users;

    @OneToOne
    @JoinColumn(name = "FK_ORDERS", updatable = true, nullable = false)
    private Order orders;

    // Default constructor
    public Schedules() {
    }

    // Parameterized constructor
    public Schedules(Integer idSchedule, Date date, LocalTime hour, Users users, Order orders) {
        this.idSchedule = idSchedule;
        this.date = date;
        this.hour = hour;
        this.users = users;
        this.orders = orders;
    }

    // Getters and Setters
    public Integer getIdSchedule() {
        return idSchedule;
    }

    public void setIdSchedule(Integer idSchedule) {
        this.idSchedule = idSchedule;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Order getOrders() {
        return orders;
    }

    public void setOrders(Order orders) {
        this.orders = orders;
    }
}
