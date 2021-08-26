package com.horus.velograph.json;

import java.util.Iterator;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.jsoniter.*;
import com.jsoniter.output.JsonStream;

import com.horus.velograph.entity.Employee;

/*
import org.codehaus.jackson.map.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
//import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
//import com.fasterxml.jackson.databind.node.JsonNodeFactory;
*/

import org.json.JSONObject;




@Converter
public class JSONObjectConverter implements AttributeConverter<JSONObject, String> {

	//ObjectMapper mapper = new ObjectMapper();

	Class klazz = Employee.class;
	
    public String convertObjectToDatabaseColumn(Object entity) {
		return JsonStream.serialize(entity);
	}

    public String convertToDatabaseColumn(JSONObject entity) {
		return null;
	}

    public Object convertJSONToEntity(String jsonStr) {
		return JsonIterator.deserialize(jsonStr, klazz);
	}

    @Override
    public JSONObject convertToEntityAttribute(String jsonStr) {
		return null;
	}
	
	/*	
	public Object convert_from_jsonobject(Class klazz, JSONObject obj) throws Exception {
	//	JSONObject obj = new JSONObject().put("value", 3.14);
	//	employee =  mapper.readValue(new File("c://temp/employee.json"), Employee.class);
		return mapper.readValue(obj.toString(), klazz);
	}

	public static JSONObject convert_to_jsonobject(Object data) throws Exception {
		return new JSONObject(data);
	//	assertThat(obj.getDouble("value"), equalTo(3.14));
	}
	*/
	/*
	public static JsonNode convertJsonFormat(JSONObject json) {
		JsonNodeFactory nodeFactory = new JsonNodeFactory();
		ObjectNode ret = nodeFactory.objectNode();

		@SuppressWarnings("unchecked")
		Iterator<String> iterator = json.keys();
		for (; iterator.hasNext();) {
			String key = iterator.next();
			Object value;
			try {
				value = json.get(key);
			} catch (JSONException e) {
				throw new RuntimeException(e);
			}
			if (json.isNull(key))
				ret.putNull(key);
			else if (value instanceof String)
				ret.put(key, (String) value);
			else if (value instanceof Integer)
				ret.put(key, (Integer) value);
			else if (value instanceof Long)
				ret.put(key, (Long) value);
			else if (value instanceof Double)
				ret.put(key, (Double) value);
			else if (value instanceof Boolean)
				ret.put(key, (Boolean) value);
			else if (value instanceof JSONObject)
				ret.put(key, convertJsonFormat((JSONObject) value));
			else if (value instanceof JSONArray)
				ret.put(key, convertJsonFormat((JSONArray) value));
			else
				throw new RuntimeException("not prepared for converting instance of class " + value.getClass());
		}
		return ret;
	}

	public static Object convertJsonFormatToObject(Class klazz, JSONObject obj) {
		return jsonNodeToObject(klazz, convertJsonFormat(obj));
	}

	public static Object jsonNodeToObject(Class klazz, JsonNode node) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(new TreeTraversingParser(node), klazz);
	}
	
	
	public static JsonNode convertJsonFormat(JSONArray json) {
		ArrayNode ret = JsonNodeFactory.instance.arrayNode();
		for (int i = 0; i < json.length(); i++) {
			Object value;
			try {
				value = json.get(i);
			} catch (JSONException e) {
				throw new RuntimeException(e);
			}
			if (json.isNull(i))
				ret.addNull();
			else if (value instanceof String)
				ret.add((String) value);
			else if (value instanceof Integer)
				ret.add((Integer) value);
			else if (value instanceof Long)
				ret.add((Long) value);
			else if (value instanceof Double)
				ret.add((Double) value);
			else if (value instanceof Boolean)
				ret.add((Boolean) value);
			else if (value instanceof JSONObject)
				ret.add(convertJsonFormat((JSONObject) value));
			else if (value instanceof JSONArray)
				ret.add(convertJsonFormat((JSONArray) value));
			else
				throw new RuntimeException("not prepared for converting instance of class " + value.getClass());
		}
		return ret;
	}

	public static String convertToJson(Object an_object) {	
	  return new Gson().toJson(an_object);
	}		

	public static Object convertToJson(Class klazz, String a_json_string) {	
	  return new Gson().fromJson(a_json_string, klazz);
	}
	*/

	
}