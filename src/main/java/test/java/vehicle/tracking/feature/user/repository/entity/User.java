package test.java.vehicle.tracking.feature.user.repository.entity;

import test.java.vehicle.tracking.feature.role.repository.entity.UserRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Version;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;


@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "user", indexes = {@Index(name = "UQ_USER_PHONE_IDX", columnList = "mobile", unique = true),
        @Index(name = "UQ_USER_EMAIL_IDX", columnList = "email", unique = true)})
public class User implements UserDetails {

    static final long serialVersionUID = -787991492884005033L;

    @Id
    @Column(name = "user_id")
    String userId = UUID.randomUUID().toString().replace("-", "");

    @Version
    Long version;

    String username;

    String firstName;

    String lastName;

    String email;

    String mobile;

    String password;

    String userType;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private List<UserRole> roles;

    boolean isEnabled = Boolean.TRUE;

    Date createdDate;

    Date lastUpdatedDate;

    @PrePersist
    public void preInsert() {
        Date now = Date.from(Instant.now());
        this.createdDate = now;
        this.lastUpdatedDate = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdatedDate = Date.from(Instant.now());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return emptyIfNull(roles).stream().map(r -> new SimpleGrantedAuthority("ROLE_".concat(r.getName()))).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((getEmail() == null) ? 0 : getEmail().hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User user = (User) obj;
        if (!getEmail().equals(user.getEmail())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [id=")
                .append(userId)
                .append(", firstName=").append(firstName)
                .append(", lastName=").append(lastName)
                .append(", email=").append(email)
                .append(", enabled=").append(isEnabled)
                .append(", roles=").append(roles)
                .append("]");
        return builder.toString();
    }


}
