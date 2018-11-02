package com.business.proposal.workflow.engine.service;

import org.springframework.stereotype.Component;

@Component
public class ProcessTwoService {
    public void approve() {
        System.out.println("Process Two Approval for work item in database ...");
    }

    public void reject() {
        System.out.println("Process Two Rejection for work item in database ...");
    }
}
