package co.q64.dynamicalsystems.inject;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

public interface ConstantBinders {
	// @formatter:off
	
	@Qualifier @Retention(RUNTIME) public static @interface ModId {}
	@Qualifier @Retention(RUNTIME) public static @interface Name {}
	@Qualifier @Retention(RUNTIME) public static @interface Author {}
	@Qualifier @Retention(RUNTIME) public static @interface Version {}
	
	// @formatter:on
}
