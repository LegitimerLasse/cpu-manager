package com.nubedian.cpuapi.repository;

import com.nubedian.cpuapi.entity.Socket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocketRepository extends JpaRepository<Socket, Long> {
}
