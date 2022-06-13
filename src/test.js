/*
 * Complete the merger_first_into_second function below.
 */
static int[] merger_first_into_second(int[] arr1, int[] arr2) {

    int n1 = arr1.length-1;
    int n2 = arr1.length-1;
    int k = arr2.length-1;

    while(n1>= 0 && n2>=0) {
        if(arr1[n1] >= arr2[n2]) {
            arr2[k] = arr1[n1];
            n1--;
            k--;
        } else {
            arr2[k] = arr2[n2];
            n2--;
            k--;
        }
    }

    while(n1 >= 0) {
        arr2[k] = arr1[n1];
        n1--;
        k--;
    }

    while(n2 >= 0) {
        arr2[k] = arr2[n2];
        n2--;
        k--;
    }
    return arr2;

}

