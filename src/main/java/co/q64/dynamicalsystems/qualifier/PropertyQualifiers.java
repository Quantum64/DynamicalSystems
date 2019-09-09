package co.q64.dynamicalsystems.qualifier;

import javax.inject.Qualifier;

public interface PropertyQualifiers {
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
