package problem;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//3+5*(2+3) ->3523+*+
public class Calculator {

    public static String transfer(String orgin){
        Stack<String> opStack = new Stack<>();
        StringBuilder postStr = new StringBuilder();
        for (int i =0;i < orgin.length();i++){
            String tempStr = String.valueOf(orgin.charAt(i));
            if (isNumber(tempStr)){
                postStr.append(tempStr);
            }else {
                switch (tempStr){
                    case "+":case "-":case "*":case "/":
                        if (opStack.isEmpty()){
                            opStack.push(tempStr);
                        }else {
                            //3+5*2 ->352*+   3*5+2->35*2+
                            while (!opStack.isEmpty()){
                                if (!compare(tempStr,opStack.peek())){
                                    postStr.append(opStack.pop());
                                }else{
                                    opStack.push(tempStr);
                                    break;
                                }

                        }


                    }
                    break;
                    case "(":
                        opStack.push("(");
                        break;
                    case ")":
                        while (!opStack.peek().equals("(") ){
                            postStr.append(opStack.pop());
                        }
                        if (opStack.peek().equals("(")){
                            opStack.pop();
                        }
                        break;
                    default:break;
                }
            }
        }
        while (!opStack.isEmpty()){
            postStr.append(opStack.pop());
        }
        return postStr.toString();
    }
    public  static boolean compare(String orgin,String post){
       return getPropetiy(orgin)>getPropetiy(post);
    }
    public static int   getPropetiy(String orgin){
        if (orgin.equals("+")||orgin.equals("-")){
            return 0;
        }else  if (orgin.equals("*")||orgin.equals("/")){
            return 1;
        }else {
            return -1;
        }
    }

    private Long calculate(String postFormula){
        Stack<String> tempStack = new Stack<>();
        char[] chars = postFormula.toCharArray();
        Long result = 0L;
        int numCount = 0;
        for (int i=0;i<chars.length;i++){
            tempStack.push(String.valueOf(chars[i]));
            if (!isNumber(String.valueOf(chars[i]))){
                String operator = tempStack.pop();
                String num2 = tempStack.pop();
                String num1 = tempStack.pop();
                result = getResult(operator,num1,num2);
                tempStack.push(result.toString());
            }
        }
        return result;
    }

    public static boolean isNumber(String strNum) {
        return strNum.matches("^[+-]?(\\d*\\.)?\\d+$");
    }

    public static Long getResult(String operator,String strNum1,String strNum2) {
       if (operator.equals("+")){
            return Long.parseLong(strNum1)+Long.parseLong(strNum2);
       }else if (operator.equals("-")){
            return Long.parseLong(strNum1)-Long.parseLong(strNum2);
       }else if (operator.equals("*")){
            return Long.parseLong(strNum1)*Long.parseLong(strNum2);
       }else{
            return Long.parseLong(strNum1)/Long.parseLong(strNum2);
       }
    }
    private static List<String> parseToSuffixExpression(List<String> expressionList) {
        //创建一个栈用于保存操作符
        Stack<String> opStack = new Stack<>();
        //创建一个list用于保存后缀表达式
        List<String> suffixList = new ArrayList<>();
        for(String item : expressionList){
            //得到数或操作符
            if(isOperator(item)){
                //是操作符 判断操作符栈是否为空
                if(opStack.isEmpty() || "(".equals(opStack.peek()) || priority(item) > priority(opStack.peek())){
                    //为空或者栈顶元素为左括号或者当前操作符大于栈顶操作符直接压栈
                    opStack.push(item);
                }else {
                    //否则将栈中元素出栈如队，直到遇到大于当前操作符或者遇到左括号时
                    while (!opStack.isEmpty() && !"(".equals(opStack.peek())){
                        if(priority(item) <= priority(opStack.peek())){
                            suffixList.add(opStack.pop());
                        }
                    }
                    //当前操作符压栈
                    opStack.push(item);
                }
            }else if(isNumber(item)){
                //是数字则直接入队
                suffixList.add(item);
            }else if("(".equals(item)){
                //是左括号，压栈
                opStack.push(item);
            }else if(")".equals(item)){
                //是右括号 ，将栈中元素弹出入队，直到遇到左括号，左括号出栈，但不入队
                while (!opStack.isEmpty()){
                    if("(".equals(opStack.peek())){
                        opStack.pop();
                        break;
                    }else {
                        suffixList.add(opStack.pop());
                    }
                }
            }else {
                throw new RuntimeException("有非法字符！");
            }
        }
        //循环完毕，如果操作符栈中元素不为空，将栈中元素出栈入队
        while (!opStack.isEmpty()){
            suffixList.add(opStack.pop());
        }
        return suffixList;
    }
    /**
     * 判断字符串是否为操作符
     * @param op
     * @return
     */
    public static boolean isOperator(String op){
        return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/");
    }


    /**
     * 获取操作符的优先级
     * @param op
     * @return
     */
    public static int priority(String op){
        if(op.equals("*") || op.equals("/")){
            return 1;
        }else if(op.equals("+") || op.equals("-")){
            return 0;
        }
        return -1;
    }

    public static void main(String[] args) {
       // 3+5*(2+3) ->3523+*+
        String a = "3+5*(2+3)";
        System.out.println(transfer(a));

//        List<String> s = new ArrayList<>();
//        s.add("3");
//        s.add("+");
//        s.add("4");
//        s.add("*");
//        s.add("7");
//        s.add("+");
//        s.add("3");
//        s.add("*");
//        s.add("(");
//        s.add("5");
//        s.add("+");
//        s.add("3");
//        s.add(")");
//        System.out.println(s);
//        Calculator calculator = new Calculator();
//        List<String> strings = parseToSuffixExpression(s);
//        StringBuilder stringBuilder = new StringBuilder();
       /* for (int i =0;i<strings.size();i++){
            stringBuilder.append(strings.get(i));
        }
        System.out.println(stringBuilder);
        Long result = calculator.calculate(stringBuilder.toString());

        System.out.println(result);*/
    }
}
