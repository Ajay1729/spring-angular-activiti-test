package com.business.proposal.workflow.engine.service;

import org.springframework.stereotype.Component;

@Component
public class ProcessOneService {

    public void approve() {
        System.out.println("Process One Approval for work item in database ...");
    }

    public void reject() {
        System.out.println("Process One Rejection for work item in database ...");
    }
}
