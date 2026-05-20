package app.Designpatterns.Proxy.annotations;

public class Main {
    public static void main(String[] args) {
        SecureService service = new SecureService();

        User admin = new User("Alice", "admin");
        User normal = new User("Bob", "user");

        AccessController.invokeIfAuthorized(service, admin, "deleteAllUsers");
        AccessController.invokeIfAuthorized(service, normal, "deleteAllUsers");
        AccessController.invokeIfAuthorized(service, normal, "viewProfile");
        AccessController.invokeIfAuthorized(service, normal, "help");
    }
}
