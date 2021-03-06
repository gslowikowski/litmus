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

package controllers;

import play.mvc.Controller;
import play.mvc.results.Forbidden;
import play.mvc.results.NotFound;

@SuppressWarnings("UnusedDeclaration")
public class ResponseController extends Controller {
	
	public static void ok(){

	}

	public static void notFound(){
		throw new NotFound("not found. Sorry");
	}

	public static void forbidden(){
		throw new Forbidden("Forbidden. Forbidden I tell ya!");
	}
	
	public static void redirect(){
		redirectTo();
	}
	
	public static void redirectTo(){

	}
	
}
