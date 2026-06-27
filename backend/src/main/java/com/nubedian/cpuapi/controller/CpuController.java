package com.nubedian.cpuapi.controller;

import com.nubedian.cpuapi.dto.CpuDetailDto;
import com.nubedian.cpuapi.dto.CpuSummaryDto;
import com.nubedian.cpuapi.dto.CpuUpdateRequest;
import com.nubedian.cpuapi.service.CpuService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cpus")
public class CpuController {

    private final CpuService cpuService;

    public CpuController(CpuService cpuService) {
        this.cpuService = cpuService;
    }

    @GetMapping
    public List<CpuSummaryDto> list() {
        return cpuService.findAll();
    }

    @GetMapping("/{id}")
    public CpuDetailDto detail(@PathVariable Long id) {
        return cpuService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CpuDetailDto> update(@PathVariable Long id,
                                               @Valid @RequestBody CpuUpdateRequest req) {
        return ResponseEntity.ok(cpuService.update(id, req));
    }
}
