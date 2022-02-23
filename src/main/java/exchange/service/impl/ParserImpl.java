package exchange.service.impl;

import exchange.service.OrderActivityHandler;
import exchange.service.Parser;
import exchange.service.PrintActivityHandler;
import exchange.service.SetActivityHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ParserImpl implements Parser {
    private static final int INDEX_ACTIVITY_TYPE = 0;
    private final OrderActivityHandler orderActivityHandler;
    private final SetActivityHandler setActivityHandler;
    private final PrintActivityHandler printActivityHandler;

    @Override
    public String parseFile(String[] data) {
        StringBuilder sb = new StringBuilder();
        for (String line : data) {
            String[] lineData = line.split(",");
            if (lineData[INDEX_ACTIVITY_TYPE].equals("u")) {
                setActivityHandler.doActivity(lineData[3],
                        Integer.parseInt(lineData[2]),
                        Integer.parseInt(lineData[1]));
            } else if (lineData[INDEX_ACTIVITY_TYPE].equals("q")) {
                sb.append(printActivityHandler.doActivity(lineData[1],
                        lineData.length == 3 ? Integer.parseInt(lineData[2]) : 0))
                        .append(System.lineSeparator());
            } else if (lineData[INDEX_ACTIVITY_TYPE].equals("o")) {
                orderActivityHandler.doActivity(lineData[1],
                        Integer.parseInt(lineData[2]));
            }
        }
        return sb.toString().trim();
    }
}
