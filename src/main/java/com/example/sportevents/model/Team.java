package com.example.sportevents.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Team extends Model {



    private int Id;

    private String Name;

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }
}
