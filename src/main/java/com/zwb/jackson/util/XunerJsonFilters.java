package com.zwb.jackson.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface XunerJsonFilters {
	XunerJsonFilter[] value();
}
