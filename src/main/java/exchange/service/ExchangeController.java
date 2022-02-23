package exchange.service;

import exchange.service.impl.ReaderServiceImpl;
import exchange.service.impl.WriterServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ExchangeController {
    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";
    private final Parser parser;

    @Autowired
    public void exchange() {
        String[] text = new ReaderServiceImpl().readFromFile(INPUT_FILE);
        String data = parser.parseFile(text);
        new WriterServiceImpl().writeData(OUTPUT_FILE, data);
    }
}
