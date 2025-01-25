package Energeenot.TestTaskFromStudioTG.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Пути, на которые разрешены запросы
                .allowedOrigins("https://minesweeper-test.studiotg.ru") // Разрешенный домен
                .allowedMethods( "POST"); // Разрешенные методы
    }
}
