package com.raz7.json;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * CLass JSONParser. Contains functionality for working with JSON.
 */
public class JSONParser {

	/**
	 * The associated {@link Gson} used for serializing/deserializing.
	 */
	private static Gson gson = new GsonBuilder().create();

	/**
	 * Parses the given {@code JSON} and returns the {@link List} of
	 * deserialised objects.
	 * 
	 * @param json
	 *            The JSON to be parsed.
	 * @param type
	 *            The {@link TypeToken} of the deserialized objects.
	 * @return The {@code List} containing the deserialized objects.
	 */
	public <T> List<T> parse(String json, TypeToken<List<T>> type) {
		return gson.fromJson(json, type.getType());
	}
}
