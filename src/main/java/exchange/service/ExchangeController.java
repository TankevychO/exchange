package exchange.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ExchangeController {
    private static final String INPUT_FILE = "input.txt";
    private static final String OUTPUT_FILE = "output.txt";
    private final ReaderService reader;
    private final WriterService writer;
    private final Parser parser;

    @Autowired
    public void exchange() {
        String[] text = reader.readFromFile(INPUT_FILE);
        String data = parser.parseFile(text);
        writer.writeData(OUTPUT_FILE, data);
    }
}
