package com.example.sportevents.model;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;

public class TeamEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        Team team = (Team) getValue();

        return team == null ? "" : team.getName();
    }

    @Override
    public void setAsText(String text){

        if (StringUtils.isEmpty(text)) {
            setValue(null);
        }else{
            Team team = new Team();
            team.setName(text);
            setValue(team);
        }

    }

}
