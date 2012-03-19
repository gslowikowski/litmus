/*
 * Copyright 2012 Ben Verbeken
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package litmus.functional;

import static java.lang.String.format;

public class WrongContentTypeException extends RuntimeException {

	public WrongContentTypeException(String expectedContentType, String actualContentType, HttpMethod method, Object request) {
		super(format("Expected to find contentType %s but was %s [Request: %s %s]", expectedContentType, actualContentType, method, request));
	}

}
