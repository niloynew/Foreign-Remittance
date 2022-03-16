package com.mislbd.ababil.foreignremittance.annotation;

import com.mislbd.ababil.foreignremittance.annotation.impl.SwiftAddressValidator;
import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.springframework.stereotype.Component;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SwiftAddressValidator.class)
@Documented
@Component
public @interface SwiftAddress {

  String message() default "Address format is not valid";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  int expRow() default 3;

  int expColumn() default 33;
}
