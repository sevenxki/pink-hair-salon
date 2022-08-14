package edu.scau.misp.hs.vo;

public class BonusVO {
    /**
     * 经手人
     */
    private String username;
    /**
     * 额外奖金/扣除
     */
    private Long bonus;
    /**
     * 工资序号
     */
    private Integer salaryId;
    /**
     * 奖励/扣除理由
     */
    private String reason;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getBonus() {
        return bonus;
    }

    public void setBonus(Long bonus) {
        this.bonus = bonus;
    }

    public Integer getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
