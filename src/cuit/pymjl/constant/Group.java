package cuit.pymjl.constant;

/**
 * @author Pymjl
 * @version 1.0
 * @date 2022/6/19 23:27
 **/
public enum Group {
    /**
     * 主菜单
     */
    Main_MENU(1),
    /**
     * 二级菜单
     */
    SECONDARY_MENU(2);

    private Integer group;

    Group() {
    }

    Group(Integer group) {
        this.group = group;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Group{" +
                "group=" + group +
                '}';
    }
}
