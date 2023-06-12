package com.bit.jsptest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpDto {
    private int empNo;
    private String eName;
    private String job;
    private int mgr;
    private String hireDate;
    private double sal;
    private double comm;
    private int deptNo;
}
