package com.business.proposal.workflow.engine.web;

import com.business.proposal.workflow.engine.pojo.WorkItem;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WorkflowEngineServiceController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/create-new-proposal", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createNewProposal(@RequestBody Map<String, String> item) {

        WorkItem workItem = new WorkItem(item.get("companyName"),
                item.get("monthOfYear"), Long.parseLong(item.get("businessAmount")));
        Map<String, Object> vars = Collections.<String, Object>singletonMap("workItem", workItem);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("process", vars);
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put("proposalUploaded", true);
        taskService.complete(task.getId(), taskVariables);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/process-one-approval/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void processOneWorkflow(@PathVariable Integer id) {

        Task task = taskService.createTaskQuery().taskId(id.toString())
                .singleResult();
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put("processOneApproval", true);
        taskService.complete(task.getId(), taskVariables);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/process-two-approval/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void processTwoWorkflow(@PathVariable Integer id) {

        Task task = taskService.createTaskQuery().taskId(id.toString())
                .singleResult();
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put("processTwoApproval", true);
        taskService.complete(task.getId(), taskVariables);
    }
}
