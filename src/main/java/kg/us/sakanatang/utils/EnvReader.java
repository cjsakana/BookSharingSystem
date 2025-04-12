package kg.us.sakanatang.utils;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

@Component
public class EnvReader {
    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    public static String getEnv(String key) {
        // 优先从.env文件获取，没有则从系统环境变量获取
        return dotenv.get(key, System.getenv(key));
    }
}