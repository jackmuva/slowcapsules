package com.jackmu.slowcapsules.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Series")
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "series_id")
    private Long seriesId;

    @Column(name = "datetime")
    private LocalDateTime datetime;

    @Column(name = "num_entries")
    private Integer numEntries;

    @Column(name = "title")
    private String title;

    @Column(name = "summary")
    private String summary;

    @Column(name = "tags")
    private String tags;

    @Column(name = "cadence")
    private Integer cadence;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "series")
    private List<Entry> entries;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "series")
    private List<Subscription> subscriptions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Writer writer;
}
