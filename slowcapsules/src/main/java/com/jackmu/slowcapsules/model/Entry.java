package com.jackmu.slowcapsules.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Entry")
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "entry_id")
    private Long entryId;

    @Column(name = "series_id")
    private Long seriesId;

    //TODO: Look into using LOB for a blob or clob
    //https://www.baeldung.com/jpa-annotation-postgresql-text-type
    @Column(name = "entry_text", columnDefinition="TEXT")
    private String entryText;

    @Column(name = "order_num")
    private Integer orderNum;

    @Column(name = "title")
    private String title;

    @Column(name = "email")
    private String email;
}
