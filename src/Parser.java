import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Parser {
    String fileName;

    public Parser(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public int getN() {
        try (Scanner sc = new Scanner(new File("../data/" + fileName))) {
            String s = sc.nextLine();
            sc.close();
            return (s.charAt(0) - '0');
        } catch (FileNotFoundException e) {
            System.err.println("Gagal 1");
            return -1;
        }
    }

    public int getM() {
        try (Scanner sc = new Scanner(new File("../data/" + fileName))) {
            String s = sc.nextLine();
            sc.close();
            return (s.charAt(2) - '0');
        } catch (FileNotFoundException e) {
            System.err.println("Gagal 1");
            return -1;
        }
    }

    public int getP() {
        try (Scanner sc = new Scanner(new File("../data/" + fileName))) {
            String s = sc.nextLine();
            sc.close();
            return (s.charAt(4) - '0');
        } catch (FileNotFoundException e) {
            System.err.println("Gagal 1");
            return -1;
        }
    }

    public String getS() {
        try (Scanner sc = new Scanner(new File("../data/" + fileName))) {
            String s = sc.nextLine();
            s = sc.nextLine();
            sc.close();
            return (s);
        } catch (FileNotFoundException e) {
            System.err.println("Gagal 1");
            return "Gagal 1";
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

    public ArrayList<block> getBlock() {
        try (Scanner sc = new Scanner(new File("../data/" + fileName))) {
            sc.nextLine();
            sc.nextLine();
            ArrayList<block> blList = new ArrayList<block>();

            String s, sWs;
            ArrayList<Character> idTrack = new ArrayList<Character>();
            ArrayList<String> sameId = new ArrayList<String>();
            ArrayList<ArrayList<String>> allId = new ArrayList<ArrayList<String>>();
            while (sc.hasNextLine()) {
                s = sc.nextLine();
                sWs = s.trim();
                // System.out.println(sWs.charAt(0));
                // System.out.println(idTrack);
                // System.out.println(idTrack.contains(sWs.charAt(0)));
                // System.out.println(s);
                if (idTrack.contains(sWs.charAt(0))) {
                    sameId.add(s);
                } else {
                    // System.out.println("hel");
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
                sChar = sChar.trim();
                // System.out.println(sChar);
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
                // System.out.println();
                // print2D(bl);
                blList.add(new block(idChar, bl));
            }
            return blList;

        } catch (FileNotFoundException e) {
            System.err.println("Gagal 1");
            return null;
        }
    }

    public static void main(String[] args) {
        Parser p = new Parser("1.txt");
        // System.out.print(p.getN());
        // System.out.print(p.getM());
        // System.out.println(p.getP());
        // System.out.println(p.getS());
        ArrayList<block> t = p.getBlock();
        papan pap = new papan(4, 3);
        solver sol = new solver();
        if (!sol.solve(pap, t, 0)) {
            System.out.println("gagal");
        } else {
            System.out.println("berhasil");
        }
        pap.display2();
        pap.write("result.txt");
    }
}
