package com.example.sportevents.model;

import javax.validation.constraints.Null;
import java.util.Date;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Event extends Model {


    private int Id;

    private String Title;

    private Team FirstTeam;
    private Team SecondTeam;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date Date;

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public Team getFirstTeam() {
        return FirstTeam;
    }

    public Team getSecondTeam() {
        return SecondTeam;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setFirstTeam(Team firstTeam) {
        FirstTeam = firstTeam;

    }

    public void setSecondTeam(Team secondTeam) {
        SecondTeam = secondTeam;

    }
}
