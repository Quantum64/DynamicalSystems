package co.q64.dynamicalsystems.binders;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

public interface PropertyBinders {
	// @formatter:off
	
	@Qualifier @Retention(RUNTIME) public static @interface Up {}
	@Qualifier @Retention(RUNTIME) public static @interface Down {}
	@Qualifier @Retention(RUNTIME) public static @interface North {}
	@Qualifier @Retention(RUNTIME) public static @interface South {}
	@Qualifier @Retention(RUNTIME) public static @interface East {}
	@Qualifier @Retention(RUNTIME) public static @interface West {}
	
	@Qualifier @Retention(RUNTIME) public static @interface AlignX {}
	@Qualifier @Retention(RUNTIME) public static @interface AlignY {}
	@Qualifier @Retention(RUNTIME) public static @interface AlignZ {}
	
	// @formatter:on
}
