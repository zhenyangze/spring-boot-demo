package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.DeptMapper;
import com.example.model.Dept;
import com.example.service.IDeptService;
import org.springframework.stereotype.Service;

@Service
public class DeptService extends ServiceImpl<DeptMapper, Dept> implements IDeptService {
}
