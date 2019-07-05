package app.enitity;

public class ResponseResult<T> {

    private final static Integer SUCCESS = 1;
    private final static Integer FAIL = 2;

    private Integer code;

    private String msg;

    private T result;

    private  ResponseResult(){
    }

    private ResponseResult(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public ResponseResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getResult() {
        return result;
    }

    public ResponseResult setResult(T result) {
        this.result = result;
        return this;
    }

    public static Integer getSUCCESS() {
        return SUCCESS;
    }

    public static Integer getFAIL() {
        return FAIL;
    }

    public static class ResultHelper{
        public static ResponseResult instance(){
           return  new ResponseResult();
        }

        public static ResponseResult successInstance(){
            return  new ResponseResult(SUCCESS);
        }

        public static ResponseResult failInstance(){
            return  new ResponseResult(FAIL);
        }
    }

}
