package com.business.proposal.workflow.engine.web;

import com.business.proposal.workflow.engine.pojo.WorkItem;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.business.proposal.workflow.engine.util.WorkFlowConstants.*;

@RestController
public class WorkflowEngineServiceController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;


    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/create-new-proposal", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createNewProposal(@RequestBody Map<String, String> item) {

        WorkItem workItem = new WorkItem(item.get("companyName"),
                item.get("monthOfYear"), Long.parseLong(item.get("businessAmount")));
        Map<String, Object> vars = Collections.<String, Object>singletonMap("workItem", workItem);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("process", vars);
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put(PROPOSAL_UPLOADED, true);
        taskService.complete(task.getId(), taskVariables);
        return processInstance.getId();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/process-one-approval/{id}/{status}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void processOneWorkflow(@PathVariable Integer id,
                                   @PathVariable boolean status) {

        Task task = taskService.createTaskQuery()
                .processInstanceId(id.toString())
                .active()
                .singleResult();
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put(PROCESS_ONE_APPROVAL, status);
        taskService.complete(task.getId(), taskVariables);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/process-two-approval/{id}/{status}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void processTwoWorkflow(@PathVariable Integer id,
                                   @PathVariable boolean status) {

        Task task = taskService.createTaskQuery()
                .processInstanceId(id.toString())
                .active()
                .singleResult();
        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put(PROCESS_TWO_APPROVAL, status);
        taskService.complete(task.getId(), taskVariables);

    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/process-status/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HistoricProcessInstance processHistory(@PathVariable Integer id) {
        return historyService.createHistoricProcessInstanceQuery().processInstanceId(id.toString()).singleResult();

    }
}
