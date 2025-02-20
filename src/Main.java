import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Selamat Datang di IQ Puzzler Pro Solver");

        System.out.print("Masukkan nama file: ");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();

        Parser pr = new Parser(fileName);
        papan pp = new papan(pr.getN(), pr.getM());
        ArrayList<block> bl = pr.getBlock();
        solver sl = new solver();

        long startTime = System.nanoTime();
        boolean isSolved = sl.solve(pp, bl, 0);
        long endTime = System.nanoTime();
        // long time = (endTime - startTime) / 1000000;
        // System.out.println(time);
        pp.setTime((endTime - startTime) / 1000000);

        if (!isSolved) {
            System.out.println("Solusi tidak ditemukan");
        } else {
            System.out.println("Solusi ditemukan.");
            pp.display2();
            System.out.print("Apakah solusi ingin disimpan? (y/n): ");
            String choice = sc.nextLine();
            if (choice.equals("y")) {
                System.out.print("Masukkan nama file: ");
                String fileNameSv = sc.nextLine();
                if (pp.write(fileNameSv)) {
                    System.out.println("Solusi berhasil disimpan");
                } else {
                    System.out.println("Solusi gagal disimpan");
                }
            } else {
                System.out.println("Solusi tidak disimpan");
            }
        }
        sc.close();
    }
}
