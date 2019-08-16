package com.example.service.impl;

import com.example.mapper.LetterMapper;
import com.example.model.po.Letter;
import com.example.service.ILetterService;
import org.springframework.stereotype.Service;

@Service
public class LetterService extends BaseService<LetterMapper, Letter> implements ILetterService {
}
