package com.example.MarketShop.Model;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Admin
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_role", schema = "")
@NamedQueries({
        @NamedQuery(name = "UserRole.findAll", query = "SELECT u FROM UserRole u"),
        @NamedQuery(name = "UserRole.findById", query = "SELECT u FROM UserRole u WHERE u.id = :id")})
public class UserRole implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Short id;

    @JoinColumn(name = "user_id", referencedColumnName = "userID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Users userId;

    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Roles roleId;

    public UserRole(Users userId, Roles roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
