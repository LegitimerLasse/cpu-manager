package com.nubedian.cpuapi.repository;

import com.nubedian.cpuapi.entity.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CpuRepository extends JpaRepository<Cpu, Long> {

    @Query("SELECT c FROM Cpu c JOIN FETCH c.socket")
    List<Cpu> findAllWithSocket();
}
