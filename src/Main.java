import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\nSelamat Datang di IQ Puzzler Pro Solver\n");
        System.out.print("Masukkan nama file: ");
        // String fileName = sc.nextLine();
        System.out.println();

        Parser pr = new Parser("test4.txt");
        int[] param = pr.readParam();
        papan pp = new papan(param[0], param[1]);
        String mode = pr.readMode();
        ArrayList<block> bl = pr.getBlock();
        if (bl.size() != param[2]) {
            System.out.println("Jumlah block tidak sesuai");
            System.exit(0);
        }
        solver sl = new solver();

        long startTime = System.nanoTime();
        boolean isSolved = sl.solveversi2(pp, bl, 0);
        long endTime = System.nanoTime();
        pp.setTime((endTime - startTime) / 1000000);

        if (!isSolved) {
            System.out.println("Solusi tidak ditemukan");
        } else {
            System.out.println("Solusi ditemukan.\n");
            pp.display();
            System.exit(0);

            System.out.print("Apakah solusi ingin disimpan? (y/n): ");
            String choice = sc.nextLine();
            if (choice.equals("y")) {
                if (pp.write("solusi.txt")) {
                    System.out.println("Solusi berhasil disimpan di ../data/solusi.txt");
                } else {
                    System.out.println("Solusi gagal disimpan");
                }
            } else if (choice.equals("n")) {
                System.out.println("Solusi tidak disimpan");
            } else {
                System.out.println("Pilihan tidak valid");
            }

            System.out.print("Apakah gambar ingin disimpan? (y/n): ");
            choice = sc.nextLine();
            if (choice.equals("y")) {
                if (pp.draw("../data/solusiGambar.jpg")) {
                    System.out.println("Gambar berhasil disimpan di ../data/solusiGambar.jpg");
                } else {
                    System.out.println("Gambar gagal disimpan");
                }
            } else if (choice.equals("n")) {
                System.out.println("Solusi tidak disimpan");
            } else {
                System.out.println("Pilihan tidak valid");
            }
        }
        sc.close();
    }
}
