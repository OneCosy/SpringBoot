package com.bit.jsptest.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.jsptest.model.mapper.AppDemoMapper;
@Service
public class AppDemoServiceImp implements AppDemoService {

    @Autowired
    private AppDemoMapper appDemoMapper;

    @Override
    public List<HashMap<String, Object>> selectEmpAll() {
        return appDemoMapper.selectEmpAll();
    }

    @Override
    public List<HashMap<String, Object>> selectDeptAll() {
        return appDemoMapper.selectDeptAll();
    }


}