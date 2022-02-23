package exchange;

import exchange.service.Parser;
import exchange.service.impl.ReaderServiceImpl;
import exchange.service.impl.WriterServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ExchangeServiceApplication {
    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication
                .run(ExchangeServiceApplication.class, args);
        String[] text = new ReaderServiceImpl().readFromFile(INPUT_FILE);
        Parser parser = run.getBean(Parser.class);
        String data = parser.parseFile(text);
        new WriterServiceImpl().writeData(OUTPUT_FILE, data);
    }
}
