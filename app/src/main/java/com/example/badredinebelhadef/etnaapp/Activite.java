package com.example.badredinebelhadef.etnaapp;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by badredinebelhadef on 06/05/2017.
 */

public class Activite {

    private String moduleName;
    private String projectName;
    private String projectDeadLine;

    public Activite (String moduleName, String projectName, String projectDeadLine) {

        this.moduleName = moduleName;
        this.projectName = projectName;
        this.projectDeadLine = projectDeadLine;
    }

    // Getter & Setter ModuleName
    public String getModuleName () {
        return this.moduleName;
    }
    public void setModuleName (String moduleName) {
        this.moduleName = moduleName;
    }

    // Getter & Setter ProjectName
    public String getProjectName () {
        return this.projectName;
    }
    public void setProjectName (String projectName) {
        this.projectName = projectName;
    }

    // Getter & Setter ProjectDeadLine
    public String getProjectDeadLine () {
        return this.projectDeadLine;
    }
    public void setProjectDeadLine (String projectDeadLine) {
        this.projectDeadLine = projectDeadLine;
    }
}
