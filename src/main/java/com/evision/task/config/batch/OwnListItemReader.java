/**
 * 
 */
package com.evision.task.config.batch;

import java.util.List;
import org.springframework.batch.item.ItemReader;
import org.springframework.lang.Nullable;

/**
 * @author Mohamed Ramadan
 *
 */
public class OwnListItemReader<T> implements ItemReader<T> {

	private List<T> list;

	public OwnListItemReader(List<T> list) {
		// If it is a proxy we assume it knows how to deal with its own state.
		// (It's probably transaction aware.)

		this.list = list;

	}

	@Nullable
	@Override
	public T read() {
		if (list != null && !list.isEmpty()) {

			return list.remove(0);
		}
		return null;
	}

}
