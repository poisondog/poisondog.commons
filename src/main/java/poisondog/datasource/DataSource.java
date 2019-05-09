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
package poisondog.datasource;

import java.util.List;
import poisondog.util.Pair;
import poisondog.filter.Filter;

/**
 * @author Adam Huang
 * @since 2018-07-28
 */
public interface DataSource {
	public boolean save(Object obj) throws Exception;
	public List<? extends Object> list(Filter conditions) throws Exception;
	public boolean delete(Filter conditions) throws Exception;
}
