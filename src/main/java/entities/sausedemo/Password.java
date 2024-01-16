package entities.sausedemo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Password {
    private String password;

    public static class PasswordBuilder {
        public PasswordBuilder(){}
    }
}
