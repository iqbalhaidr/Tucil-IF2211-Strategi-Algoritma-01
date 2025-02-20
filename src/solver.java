import java.util.ArrayList;

public class solver {
    public boolean solve(papan p, ArrayList<block> b, int idx) {
        if (idx >= b.size()) {
            return p.isFull();
        }

        for (int m = 0; m < 2; m++) {
            if (m > 0) {
                b.get(idx).mirror();
            }
            for (int r = 0; r < 4; r++) {
                if (r > 0) {
                    b.get(idx).rotate();
                }
                for (int y = 0; y < p.getRow(); y++) {
                    for (int x = 0; x < p.getCol(); x++) {
                        // p.incEval();
                        if (p.checkPlacement(b.get(idx), x, y)) {
                            p.incEval();
                            p.place(b.get(idx), x, y);
                            if (solve(p, b, idx + 1)) {
                                return true;
                            }
                            // p.incEval();
                            p.remove(b.get(idx), x, y);
                        }
                    }
                }
            }
        }
        return false;
    }

    /* ======================================================================== */
    public static void main(String[] args) {
        papan p = new papan(3, 3);
        int[][] bufa = {
                { 1, 1 },
                { 1, 0 }
        };
        int[][] bufb = {
                { 1, 1 }
        };
        int[][] bufc = {
                { 0, 1 },
                { 1, 1 }
        };
        int[][] bufd = {
                { 0, 0, 0, 1, 1 },
                { 1, 1, 1, 1, 1 }
        };
        int[][] bufe = {
                { 1 },
                { 1 }
        };
        int[][] bufef = {
                { 1, 0, 0, 0, 1 },
                { 1, 1, 1, 1, 1 }
        };
        int[][] bufg = {
                { 1, 1, 1 }
        };
        int[][] bufh = {
                { 1 }
        };
        block b1 = new block('A', bufa);
        block b2 = new block('B', bufb);
        block b3 = new block('C', bufc);
        block b4 = new block('D', bufd);
        block b5 = new block('E', bufe);
        block b6 = new block('F', bufef);
        block b7 = new block('G', bufg);
        block b8 = new block('H', bufh);
        ArrayList<block> b = new ArrayList<block>();
        ArrayList<block> bb = new ArrayList<block>();
        b.add(b1);
        b.add(b2);
        b.add(b3);
        b.add(b4);
        b.add(b5);
        b.add(b6);
        b.add(b7);
        bb.add(b8);

        solver s = new solver();
        Parser ps = new Parser("1.txt");

        if (!s.solve(p, ps.getBlock(), 0)) {
            System.out.println("gagal");
        } else {
            System.out.println("berhasil");
        }
        p.display();
    }
}
/*
 * System.out.print(m);
 * System.out.print(r);
 * System.out.print(y);
 * System.out.print(x);
 * System.out.print(p.checkPlacement(b.get(idx), x, y));
 * System.out.println();
 */