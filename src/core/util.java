package core;


/**
 * Created by Admin on 2018/3/22.
 * 1.假设社区的范围为一个正方形范围为：
 *    nodeX,nodeY的范围要求为：
 *    10<nodeX<60
 *    10<nodeY<60
 *    并且nodeX与nodeY为整正数；
 *  2.假设每次博弈之前，至少会产生1000个随机节点并且符合假设1，其中包含源节点s与它的邻居节点；
 *  3.源节点s的选择为随机产生，并且它与节点的有效范围为5米，这些符合条件的节点称为邻居节点（邻居节点的数量至少大于2个）；
 */
public class util {

    public final static Integer NODE_NUMBER = 150;

    public final static Integer MESSAGE = 0;//0表示没有消息，1表示有消息；

}
