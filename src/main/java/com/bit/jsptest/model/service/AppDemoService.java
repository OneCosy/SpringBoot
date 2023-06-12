package com.bit.jsptest.model.service;

import java.util.HashMap;
import java.util.List;
//emp,list
public interface AppDemoService {
    public List<HashMap<String,Object>>selectEmpAll();
    public List<HashMap<String,Object>>selectDeptAll();

}