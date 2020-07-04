import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompareListOfObjects {

    public static class ListNode {
        String sentences;
        int times;
    }

    public static void main(String[] args) {
        List<ListNode> list = new ArrayList<>();

        ListNode listNode = new ListNode();
        listNode.sentences = "island";
        listNode.times = 3;

        list.add(listNode);

        listNode = new ListNode();
        listNode.sentences = "i love you";
        listNode.times = 5;

        list.add(listNode);

        listNode = new ListNode();
        listNode.sentences = "ironman";
        listNode.times = 2;

        list.add(listNode);

        listNode = new ListNode();
        listNode.sentences = "i love leetcode";
        listNode.times = 2;

        list.add(listNode);

        for(ListNode node: list) {
            System.out.println(node.sentences + " : " + node.times);
        }

        Collections.sort(list, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode n1, ListNode n2) {
                if(n1.times == n2.times) {
                    return n1.sentences.compareTo(n2.sentences);
                } else {
                    return n2.times - n1.times;
                }
            }
        });

        System.out.println("After sorting");
        for(ListNode node: list) {
            System.out.println(node.sentences + " : " + node.times);
        }
    }
}
