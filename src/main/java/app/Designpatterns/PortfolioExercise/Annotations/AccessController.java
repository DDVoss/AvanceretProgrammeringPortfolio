package app.Designpatterns.PortfolioExercise.Annotations;

import java.lang.reflect.Method;

public class AccessController implements Service{
    SecureService service;
    User user;

    public AccessController(SecureService service, User user) {
        this.service = service;
        this.user = user;
    }

    public static void invokeIfAuthorized(Object service, User user, String methodName) {
        try {
            Method method = service.getClass().getMethod(methodName);
            Role roleAnnotation = method.getAnnotation(Role.class);

            if (roleAnnotation == null) {
                System.out.println("Metoden '" + methodName + "' kræver ingen rolle – kaldes.");
                method.invoke(service);
                return;
            }

            String requiredRole = roleAnnotation.value();
            if (user.getRole().equals(requiredRole)) {
                System.out.println("Adgang givet til '" + methodName + "' for bruger '" + user.getName() + "'");
                method.invoke(service);
            } else {
                System.out.println("Adgang nægtet til '" + methodName + "' for bruger '" + user.getName() + "'");
            }

        } catch (NoSuchMethodException e) {
            System.out.println("Metoden '" + methodName + "' findes ikke.");
        } catch (Exception e) {
            System.out.println("Fejl under metodekald: " + e.getMessage());
        }
    }

    @Override
    public void deleteAllUsers() {
        AccessController.invokeIfAuthorized(service, user, "deleteAllUsers");

    }

    @Override
    public void viewProfile() {
        AccessController.invokeIfAuthorized(service, user, "viewProfile");
    }

    @Override
    public void help() {
        AccessController.invokeIfAuthorized(service, user, "help");
    }
}
