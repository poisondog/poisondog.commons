/*
 * Copyright (C) 2018 Adam Huang <poisondog@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package poisondog.mail;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2018-01-26
 */
public class Send implements Mission<Send.Parameter> {

	@Override
	public Void execute(Send.Parameter parameter) throws EmailException {
		SimpleEmail se = new SimpleEmail();
		se.setCharset(parameter.mCharset);
		se.setHostName(parameter.mHost);
		se.setFrom(parameter.mFromMail, parameter.mFrom, parameter.mCharset);
		se.addTo(parameter.mToMail, parameter.mTo, parameter.mCharset);
		se.setSubject(parameter.mSubject);
		se.setMsg(parameter.mMessage);
		se.send();
		return null;
	}

	public static class Parameter {
		private String mCharset;
		private String mHost;
		private String mFrom;
		private String mFromMail;
		private String mTo;
		private String mToMail;
		private String mSubject;
		private String mMessage;

		/**
		 * Constructor
		 */
		public Parameter(String host, String fromMail, String toMail) {
			mCharset = "utf-8";
			mHost = host;
			mFrom = fromMail;
			mFromMail = fromMail;
			mTo = toMail;
			mToMail = toMail;
			mSubject = "";
			mMessage = "";
		}

		public void setCharset(String charset) {
			mCharset = charset;
		}

		public void setFromName(String name) {
			mFrom = name;
		}

		public void setToName(String name) {
			mTo = name;
		}

		public void setSubject(String subject) {
			mSubject = subject;
		}

		public void setMessage(String message) {
			mMessage = message;
		}
	}
}
