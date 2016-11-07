package edu.utdallas.wpl.cookies.spring.biz.manager.utils;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;

/**
 * A simple dozer helper class to auto-convert lists from source type to destination class type (list).
 * 
 * @author Heman
 *
 */
public class DozerHelper {

	@SuppressWarnings({ "rawtypes", "serial", "unchecked" })
	public static <T, U> List<U> map(final Mapper mapper, final Iterable<T> source, final Class<U> destType) {
		
		final ArrayList<U> resultingList = new ArrayList<U>();

		for (T element : source) {
			if (element == null) {
				continue;
			}
			resultingList.add(mapper.map(element, destType));
		}

		// finally remove all null values if any
		resultingList.removeAll(new ArrayList() {{ add(null); }});

		return resultingList;
	}
}
