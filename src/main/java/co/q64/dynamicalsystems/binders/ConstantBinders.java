package co.q64.dynamicalsystems.binders;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

public interface ConstantBinders {
    // @formatter:off

    @Qualifier
    @Retention(RUNTIME)
    public static @interface ModId {}

    @Qualifier
    @Retention(RUNTIME)
    public static @interface Name {}

    @Qualifier
    @Retention(RUNTIME)
    public static @interface Author {}

    @Qualifier
    @Retention(RUNTIME)
    public static @interface Version {}

    @Qualifier
    @Retention(RUNTIME)
    public static @interface SharedNamespace {}

    @Qualifier
    @Retention(RUNTIME)
    public static @interface MaterialsItemGroup {}

    @Qualifier
    @Retention(RUNTIME)
    public static @interface MachinesItemGroup {}

    // @formatter:on
}
