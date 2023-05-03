package com.coderecipe.v1.limitation.service.impl;

import com.coderecipe.v1.limitation.dto.LimitDTO;
import com.coderecipe.v1.limitation.model.Limitation;
import com.coderecipe.v1.limitation.model.repository.ILimitRepository;
import com.coderecipe.v1.limitation.service.ILimitService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class LimitServiceImpl implements ILimitService {

    private final ILimitRepository iLimitRepository;

    @Override
    public List<Integer> addLimit(List<LimitDTO> req) {
        return req.stream().map(e -> {
            Limitation limitation = Limitation.of(e);
            iLimitRepository.save(limitation);
            return limitation.getId();
        }).toList();
    }
}
