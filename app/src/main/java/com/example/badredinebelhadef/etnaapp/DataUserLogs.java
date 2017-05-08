package com.example.badredinebelhadef.etnaapp;

import java.util.List;
import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by badredinebelhadef on 02/05/2017.
 */

class Absences implements Serializable
{

    @SerializedName("catchings")
    @Expose
    private List<Object> catchings = null;
    @SerializedName("credit")
    @Expose
    private Integer credit;
    private final static long serialVersionUID = 923771429034173639L;

    public List<Object> getCatchings() {
        return catchings;
    }

    public void setCatchings(List<Object> catchings) {
        this.catchings = catchings;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

}

class Contract implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("learning_start")
    @Expose
    private String learningStart;
    @SerializedName("learning_end")
    @Expose
    private String learningEnd;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("total_expected")
    @Expose
    private Integer totalExpected;
    @SerializedName("total_log")
    @Expose
    private Integer totalLog;
    @SerializedName("periods")
    @Expose
    private List<Period> periods = null;
    @SerializedName("schedules")
    @Expose
    private List<Schedule> schedules = null;
    @SerializedName("absences")
    @Expose
    private Absences absences;
    private final static long serialVersionUID = -2794128766653735380L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLearningStart() {
        return learningStart;
    }

    public void setLearningStart(String learningStart) {
        this.learningStart = learningStart;
    }

    public String getLearningEnd() {
        return learningEnd;
    }

    public void setLearningEnd(String learningEnd) {
        this.learningEnd = learningEnd;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getTotalExpected() {
        return totalExpected;
    }

    public void setTotalExpected(Integer totalExpected) {
        this.totalExpected = totalExpected;
    }

    public Integer getTotalLog() {
        return totalLog;
    }

    public void setTotalLog(Integer totalLog) {
        this.totalLog = totalLog;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public Absences getAbsences() {
        return absences;
    }

    public void setAbsences(Absences absences) {
        this.absences = absences;
    }

}


public class DataUserLogs implements Serializable
{

    @SerializedName("student")
    @Expose
    private Student student;
    @SerializedName("contracts")
    @Expose
    private List<Contract> contracts = null;
    private final static long serialVersionUID = -4141305594670310L;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

}

class Period implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("contract")
    @Expose
    private Integer contract;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("log")
    @Expose
    private Integer log;
    @SerializedName("expected")
    @Expose
    private Integer expected;
    @SerializedName("invoice")
    @Expose
    private Object invoice;
    @SerializedName("absence")
    @Expose
    private Integer absence;
    @SerializedName("catch")
    @Expose
    private Integer _catch;
    @SerializedName("log_minutes")
    @Expose
    private Integer logMinutes;
    private final static long serialVersionUID = 4257255308246274629L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getContract() {
        return contract;
    }

    public void setContract(Integer contract) {
        this.contract = contract;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Integer getLog() {
        return log;
    }

    public void setLog(Integer log) {
        this.log = log;
    }

    public Integer getExpected() {
        return expected;
    }

    public void setExpected(Integer expected) {
        this.expected = expected;
    }

    public Object getInvoice() {
        return invoice;
    }

    public void setInvoice(Object invoice) {
        this.invoice = invoice;
    }

    public Integer getAbsence() {
        return absence;
    }

    public void setAbsence(Integer absence) {
        this.absence = absence;
    }

    public Integer getCatch() {
        return _catch;
    }

    public void setCatch(Integer _catch) {
        this._catch = _catch;
    }

    public Integer getLogMinutes() {
        return logMinutes;
    }

    public void setLogMinutes(Integer logMinutes) {
        this.logMinutes = logMinutes;
    }

}


class Schedule implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("end")
    @Expose
    private String end;
    private final static long serialVersionUID = -8372172472790773608L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}

class Student implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("salutation")
    @Expose
    private String salutation;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("in_etna_date")
    @Expose
    private String inEtnaDate;
    @SerializedName("out_etna_date")
    @Expose
    private String outEtnaDate;
    private final static long serialVersionUID = 5466514270087556318L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInEtnaDate() {
        return inEtnaDate;
    }

    public void setInEtnaDate(String inEtnaDate) {
        this.inEtnaDate = inEtnaDate;
    }

    public String getOutEtnaDate() {
        return outEtnaDate;
    }

    public void setOutEtnaDate(String outEtnaDate) {
        this.outEtnaDate = outEtnaDate;
    }

}
