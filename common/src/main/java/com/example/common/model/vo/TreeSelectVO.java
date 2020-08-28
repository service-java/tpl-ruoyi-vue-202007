package com.example.common.model.vo;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.example.common.model.entity.SysDept;
import com.example.common.model.entity.SysMenu;

/**
 * Treeselect树结构实体类
 *
 * @author ruoyi
 */
public class TreeSelectVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private Long id;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelectVO> children;

    public TreeSelectVO() {

    }

    public TreeSelectVO(SysDept dept) {
        this.id = dept.getDeptId();
        this.label = dept.getDeptName();
        this.children = dept.getChildren().stream().map(TreeSelectVO::new).collect(Collectors.toList());
    }

    public TreeSelectVO(SysMenu menu) {
        this.id = menu.getMenuId();
        this.label = menu.getMenuName();
        this.children = menu.getChildren().stream().map(TreeSelectVO::new).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TreeSelectVO> getChildren() {
        return children;
    }

    public void setChildren(List<TreeSelectVO> children) {
        this.children = children;
    }
}
