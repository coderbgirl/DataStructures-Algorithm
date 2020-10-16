
public class exprchecker {

	public static void main(String[] args) {
		ExpressionEvaluator.EXPRESSIONTYPE t = ExpressionEvaluator.EXPRESSIONTYPE.Infix;

		/*{
			ExpressionEvaluator e = new ExpressionEvaluator("(300+23)*(43-21)/(84+7) ", t);
			System.out.println(e.GetPostfixExpression());
		}*/

		{
			ExpressionEvaluator e = new ExpressionEvaluator("(((5 + 2) * 2)+ ((4 / (3 + 1 * 3)) - 7)) ", t);
			System.out.println(e.GetPostfixExpression());
			System.out.println(e.GetValue());
		}
	}

}

