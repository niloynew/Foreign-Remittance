package com.mislbd.ababil.foreignremittance.configuration;

import com.google.gson.Gson;

public class JsonConverter {

  public static Object jsonDeserializer(String classname, String data)
      throws ClassNotFoundException {
    Gson gson = new Gson();
    return gson.fromJson(data, Class.forName(classname));
  }

  public static String jsonSerializer(Object object) {
    Gson gson = new Gson();
    String string = gson.toJson(object);
    return string;
  }
}
