package indi.icear.framework.coolqhttpapi.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;

public class JacksonUtil {
    private static ObjectMapper objectMapper;

    public static @NotNull ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

}
