import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;

public class papan {
    private int row, col;
    private char[][] buffer;
    private long time = 0;
    private int eval = 0;

    public papan(int row, int col) {
        this.row = row;
        this.col = col;
        this.buffer = new char[row][col];

        for (char[] rows : buffer) {
            Arrays.fill(rows, '.');
        }
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public long getTime() {
        return time;
    }

    public int getEval() {
        return eval;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setEval(int eval) {
        this.eval = eval;
    }

    public void incEval() {
        this.eval++;
    }

    public boolean checkPlacement(block b, int tlx, int tly) {
        int[][] buff = b.getBuffer();
        int rows = buff.length;
        int cols = buff[0].length;

        if (tlx + cols > col || tly + rows > row) {
            return false;
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (buff[i][j] == 1 && buffer[tly + i][tlx + j] != '.') {
                    return false;
                }
            }
        }

        return true;
    }

    public void place(block b, int tlx, int tly) {
        char id = b.getId();
        int[][] buff = b.getBuffer();
        int rows = buff.length;
        int cols = buff[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (buff[i][j] == 1) {
                    buffer[tly + i][tlx + j] = id;
                }
            }
        }
    }

    public void remove(block b, int tlx, int tly) {
        int[][] buff = b.getBuffer();
        int rows = buff.length;
        int cols = buff[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (buff[i][j] == 1) {
                    buffer[tly + i][tlx + j] = '.';
                }
            }
        }
    }

    public void display() {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(buffer[i][j]);
            }
            System.out.println();
        }
    }

    public void display2() {
        String[] bgColors = {
                "\u001B[38;5;196m", // a Merah terang
                "\u001B[38;5;202m", // bOranye
                "\u001B[38;5;208m", // cKuning oranye
                "\u001B[38;5;214m", // dKuning terang
                "\u001B[38;5;220m", // eKuning emas
                "\u001B[38;5;226m", // fKuning lemon
                "\u001B[38;5;82m", // gHijau terang
                "\u001B[38;5;46m", // hHijau daun
                "\u001B[38;5;34m", // iHijau gelap
                "\u001B[38;5;21m", // jBiru terang
                "\u001B[38;5;27m", // kBiru muda
                "\u001B[38;5;33m", // lBiru laut
                "\u001B[38;5;129m", // mUngu muda
                "\u001B[38;5;135m", // nUngu sedang
                "\u001B[38;5;141m", // oUngu gelap
                "\u001B[38;5;201m", // pPink
                "\u001B[38;5;207m", // qPink terang
                "\u001B[38;5;213m", // rPink lembut
                "\u001B[38;5;124m", // sMerah gelap
                "\u001B[38;5;88m", // tCoklat merah
                "\u001B[38;5;130m", // uCoklat muda
                "\u001B[38;5;136m", // vCoklat emas
                "\u001B[38;5;244m", // wAbu-abu terang
                "\u001B[38;5;248m", // xAbu-abu medium
                "\u001B[38;5;252m", // yAbu-abu gelap
                "\u001B[38;5;146m" // zNtah apaan
        };

        String RESET = "\u001B[0m"; // Reset warna ke default terminal

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                char ch = buffer[i][j];
                int colorIndex = (ch - 'A') % 26; // Pilih warna berdasarkan huruf
                System.out.print(bgColors[colorIndex] + ch + RESET);
            }
            System.out.println();
        }
        System.out.println("\nWaktu pencarian: " + time + "ms");

        System.out.println("\nBanyak kasus yang ditinjau: " + eval + "\n");
    }

    public boolean write(String fileName) {
        try {
            FileWriter wr = new FileWriter("../data/" + fileName);
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    wr.write(buffer[i][j]);
                }
                wr.write("\n");
            }
            wr.write("\nWaktu pencarian: " + time + " ms\n");
            wr.write("\nBanyak kasus yang ditinjau: " + eval);
            wr.close();
            return true;
        } catch (IOException e) {
            System.out.println("Gagal");
            return false;
        }
    }

    public boolean isFull() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (buffer[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    /* ======================================================================== */
    public static void main(String[] args) {
        papan p = new papan(5, 5);

        int[][] buffer = {
                { 1, 0 },
                { 1, 0 },
                { 1, 1 }
        };
        block b = new block('A', buffer);
        System.out.println(p.checkPlacement(b, 4, 2));
        // p.place(c, 1, 0);
        // p.display();
        p.place(b, 0, 0);
        // p.remove(b, 0, 0);
        // System.out.println();
        p.display();
        // System.out.println(p.checkPlacement(b, 4, 4));
    }
}
