package domain;


import java.util.Map;

/**
 * Created by Admin on 2018/3/23.
 * 结果类
 */
public class Result {
    /**
     * 节点博弈一次，是否将消息共享给邻居节点，如果是，则成功
     *  不成功的情况分析：
     *        1.没有邻居节点
     *        2.有邻居节点但是没有同意的邻居节点
     */
    private Boolean isSuccess;

    /**
     * 节点博弈一次，得到同意协助的邻居节点的map;
     */
    private Map<Integer,Object> agreeNeighborNodeMap;

    private Integer messageTimes;//消息被分享的次数

    public Result(){
        super();
    }

    public Result(Map<Integer,Object> map,Integer messageTimes){
        this.isSuccess = true;
        this.agreeNeighborNodeMap = map;
        this.messageTimes = messageTimes;

    }

    public Result(Boolean fail){
        this.isSuccess = fail;
    }

    public Result(Boolean isSuccess, Map<Integer,Object> map ,Integer messageTimes){
        this.isSuccess = isSuccess;
        this.agreeNeighborNodeMap = map;
        this.messageTimes = messageTimes;
    }

    public static Result getSuccessResult(Map<Integer,Object> map ,Integer messageTimes){//得到成功的结果
        return new Result(map,messageTimes);
    }

    public static Result getFailResult(){//得到失败的结果
        return new Result(false);
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public Map<Integer, Object> getAgreeNeighborNodeMap() {
        return agreeNeighborNodeMap;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public void setAgreeNeighborNodeMap(Map<Integer, Object> agreeNeighborNodeMap) {
        this.agreeNeighborNodeMap = agreeNeighborNodeMap;
    }

    @Override
    public String toString(){
        return "博弈是否成功:"+this.isSuccess+",得到同意邻居节点数量为:"+this.agreeNeighborNodeMap.size()+
                ",消息被分享次数:"+this.messageTimes;
    }

    public static void printResult(Integer playTimes){
        String resultPrint = "递归调用:"+playTimes+"次";
        System.out.print(resultPrint);
    }


}
