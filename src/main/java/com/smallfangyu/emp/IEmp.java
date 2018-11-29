package com.smallfangyu.emp;

import java.util.List;

public interface IEmp {
    /**
     * 新增员工
     * @param emp
     */
    public void addEmp(Emp emp);

    /**
     * 根据员工编号删除员工
     * @param empno
     */
    public void deleteEmp(int empno);

    /**
     * 根据员工编号修改员工
     * @param emp
     */
    public void updateEmp(Emp emp);

    /**
     * 查询所有员工
     * @return
     */
    public List<Emp> selectEmp();

    /**
     * 连表查询所有员工
     * @return
     */
    public List<Emp> selectEmpAndDepartment();
}
