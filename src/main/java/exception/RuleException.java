package exception;

/**
 * @author YaoWenHao
 * @Title: RuleException
 * @ProjectName vote
 * @Description: TODO
 * @date 2018/12/7 8:27
 */
public class RuleException extends Exception {

    public RuleException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
