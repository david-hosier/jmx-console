package org.wildfly.extras.jmxconsole.util;

import java.beans.IntrospectionException;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.util.HashMap;
import java.util.Map;

public class WildflyUtil {

	/** Primitive type name -> class map. */
	private static final Map<String, Class<?>> PRIMITIVE_NAME_TYPE_MAP = new HashMap<>();

	/** Setup the primitives map. */
	static {
		PRIMITIVE_NAME_TYPE_MAP.put("boolean", Boolean.TYPE);
		PRIMITIVE_NAME_TYPE_MAP.put("byte", Byte.TYPE);
		PRIMITIVE_NAME_TYPE_MAP.put("char", Character.TYPE);
		PRIMITIVE_NAME_TYPE_MAP.put("short", Short.TYPE);
		PRIMITIVE_NAME_TYPE_MAP.put("int", Integer.TYPE);
		PRIMITIVE_NAME_TYPE_MAP.put("long", Long.TYPE);
		PRIMITIVE_NAME_TYPE_MAP.put("float", Float.TYPE);
		PRIMITIVE_NAME_TYPE_MAP.put("double", Double.TYPE);
	}

	public static Object convertValue(String text, String typeName)
			throws ClassNotFoundException, IntrospectionException {
		// see if it is a primitive type first
		Class<?> typeClass = getPrimitiveTypeForName(typeName);
		if (typeClass == null) {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			typeClass = loader.loadClass(typeName);
		}

		PropertyEditor editor = PropertyEditorManager.findEditor(typeClass);
		if (editor == null) { throw new IntrospectionException(
				"No property editor for type=" + typeClass); }

		editor.setAsText(text);
		return editor.getValue();
	}

	public static Class<?> getPrimitiveTypeForName(final String name) {
		return (Class<?>) PRIMITIVE_NAME_TYPE_MAP.get(name);
	}

	public static PropertyEditor findEditor(final Class<?> type) {
		return PropertyEditorManager.findEditor(type);
	}

	public static boolean isNullHandlingEnabled() {
		return true;
	}
}
