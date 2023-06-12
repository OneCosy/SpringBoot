package com.bit.jsptest.model.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface AppDemoMapper {
    public List<HashMap<String,Object>>selectEmpAll();
    public List<HashMap<String,Object>>selectDeptAll();
}
