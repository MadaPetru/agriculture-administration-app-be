package ro.adi.agroadmin.config.redis.generator;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;
import reactor.util.annotation.NonNull;

import java.lang.reflect.Method;

@Configuration
public class ListFieldImageRequestDtoKeyGenerator implements KeyGenerator {
    @Override
    public @NonNull Object generate(@NonNull Object target, @NonNull Method method, @NonNull Object... params) {
        var sol = new StringBuilder();
        for (var param : params) {
            if (!sol.isEmpty()) sol.append('-');
            sol.append(param.toString());
        }
        return sol.toString();
    }
}
