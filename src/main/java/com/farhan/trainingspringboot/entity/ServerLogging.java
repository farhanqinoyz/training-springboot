package com.farhan.trainingspringboot.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity(name="ServerLogging")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ServerLogging {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "LOG_ID", nullable = false, length = 40)
    private String id;

    @Column(name = "DETAIL_DESC")
    private String desc;

    @Column(name = "LOG_DATE")
    private Date date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ServerLogging that = (ServerLogging) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
