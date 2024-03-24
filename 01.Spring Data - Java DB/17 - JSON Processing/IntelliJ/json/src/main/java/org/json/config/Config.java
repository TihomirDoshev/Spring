package org.json.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean("withNulls")
    public Gson createGSONWithNulls() {
        return new GsonBuilder()
                //.excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()
                .setPrettyPrinting()
                .create();
    }
    @Bean("withoutNulls")
    public Gson createGSONWithoutNulls() {
        return new GsonBuilder()
                //.excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }
}
