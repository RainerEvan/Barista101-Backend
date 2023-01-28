package com.project.barista101.model.brew;

import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "brews")
public class Brews {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String title;

    @Lob
    private String description;

    private Integer coffee;

    private Integer water;

    private Integer ratio;

    private Integer time;

    private String grindSize;

    @Lob
    private String preparations;

    @Lob
    private String steps;

    @Lob
    private String thumbnail;

    private OffsetDateTime createdAt;
}
