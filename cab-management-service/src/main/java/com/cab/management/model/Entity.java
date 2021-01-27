package com.cab.management.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class Entity {
    public String id = UUID.randomUUID().toString();

}
