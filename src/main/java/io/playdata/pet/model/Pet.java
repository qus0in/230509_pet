package io.playdata.pet.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Pet {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String image;
    private String voice;
    private String race;
}
