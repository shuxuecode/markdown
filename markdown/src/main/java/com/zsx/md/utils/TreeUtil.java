package com.zsx.md.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zsx.md.entity.Mnote;
import com.zsx.md.vo.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {


    public static void main(String[] args) {

    }

    public static List<TreeNode> convert(List<Mnote> list) {
        List<TreeNode> trees = new ArrayList<TreeNode>();
        list.forEach(item -> {
            TreeNode treeNode = JSONObject.parseObject(JSON.toJSONString(item), TreeNode.class);

            treeNode.setName(item.getTitle());

            trees.add(treeNode);
        });

        return trees;
    }

    public static List<TreeNode> buildTree(List<TreeNode> treeNodes) {
        List<TreeNode> trees = new ArrayList<TreeNode>();
        for (TreeNode treeNode : treeNodes) {
            if (treeNode.getPid() == 0) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    public static TreeNode findChildren(TreeNode treeNode, List<TreeNode> treeNodes) {
        for (TreeNode it : treeNodes) {
            if (treeNode.getId().equals(it.getPid())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<TreeNode>());
                }
                treeNode.getChildren().add(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }

}
