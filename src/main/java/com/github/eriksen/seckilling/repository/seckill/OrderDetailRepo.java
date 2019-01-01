package com.github.eriksen.seckilling.repository.seckill;

import com.github.eriksen.seckilling.model.OrderDetail;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * OrderDetailRepo
 */
public interface OrderDetailRepo extends MongoRepository<OrderDetail, String> {

}