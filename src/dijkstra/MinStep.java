package dijkstra;

import java.util.List;

/**
 * Created by Admin on 2018/4/9.
 * 返回值数据结构
 */
public class MinStep {
    private boolean reachable;//是否可达
    private int minStep;//最短步长:这里表示源节点与目的节点之间，经过的路径的距离之和。
    private List<Integer> step;//最短路径：这个List数组保存了从起点到终点最短路径所经历的节点。

    public MinStep() {
    }
    public MinStep(boolean reachable){
        this.reachable = reachable;
        this.minStep = Integer.MAX_VALUE;
    }

    public MinStep(boolean reachable, int minStep) {
        this.reachable = reachable;
        this.minStep = minStep;
    }

    public boolean isReachable() {
        return reachable;
    }
    public void setReachable(boolean reachable) {
        this.reachable = reachable;
    }
    public int getMinStep() {
        return minStep;
    }
    public void setMinStep(int minStep) {
        this.minStep = minStep;
    }
    public List<Integer> getStep() {
        return step;
    }
    public void setStep(List<Integer> step) {
        this.step = step;
    }

    @Override
    public String toString(){
        return "reachable:"+this.reachable+",minStep:"+this.minStep;
    }
}
