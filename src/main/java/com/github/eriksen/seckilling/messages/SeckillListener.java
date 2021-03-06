package com.github.eriksen.seckilling.messages;

import com.github.eriksen.seckilling.model.Activity;
import com.github.eriksen.seckilling.model.Order;
import com.github.eriksen.seckilling.service.SeckillSvc;
import com.github.eriksen.seckilling.utils.MQConst;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * SeckillListener
 */
@Component
@Slf4j
public class SeckillListener {

  @Resource
  private SeckillSvc seckillSvc;

  @KafkaListener(topics = MQConst.SECKILL_ACTIVITY_TOPIC, groupId = MQConst.SECKILL_BROADCAST_CONSUMER_GROUP)
  public void onInit(Activity activity) {
    try {
      log.debug("[Msg] " + activity);
      if (seckillSvc.preheatSeckillActivity(activity)) {
        log.debug("Activity preheated");
      }
    } catch (Exception e) {
      log.error("[Error] failed to preheat, due to " + e.getMessage());
      e.printStackTrace();
    }
  }

  @KafkaListener(topics = MQConst.SECKILL_ORDER_CREATE_TOPIC, groupId = MQConst.SECKILL_CONSUMER_GROUP)
  public void onOrderCreated(Order order) {
    log.debug("[OnOrderCreated]", order);
  }
}