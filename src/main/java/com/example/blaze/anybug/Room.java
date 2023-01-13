package com.example.blaze.anybug;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import lombok.Getter;
import org.hibernate.annotations.AnyDiscriminator;
import org.hibernate.annotations.AnyDiscriminatorValue;
import org.hibernate.annotations.AnyDiscriminatorValues;
import org.hibernate.annotations.AnyKeyJavaClass;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ManyToAny;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToAny
    @AnyDiscriminator(DiscriminatorType.STRING)
    @AnyDiscriminatorValues({
        @AnyDiscriminatorValue(entity = Chair.class, discriminator = "chair"),
        @AnyDiscriminatorValue(entity = Bed.class, discriminator = "bed"),
    })
    @AnyKeyJavaClass(Long.class)
    @Cascade(CascadeType.ALL)
    @Column(name = "furniture_item")
    @JoinTable(name = "room_furniture_items",
        joinColumns = @JoinColumn(name = "room_id"),
        inverseJoinColumns = @JoinColumn(name = "furniture_item_id"))
    private Set<FurnitureItem> furnitureItems = new HashSet<>();
}
