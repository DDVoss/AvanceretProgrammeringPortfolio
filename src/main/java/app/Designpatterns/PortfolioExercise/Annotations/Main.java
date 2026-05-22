package app.Designpatterns.PortfolioExercise.Annotations;

public class Main {
    public static void main(String[] args) {
        SecureService service = new SecureService();

        User admin = new User("Alice", "admin");
        User normal = new User("Bob", "user");

        Service adminProxy = new AccessController(service, admin);
        Service normalProxy = new AccessController(service, normal);

        // Call methods as admin
        System.out.println("=== Admin access ===");
        adminProxy.deleteAllUsers();
        adminProxy.viewProfile();
        adminProxy.help();

        // Call methods as normal
        System.out.println("\n=== Normal Access ===");
        normalProxy.deleteAllUsers();
        normalProxy.viewProfile();
        normalProxy.help();
    }
}
