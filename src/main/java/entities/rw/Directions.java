package entities.rw;

import lombok.*;

@Data
@ToString
@Builder
public class Directions {
    private String from;
    private String to;

    public static class DirectionsBuilder {
        public DirectionsBuilder(){}
    }


}
