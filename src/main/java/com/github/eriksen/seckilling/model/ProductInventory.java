package com.github.eriksen.seckilling.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * ProductInventory
 */
@Data
@Document(collection="product.inventory")
public class ProductInventory {
  @Id
  private String id;
  
  @Indexed
  private ObjectId pId;

  private int count;

  private String direction;

  private Date createdTime = new Date();
}