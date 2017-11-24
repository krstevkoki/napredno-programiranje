package mk.ukim.finki.lab6;

import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class StackTest {
    public static void main(String[] args) {
        Scanner jin = new Scanner(System.in);
        int k = jin.nextInt();
        System.out.println("Test#" + k);
        if (k == 0) {
            System.out.println("testing: Stack::push(T) , Stack::pop():T , T is Integer");
            int n = jin.nextInt();
            Stack<Integer> stack = new Stack<Integer>();
            System.out.println("Pushing elements:");
            for (int i = 1; i <= n; ++i) {
                if (i > 1) System.out.print(" ");
                System.out.print(i);
                stack.push(i);
            }
            System.out.println();
            System.out.println("Poping elements:");
            for (int i = n; i >= 1; --i) {
                if (i < n) System.out.print(" ");
                try {
                    System.out.print(stack.pop());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
            System.out.println();
        }
        if (k == 1) {
            System.out.println("testing: Stack::push(T) , Stack::pop():T , T is String");

            int n = jin.nextInt();
            Stack<String> stack = new Stack<String>();
            System.out.println("Pushing elements:");
            for (int i = 0; i < n; ++i) {
                if (i > 0) System.out.print(" ");
                String next = jin.next();
                System.out.print(next);
                stack.push(next);
            }
            System.out.println();
            System.out.println("Poping elements:");
            for (int i = 0; i < n; ++i) {
                if (i > 0) System.out.print(" ");
                try {
                    System.out.print(stack.pop());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
        }
        if (k == 2) {
            System.out.println("testing: Stack::push(T) , Stack::pop():T , Stack::isEmpty():boolean, T is Double");

            Stack<Double> stack = new Stack<Double>();
            System.out.println("Pushing elements:");
            boolean flag = false;
            while (jin.hasNextDouble()) {
                double d = jin.nextDouble();
                stack.push(d);
                if (flag) System.out.print(" ");
                System.out.printf("%.2f", d);
                flag = true;
            }
            int i = 0;
            System.out.println();
            System.out.println("Poping elements:");
            while (!stack.isEmpty()) {
                if (i > 0) System.out.print(" ");
                ++i;
                try {
                    System.out.printf("%.2f", stack.pop());
                } catch (Exception e) {
                    System.out.println(e.getClass().getSimpleName());
                }
            }
        }
        if (k == 3) {
            System.out.println("testing: Stack::push(T) , Stack::pop():T , Stack::isEmpty():boolean , Stack::peek():T , T is Long");

            int n = jin.nextInt();
            Stack<Long> stack = new Stack<Long>();
            LinkedList<Long> control_stack = new LinkedList<Long>();
            boolean exact = true;
            for (int i = 0; exact && i < n; ++i) {
                if (Math.random() < 0.5) {//add
                    long to_add = (long) (Math.random() * 456156168);
                    stack.push(to_add);
                    control_stack.addFirst(to_add);
                } else {
                    exact &= control_stack.isEmpty() == stack.isEmpty();
                    if (exact && !stack.isEmpty()) {
                        if (Math.random() > 0.7) try {
                            exact &= control_stack.removeFirst().equals(stack.pop());
                        } catch (Exception e) {
                            System.out.println(e.getClass().getSimpleName());
                        }
                        else try {
                            exact &= control_stack.peekFirst().equals(stack.peek());
                        } catch (Exception e) {
                            System.out.println(e.getClass().getSimpleName());
                        }
                    }
                }
            }
            System.out.println("Your stack outputs compared to the built in java stack were the same? " + exact);
        }
        if (k == 4) {
            System.out.println("testing: Stack::pop():T , Stack::isEmpty():boolean , Stack::peek():T , T is Long");

            Stack<Date> test_stack = new Stack<Date>();

            System.out.println("Stack empty? " + test_stack.isEmpty());
            try {
                test_stack.pop();
                System.out.println("NO exeption was thrown when trying to pop from an empty stack!");

            } catch (Exception e) {
                System.out.print("Exeption thrown when trying to pop from an empty stack ");
                System.out.println(e.getClass().getSimpleName());
            }
            try {
                test_stack.peek();
                System.out.println("NO exeption was thrown when trying to peek in an empty stack!");
            } catch (Exception e) {
                System.out.print("Exeption thrown when trying to peek in an empty stack ");
                System.out.println(e.getClass().getSimpleName());
            }
        }
    }
}
