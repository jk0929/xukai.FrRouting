package dijkstra;

import domain.Node;

import java.util.Map;

/**
 * Created by Admin on 2018/4/9.
 * 接口：用于计算两节点之间的最短路径
 */
public interface Distance {
    /**
     * @param start：起始节点的编号
     * @param end：终点节点的编号
     * @param stepLength：图中直接相邻两个节点的距离组成的map
     * @return
     * @Author:lulei
     * @Description: 起点到终点的最短路径
     */
    public Map<Integer,Object> getMinStep(int start, int end, final Map<Integer,
            Map<Integer, Integer>> stepLength, Map<Integer, Node> nodeMap);
}
