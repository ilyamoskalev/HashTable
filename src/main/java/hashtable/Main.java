package hashtable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        final Path pathInput = Paths.get(args[0]);
        final Path pathOutput = Paths.get(args[1]);
        final ChainHashTable chainTable = new ChainHashTable();
        final OpenAddressHashTableLP lpTable = new OpenAddressHashTableLP();
        final OpenAddressHashTableDH dhTable = new OpenAddressHashTableDH();
        try {
            final List<String> inputData = Files.readAllLines(pathInput);
            final List<String> outputData1 = new ArrayList<>();
            final List<String> outputData2 = new ArrayList<>();
            final List<String> outputData3 = new ArrayList<>();
            for (String line : inputData) {
                if (line.equals("print") || line.equals("min") || line.equals("max")) {
                    line += " ";
                }
                System.out.println(line);
                Integer value;
                switch (line.substring(0, line.indexOf(' '))) {
                    case "add":
                        value = Integer.parseInt(line.substring(line.lastIndexOf(' ') + 1));
                        int key = Integer.parseInt(line.substring(line.indexOf(' ') + 1, line.lastIndexOf(' ')));
                        chainTable.add(key, value);
                        lpTable.add(key, value);
                        dhTable.add(key, value);
                        break;
                    case "delete":
                        key = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
                        chainTable.delete(key);
                        lpTable.delete(key);
                        dhTable.delete(key);
                        break;
                    case "search":
                        key = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
                        value = chainTable.search(key);
                        if (value == null) {
                            outputData1.add("error");
                        } else {
                            outputData1.add(value.toString());
                        }
                        value = lpTable.search(key);
                        if (value == null) {
                            outputData2.add("error");
                        } else {
                            outputData2.add(value.toString());
                        }
                        value = dhTable.search(key);
                        if (value == null) {
                            outputData3.add("error");
                        } else {
                            outputData3.add(value.toString());
                        }
                        break;
                    case "min":
                        value = chainTable.min();
                        if (value == null) {
                            outputData1.add("empty");
                        } else {
                            outputData1.add(value.toString());
                        }
                        value = lpTable.min();
                        if (value == null) {
                            outputData2.add("empty");
                        } else {
                            outputData2.add(value.toString());
                        }
                        value = dhTable.min();
                        if (value == null) {
                            outputData3.add("empty");
                        } else {
                            outputData3.add(value.toString());
                        }
                        break;
                    case "max":
                        value = chainTable.max();
                        if (value == null) {
                            outputData1.add("empty");
                        } else {
                            outputData1.add(value.toString());
                        }
                        value = lpTable.max();
                        if (value == null) {
                            outputData2.add("empty");
                        } else {
                            outputData2.add(value.toString());
                        }
                        value = dhTable.max();
                        if (value == null) {
                            outputData3.add("empty");
                        } else {
                            outputData3.add(value.toString());
                        }
                        break;
                    case "print":
                        outputData1.add(chainTable.print());
                        outputData2.add(lpTable.print());
                        outputData3.add(dhTable.print());
                        break;
                }
            }
            outputData1.add("");
            outputData2.add("");
            outputData1.addAll(outputData2);
            outputData1.addAll(outputData3);
            Files.write(pathOutput, outputData1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
