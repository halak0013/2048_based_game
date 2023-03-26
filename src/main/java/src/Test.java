package src;

public class Test {
    public static void main(String[] args) {
        MultiDimList<Integer> l = new MultiDimList<>();
        int index[] = { 4, 1, 3, 2, 0, 3, 0, 4,
            4, 3, 2, 2, 1, 3, 2, 4, 0, 2, 3, 1,
            1, 1, 1, 3, 2, 2, 2, 2, 2, 3, 3 };

    int vals[] = { 2, 2, 4, 2, 4, 2, 4, 8,
            8, 32, 2, 64, 16, 64, 32, 16, 16,
            32, 64, 8, 4, 2, 2, 2, 64, 32,
            16, 8, 8, 4, 8 };
        for (int i = 0; i < vals.length; i++) {
            l.addProces(vals[i], index[i]);
        }
/* 
        l.addProces(2, 4);
        l.addProces(2, 1);
        l.addProces(4, 3);
        l.addProces(2, 2);
        l.addProces(4, 0);
        l.addProces(2, 3);
        l.addProces(4, 0);
        l.addProces(8, 4);
        l.addProces(8, 4);
        l.addProces(32, 3);
        l.addProces(2, 2);
        l.addProces(64, 2);
        l.addProces(16, 1);
        l.addProces(64, 3);
        l.addProces(32, 2);
        l.addProces(16, 4);
        l.addProces(16, 0);
        l.addProces(32, 2);
        l.addProces(64, 3);
        l.addProces(8, 1);
        l.addProces(4, 1);
        l.addProces(2, 1);
        l.addProces(2, 1);
        l.addProces(2, 3);
        l.addProces(64, 2);
        l.addProces(32, 2);
        l.addProces(16, 2);
        l.addProces(8, 2);
        l.addProces(8, 2);
        l.addProces(4, 3);
        l.addProces(8, 3); */




    }
}

/* 
but when come 

 */