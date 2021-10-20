package com.makarand;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ArgOptions {
    String[] args;
//    -r 6 -c 7 -p 0
    private int rowCount, colCount, p;
    private Options options;
    private final String ROW_ID = "-r", COL_ID ="-c", PIECE_ID ="-p";
    private final Map<String, String> valueMap = new HashMap<String, String>();
    static class Options {
        public int rowCount, colCount, p;
        public Options(int rowCount, int colCount, int p) {
            this.rowCount = rowCount;
            this.colCount = colCount;
            this.p = p;
        }
    }
    public ArgOptions(String[] args) {
        this.args = args;
    }

    public Options getOptions() {
        if(!isValidInput(this.args)) return null;
        if(this.options == null)
            this.options = new Options(this.rowCount, this.colCount, this.p);
        return options;
    }

    public boolean isValidInput(String[] stringArguments) {
        // validates the input given by command line args

        validateArguments(stringArguments);

        if(!valueMap.containsKey(ROW_ID) || !valueMap.containsKey(COL_ID) || !valueMap.containsKey(PIECE_ID)) {
            return false;
        }


        try {
            this.rowCount = Integer.parseInt(valueMap.get(ROW_ID));
            this.colCount = Integer.parseInt(valueMap.get(COL_ID));
            this.p = Integer.parseInt(valueMap.get(PIECE_ID));
// TODO: More robust validation check.
            if(rowCount <= 0 || colCount <= 0 || p <= 0) {
                throw new Exception("The input should be non-zero positive integer");
            }
        } catch (NumberFormatException e) {
            System.out.println("The row, column and p options need to be positive integers.");
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private void validateArguments(String[] stringArguments) {
        for(int i = 0; i < stringArguments.length-1; i++) {
            switch (stringArguments[i]) {
                case ROW_ID:
                    valueMap.put(ROW_ID, stringArguments[i + 1]);
                    break;
                case COL_ID:
                    valueMap.put(COL_ID, stringArguments[i + 1]);
                    break;
                case PIECE_ID:
                    valueMap.put(PIECE_ID, stringArguments[i + 1]);
                    break;
            }
        }
    }

}
