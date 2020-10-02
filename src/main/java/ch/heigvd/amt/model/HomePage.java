package ch.heigvd.amt.model;

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
    private Post featured;

    public HomePage(Boolean isConnected, String description, String rules, ArrayList<Integer> statistics, Post q){
        this.description = description;
        this.rules = rules;
        this.statistics = statistics;
        this.isConnected = isConnected;
        this.featured = q;
    }
}
