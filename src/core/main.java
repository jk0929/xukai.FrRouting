package core;

import domain.Neighbour;
import domain.Node;
import domain.Result;

import java.util.*;

/**
 * Created by Admin on 2018/3/22.
 * 算法基本步骤：
 *    1.社区初始化得到1000个节点；
 *    2.随机得到源节点，以及它的邻居节点；
 *    3.刚开始所有邻居节点，存储其他邻居的收益值，
 *    3.如果邻居节点同意协助的话，就增加该邻居节点的一次收益；
 *    4.
 *    ....
 *  遇到的问题：
 *    1.需要得到哪些数据：如消息的成功率吗？
 *
 */
public class main {
    public static void main(String[] args){
        Map<Integer,String> mapTest = new HashMap<>();
        Map<Integer,Integer> playTimesMap = new HashMap<>();
        Map<Integer,Integer> agreeMap = new HashMap<>();
        for(int i=0;i<2;i++){//循环次数先控制下，为1次；
            //源节点id定为1
            Node destinationNode = new Node(100,100,0,true,1,0);
            Map<Integer,Node> nodeMap =Neighbour.getInitNodeList(true);
            Random random = new Random();
            Integer sourceNodeId = 1+random.nextInt(util.NODE_NUMBER-1);
            Map<Integer,Object> map = Neighbour.getSourceNodeOfNeighborMap(20,nodeMap,nodeMap.get(sourceNodeId));
            Neighbour neighbour = new Neighbour(map,nodeMap);//初始化定好以后，就不在需要修改！
            //1.第一次博弈，调用方法执行；
            Integer playTimes = oneTimePalyGame(neighbour,destinationNode,0,20,playTimesMap,agreeMap);
            if(playTimes!=2900){
                mapTest.put(playTimesMap.get(1),nodeMap.get(1).toString());
            }
        }
        for(Map.Entry<Integer,String> entry:mapTest.entrySet()){
            if(entry!=null){
                String  key = entry.getKey().toString();
                System.out.println(key);

            }
        }
        System.out.println();
        for(Map.Entry<Integer,String> entry:mapTest.entrySet()){
            String value = entry.getValue().toString();
            System.out.println(value);
        }
        System.out.println();
        Integer agreeNumber = 0;
        Integer sumNum = 0;
        for(Map.Entry<Integer,Integer> entry:agreeMap.entrySet()){

            Integer value = entry.getValue();
            agreeNumber++;
            sumNum +=value;
        }
        System.out.println("同意节点的遍历的次数："+agreeNumber+",总个数为："+sumNum+"平均同意节点个数："+sumNum/agreeNumber);


    }

    private static Integer  oneTimePalyGame(Neighbour neighbour,Node destinationNode, Integer playTimes,
                                            Integer nodeRadial,Map playTimesMap,Map agreeMap){//假设源节点与邻居节点一次博弈的过程
        List<Node> neighborList = neighbour.getSourceNodeOfNeighborList();
        Integer  agreeNeighbourNum = neighbour.getAgreeNighbourNodeNum();
        List<Node> agreeNeighbourList = neighbour.getAgreeNighbourList();
        //2.2得到邻居节点数量，并判断是否大于等于2；
        if(null != neighborList && neighborList.size() >= 2){
            if(agreeNeighbourList != null && agreeNeighbourNum >= 1) {//如果同意节点数量大于1
                Collections.sort(neighborList);//对所有邻居节点的收益进行排序,排序的结果为：从小变大
                Integer binaryNodeIndex = neighborList.size()/2;
                for(int i=0; i <= binaryNodeIndex; i++){//将排序为从前到一半的节点，设置为同意的策略
                    neighborList.get(i).setAgree(true);
                }
                for(int j=0; j<agreeNeighbourList.size();j++){//将同意的邻居节点增加收益，并组装同意邻居节点的map;
                    Node node =agreeNeighbourList.get(j);
                    if(null == node.getMessage()){//只有节点的消息没有被分享，才能设置消息并增加收益
                        node.setMessage(util.MESSAGE);
                        Node.increaseOnceIncome(node);
                        Node.payNodeCost(node);
                    }
                }
                if(agreeNeighbourNum>=1){
                    Boolean isEndPlayGames = Neighbour.containsDestinationNode(agreeNeighbourList,destinationNode);
                    if(isEndPlayGames){//递归基准，用于结束递归调用；
//                        System.out.println(destinationNode.toString()+"为目的节点,已经收到消息，递归结束！");
                        playTimesMap.put(1,playTimes);
                        return playTimes;
                    }
                    Node sourceNode = Neighbour.getOneOfAgreeNighbourList(agreeNeighbourList);//找同意节点中的一个下跳节点为中介节点，共享消息
                    Map<Integer,Object> mapFirst = Neighbour.getSourceNodeOfNeighborMap(nodeRadial,neighbour.getNodeMap(),sourceNode);
                    Neighbour neighbourNewFirst = new Neighbour(mapFirst,neighbour.getNodeMap());

                    playTimes++;
                    agreeMap.put(playTimes,agreeNeighbourNum);
                    if(playTimes >= 2900){
                        System.out.println("博弈超过2900次，结束博弈！");
                        return 2900;
                    }
                    oneTimePalyGame(neighbourNewFirst,destinationNode,playTimes,nodeRadial,playTimesMap,agreeMap);
                }
            }
        }
        return 0;
    }

}
