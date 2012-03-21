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

package functional.response;

import org.junit.Test;
import litmus.functional.FunctionalTest;

public class ContentTypeExample extends FunctionalTest {

	@Test
	public void html() {
		assertThat(get("/contentTypes/html")).isHtml();
		assertThat(get("/contentTypes/html")).hasContentType("text/html");
	}
	
	@Test
	public void xml(){
		assertThat(get("/contentTypes/xml")).isXml();
		assertThat(get("/contentTypes/xml")).hasContentType("text/xml");
	}
	
	@Test
	public void json(){
		assertThat(get("/contentTypes/json")).isJson();
		assertThat(get("/contentTypes/json")).hasContentType("application/json");
	}
	
	@Test
	public void text(){
		assertThat(get("/contentTypes/text")).isText();
		assertThat(get("/contentTypes/text")).hasContentType("text/plain");
	}

}