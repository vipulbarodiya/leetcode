class MinStack {
    Stack<Integer> min;
    Stack<Integer> stack;

    public MinStack() {
        min = new Stack<>();
        stack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if(min.isEmpty()) {
            min.push(val);
            return;
        }
        if(val <= min.peek()) {
            min.push(val);
        } else 
        min.push(min.peek());
    }

    public void pop() {
        stack.pop();
        if(!min.isEmpty())
        min.pop();

    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */