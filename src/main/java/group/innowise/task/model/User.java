package group.innowise.task.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;


public class User implements Serializable {

    private int id;
    private String name;
    private String lastName;
    private String email;
    private EnumSet<Role> roles = EnumSet.noneOf(Role.class);
    private List<String> telephoneNumbers = new ArrayList<>();

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EnumSet<Role> getRoles() {
        return EnumSet.copyOf(roles);
    }

    public void setRoles(EnumSet<Role> roles) {
        this.roles = roles;
    }

    public List<String> getTelephoneNumbers() {
        return new ArrayList<>(telephoneNumbers);
    }

    public void setTelephoneNumbers(List<String> telephoneNumbers) {
        this.telephoneNumbers = telephoneNumbers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) &&
                lastName.equals(user.lastName) &&
                email.equals(user.email) &&
                telephoneNumbers.equals(user.telephoneNumbers)&&
                roles.equals(user.roles);

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = name.hashCode();
        result = prime * result + (lastName ==null? 0: lastName.hashCode());
        result = prime * result + (email ==null? 0: email.hashCode());
        result = prime * result + (telephoneNumbers == null? 0: telephoneNumbers.hashCode());
        result = prime * result + (roles == null? 0: roles.hashCode());
        return result;

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id);
        sb.append(", name='").append(name);
        sb.append(", lastName=").append(lastName);
        sb.append(", email=").append(email);
        sb.append(", phones=").append(telephoneNumbers);
        sb.append(", roles=").append(roles);
        return sb.toString();
    }

    public static class Builder {

        private User newUser;

        public Builder() {
            this.newUser = new User();
        }

        public Builder setId(int id) {
            newUser.id = id;
            return this;
        }

        public Builder setName(String name) {
            newUser.name = name;
            return this;
        }

        public Builder setLastName(String lastName) {
            newUser.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            newUser.email = email;
            return this;
        }

        public Builder setRoles(EnumSet<Role> roles) {
            newUser.roles = roles;
            return this;
        }

        public Builder setTelephoneNumbers(List<String> telephoneNumbers) {
            newUser.telephoneNumbers = telephoneNumbers;
            return this;
        }

        public User build() {
            return newUser;
        }

    }
}
