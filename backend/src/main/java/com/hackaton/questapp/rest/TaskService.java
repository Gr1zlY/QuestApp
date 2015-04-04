package com.hackaton.questapp.rest;

import com.google.common.collect.Lists;
import com.hackaton.questapp.dao.QuestDao;
import com.hackaton.questapp.dao.QuestStatusDao;
import com.hackaton.questapp.dao.TaskDao;
import com.hackaton.questapp.dao.TeamMemberDao;
import com.hackaton.questapp.entity.*;
import org.apache.xmlbeans.impl.util.Base64;
import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Sheremeta on 04.04.2015.
 */

@RestController
public class TaskService {

    private TeamMemberDao teamMemberDao;

    private QuestStatusDao questStatusDao;

    private QuestDao questDao;

    private TaskDao taskDao;



    @RequestMapping(value = "/getLatestTask", headers = "Accept=application/json")
    public TaskEntity getLatestAvialableTask(@RequestParam String deviceId){
        TeamMemberEntity teamMember = teamMemberDao.getTeamMemberById(deviceId);
        if(teamMember == null) return null;
        TeamEntity team = teamMember.getTeam();
        QuestStatusEntity status = questStatusDao.getByTeam(team);
        return taskDao.getTaskByNumberAndQuestId(status.getTasksCompleted()+1,team.getQuest().getQuestId());
    }

    @RequestMapping(value = "/getAvailableTasks", headers = "Accept=application/json")
    public List<TaskEntity> getAvailableQuests(@RequestParam String deviceId){
        List<TaskEntity> result = Lists.newArrayList();
        TeamMemberEntity teamMember = teamMemberDao.getTeamMemberById(deviceId);
        if(teamMember == null) return null;
        TeamEntity team = teamMember.getTeam();
        QuestStatusEntity status = questStatusDao.getByTeam(team);
        for(int i = 1;i<=status.getTasksCompleted()+1;i++){
            result.add(taskDao.getTaskByNumberAndQuestId(i,team.getQuest().getQuestId()));
        }
        return result;
    }

    @RequestMapping(value = "/attemptSolution", headers = "Accept=application/json")
    public Status attemptSolution(@RequestParam String solutionCandidate,@RequestParam Long taskId,
                                  @RequestParam String deviceId) throws JSONException {
        TaskEntity taskEntity = taskDao.getById(taskId);
        TeamMemberEntity teamMember = teamMemberDao.getTeamMemberById(deviceId);
        TeamEntity team = teamMember.getTeam();
        if(taskEntity.getSolution().equals(solutionCandidate)){ // trim
            QuestStatusEntity questStatusEntity = questStatusDao.getByTeam(team);
            if(questStatusEntity.getTasksCompleted() != taskEntity.getTaskOrdinalNumber() - 1) return new Status("WRONG TASK ATTEMPTED");
            questStatusEntity.setTasksCompleted(questStatusEntity.getTasksCompleted() + 1);
            return new Status("OK");
        } else {
            return new Status("WRONG");
        }
    }

    @RequestMapping(value = "/getTasksByQuest", headers = "Accept=application/json")
    public List<TaskEntity> getTasksByQuest(@RequestParam Long questId){
        return taskDao.getTasksByQuestId(questId);
    }

    @RequestMapping(value = "/insertTask", headers = "Accept=application/json", method = RequestMethod.PUT)
    public Status insertTask(@RequestParam Long questId,@RequestParam String name, @RequestParam String description, @RequestParam String photo,
                             @RequestParam int ordinalNumber,@RequestParam String taskType, @RequestParam String solution){

       TaskEntity entity = new TaskEntity(System.currentTimeMillis(),questDao.getById(questId),name,description,
                Base64.decode(photo.getBytes()), ordinalNumber, TaskType.getTaskTypeByString(taskType),solution);
        taskDao.insert(entity.getTaskId(),entity); // TODO: ORDINAL LOGIC
        return new Status("OK");
    }

    @RequestMapping(value = "/removeTaskByTaskId", headers = "Accept=application/json", method = RequestMethod.DELETE)
    public Status removeTaskByTaskId(@RequestParam Long taskId){
        taskDao.removeByTaskId(taskId);
        return new Status("OK");
    }

    @RequestMapping(value = "/updateTask", headers = "Accept=application/json", method = RequestMethod.DELETE)
    public Status updateTaskByTaskId(@RequestParam Long taskId,@RequestParam String name, @RequestParam String description, @RequestParam String photo,
                                     @RequestParam int ordinalNumber,@RequestParam String taskType, @RequestParam String solution){
         TaskEntity taskEntity = taskDao.getById(taskId);
        if(taskEntity == null) return new Status("NOT FOUND");
        taskEntity.setTaskName(name);
        taskEntity.setDescription(description);
        taskEntity.setPhoto(Base64.decode(photo.getBytes()));
        taskEntity.setTaskOrdinalNumber(ordinalNumber);
        taskEntity.setTaskType(TaskType.getTaskTypeByString(taskType));
        taskEntity.setSolution(solution);
        return new Status("OK");
    }

    public void setTeamMemberDao(TeamMemberDao teamMemberDao) {
        this.teamMemberDao = teamMemberDao;
    }

    public void setQuestStatusDao(QuestStatusDao questStatusDao) {
        this.questStatusDao = questStatusDao;
    }

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public void setQuestDao(QuestDao questDao) {
        this.questDao = questDao;
    }
}
