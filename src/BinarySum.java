class BinarySum {
    public String addBinary(String a, String b) {

        int i = a.length() - 1;
        int j = b.length() - 1;
        int c = 0;
        StringBuilder sb = new StringBuilder();

        while (i >= 0 || j >= 0) {

            int x = 0;
            int y = 0;
            int sum = 0;

            if (i >= 0) {
                x = a.charAt(i) - '0';
            }

            if (j >= 0) {
                y = b.charAt(j) - '0';
            }

            sum = (x + y + c) % 2;
            c = (x + y + c)/2;

            sb.insert(0, sum);

            i--;
            j--;
        }

        if (c == 1) {
            sb.insert(0, c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String a = "11";
        String b = "1";

        BinarySum binarySum = new BinarySum();
        System.out.println(binarySum.addBinary(a, b));
    }

}