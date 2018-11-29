package com.business.proposal.workflow.engine.service;

import com.business.proposal.workflow.engine.pojo.WorkItem;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class WorkItemService implements JavaDelegate {

    public void createWorkItem(WorkItem workItem) {
        System.out.println("Creating work in database with request:+" + workItem.toString());
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        createWorkItem((WorkItem) delegateExecution.getVariable("workItem"));
    }
}
