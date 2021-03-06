package com.horus.velograph.util;

/*
 * Copyright (C) 2015 Hannes Dorfmann
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Factory annotation class
 *
 * @author Nitin Prakash
 */ 
@Target(ElementType.TYPE) @Retention(RetentionPolicy.CLASS)
public @interface Factory {

  /**
   * The name of the factory
   */
//  Class type();

  /**
   * The identifier for determining which item should be instantiated
   */
	String value() default "";
}