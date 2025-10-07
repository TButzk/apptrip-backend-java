package model;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import model.shared.SoftDeleteEntityBase;

import java.util.List;

public class Address extends SoftDeleteEntityBase {
    private String street;
    private String streetNumber;
    private String complement;
    private String city;
    private String postalCode;
    private String country;
    private String state;

    @ManyToMany(mappedBy = "address")
    private List<UserAddress> users;

    @OneToOne
    private Place place;
}
