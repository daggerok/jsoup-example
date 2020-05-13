package daggerok.jsoup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class JsoupExampleApplication {

  @Bean
  RouterFunction<ServerResponse> routes(@Value("classpath:/public/index.html") Resource indexHtml) {
    return route().GET("/**", r -> ok().contentType(MediaType.TEXT_HTML)
                                       .bodyValue(indexHtml))
                  .build();
  }

  public static void main(String[] args) {
    SpringApplication.run(JsoupExampleApplication.class, args);
  }
}
