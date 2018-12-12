package com.smallfangyu.emp;

import java.util.List;

public interface IDepartment {

    /**
     * 添加新部门
     * @param depart
     */
    public void addDepartment(Department depart);

    public int selectDname(String dname);

    public List<Department> selectDepartmentAndEmp();
}
