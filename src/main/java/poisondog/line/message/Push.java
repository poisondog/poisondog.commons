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
package poisondog.line.message;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import poisondog.core.Data;
import poisondog.core.Mission;
import poisondog.net.http.HttpParameter;
import poisondog.net.http.HttpPost;

/**
 * @author Adam Huang
 * @since 2018-01-24
 */
public class Push implements Mission<Push.Parameter> {

	@Override
	public Void execute(Push.Parameter parameter) throws IOException {
		HttpParameter para = new HttpParameter();
		para.setUrl("https://api.line.me/v2/bot/message/push");
		para.addHeader("Content-Type", "application/json");
		para.addHeader("Authorization", "Bearer " + parameter.mAuth);

		JSONArray array = new JSONArray();
		for (String line : parameter.mReplyMessages) {
			JSONObject msg = new JSONObject();
			msg.put("type", "text");
			msg.put("text", line);
			array.put(msg);
		}

		JSONObject relay = new JSONObject();
		relay.put("to", parameter.mId);
		relay.put("messages", array);

		para.setData(new Data(relay.toString().getBytes()));
		HttpPost post = new HttpPost();
		post.execute(para);
		return null;
	}

	public static class Parameter {
		private String mAuth;
		private String mId;
		private List<String> mReplyMessages;

		/**
		 * Constructor
		 */
		public Parameter(String auth, String id) {
			mAuth = auth;
			mId = id;
			mReplyMessages = new LinkedList<String>();
		}

		public void addLine(String line) {
			if (mReplyMessages.size() >= 5)
				throw new IllegalArgumentException("push messages can't over 5.");
			mReplyMessages.add(line);
		}
	}
}
