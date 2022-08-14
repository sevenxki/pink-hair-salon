package edu.scau.misp.hs.entity;

import java.io.Serializable;

/**
 * (HsSalaryCom)实体类
 *
 * @author makejava
 * @since 2022-05-08 10:52:41
 */
public class HsSalaryCom implements Serializable {
    private static final long serialVersionUID = 135932790135109407L;
    /**
     * 某月工资对应的业绩单ID
     */
    private Integer salaryComId;
    /**
     * 某月的工资ID
     */
    private Integer salaryId;
    /**
     * 某月的某个业绩单
     */
    private Integer saleCommissionId;

    private HsSalary hsSalary;
    private HsSaleCommission hsSaleCommission;


    public Integer getSalaryComId() {
        return salaryComId;
    }

    public void setSalaryComId(Integer salaryComId) {
        this.salaryComId = salaryComId;
    }

    public Integer getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
    }

    public Integer getSaleCommissionId() {
        return saleCommissionId;
    }

    public void setSaleCommissionId(Integer saleCommissionId) {
        this.saleCommissionId = saleCommissionId;
    }

    public HsSalary getHsSalary() {
        return hsSalary;
    }

    public void setHsSalary(HsSalary hsSalary) {
        this.hsSalary = hsSalary;
    }

    public HsSaleCommission getHsSaleCommission() {
        return hsSaleCommission;
    }

    public void setHsSaleCommission(HsSaleCommission hsSaleCommission) {
        this.hsSaleCommission = hsSaleCommission;
    }
}

