package ch.heigvd.amt.domain;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode
public abstract class Id {

    private UUID id;

    public Id(){
        this.id = UUID.randomUUID();
    }

    public Id(String id) {
        this.id = UUID.fromString(id);
    }

    public Id(UUID id){
        if(id == null){
            throw new NullPointerException();

        } else {
            this.id = id;
        }
    }

    public String asString() {
        return id.toString();
    }

}

