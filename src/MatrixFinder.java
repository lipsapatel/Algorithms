public class MatrixFinder {

    public static void main(String[] args) {

        int[][] mat = { {2,3,8},
                {1,5,7},
                {2,7,5}};
        int N =3;

        System.out.println(countSquarePrimeSubMatrices(mat, N));
    }

    private static int countSquarePrimeSubMatrices(int[][] mat, int N) {

        int count = 0;
        for (int row = 0; row < N; row++) {

            for (int col = 0; col < N; col++) {

                if (isPrime(mat[row][col])) {

                    //count++; //for 1x1 matrix
                    int maxSize = N - Math.max(row, col);

                    while (maxSize > 0) {
                        boolean found = true;

                        for (int rowStart = row; rowStart < maxSize + row; rowStart++) {

                            for (int colStart = col; colStart < maxSize + col; colStart++) {

                                if (!isPrime(mat[rowStart][colStart])) {
                                    found = false;
                                }
                            }
                        }

                        maxSize--;
                        if (found) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    //checks whether an int is prime or not.
    static boolean isPrime(int n) {
        //check if n is a multiple of 2
        if (n == 2) {
            return true;
        }
        if (n == 1 || n%2==0) return false;

        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }

}

