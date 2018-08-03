package cn.ctx.common.framework;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataHelper {
	
	private static Logger LOG = LoggerFactory.getLogger(DataHelper.class);
	
	private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
	private static final String CLASS_STRING = "java.lang.String";

	private static final int TYPE_NULL = 0;
	private static final int TYPE_STRING = 1;
	private static final int TYPE_ENTITY = 2;
	private static final int TYPE_LIST = 3;
	private static final int TYPE_MAP = 4;

	public static Object readObject(UTDataInputStream in) throws IOException {
		Object result;
		int type = in.readUnsignedByte();
		if (type == TYPE_NULL) {
			result = null;
		} else if (type == TYPE_ENTITY) {
			result = readEntity(in);
		} else if (type == TYPE_LIST) {
			result = readList(in);
		} else if (type == TYPE_MAP) {
			result = readMap(in);
		} else {
			result = in.readString();
		}
		return result;
	}
	
	@SuppressWarnings({ "unchecked" })
	public static void writeObject(UTDataOutputStream out, Object value) throws IOException {
		if (value == null) {
			out.writeByte(TYPE_NULL);
		} else if (value instanceof Entity) {
			out.writeByte(TYPE_ENTITY);
			writeEntity(out, (Entity) value, true);
		} else if (value instanceof List) {
			out.writeByte(TYPE_LIST);
			writeList(out, (List<Entity>) value, true);
		} else if (value instanceof Map) {
			out.writeByte(TYPE_MAP);
			writeMap(out, (Map<String, Object>) value);
		} else {
			out.writeByte(TYPE_STRING);
			out.writeString(value.toString());
		}
	}

	@SuppressWarnings("rawtypes")
	public static Entity readEntity(UTDataInputStream in) throws IOException {
		String className = in.readString();
		Entity result = null;
		try {
			Class itemClass = Class.forName(className);
			result = (Entity) itemClass.newInstance();
			result.readFrom(in);
		} catch (ClassNotFoundException e) {
			LOG.error("Class not found: " + className);
		} catch (InstantiationException e) {
			LOG.error("New object error: " + className);
		} catch (IllegalAccessException e) {
			LOG.error("New object error: " + className);
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	private static Entity readEntity(UTDataInputStream in, Class entityClass) throws IOException {
		Entity result = null;
		try {
			result = (Entity) entityClass.newInstance();
			result.readFrom(in);
		} catch (InstantiationException e) {
			LOG.error("New object error: " + entityClass.getName());
		} catch (IllegalAccessException e) {
			LOG.error("New object error: " + entityClass.getName());
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static Entity readEntity(String data, Class entityClass) {
		if (data == null) {
			return null;
		}
		UTDataInputStream in = new UTDataInputStream(new ByteArrayInputStream(data.getBytes(ISO_8859_1)));
		try {
			int type = in.readUnsignedByte();
			if (type == TYPE_ENTITY) {
				return readEntity(in, entityClass);
			}
		} catch (IOException e) {
			LOG.error("IO error", e);
		}
		return null;
	}

	public static void writeEntity(UTDataOutputStream out, Entity entity, boolean writeClassName) throws IOException {
		if (writeClassName) {
			String className = entity.getClass().getName();
			out.writeString(className);
		}
		entity.writeTo(out);
	}

	public static String writeEntity(Entity entity) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		UTDataOutputStream out = new UTDataOutputStream(bos);
		try {
			if (entity == null) {
				out.writeByte(TYPE_NULL);
			} else {
				out.writeByte(TYPE_ENTITY);
				entity.writeTo(out);
			}
		} catch (IOException e) {
			LOG.error("IO Error in DataHelper.writeEntity");
		}
		return new String(bos.toByteArray(), ISO_8859_1);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List readList(UTDataInputStream in) throws IOException {
		List result = new ArrayList();
		int size = in.readUnsignedShort();
		if (size > 0) {
			String className = in.readString();
			try {
				Class itemClass = Class.forName(className);
				for (int i = 0; i < size; i++) {
					Entity o = (Entity) itemClass.newInstance();
					o.readFrom(in);
					result.add(o);
				}
			} catch (ClassNotFoundException e) {
				LOG.error("Class not found: " + className);
			} catch (InstantiationException e) {
				LOG.error("New object error: " + className);
			} catch (IllegalAccessException e) {
				LOG.error("New object error: " + className);
			}
		}
		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List readList(UTDataInputStream in, Class itemClass) throws IOException {
		List result = new ArrayList();
		int size = in.readUnsignedShort();
		if (size > 0) {
			boolean isString = CLASS_STRING.equals(itemClass.getName());
			
			try {
				for (int i = 0; i < size; i++) {
					if (isString) {
						result.add(in.readString());
					} else {
						Entity o = (Entity) itemClass.newInstance();
						o.readFrom(in);
						result.add(o);
					}
				}
			} catch (InstantiationException e) {
				LOG.error("New object error: " + itemClass.getName());
			} catch (IllegalAccessException e) {
				LOG.error("New object error: " + itemClass.getName());
			}
		}
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static List readList(String data, Class itemClass) {
		if (data == null) {
			return null;
		}
		UTDataInputStream in = new UTDataInputStream(new ByteArrayInputStream(data.getBytes(ISO_8859_1)));
		List result = null;
		try {
			result = readList(in, itemClass);
		} catch (IOException e) {
			LOG.error("IO Error in DataHelper.readList");
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public static void writeList(UTDataOutputStream out, List data, boolean writeClassName) throws IOException {
		if (data == null || data.isEmpty()) {
			out.writeShort(0);
		} else {
			out.writeShort(data.size());
			Object item = data.get(0);
			if (writeClassName) {
				out.writeString(item.getClass().getName());
			}
			boolean isString = item instanceof String;
			for (Object o : data) {
				if (isString) {
					out.writeString((String) o);
				} else {
					((Entity) o).writeTo(out);
				}
			}
		}
	}
	
	/**
	 * Not write class name into result
	 * @param data
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String writeList(List data) {
		return writeList(data, false);
	}

	@SuppressWarnings("rawtypes")
	public static String writeList(List data, boolean writeClassName) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		UTDataOutputStream out = new UTDataOutputStream(bos);
		try {
			writeList(out, data, writeClassName);
		} catch (IOException e) {
			LOG.error("IO Error in DataHelper.writeList");
		}
		return new String(bos.toByteArray(), ISO_8859_1);
	}

	public static Map<String, Object> readMap(UTDataInputStream in) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		int size = in.readUnsignedShort();
		for (int i = 0; i < size; i++) {
			String key = in.readString();
			map.put(key, readObject(in));
		}
		return map;
	}
	
	public static Map<String, Object> readMap(String data) {
		if (data == null) {
			return null;
		}
		UTDataInputStream in = new UTDataInputStream(new ByteArrayInputStream(data.getBytes(ISO_8859_1)));
		Map<String, Object> result = null;
		try {
			result = readMap(in);
		} catch (IOException e) {
			LOG.error("IO Error in DataHelper.readMap");
		}
		return result;
	}

	public static void writeMap(UTDataOutputStream out, Map<String, Object> map) throws IOException {
		if (map == null || map.isEmpty()) {
			out.writeShort(0);
		} else {
			out.writeShort(map.size());
			for (String key : map.keySet()) {
				out.writeString(key);
				writeObject(out, map.get(key));
			}
		}
	}
	
	public static String writeMap(Map<String, Object> map) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		UTDataOutputStream out = new UTDataOutputStream(bos);
		try {
			writeMap(out, map);
		} catch (IOException e) {
			LOG.error("IO Error in DataHelper.writeMap");
		}
		return new String(bos.toByteArray(), ISO_8859_1);
	}

}
