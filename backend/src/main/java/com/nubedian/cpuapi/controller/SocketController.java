package com.nubedian.cpuapi.controller;

import com.nubedian.cpuapi.entity.Socket;
import com.nubedian.cpuapi.repository.SocketRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sockets")
public class SocketController {

    private final SocketRepository socketRepository;

    public SocketController(SocketRepository socketRepository) {
        this.socketRepository = socketRepository;
    }

    @GetMapping
    public List<Socket> list() {
        return socketRepository.findAll();
    }
}
