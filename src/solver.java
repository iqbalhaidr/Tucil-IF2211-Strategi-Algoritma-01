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
                // p.incEval();
                for (int y = 0; y <= (p.getRow() - b.get(idx).getRowLen()); y++) {
                    for (int x = 0; x <= (p.getCol() - b.get(idx).getColLen()); x++) {
                        if (p.checkPlacement(b.get(idx), x, y)) {
                            p.incEval();
                            p.place(b.get(idx), x, y);
                            if (solve(p, b, idx + 1)) {
                                return true;
                            }
                            p.remove(b.get(idx), x, y);
                        }
                    }
                }
            }
        }
        return false;
    }
}