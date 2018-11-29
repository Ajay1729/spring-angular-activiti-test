package com.business.proposal.workflow.engine.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import static com.business.proposal.workflow.engine.util.WorkFlowConstants.PROCESS_TWO_APPROVAL;

@Component
public class ProcessTwoService implements JavaDelegate {
    public void approve() {
        System.out.println("Process Two Approval for work item in database ...");
    }

    public void reject() {
        System.out.println("Process Two Rejection for work item in database ...");
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        if ((Boolean) delegateExecution.getVariable(PROCESS_TWO_APPROVAL)) {
            approve();
        } else {
            reject();
        }
    }
}
