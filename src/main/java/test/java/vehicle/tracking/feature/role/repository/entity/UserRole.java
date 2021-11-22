package test.java.vehicle.tracking.feature.role.repository.entity;


import test.java.vehicle.tracking.feature.user.repository.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "user_role", indexes = {@Index(name = "IDX_UN_USER_ROLE_NAME", columnList = "name", unique = true)})
public class UserRole {

    static final long serialVersionUID = -787991392884005033L;

    @Id
    @Column(name = "role_id")
    String roleId = UUID.randomUUID().toString().replace("-", "");

    String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
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
        final UserRole role = (UserRole) obj;
        if (!getName().equals(role.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Role [name=").append(name).append("]").append("[id=").append(roleId).append("]");
        return builder.toString();
    }
}
