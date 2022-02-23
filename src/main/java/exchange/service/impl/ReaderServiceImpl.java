package exchange.service.impl;

import exchange.service.ReaderService;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Component;

@Component
public class ReaderServiceImpl implements ReaderService {
    public String[] readFromFile(String filePath) {
        try {
            FileInputStream resource = new FileInputStream(filePath);
            byte[] bytes = resource.readAllBytes();
            String text = new String(bytes, StandardCharsets.UTF_8);
            return text.split(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Can't read file!", e);
        }
    }
}
