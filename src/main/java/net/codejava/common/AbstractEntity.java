package net.codejava.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Data
@Getter
@Setter
@MappedSuperclass
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;


    private Date creationDate;

    private Date updateDate;

    @PrePersist
    void preInsert() {
        this.creationDate = new Date();
        this.version = 0;
    }

    @PreUpdate
    void preUpdate() {
        this.updateDate = new Date();
    }
}
