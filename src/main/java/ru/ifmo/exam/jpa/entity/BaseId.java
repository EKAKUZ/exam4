package ru.ifmo.exam.jpa.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.ToString;

@ToString
@MappedSuperclass
public abstract class BaseId {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY) //SERIAL
    private int id;
}
