package daggerok.jsoup;

import io.vavr.control.Try;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JsoupExampleApplicationTests {

  @LocalServerPort
  Integer port;

  @Test
  void test_jsonp_connect_DOM() {
    var connection = Jsoup.connect(String.format("http://127.0.0.1:%d", port));
    var document = Try.of(connection::get)
                      .onFailure(System.err::println)
                      .getOrElseThrow((Function<Throwable, RuntimeException>) RuntimeException::new);
    assertThat(document.title()).isEqualTo("Home Page");

    var body = document.body();
    var elements = body.select("h1");
    assertThat(elements.size()).isEqualTo(1);

    var h1 = elements.first();
    assertThat(h1.text()).isEqualTo("Hello, World!");
  }
}
