package domain;


import core.util;
import java.util.*;


/**
 * Created by Admin on 2018/3/22.
 * 邻居节点类
 */
public class Neighbour {

    protected Map<Integer, Node> nodeMap;

    private List<Node> nodesList;//邻居节点

    private  List<Node> sourceNodeOfNeighborList;//邻居节点集合

    private  Integer nighbourNodeNum;//邻居节点个数；

    private  List<Node> agreeNighbourList;//同意的邻居节点的集合；

    private  Integer agreeNighbourNodeNum;//邻居节点同意的个数；

    private Map<Integer,Map<Integer,Integer>>  stepLength = new HashMap<>();

    public Neighbour( Map<Integer,Object> map,Map<Integer,Node> nodeMap){
        super();
        this.nodeMap = nodeMap;
        this.sourceNodeOfNeighborList = (List<Node>) map.get(1);
        this.nighbourNodeNum = (Integer) map.get(2);
        this.agreeNighbourList = (List<Node>) map.get(3);
        this.agreeNighbourNodeNum = (Integer) map.get(4);
        this.stepLength = (Map<Integer, Map<Integer,Integer>>) map.get(5);
    }


    public static Map<Integer,Node> getInitNodeList(Boolean status){//初始化社区内，得到100个节点
        Map<Integer,Node> map = new HashMap<>();
        Node destinationNode = new Node(50,50,0,true,0,0);//目的节点定为同意策略；
        if(status == true){
            for(int i = 1; i < util.NODE_NUMBER; i++){
                Random random = new Random();
                Integer nodeX = random.nextInt(100);
                Integer nodeY = random.nextInt(100);
                Integer isAgree = random.nextInt(2);//对于初始化的节点，随机产生是否同意的策略
                if(1 == isAgree){
                    map.put(i, Node.getInitNode(nodeX, nodeY ,true,i));
                }else {
                    map.put(i, Node.getInitNode(nodeX, nodeY ,false,i));
                }
            }
            map.put(0,destinationNode);
            return map;
        }
        return null;
    }

    public static Map<Integer,Object> getSourceNodeOfNeighborMap(Integer nodeRadial,Map<Integer,Node> nodeMap,Node sourceNode) {//得到源节点的邻居节点数量以及集合，邻居节点中同意的数量以及集合；
        Map<Integer, Object> map = new HashMap<>();
        List<Node> sourceNodeOfNeighborListNew = new ArrayList<>();
        Integer nighbourNodeNumNew = 0;
        List<Node> agreeNighbourListNew = new ArrayList<>();
        Integer agreeNighbourNodeNumNew = 0;

        sourceNode.setMessage(util.MESSAGE);//为源节点设置要传输的消息；
        Integer sourceNodeX = sourceNode.getNodeX();
        Integer sourceNodeY = sourceNode.getNodeY();
        for (int j = 1; j < util.NODE_NUMBER; j++) {
            Node node = nodeMap.get(j);
            if(node.getMessage() == 0){//表示该节点已经被传播到了，不能在作为下一个中介节点的邻居节点
                Double dx = Math.pow(sourceNodeX-node.getNodeX(),2);
                Double dy = Math.pow(sourceNodeY-node.getNodeY(),2);
                Integer sourceNodeAndNodeDistance = (int)Math.sqrt(dx+dy);
                if (nodeRadial >= sourceNodeAndNodeDistance) {
                    sourceNodeOfNeighborListNew.add(node);
                    nighbourNodeNumNew++;
                    Boolean isAgree = node.getAgree();
                    if (true == isAgree ) {
                        agreeNighbourListNew.add(node);
                        agreeNighbourNodeNumNew++;
                    }
                }
            }

        }
        map.put(1,sourceNodeOfNeighborListNew);
        map.put(2,nighbourNodeNumNew);
        map.put(3,agreeNighbourListNew);
        map.put(4,agreeNighbourNodeNumNew);
        return map;
    }

    public static Node getOneOfAgreeNighbourList(List<Node> agreeNeighbourList){
        Integer id = 1;
        Map<Integer,Node> agreeListMap = new HashMap<>();
        for(Node node : agreeNeighbourList){
            agreeListMap.put(id,node);
            id++;
        }
        Random random = new Random();
        Integer randomId = 1+random.nextInt(agreeListMap.size());
        return agreeListMap.get(randomId);
    }

    public static Boolean containsDestinationNode(List<Node> agreeNeighbourList,Node destinationNode){
        for(Node node : agreeNeighbourList){
            if(Node.equals(destinationNode,node)){
                return true;
            }
        }
        return false;
    }

    public Map<Integer, Node> getNodeMap() {
        return nodeMap;
    }

    public List<Node> getNodesList() {
        return nodesList;
    }

    public List<Node> getSourceNodeOfNeighborList() {
        return sourceNodeOfNeighborList;
    }

    public Integer getNighbourNodeNum() {
        return nighbourNodeNum;
    }

    public List<Node> getAgreeNighbourList() {
        return agreeNighbourList;
    }

    public Integer getAgreeNighbourNodeNum() {
        return agreeNighbourNodeNum;
    }

    public void setNodeMap(Map<Integer, Node> nodeMap) {
        this.nodeMap = nodeMap;
    }

    public void setNodesList(List<Node> nodesList) {
        this.nodesList = nodesList;
    }

    public void setSourceNodeOfNeighborList(List<Node> sourceNodeOfNeighborList) {
        this.sourceNodeOfNeighborList = sourceNodeOfNeighborList;
    }

    public void setNighbourNodeNum(Integer nighbourNodeNum) {
        this.nighbourNodeNum = nighbourNodeNum;
    }

    public void setAgreeNighbourList(List<Node> agreeNighbourList) {
        this.agreeNighbourList = agreeNighbourList;
    }

    public void setAgreeNighbourNodeNum(Integer agreeNighbourNodeNum) {
        this.agreeNighbourNodeNum = agreeNighbourNodeNum;
    }
}
