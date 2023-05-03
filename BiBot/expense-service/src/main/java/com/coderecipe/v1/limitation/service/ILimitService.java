package com.coderecipe.v1.limitation.service;

import com.coderecipe.v1.limitation.dto.LimitDTO;

import java.util.List;

public interface ILimitService {

    List<Integer> addLimit(List<LimitDTO> req);
}
