package com.example.demo.domain;

import com.example.demo.domain.embeddableDomain.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "instructor")
@DynamicInsert
@DynamicUpdate
@JsonIgnoreProperties(ignoreUnknown = true) // ignore JSON properties while Jackson cannot find appropriate setter methods. ( from JSON to POJOs )
public class Instructor implements Serializable {

    private static final long serialVersionUID = 27821924L;

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Address address;

    public Instructor() {
    }

    public Instructor(String firstName, String lastName,
                      String email,
                      Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        System.out.println("Instructor.getId");
        return id;
    }

    public void setId(Long id) {
        System.out.println("Instructor.setId " + id);
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        System.out.println("Instructor.getFirstName");
        return firstName;
    }

    public void setFirstName(String firstName) {
        System.out.println("Instructor.setFirstName " + firstName);
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        System.out.println("Instructor.getLastName");
        return lastName;
    }

    public void setLastName(String lastName) {
        System.out.println("Instructor.setLastName " + lastName);
        this.lastName = lastName;
    }

    @Column(name = "email")
    public String getEmail() {
        System.out.println("Instructor.getEmail");
        return email;
    }

    public void setEmail(String email) {
        System.out.println("Instructor.setEmail " + email);
        this.email = email;
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "country", column = @Column(name = "address_country")),
            @AttributeOverride(name = "street", column = @Column(name = "address_street")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "address_zipCode"))
    })
    public Address getAddress() {
        System.out.println("Instructor.getAddress");
        return address;
    }

    public void setAddress(Address address) {
        System.out.println("Instructor.setAddress " + address);
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instructor that = (Instructor) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, address);
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }

}
