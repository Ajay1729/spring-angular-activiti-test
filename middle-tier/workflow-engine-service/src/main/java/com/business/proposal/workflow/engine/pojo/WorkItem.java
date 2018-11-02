package com.business.proposal.workflow.engine.pojo;

import java.io.Serializable;

public class WorkItem implements Serializable {

    private String companyName;
    private String monthOfYear;
    private Long businessAmount;

    public WorkItem(String companyName, String monthOfYear, Long businessAmount) {
        this.companyName = companyName;
        this.monthOfYear = monthOfYear;
        this.businessAmount = businessAmount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getMonthOfYear() {
        return monthOfYear;
    }

    public Long getBusinessAmount() {
        return businessAmount;
    }


}
