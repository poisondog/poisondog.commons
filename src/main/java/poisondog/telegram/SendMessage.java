/*
 * Copyright (C) 2019 Adam Huang <poisondog@gmail.com>
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
package poisondog.telegram;

import java.io.IOException;
import org.json.JSONObject;
import poisondog.core.Data;
import poisondog.core.Mission;
import poisondog.net.http.HttpParameter;
import poisondog.net.http.HttpPost;

/**
 * @author Adam Huang
 * @since 2019-02-27
 */
public class SendMessage implements Mission<String[]> {
	private String mToken;

	/**
	 * Constructor
	 */
	public SendMessage(String token) {
		mToken = token;
	}

	@Override
	public Void execute(String... input) throws IOException {
		String chatID = input[0];
		String line = input[1];

		StringBuilder builder = new StringBuilder();
		builder.append("https://api.telegram.org/bot");
		builder.append(mToken);
		builder.append("/sendMessage");

		HttpParameter para = new HttpParameter();
		para.setUrl(builder.toString());
		para.addHeader("Content-Type", "application/json");

		JSONObject msg = new JSONObject();
		msg.put("chat_id", chatID);
		msg.put("text", line);

		para.setData(new Data(msg.toString().getBytes()));
		HttpPost post = new HttpPost();
		post.execute(para);
		return null;
	}
}
