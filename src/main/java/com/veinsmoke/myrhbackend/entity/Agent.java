package com.veinsmoke.myrhbackend.entity;

import com.veinsmoke.myrhbackend.entity.superclass.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Agent extends User {
}
