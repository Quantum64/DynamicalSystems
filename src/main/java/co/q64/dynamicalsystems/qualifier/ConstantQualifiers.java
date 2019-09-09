package co.q64.dynamicalsystems.qualifier;

import javax.inject.Qualifier;

public interface ConstantQualifiers {
    // @formatter:off
    public static @Qualifier @interface ModId {}
    public static @Qualifier @interface Name {}
    public static @Qualifier @interface Author {}
    public static @Qualifier @interface Version {}
    public static @Qualifier @interface SharedNamespace {}
    public static @Qualifier @interface ConfigFolder {}
    // @formatter:on
}
