public class Calculadora {
    private static Calculadora instancia;
    
    private Calculadora() { }
    
    public static Calculadora getInstance() {
        if (instancia == null) {
            instancia = new Calculadora();
        }
        return instancia;
    }
    



    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") ||
               token.equals("/") || token.equals("^") || token.equals("%");
    }
    
    private int precedence(String op) {
        switch(op) {
            case "^": return 3;
            case "*": 
            case "/": 
            case "%": return 2;
            case "+": 
            case "-": return 1;
            default: return 0;
        }
    }
    
    /**
     * Convierte una expresión infix a postfix
     * @param infix expresión en notación infix
     * @param stack instancia de IStack<String> para uso temporal
     */
    public String convertirInfixAPostfix(String infix, IStack<String> stack) {
        StringBuilder postfix = new StringBuilder();
        stack.push("#");
        
        int i = 0;
        while (i < infix.length()) {
            char c = infix.charAt(i);
            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }
            if (Character.isDigit(c)) {
                StringBuilder num = new StringBuilder();
                while (i < infix.length() && Character.isDigit(infix.charAt(i))) {
                    num.append(infix.charAt(i));
                    i++;
                }
                postfix.append(num.toString()).append(" ");
                continue;
            } else if (Character.isLetter(c)) {
                postfix.append(c).append(" ");
                i++;
                continue;
            } else if (c == '(') {
                stack.push("(");
                i++;
            } else if (c == ')') {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    String op = stack.pop();
                    if (op.equals("#")) break;
                    postfix.append(op).append(" ");
                }
                if (!stack.isEmpty() && stack.peek().equals("(")) {
                    stack.pop();
                }
                i++;
            } else if (isOperator(String.valueOf(c))) {
                String op = String.valueOf(c);
                while (!stack.isEmpty() && !stack.peek().equals("#") && !stack.peek().equals("(") &&
                       precedence(op) <= precedence(stack.peek())) {
                    postfix.append(stack.pop()).append(" ");
                }
                stack.push(op);
                i++;
            } else {
                i++;
            }
        }
        while (!stack.isEmpty() && !stack.peek().equals("#")) {
            postfix.append(stack.pop()).append(" ");
        }
        return postfix.toString().trim();
    }
    
    /**
     * Evaluamos una expresión en notación postfix
     * @param postfix, la expresión de tokens separados por espacios
     * @throws una excepction en caso de división o módulo entre cero
     */
    public int evaluarPostfix(String postfix) {
        IStack<Integer> evalStack = new SArrayList<>();
        String[] tokens = postfix.split("\\s+");
        
        for (String token : tokens) {
            if (token.matches("\\d+")) {
                evalStack.push(Integer.parseInt(token));
            } else if (isOperator(token)) {
                int b = evalStack.pop();
                int a = evalStack.pop();
                int result = 0;
                switch(token) {
                    case "+": result = a + b; break;
                    case "-": result = a - b; break;
                    case "*": result = a * b; break;
                    case "/": 
                        if(b == 0) {
                            throw new ArithmeticException("No se puede dividir por cero");
                        }
                        result = a / b; 
                        break;
                    case "%": 
                        if(b == 0) {
                            throw new ArithmeticException("No se puede dividir por cero");
                        }
                        result = a % b; 
                        break;
                    case "^": result = (int) Math.pow(a, b); break;
                    default: throw new IllegalArgumentException("Este operador no es válido: " + token);
                }
                evalStack.push(result);
            }
        }
        return evalStack.pop();
    }
}