package templatemethoad;

import templatemethoad.parser.HTMLParser;
import templatemethoad.parser.JSONParser;
import templatemethoad.parser.ParserTemplate;
import templatemethoad.parser.XMLParser;

public class Client {
    public static void main(String[] args) {
        ParserTemplate xmlParser = new XMLParser();
        ParserTemplate htmlParser = new HTMLParser();
        ParserTemplate jsonParser = new JSONParser();

        xmlParser.parseData(ParserTemplate.DataType.XML);
        htmlParser.parseData(ParserTemplate.DataType.HTML);
        jsonParser.parseData(ParserTemplate.DataType.JSON);
    }
}
