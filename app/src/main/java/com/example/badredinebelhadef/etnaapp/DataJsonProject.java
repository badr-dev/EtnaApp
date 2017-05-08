package com.example.badredinebelhadef.etnaapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by badredinebelhadef on 05/05/2017.
 */
public class DataJsonProject implements Serializable
{

    @SerializedName("project")
    @Expose
    private List<Project> project = null;
    @SerializedName("quest")
    @Expose
    private List<Quest> quest = null;
    @SerializedName("cours")
    @Expose
    private List<Cour> cours = null;
    @SerializedName("toeic")
    @Expose
    private List<Object> toeic = null;
    @SerializedName("waiting")
    @Expose
    private List<Object> waiting = null;
    private final static long serialVersionUID = -3976818990199374465L;

    public List<Project> getProject() {
        return project;
    }

    public void setProject(List<Project> project) {
        this.project = project;
    }

    public List<Quest> getQuest() {
        return quest;
    }

    public void setQuest(List<Quest> quest) {
        this.quest = quest;
    }

    public List<Cour> getCours() {
        return cours;
    }

    public void setCours(List<Cour> cours) {
        this.cours = cours;
    }

    public List<Object> getToeic() {
        return toeic;
    }

    public void setToeic(List<Object> toeic) {
        this.toeic = toeic;
    }

    public List<Object> getWaiting() {
        return waiting;
    }

    public void setWaiting(List<Object> waiting) {
        this.waiting = waiting;
    }

}


class Cour implements Serializable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("date_start")
    @Expose
    private String dateStart;
    @SerializedName("date_end")
    @Expose
    private String dateEnd;
    @SerializedName("registration_date")
    @Expose
    private String registrationDate;
    @SerializedName("activity_id")
    @Expose
    private Integer activityId;
    @SerializedName("module_id")
    @Expose
    private Integer moduleId;
    @SerializedName("svn")
    @Expose
    private String svn;
    @SerializedName("unsubscribed")
    @Expose
    private List<Object> unsubscribed = null;
    @SerializedName("events")
    @Expose
    private List<Object> events = null;
    private final static long serialVersionUID = -5364621023277437143L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getSvn() {
        return svn;
    }

    public void setSvn(String svn) {
        this.svn = svn;
    }

    public List<Object> getUnsubscribed() {
        return unsubscribed;
    }

    public void setUnsubscribed(List<Object> unsubscribed) {
        this.unsubscribed = unsubscribed;
    }

    public List<Object> getEvents() {
        return events;
    }

    public void setEvents(List<Object> events) {
        this.events = events;
    }

}


class Project implements Serializable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("date_start")
    @Expose
    private String dateStart;
    @SerializedName("date_end")
    @Expose
    private String dateEnd;
    @SerializedName("registration_date")
    @Expose
    private String registrationDate;
    @SerializedName("activity_id")
    @Expose
    private Integer activityId;
    @SerializedName("module_id")
    @Expose
    private Integer moduleId;
    @SerializedName("svn")
    @Expose
    private String svn;
    @SerializedName("unsubscribed")
    @Expose
    private List<Object> unsubscribed = null;
    @SerializedName("events")
    @Expose
    private List<Object> events = null;
    private final static long serialVersionUID = -3492278794061497372L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getSvn() {
        return svn;
    }

    public void setSvn(String svn) {
        this.svn = svn;
    }

    public List<Object> getUnsubscribed() {
        return unsubscribed;
    }

    public void setUnsubscribed(List<Object> unsubscribed) {
        this.unsubscribed = unsubscribed;
    }

    public List<Object> getEvents() {
        return events;
    }

    public void setEvents(List<Object> events) {
        this.events = events;
    }

}


class Quest implements Serializable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("date_start")
    @Expose
    private String dateStart;
    @SerializedName("date_end")
    @Expose
    private String dateEnd;
    @SerializedName("registration_date")
    @Expose
    private String registrationDate;
    @SerializedName("activity_id")
    @Expose
    private Integer activityId;
    @SerializedName("module_id")
    @Expose
    private Integer moduleId;
    @SerializedName("svn")
    @Expose
    private String svn;
    @SerializedName("stages")
    @Expose
    private List<Stage> stages = null;
    @SerializedName("unsubscribed")
    @Expose
    private List<Object> unsubscribed = null;
    @SerializedName("events")
    @Expose
    private List<Object> events = null;
    private final static long serialVersionUID = 7358972849634583346L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getSvn() {
        return svn;
    }

    public void setSvn(String svn) {
        this.svn = svn;
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void setStages(List<Stage> stages) {
        this.stages = stages;
    }

    public List<Object> getUnsubscribed() {
        return unsubscribed;
    }

    public void setUnsubscribed(List<Object> unsubscribed) {
        this.unsubscribed = unsubscribed;
    }

    public List<Object> getEvents() {
        return events;
    }

    public void setEvents(List<Object> events) {
        this.events = events;
    }

}

class Stage implements Serializable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("can_validate_after_end")
    @Expose
    private Boolean canValidateAfterEnd;
    @SerializedName("interval")
    @Expose
    private Object interval;
    @SerializedName("depends_on")
    @Expose
    private Object dependsOn;
    @SerializedName("weak_depends_on")
    @Expose
    private Object weakDependsOn;
    @SerializedName("invalidate")
    @Expose
    private Object invalidate;
    @SerializedName("has_moulinette")
    @Expose
    private Boolean hasMoulinette;
    private final static long serialVersionUID = 3602281162332022697L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Boolean getCanValidateAfterEnd() {
        return canValidateAfterEnd;
    }

    public void setCanValidateAfterEnd(Boolean canValidateAfterEnd) {
        this.canValidateAfterEnd = canValidateAfterEnd;
    }

    public Object getInterval() {
        return interval;
    }

    public void setInterval(Object interval) {
        this.interval = interval;
    }

    public Object getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(Object dependsOn) {
        this.dependsOn = dependsOn;
    }

    public Object getWeakDependsOn() {
        return weakDependsOn;
    }

    public void setWeakDependsOn(Object weakDependsOn) {
        this.weakDependsOn = weakDependsOn;
    }

    public Object getInvalidate() {
        return invalidate;
    }

    public void setInvalidate(Object invalidate) {
        this.invalidate = invalidate;
    }

    public Boolean getHasMoulinette() {
        return hasMoulinette;
    }

    public void setHasMoulinette(Boolean hasMoulinette) {
        this.hasMoulinette = hasMoulinette;
    }

}

