package org.storck.medium.blob;

public class GreeterApplication {

    public static void main(String[] args) {
        MediumUserService userService = new MediumUserService();
        MediumUser user = userService.getCurrentUser();
        GreeterService greeter = new GreeterService();
        greeter.greet(user);
    }
}
