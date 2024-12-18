package ro.adi.agroadmin.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfiguration {
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            configForDeserializers(builder);
            configForSerializers(builder);
        };
    }

    private void configForSerializers(Jackson2ObjectMapperBuilder builder) {
        builder.serializers(new LocalDateSerializer(dateFormatter));
        builder.serializers(new LocalDateTimeSerializer(dateTimeFormatter));
    }

    private void configForDeserializers(Jackson2ObjectMapperBuilder builder) {
        builder.deserializers(new LocalDateDeserializer(dateFormatter));
        builder.deserializers(new LocalDateTimeDeserializer(dateTimeFormatter));
    }
}
