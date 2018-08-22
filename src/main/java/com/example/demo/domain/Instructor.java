package com.example.demo.domain;

import com.example.demo.domain.embeddableDomain.Address;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/*
*    Notes:
*    #1: When you put @Id & @GeneratedValue on field "Long id" instead of getter method "getId()",
*        Hibernate will use AccessType.Field to create SQL ( which means no setters/getters except Id's setter/getter are being used )
*        However, you can use @Access(value = AccessType.PROPERTY) on each field to let Hibernate uses setters/getters
*        @Access(value = AccessType.PROPERTY) => use setters/getters
*        @Access(value = AccessType.FIELD) => use fields
*    #2: You can remove all @Column(name = "xx") on fields/getters if no name alignment between table & entity is considered
*    #3: You can remove @Embedded on the entities which use embeddable domains, but you must keep either @Embedded or @Embeddable on one side.
*        For example, in "Instructor" entity, you can remove @Embedded on Address, but you must explicitly set @Embeddable on "Address" entity.
*    #4: You can remove @AttributeOverrides on @embedded fields/getters if no name alignment between table & entity is considered
*    #5: @DynamicInsert & @DynamicUpdate are optional, use them when the there are many columns
*    #6: You can use @Immutable on class type and remove all setter methods, which make the Entity/Table becomes read-only
*        @Immutable is good for "view" on Database ( for view, say, you need to join 5 tables together and only select partial fields )
* */

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
        System.out.print("Instructor.getId");
        return id;
    }

    public void setId(Long id) {
        System.out.print("Instructor.setId " + id);
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        System.out.print(" & Instructor.getFirstName");
        return firstName;
    }

    public void setFirstName(String firstName) {
        System.out.print(" & Instructor.setFirstName " + firstName);
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        System.out.print(" & Instructor.getLastName");
        return lastName;
    }

    public void setLastName(String lastName) {
        System.out.print(" & Instructor.setLastName " + lastName);
        this.lastName = lastName;
    }

    @Column(name = "email")
    public String getEmail() {
        System.out.print(" & Instructor.getEmail");
        return email;
    }

    public void setEmail(String email) {
        System.out.print(" & Instructor.setEmail " + email);
        this.email = email;
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "country", column = @Column(name = "address_country")),
            @AttributeOverride(name = "street", column = @Column(name = "address_street")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "address_zipCode"))
    })
    public Address getAddress() {
        System.out.print(" & Instructor.getAddress");
        return address;
    }

    public void setAddress(Address address) {
        System.out.print(" & Instructor.setAddress " + address);
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
