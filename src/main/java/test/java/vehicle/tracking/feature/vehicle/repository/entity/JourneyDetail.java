package test.java.vehicle.tracking.feature.vehicle.repository.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "journey_details")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class JourneyDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "journey_id", nullable = false, insertable = false, updatable = false)
    Integer journeyId;

    Double latitude;

    Double longitude;

    Boolean isDeleted;

    Date createdDate;

    Date lastUpdatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "journey_id", nullable = false)
    Journey journey;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JourneyDetail that = (JourneyDetail) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
