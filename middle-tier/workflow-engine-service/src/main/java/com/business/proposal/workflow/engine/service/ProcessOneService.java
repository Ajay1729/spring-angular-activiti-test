package com.business.proposal.workflow.engine.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import static com.business.proposal.workflow.engine.util.WorkFlowConstants.PROCESS_ONE_APPROVAL;

@Component
public class ProcessOneService implements JavaDelegate {

    public void approve() {
        System.out.println("Process One Approval for work item in database ...");
    }

    public void reject() {
        System.out.println("Process One Rejection for work item in database ...");
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        if ((Boolean) delegateExecution.getVariable(PROCESS_ONE_APPROVAL)) {
            approve();
        } else {
            reject();
        }
    }
}
