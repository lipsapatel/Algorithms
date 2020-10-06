/*
 * Complete the detect_primes function below.
 */
static String detect_primes(int[] a) {
    StringBuilder sb = new StringBuilder();

    for(int i=0; i<a.length; i++) {
        if(isPrime(a[i]))
            sb = sb.append("1");
        else
            sb = sb.append("0");
    }
    return sb.toString();
}

static boolean isPrime(int num) {

    if(num == 1)
        return false;
    if(num == 2)
        return true;

    for(int i=2; i<=Math.sqrt(num); i++) {
        if((num % i) == 0)
            return false;
    }

    return true;
}

