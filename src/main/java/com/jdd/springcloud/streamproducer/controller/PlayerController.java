package com.jdd.springcloud.streamproducer.controller;

import com.jdd.springcloud.streamproducer.model.Player;
import com.jdd.springcloud.streamproducer.model.TestPlayer;
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
  BlockingQueue<TestPlayer> playerBlockingQueue;

  @PostMapping("/player/create")
  public ResponseEntity<TestPlayer> createPlayer(@RequestBody TestPlayer player) {
    ResponseEntity<TestPlayer> playerResponseEntity;
    try {
      playerBlockingQueue.put(player);
      playerResponseEntity = new ResponseEntity<>(player, HttpStatus.ACCEPTED);
    } catch (InterruptedException interruptedException) {
      playerResponseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return playerResponseEntity;
  }

}
