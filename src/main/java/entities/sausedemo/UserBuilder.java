package entities.sausedemo;

public class UserBuilder {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }

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

    @Override
    public String toString() {
        return "UserBuilder{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
