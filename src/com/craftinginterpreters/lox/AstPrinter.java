package com.craftinginterpreters.lox;

class AstPrinter implements Expr.Visitor<String> {
    String print(Expr expr) {
        return expr.accept(this);
    }

    /**为了能够正常运行**/
    @Override
    public String visitAssignExpr(Expr.Assign expr) {
        return "";
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme,
                expr.left, expr.right);
    }

    /**为了能够正常运行**/
    @Override
    public String visitCallExpr(Expr.Call expr) {
        return "";
    }

    /**为了能够正常运行**/
    @Override
    public String visitGetExpr(Expr.Get expr) {
        return "";
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    /**为了能够正常运行**/
    @Override
    public String visitLogicalExpr(Expr.Logical expr) {
        return "";
    }

    /**为了能够正常运行**/
    @Override
    public String visitSetExpr(Expr.Set expr) {
        return "";
    }

    /**为了能够正常运行**/
    @Override
    public String visitSuperExpr(Expr.Super expr) {
        return "";
    }

    /**为了能够正常运行**/
    @Override
    public String visitThisExpr(Expr.This expr) {
        return "";
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    /**为了能够正常运行**/
    @Override
    public String visitVariableExpr(Expr.Variable expr) {
        return "";
    }

    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }

    public static void main(String[] args) {
        Expr expression = new Expr.Binary(
                new Expr.Unary(
                        new Token(TokenType.MINUS, "-", null, 1),
                        new Expr.Literal(123)),
                new Token(TokenType.STAR, "*", null, 1),
                new Expr.Grouping(
                        new Expr.Literal(45.67)));

        System.out.println(new AstPrinter().print(expression));
    }
}