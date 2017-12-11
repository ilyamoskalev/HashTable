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
        try {
            final List<String> inputData = Files.readAllLines(pathInput);
            final List<String> outputData = new ArrayList<>();
            for (String line : inputData) {
                Integer value;
                if(line.equals("print") || line.equals("min") || line.equals("max")){
                    line += " ";
                }
                switch (line.substring(0, line.indexOf(' '))) {
                    case "add":
                        value = Integer.parseInt(line.substring(line.lastIndexOf(' ') + 1));
                        final int key = Integer.parseInt(line.substring(line.indexOf(' ') + 1, line.lastIndexOf(' ') - 1));
                        chainTable.add(key, value);
                        break;
                    case "delete":
                        chainTable.delete(Integer.parseInt(line.substring(line.indexOf(' ') + 1)));
                        break;
                    case "search":
                        value = chainTable.search(Integer.parseInt(line.substring(line.indexOf(' ') + 1)));
                        if (value == null) {
                            outputData.add("error");
                        } else {
                            outputData.add(value.toString());
                        }
                        break;
                    case "min":
                        value = chainTable.min();
                        if (value == null) {
                            outputData.add("empty");
                        } else {
                            outputData.add(value.toString());
                        }
                        break;
                    case "max":
                        value = chainTable.max();
                        if (value == null) {
                            outputData.add("empty");
                        } else {
                            outputData.add(value.toString());
                        }
                        break;
                    case "print":
                        outputData.add(chainTable.print());
                        break;
                }
            }
            Files.write(pathOutput, outputData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
