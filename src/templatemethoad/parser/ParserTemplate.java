package templatemethoad.parser;

public abstract class ParserTemplate {
    public enum DataType {
        JSON("JSON data"), XML("XML data"), HTML("HTML data");

        private String type;

        DataType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    public final void parseData(DataType data) {
        readData();
        checkDataType(data);
        doParse();
        System.out.println("==================================");
    }

    private void readData() {
        System.out.println("read data");
    }

    private void checkDataType(DataType type) {
        System.out.println("Data Type: " + type.getType());
    }

    abstract void doParse();
}
