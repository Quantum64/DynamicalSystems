package co.q64.dynamicalsystems.binders;

import javax.inject.Qualifier;

public interface ConstantBinders {
    // @formatter:off
    public static @Qualifier @interface ModId {}
    public static @Qualifier @interface Name {}
    public static @Qualifier @interface Author {}
    public static @Qualifier @interface Version {}
    public static @Qualifier @interface SharedNamespace {}
    // @formatter:on
}
