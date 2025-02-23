import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.Arrays;
import java.io.File;
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

    public void display() {
        String[] Color = {
                "\u001B[48;5;196m",
                "\u001B[48;5;208m",
                "\u001B[48;5;220m",
                "\u001B[48;5;118m",
                "\u001B[48;5;35m",
                "\u001B[48;5;27m",
                "\u001B[48;5;39m",
                "\u001B[48;5;93m",
                "\u001B[48;5;135m",
                "\u001B[48;5;163m",
                "\u001B[48;5;213m",
                "\u001B[48;5;130m",
                "\u001B[48;5;226m",
                "\u001B[48;5;100m",
                "\u001B[48;5;63m",
                "\u001B[48;5;54m",
                "\u001B[48;5;128m",
                "\u001B[48;5;168m",
                "\u001B[48;5;190m",
                "\u001B[48;5;217m",
                "\u001B[48;5;113m",
                "\u001B[48;5;18m",
                "\u001B[48;5;88m",
                "\u001B[48;5;229m",
                "\u001B[48;5;51m",
                "\u001B[48;5;244m"
        };

        String RESET = "\u001B[0m";

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                char ch = buffer[i][j];
                int idx = (ch - 'A') % 26;
                System.out.print(Color[idx] + ch + RESET);
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

    public boolean draw(String fileName) {
        try {
            int cellSize = 50;
            int canvasWidth = buffer[0].length * cellSize;
            int canvasHeight = buffer.length * cellSize;

            BufferedImage canvas = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D drawer = canvas.createGraphics();
            drawer.setFont(new Font("Arial", Font.BOLD, 30));

            Color[] colorPool = {
                    new Color(255, 0, 0),
                    new Color(0, 255, 0),
                    new Color(0, 0, 255),
                    new Color(255, 255, 0),
                    new Color(0, 255, 255),
                    new Color(255, 0, 255),
                    new Color(255, 165, 0),
                    new Color(128, 0, 128),
                    new Color(255, 20, 147),
                    new Color(0, 255, 127),
                    new Color(70, 130, 180),
                    new Color(210, 105, 30),
                    new Color(128, 128, 0),
                    new Color(0, 128, 128),
                    new Color(255, 69, 0),
                    new Color(147, 112, 219),
                    new Color(0, 206, 209),
                    new Color(154, 205, 50),
                    new Color(255, 215, 0),
                    new Color(186, 85, 211),
                    new Color(139, 69, 19),
                    new Color(233, 150, 122),
                    new Color(72, 61, 139),
                    new Color(0, 100, 0),
                    new Color(255, 140, 0),
                    new Color(219, 112, 147)
            };

            for (int i = 0; i < buffer.length; i++) {
                for (int j = 0; j < buffer[i].length; j++) {
                    int idx = buffer[i][j] - 'A';
                    drawer.setColor(colorPool[idx]);
                    drawer.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                    drawer.setColor(Color.BLACK);
                    drawer.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
                    drawer.setColor(Color.WHITE);
                    drawer.drawString(String.valueOf(buffer[i][j]), j * cellSize + 15, i * cellSize + 35);
                }
            }

            drawer.dispose();
            ImageIO.write(canvas, "jpg", new File(fileName));
            return true;

        } catch (IOException e) {
            System.out.println("Gagal menyimpan gambar");
            return false;
        }
    }

}
