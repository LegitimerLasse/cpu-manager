package com.nubedian.cpuapi.service;

import com.nubedian.cpuapi.dto.CpuDetailDto;
import com.nubedian.cpuapi.dto.CpuSummaryDto;
import com.nubedian.cpuapi.dto.CpuUpdateRequest;
import com.nubedian.cpuapi.entity.Cpu;
import com.nubedian.cpuapi.entity.Socket;
import com.nubedian.cpuapi.repository.CpuRepository;
import com.nubedian.cpuapi.repository.SocketRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CpuService {

    private final CpuRepository cpuRepository;
    private final SocketRepository socketRepository;

    public CpuService(CpuRepository cpuRepository, SocketRepository socketRepository) {
        this.cpuRepository = cpuRepository;
        this.socketRepository = socketRepository;
    }

    public List<CpuSummaryDto> findAll() {
        return cpuRepository.findAllWithSocket().stream()
                .map(c -> new CpuSummaryDto(c.getId(), c.getBrand(), c.getModel(), c.getSocket().getName()))
                .toList();
    }

    public CpuDetailDto findById(Long id) {
        Cpu cpu = cpuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CPU not found: " + id));
        return toDetailDto(cpu);
    }

    @Transactional
    public CpuDetailDto update(Long id, CpuUpdateRequest req) {
        Cpu cpu = cpuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CPU not found: " + id));

        Socket socket = socketRepository.findById(req.socketId())
                .orElseThrow(() -> new EntityNotFoundException("Socket not found: " + req.socketId()));

        cpu.setBrand(req.brand());
        cpu.setModel(req.model());
        cpu.setSocket(socket);
        cpu.setClockSpeedGhz(req.clockSpeedGhz());
        cpu.setCores(req.cores());
        cpu.setThreads(req.threads());
        cpu.setTdpWatt(req.tdpWatt());
        cpu.setPriceEur(req.priceEur());

        return toDetailDto(cpu);
    }

    private CpuDetailDto toDetailDto(Cpu cpu) {
        return new CpuDetailDto(
                cpu.getId(),
                cpu.getBrand(),
                cpu.getModel(),
                cpu.getSocket().getId(),
                cpu.getSocket().getName(),
                cpu.getClockSpeedGhz(),
                cpu.getCores(),
                cpu.getThreads(),
                cpu.getTdpWatt(),
                cpu.getPriceEur(),
                cpu.getVersion()
        );
    }
}
