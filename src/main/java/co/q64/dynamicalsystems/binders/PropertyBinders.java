package co.q64.dynamicalsystems.binders;

import javax.inject.Qualifier;

public interface PropertyBinders {
    // @formatter:off
    public static @Qualifier @interface Up {}
    public static @Qualifier @interface Down {}
    public static @Qualifier @interface North {}
    public static @Qualifier @interface South {}
    public static @Qualifier @interface East {}
    public static @Qualifier @interface West {}
    public static @Qualifier @interface Running {}
    // @formatter:on
}
