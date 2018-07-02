package dijkstra;

import domain.Node;

import java.util.*;

/**
 * Created by Admin on 2018/4/9.
 * 最短路径算法实现类(Dijkstra)
 */
public class DistanceDijkstraImpl implements Distance {
    //key1节点编号，key2节点编号，value为key1到key2的步长
    private Map<Integer, Map<Integer, Integer>> stepLength;
    //非独立节点个数
    private int nodeNum;
    //移除节点
    private Set<Integer> outNode;
    //起点到各点的步长，key为目的节点，value为到目的节点的步长
    private Map<Integer, PreNode> nodeStep;
    //下一次计算的节点
    private List<Integer> nextNode;
    //起点、终点
    private int startNode;//起点的编号
    private int endNode;//终点的编号
    private Map<Integer,Node> nodeMap;
    private Integer lastTime;


    /**
     * @param start
     * @Author:lulei
     * @Description: 初始化属性
     */
    private void initProperty(int start, int end) {
        outNode = new HashSet<>();
        nodeStep = new HashMap<>();
        nextNode = new LinkedList<>();
        nextNode.add(start);
        startNode = start;
        endNode = end;
        lastTime = 0;
    }

    /**
     * @param start
     * @param end
     * @param stepLength
     * @return
     * @Author:lulei
     * @Description: start 到 end 的最短距离
     */
    @Override
    public Map<Integer,Object> getMinStep(int start, int end, final Map<Integer,
                           Map<Integer, Integer>> stepLength, Map<Integer, Node> nodeMap) {
        Map<Integer,Object> map = new HashMap<>();
        this.stepLength = stepLength;
        this.nodeNum = this.stepLength != null ? this.stepLength.size() : 0;
        this.nodeMap = nodeMap;
        //起点、终点不在目标节点内，返回不可达
        if (this.stepLength == null || (!this.stepLength.containsKey(start)) || (!this.stepLength.containsKey(end))) {
           MinStep minStep1 = new MinStep(false);
            map.put(1,minStep1);
            return map;
        }
        initProperty(start, end);
        step();//进入核心算法中，采用递归；
        if (nodeStep.containsKey(end)) {
            MinStep minStep = changeToMinStep();
            map.put(1,minStep);
            map.put(2,nodeMap);
            return map;
        }
        map.put(1,null);
        map.put(2,null);
        return map;
    }


    private void step() {
        if (nextNode == null || nextNode.size() < 1) {//这是递归的基准，符合条件就停止；
            return;
        }
        if (outNode.size() == nodeNum) {//这是递归的基准，符合条件就停止；
            return;
        }
        //获取下一个计算节点
        int start = nextNode.remove(0);
        //到达该节点的最小距离
        int step = 0;
        if (nodeStep.containsKey(start)) {
            step = nodeStep.get(start).getNodeStep();
        }
        //获取该节点可达节点
        Map<Integer,Integer> nextStep = stepLength.get(start);
        Iterator<Map.Entry<Integer, Integer>> iter = nextStep.entrySet().iterator();
        while (iter.hasNext()) {//这里相当于在遍历的start节点的邻居节点；
            Map.Entry<Integer, Integer> entry = iter.next();
            Integer key = entry.getKey();
            //如果是起点到起点，不计算之间的步长
            if (key == startNode) {
                continue;
            }

            Node keyNode = nodeMap.get(key);
            if(keyNode.getAgree() == false){//邻居节点中拒绝的策略的节点，不能作为用于传输消息
                continue;
            }
            if(keyNode.getMessage() == 0){
                //如果该节点的策略为同意,且没有被消息共享过，则增加收益，减去成本
                Node.increaseOnceIncome(keyNode);
                Node.increaseOnceCost(keyNode);
                keyNode.setMessage(1);//将该节点的消息设置为已收到状态；
                nodeMap.put(keyNode.getNodeId(),keyNode);
            }
            //起点到可达节点的距离
            Integer value = entry.getValue() + step;
            if ((!nextNode.contains(key)) && (!outNode.contains(key))) {
                nextNode.add(key);
            }
            if (nodeStep.containsKey(key)) {
                if (value < nodeStep.get(key).getNodeStep()) {
                    nodeStep.put(key, new PreNode(start, value));
                }
            } else {
                nodeStep.put(key, new PreNode(start, value));
            }
        }
        lastTime++;
        if(lastTime >= nextStep.size()){
            List<Node> list = new ArrayList<>();
            for (Map.Entry<Integer,Integer> entry : nextStep.entrySet()){
                Integer nodeKey = entry.getKey();
                Node node = nodeMap.get(nodeKey);
                if(node.getAgree() == false){//将不同意策略的节点，进行组装，排序；
                    list.add(node);
                }
            }
            if(list != null && list.size()>=1){
                Collections.sort(list);//对这个节点的邻居节点进行排序，取出前一半的节点，重新更新到nodeMap中；
                Integer binaryNodeIndex = list.size();
                for (int i=0;i<binaryNodeIndex;i++){
//                    list.get(i).setAgree(true);
//                    nodeMap.put(list.get(0).getNodeId(),list.get(i));
                     list.get(i).setAgree(true);
                     Integer nodeId = list.get(i).getNodeId();
                     nodeMap.put(nodeId,list.get(i));
                }
            }
            lastTime = 0;
        }

        //将该节点移除
        outNode.add(start);
        //计算下一个节点
        step();
    }
//    private Boolean getSumIncomeEnough(){
//        Integer thisSumIncome = this.sumIncome;
//        Integer sum = 0;
//        for(Map.Entry<Integer,Node> entry:nodeMap.entrySet()){
//            Integer income = entry.getValue().getNodeIncome();
//            sum += income;
//        }
//        if(sum >= thisSumIncome){
//            return true;
//        }
//        return false;
//    }

    /**
     * 返回最短距离以及路径
     */
    private MinStep changeToMinStep() {
        MinStep minStep = new MinStep();
        minStep.setMinStep(nodeStep.get(endNode).getNodeStep());
        minStep.setReachable(true);
        List<Integer> step = new LinkedList<>();
        minStep.setStep(step);
        int nodeNum = endNode;
        step.add(nodeNum);
        while (nodeStep.containsKey(nodeNum)) {//在循环中，通过end来不断寻找preNode，并组装到list中；
            int node = nodeStep.get(nodeNum).getPreNodeNum();//得到前一个节点id
            step.add(node);
            nodeNum = node;
        }
        return minStep;
    }
}
