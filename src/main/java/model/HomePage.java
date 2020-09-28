package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;


@Setter
@Getter
@Builder
public class HomePage {
    private Boolean isConnected;
    private String description;
    private String rules;
    private ArrayList<Integer> statistics;

    public HomePage(Boolean isConnected, String description, String rules, ArrayList<Integer> statistics){
        this.description = description;
        this.rules = rules;
        this.statistics = statistics;
        this.isConnected = isConnected;
    }
}
