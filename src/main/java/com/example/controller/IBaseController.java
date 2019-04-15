package com.example.controller;

import com.example.exception.ProjectException;
import com.example.model.BaseModel;
import com.example.model.vo.ResultVO;

public interface IBaseController<VO extends BaseModel> {

    default ResultVO list(long current, long size, VO vo) {
        throw new ProjectException("方法未实现！");
    }

    default ResultVO<VO> findById(Integer id) {
        throw new ProjectException("方法未实现！");
    }

    default ResultVO save(VO vo) {
        throw new ProjectException("方法未实现！");
    }

    default ResultVO update(VO vo) {
        throw new ProjectException("方法未实现！");
    }

    default ResultVO delete(Integer id) {
        throw new ProjectException("方法未实现！");
    }

}
