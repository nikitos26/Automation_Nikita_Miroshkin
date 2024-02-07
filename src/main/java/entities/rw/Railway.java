package entities.rw;

import lombok.*;

@Data
@ToString
@Builder
public class Railway {
    private String from;
    private String to;
    private String station;

    public static class RailwayBuilder {
        public RailwayBuilder(){}
    }


}
