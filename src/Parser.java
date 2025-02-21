import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Parser {
    String fileName;

    public Parser(String fileName) {
        if (!isFileExsist(fileName) || !fileName.endsWith(".txt")) {
            System.out.println("File tidak valid");
            System.exit(0);
        }
        this.fileName = fileName;
    }

    public static boolean isFileExsist(String fileName) {
        File f = new File("../data/" + fileName);
        return f.exists();
    }

    public boolean isUniformCapital(String str) {
        return str.matches("[A-Z]+") && str.chars().distinct().count() == 1;
    }

    public String getFileName() {
        return this.fileName;
    }

    public int[] readParam() {
        int[] param = new int[3];

        try (Scanner sc = new Scanner(new File("../data/" + fileName))) {
            if (!sc.hasNextLine()) {
                System.out.println("Parameter tidak ditemukan");
                System.exit(0);
            }

            String firstLine = sc.nextLine();
            Scanner sc2 = new Scanner(firstLine);

            if (!firstLine.matches("-?\\d+ -?\\d+ -?\\d+")) {
                System.out.println("Format parameter tidak valid");
                System.exit(0);
            }

            for (int i = 0; i < 3; i++) {
                param[i] = sc2.nextInt();
                if (param[i] <= 0) {
                    System.out.println("Nilai parameter tidak valid");
                    System.exit(0);
                }
            }

            sc2.close();
            return param;

        } catch (FileNotFoundException e) {
            System.err.println("File tidak ditemukan");
            System.exit(0);
            return null;
        }
    }

    public String readMode() {
        try (Scanner sc = new Scanner(new File("../data/" + fileName))) {
            if (!sc.hasNextLine()) {
                System.out.println("Mode tidak ditemukan");
                System.exit(0);
            }
            sc.nextLine();

            if (!sc.hasNextLine()) {
                System.out.println("Mode tidak ditemukan");
                System.exit(0);
            }
            String secondLine = sc.nextLine();

            sc.close();

            if (!secondLine.equals("DEFAULT")) {
                System.out.println("Format mode tidak valid");
                System.exit(0);
            }

            return (secondLine);

        } catch (FileNotFoundException e) {
            System.err.println("File tidak ditemukan");
            return null;
        }
    }

    public ArrayList<block> getBlock() {
        try (Scanner sc = new Scanner(new File("../data/" + fileName))) {
            if (!sc.hasNextLine()) {
                System.out.println("Block tidak ditemukan");
                System.exit(0);
            }
            sc.nextLine();

            if (!sc.hasNextLine()) {
                System.out.println("Block tidak ditemukan");
                System.exit(0);
            }
            sc.nextLine();

            if (!sc.hasNextLine()) {
                System.out.println("Block tidak ditemukan");
                System.exit(0);
            }

            ArrayList<block> blList = new ArrayList<block>();
            String s, sWs;
            ArrayList<Character> idTrack = new ArrayList<Character>();
            ArrayList<String> sameId = new ArrayList<String>();
            ArrayList<ArrayList<String>> allId = new ArrayList<ArrayList<String>>();
            while (sc.hasNextLine()) {
                s = sc.nextLine().stripTrailing();
                sWs = s.replaceAll("\\s", "");

                if (!isUniformCapital(sWs)) {
                    System.out.println("Format block tidak valid");
                    System.exit(0);
                }

                if (idTrack.contains(sWs.charAt(0))) {
                    sameId.add(s);
                } else {
                    ArrayList<String> sameId2 = new ArrayList<String>(sameId);
                    allId.add(sameId2);
                    sameId.clear();
                    idTrack.add(sWs.charAt(0));
                    sameId.add(s);
                }
            }
            allId.add(sameId);
            allId.remove(0);

            int row, col, maxCol;
            String sChar, sWhite;
            char idChar;
            for (ArrayList<String> id : allId) {
                sChar = id.get(0);
                sChar = sChar.replaceAll("\\s", "");
                idChar = sChar.charAt(0);
                row = id.size();
                maxCol = -1;
                for (String st : id) {
                    if (st.length() > maxCol) {
                        maxCol = st.length();
                    }
                }
                col = maxCol;

                int[][] bl = new int[row][col];
                for (int i = 0; i < row; i++) {
                    sWhite = id.get(i);
                    for (int j = 0; j < id.get(i).length(); j++) {
                        if (sWhite.charAt(j) != ' ') {
                            bl[i][j] = 1;
                        }
                    }
                }
                blList.add(new block(idChar, bl));
            }
            return blList;

        } catch (FileNotFoundException e) {
            System.err.println("Gagal 1");
            return null;
        }
    }

    public static void print2D(int mat[][]) {
        for (int[] row : mat) {
            System.out.println();
            for (int x : row) {
                System.out.print(x + " ");
            }
        }
    }

}
