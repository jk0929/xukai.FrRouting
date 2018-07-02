package test;

import dijkstra.Distance;
import dijkstra.DistanceDijkstraImpl;
import dijkstra.DistanceTest;
import dijkstra.MinStep;
import domain.Neighbour;
import domain.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2018/4/13.
 * 用于测试博弈之前，与博弈之后的两组数据，并保存初始化一样的设置；
 */
public class main {
    public static void main(String[] args) {
    }
}
//            Map<Integer,Double> mapIncome = new HashMap<>();
//            Map<Integer,Double> mapCost = new HashMap<>();
//            Map<Integer,Integer> mapSize = new HashMap<>();
//            Map<Integer,Object> map;
//            Integer sourceNodeId = 1;
//            Map<Integer, Node> nodeMap;
//            Map<Integer,Node> nodeMapNew;
//            for(int i=0;i<100;i++,sourceNodeId++){//循环次数先控制下，为1次；
//                nodeMap = Neighbour.getInitNodeList(true);
//                Distance distance = new DistanceDijkstraImpl();
////                map = distance.getMinStep(sourceNodeId, 0, DistanceTest.getTwoNodeOfDistance(nodeMap,20),nodeMap);
////                MinStep minStep = (MinStep) map.get(1);
////                List<Integer> stepList =  minStep.getStep();
////                nodeMapNew = (Map<Integer, Node>) map.get(2);
////            System.out.println("源节点id为："+stepList.get(stepList.size()-1)+"，路由中所有节点情况：");
//                Double sumIncome = 0.0;
//                Double sumCost = 0.0;
////            Integer sum = 0;
//                if(nodeMapNew == null){
//                    return;
//                }
//                for (Map.Entry<Integer,Node> entry:nodeMapNew.entrySet()){
//                    Node nodeOne = entry.getValue();
//                    sumIncome += nodeOne.getNodeIncome();
//                    sumCost += nodeOne.getNodeCost();
////                System.out.println(nodeOne);
////                sum = nodeMapNew.entrySet().size();
//                }
////            System.out.println(sumIncome/sum);
//                mapIncome.put(i,sumIncome);
//                mapCost.put(i,sumCost);
////            System.out.println(sumCost/sum);
//
////            for (Integer sum : stepList){
////               Node node = nodeMapNew.get(sum);
////               System.out.println(node);
////            }
//                mapSize.put(i,stepList.size()-1);
////            System.out.println(stepList.size()-1);
//
////            System.out.println();
////            mapList.put(i,stepList);
////            mapSize.put(i,stepList.size()-1);
//            }
//            for(int j=0;j<100;j++){
//                System.out.println(mapIncome.get(j));
//            }
//            System.out.println();
//            for (int j=0;j<100;j++){
//                System.out.println(mapCost.get(j));
//            }
//            System.out.println();
//            for (int j=0;j<100;j++){
//                System.out.println(mapSize.get(j));
//            }
//            System.out.println();
//        }
//
//}
