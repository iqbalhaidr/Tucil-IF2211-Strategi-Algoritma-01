import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.HashMap;

public class blockcol {
    public static void main(String[] args) {
        char[][] buffer = {
                { 'A', 'A', 'C' },
                { 'A', 'C', 'C' },
                { 'B', 'C', 'C' },
                { 'B', 'B', 'C' }
        };

        int cellSize = 50; // Ukuran setiap blok
        int rows = buffer.length;
        int cols = buffer[0].length;
        int width = cols * cellSize;
        int height = rows * cellSize;

        // Buat gambar kosong
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // Definisi warna blok (bisa disesuaikan)
        HashMap<Character, Color> colorMap = new HashMap<>();
        colorMap.put('A', new Color(255, 0, 0)); // Merah
        colorMap.put('B', new Color(0, 0, 255)); // Biru
        colorMap.put('C', new Color(0, 255, 0)); // Hijau

        // Gambar setiap blok sesuai buffer
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char ch = buffer[i][j];
                Color blockColor = colorMap.getOrDefault(ch, Color.WHITE);

                // Gambar kotak warna
                g2d.setColor(blockColor);
                g2d.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);

                // Gambar teks di tengah blok
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Arial", Font.BOLD, 20));
                FontMetrics fm = g2d.getFontMetrics();
                int textX = j * cellSize + (cellSize - fm.charWidth(ch)) / 2;
                int textY = i * cellSize + ((cellSize - fm.getHeight()) / 2) + fm.getAscent();
                g2d.drawString(String.valueOf(ch), textX, textY);
            }
        }

        g2d.dispose();

        // Simpan gambar sebagai .jpg
        try {
            ImageIO.write(image, "jpg", new File("output.jpg"));
            System.out.println("Gambar telah disimpan sebagai output.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
