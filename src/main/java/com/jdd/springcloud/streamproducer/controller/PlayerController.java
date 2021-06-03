package com.jdd.springcloud.streamproducer.controller;

import com.jdd.springcloud.streamproducer.model.Player;
import java.util.concurrent.BlockingQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

  @Autowired
  BlockingQueue<Player> playerBlockingQueue;

  @PostMapping("/player/create")
  public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
    ResponseEntity<Player> playerResponseEntity;
    try {
      playerBlockingQueue.put(player);
      playerResponseEntity = new ResponseEntity<>(player, HttpStatus.ACCEPTED);
    } catch (InterruptedException interruptedException) {
      playerResponseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return playerResponseEntity;
  }

}
