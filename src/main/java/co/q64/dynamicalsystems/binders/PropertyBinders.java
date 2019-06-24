package co.q64.dynamicalsystems.binders;

import javax.inject.Qualifier;

public interface PropertyBinders {
    // @formatter:off
    @Qualifier
    public static @interface Up {}
    @Qualifier
    public static @interface Down {}
    @Qualifier
    public static @interface North {}
    @Qualifier
    public static @interface South {}
    @Qualifier
    public static @interface East {}
    @Qualifier
    public static @interface West {}
    @Qualifier
    public static @interface AlignX {}
    @Qualifier
    public static @interface AlignY {}
    @Qualifier
    public static @interface AlignZ {}
    // @formatter:on
}
