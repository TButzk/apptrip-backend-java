package model;

import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import model.shared.EntityBase;

public class UserAddress extends EntityBase {

    @ManyToMany()
    @JoinColumn(name =  "user", foreignKey = @ForeignKey(name = "fk_user_address"))
    private User user;

    @ManyToMany()
    @JoinColumn(name =  "address", foreignKey = @ForeignKey(name = "fk_address_user"))
    private Address address;
}
