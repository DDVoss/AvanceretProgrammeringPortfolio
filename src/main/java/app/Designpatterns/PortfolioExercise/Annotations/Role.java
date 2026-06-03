package app.Designpatterns.PortfolioExercise.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // Behold annotationerne imens programmet kører
@Target(ElementType.METHOD)  // Kan kun blive brugt på metoder
public @interface Role {
    String value(); // fx "admin", "user"
}
