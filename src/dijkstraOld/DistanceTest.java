package dijkstraOld;


import core.util;
import domain.Neighbour;
import domain.Node;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 2018/4/9.
 * 测试Dijkstra算法结果
 */
public class DistanceTest {
    public static void main(String[] args) {
        Map<Integer, Double> mapIncome = new HashMap<>();
        Map<Integer, Double> mapCost = new HashMap<>();
        Map<Integer, Integer> mapSize = new HashMap<>();
        Map<Integer,Double> agreeMap = new HashMap<>();
        Integer id = 1000;
        Integer nodeNum = 150;
        Map<Integer, Object> map;
//        Integer sourceNodeId = 1;
        Map<Integer, Node> nodeMapNew;
        Map<Integer, Node> nodeMap;
        for (int j=1;j<=id;j++){//得到100组数据
            nodeMap = Neighbour.getInitNodeList(true);
            writeObjectToFile(nodeMap,j);
            Double sumIncome = 0.0;
            Double sumCost = 0.0;
            Double agreeNum = 0.0;
            Integer tiaoShu = 0;
            for (int i = 1; i < nodeNum; i++) {//每组数据，遍历源节点id从1-100，并求所有节点总收益，总成本，总同意节点数目，跳数的平均值；
                Distance distance = new DistanceDijkstraImpl();
                nodeMap = (Map<Integer, Node>) readObjectFromFile(j);
                map = distance.getMinStep(i, 0, getTwoNodeOfDistance(nodeMap, 15), nodeMap);
//                if(map == null){
//                    return;
//                }
                MinStep minStep = (MinStep) map.get(1);
                List<Integer> stepList;
                if(minStep == null){
                    stepList = null;
                }else {
                    stepList = minStep.getStep();
                }
                nodeMapNew = (Map<Integer, Node>) map.get(2);
                if(nodeMapNew != null){
                    for (Map.Entry<Integer, Node> entry : nodeMapNew.entrySet()) {
                        Node nodeOne = entry.getValue();
                        sumIncome += nodeOne.getNodeIncome();
                        sumCost += nodeOne.getNodeCost();
                        if(nodeOne.getAgree() == true){
                            agreeNum++;
                        }
                    }
                }
                if(stepList == null){
                    tiaoShu = -1;
                }else {
                    tiaoShu = stepList.size()-1;
                }
            }
            Double avgSumIncome = sumIncome/nodeNum;
            Double avgSumCost = sumCost/nodeNum;
            Double avgAgreeNum = agreeNum/nodeNum;
            mapIncome.put(j,avgSumIncome);
            mapCost.put(j,avgSumCost);
            agreeMap.put(j,avgAgreeNum);
            mapSize.put(j,tiaoShu);
        }

        Double sizeSum = 0.0;
        Integer sizeTime = 0;
        for (int j = 1; j <= id; j++) {
            Integer size = mapSize.get(j);
            if(size != -1){
                sizeSum += size;
                sizeTime++;
            }
        }
        System.out.println(sizeSum/sizeTime);
        System.out.println();
        Double sumIncome = 0.0;
        Integer incomeTime = 0;
        for (int j = 1; j <= id; j++) {
            Integer size = mapSize.get(j);
            if(size != -1){
                Double income = mapIncome.get(j);
                sumIncome += income;
                incomeTime++;
            }
        }
        System.out.println(sumIncome/incomeTime);
        System.out.println();
        Double costSum = 0.0;
        Integer costTime = 0;
        for (int j = 1; j <= id; j++) {
            Integer size = mapSize.get(j);
            if(size != -1){
                Double cost = mapCost.get(j);
                costSum += cost;
                costTime++;
            }
        }
        System.out.println(costSum/costTime);
        System.out.println();
        Double agreeSum = 0.0;
        Integer agreeTime = 0;
        for (int j = 1; j <= id; j++) {
            Integer size = mapSize.get(j);
            if(size != -1){
                Double agree = agreeMap.get(j);
                agreeSum += agree;
                agreeTime++;
            }
        }
        System.out.println(agreeSum/agreeTime);
    }

    private static Map<Integer, Map<Integer, Integer>> getTwoNodeOfDistance(Map<Integer, Node> nodeMap, Integer nodeRadial) {
        Map<Integer, Map<Integer, Integer>> stepLength = new HashMap<>();
        Map<Integer, Integer>[] step = new Map[util.NODE_NUMBER * util.NODE_NUMBER];
        Node nodeFirst;
        Node nodeTwo;
        for (int i = 0; i < util.NODE_NUMBER; i++) {
            nodeFirst = nodeMap.get(i);
            step[i] = new HashMap<>();
            for (int j = 0; j < util.NODE_NUMBER; j++) {
                if (j == i) {
                    continue;
                }
                nodeTwo = nodeMap.get(j);
                Double nodeX = Math.pow(nodeFirst.getNodeX() - nodeTwo.getNodeX(), 2);
                Double nodeY = Math.pow(nodeFirst.getNodeY() - nodeTwo.getNodeY(), 2);
                Integer distance = (int) Math.sqrt(nodeX + nodeY);
                if (distance <= nodeRadial) {
                    step[i].put(j, distance);
                    stepLength.put(i, step[i]);
                }
            }
        }
        return stepLength;
    }
    private static void writeObjectToFile(Map<Integer,Node> nodeMap,Integer id)
    {
        File file =new File("F:\\test/t" + id + "+est.txt");
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut=new ObjectOutputStream(out);
            objOut.writeObject(nodeMap);
            objOut.flush();
            objOut.close();
//            System.out.println("write object success!");
        } catch (IOException e) {
            System.out.println("write object failed");
            e.printStackTrace();
        }
    }
    private static Object readObjectFromFile(Integer id) {
        Object temp=null;
        File file =new File("F:\\test/t"+id+"+est.txt");
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn=new ObjectInputStream(in);
            temp=objIn.readObject();
            objIn.close();
//            System.out.println("read object success!");
        } catch (IOException e) {
            System.out.println("read object failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
    }
}
