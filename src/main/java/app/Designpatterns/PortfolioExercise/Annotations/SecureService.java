package app.Designpatterns.PortfolioExercise.Annotations;

public class SecureService implements Service{

    @Role("admin")
    public void deleteAllUsers() {
        System.out.println("Alle brugere er slettet.");
    }

    @Role("user")
    public void viewProfile() {
        System.out.println("Profil vist.");
    }

    public void help() {
        System.out.println("Hjælp åbnet.");
    }
}
