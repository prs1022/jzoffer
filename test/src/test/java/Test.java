package test.java;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;


/**
 * @author purensong
 * @date 2019/4/15 18:44
 */
@Slf4j
public class Test {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.isEmpty()) {
            Integer tmp = null;
            while (!stack1.isEmpty() && (tmp = stack1.pop()) != null) {
                stack2.push(tmp);
            }
        }
        return stack2.pop();
    }

    public static void main(String[] args) {
        Test test = new Test();
        String[] arr = new String[]{"PSH1", "PSH2", "PSH3", "POP", "POP", "PSH4", "POP", "PSH5", "POP", "POP"};
        for (String s : arr) {
            if (s.startsWith("PSH")) {
                test.push(Integer.parseInt(s.replace("PSH", "")));
            }
            if (s.startsWith("POP")) {
                System.out.println(test.pop());
            }
        }
    }
}
