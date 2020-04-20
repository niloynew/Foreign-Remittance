package com.mislbd.ababil.foreignremittance.configuration;

import com.google.gson.Gson;

public class JsonConverter {

  public static Object jsonDeserializer(Class clz, String data)
      throws ClassNotFoundException {
    Gson gson = new Gson();
    return gson.fromJson(data, clz);
  }

  public static String jsonSerializer(Object object) {
    Gson gson = new Gson();
    String string = gson.toJson(object);
    return string;
  }
}
