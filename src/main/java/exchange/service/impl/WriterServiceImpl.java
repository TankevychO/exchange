package exchange.service.impl;

import exchange.service.WriterService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WriterServiceImpl implements WriterService {
    @Override
    public boolean writeData(String toFilePath, String data) {
        try {
            FileOutputStream resource = new FileOutputStream(toFilePath);
            resource.write(data.getBytes(StandardCharsets.UTF_8));
            return true;
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file!", e);
        }
    }
}
