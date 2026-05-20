package app.Designpatterns.Proxy.annotations;

import java.lang.reflect.Method;

public class AccessController {

    //EXPLANATION Bruger et proxy pattern. Selve AccessController fungere som mellem mand hvor kontrollen bliver givet til diverse roller som kan findes i ENUM Role.

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
}
