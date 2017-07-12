package com.soshow.ssi.common.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import redis.clients.util.SafeEncoder;

/**
 * @date 2016年5月10日 下午4:43:32
 * @version 1.0.0 
 */
public class JdkSerializer implements ISerializer {
	public static final ISerializer me = new JdkSerializer();

	public byte[] keyToBytes(String key) {
		return SafeEncoder.encode(key);
	}

	public String keyFromBytes(byte[] bytes) {
		return SafeEncoder.encode(bytes);
	}

	public byte[] fieldToBytes(Object field) {
		return valueToBytes(field);
	}

	public Object fieldFromBytes(byte[] bytes) {
		return valueFromBytes(bytes);
	}

	public byte[] valueToBytes(Object value) {
		ObjectOutputStream objectOut = null;
		try {
			ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
			objectOut = new ObjectOutputStream(bytesOut);
			objectOut.writeObject(value);
			objectOut.flush();
			return bytesOut.toByteArray();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (objectOut != null)
				try {
					objectOut.close();
				} catch (Exception e) {
				}
		}
	}

	public Object valueFromBytes(byte[] bytes) {
		if (bytes == null || bytes.length == 0)
			return null;

		ObjectInputStream objectInput = null;
		try {
			ByteArrayInputStream bytesInput = new ByteArrayInputStream(bytes);
			objectInput = new ObjectInputStream(bytesInput);
			return objectInput.readObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (objectInput != null)
				try {
					objectInput.close();
				} catch (Exception e) {
				}
		}
	}
}
