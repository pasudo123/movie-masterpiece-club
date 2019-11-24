package com.club.masterpiece.common.updatestate.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by pasudo123 on 2019-10-29
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Entity
@Table(name = "update_state")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UpdateState {

    @Id
    @Column(name = "table_name", length = 50, nullable = false)
    private String tableName;

    @Column(name = "last_mod_date", nullable = false)
    private LocalDateTime lastModifiedDate;

    public UpdateState(String tableName) {
        this.tableName = tableName;
    }

    public void updating() {
        this.lastModifiedDate = LocalDateTime.now();
    }
}
