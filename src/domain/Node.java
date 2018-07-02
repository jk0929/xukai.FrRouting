package domain;


import java.io.Serializable;

/**
 * Created by Admin on 2018/3/22.
 * 节点类
 */
public class Node implements Comparable<Node>,Serializable{
    private Integer nodeId;
    private Integer nodeX;
    private Integer nodeY;
    private Integer nodeIncome;//节点传播所获得的总收益；
    private Integer nodeCost;//节点获得的总成本；
    private Boolean isAgree;//节点是否同意帮助传播，true = 同意，false = 拒绝
    private Integer message;//节点中保存的消息，如果保存消息，就不能被再次使用；
    private final static Integer NODE_ONCE_INCOME = 6;//节点传播一次获得的收益；
    private final static Integer NODE_ONCE_COST = 5;//节点传播一次付出的成本；
    public Node(){
        super();
    }
    public Node(Integer x, Integer y, Integer nodeIncome ,Boolean isAgree,Integer nodeId,Integer message){
        super();
        this.nodeId = nodeId;
        this.nodeX = x;
        this.nodeY = y;
        this.nodeIncome = nodeIncome;
        this.nodeCost = 0;//成本初始值为0
        this.isAgree = isAgree;
        this.message = message;
    }

    public Integer getNodeCost() {
        return nodeCost;
    }

    public void setNodeCost(Integer nodeCost) {
        this.nodeCost = nodeCost;
    }

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getNodeX() {
        return nodeX;
    }

    public Integer getNodeY() {
        return nodeY;
    }

    public Integer getNodeIncome() {
        return nodeIncome;
    }

    public void setNodeX(Integer nodeX) {
        this.nodeX = nodeX;
    }

    public void setNodeY(Integer nodeY) {
        this.nodeY = nodeY;
    }

    public void setNodeIncome(Integer nodeIncome) {
        this.nodeIncome = nodeIncome;
    }

    public void setAgree(Boolean agree) {
        isAgree = agree;
    }

    public Boolean getAgree() {
        return isAgree;
    }

    public Integer getMessage() {
        return message;
    }

    public void setMessage(Integer message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return "节点id为:"+this.nodeId+"，("+nodeX+","+nodeY+")"+"收益："+this.nodeIncome+",成本："+this.nodeCost;
    }

    public static Node getInitNode(Integer x, Integer y, Boolean isAgree,Integer nodeId){//初始化，得到一个收益为0的节点
        return new Node(x,y,0,isAgree,nodeId,0);
    }
    public static void increaseOnceIncome(Node node){//接受一次传播，就增加一次收益
        node.setNodeIncome(node.getNodeIncome()+ Node.NODE_ONCE_INCOME);
    }
    public static void increaseOnceCost(Node node){
        node.setNodeCost(node.getNodeCost()+NODE_ONCE_COST);
    }
    public static void payNodeCost(Node node){
        node.setNodeIncome(node.getNodeIncome()- Node.NODE_ONCE_COST);
    }

    @Override
    public int compareTo(Node node) {
        int i = this.nodeIncome-node.getNodeIncome();
        return i;
    }

    public static Boolean equals(Node anotherNode,Node thisNode){
        if(thisNode.getNodeX() == anotherNode.getNodeX() && thisNode.getNodeY()==anotherNode.getNodeY()){
            return true;
        }
        return false;
    }
}
