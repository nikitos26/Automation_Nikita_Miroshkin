package entities.sausedemo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserBuilder {
    private String userName;
    private String password;

    public static class Builder {
        private UserBuilder userBuilder;

        public Builder withUserName(String userName) {
            userBuilder.userName = userName;
            return this;
        }

        public Builder withPassword(String password) {
            userBuilder.password = password;
            return this;
        }

        public Builder() {
            this.userBuilder = new UserBuilder();
        }

        public UserBuilder build() {
            return userBuilder;
        }
    }

}
