package com.rest.springapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "boathouses")
public class BoatHouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version;  // Hibernate will use this for optimistic locking

    private String name;
    private String location;
    private int capacity;
}
