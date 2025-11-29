package com.great.project.dto;

import com.great.project.domain.model.Product;

import java.io.Serializable;

/**
 * A DTO (Data Transfer Object) used to serialize / deserialize {@link Product} objects
 *
 * @author bogdan.solga
 */
public record ProductDTO(int id, String name, double price) {}
