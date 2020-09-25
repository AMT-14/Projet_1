package Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Builder
public class Question {
    private User author;
    private String body;
    
}
