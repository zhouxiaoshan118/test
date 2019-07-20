package com.zte.zudp.common.listener;

import com.zte.zudp.common.annotation.Menu;
import com.zte.zudp.common.persistence.entity.MenuTree;
import com.zte.zudp.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.*;

public class MenuAnnotationListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log= LoggerFactory.getLogger(MenuAnnotationListener.class);
    private static MenuTree tree =  new MenuTree();
    //        未插入Map<当前node节点id，父节点id>
    private Map<MenuTree, String> tempNodes = new HashMap<>();

    static {
        tree.setId("-1");
        tree.setChildren(new ArrayList<MenuTree>());
    }

    /**
     *  对menu注解的类进行监听 并生成树形结构
     * @param event
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Map<String, Object> menuMap=event.getApplicationContext().getBeansWithAnnotation(Menu.class);

        Map<String,String> mapMap=new HashMap<>();
        List<Menu> voidMenu=new ArrayList<>();
        List<String> voidurl=new ArrayList<>();
        List<String> otherurl=new ArrayList<>();
        List<Menu> otherMenu=new ArrayList<>();

        for (Map.Entry<String,Object> entry:menuMap.entrySet()){
            Class cls=entry.getValue().getClass();
            Menu menu = (Menu) cls.getAnnotation(Menu.class);
            String url="";
            RequestMapping rq=((RequestMapping)cls.getAnnotation(RequestMapping.class));
            if(rq!=null){
                url=rq.value()[0];
            }else{
                url="";
            }
            if(StringUtils.isEmpty(menu.id())){
                log.error(entry.getKey()+"类上的注解@Menu的属性id值为空");
                throw new RuntimeException(entry.getKey()+"类上的注解@Menu的属性id值为空");
            }
            if(mapMap.containsKey(menu.id())){
                String str=mapMap.get(menu.id());
                String error=entry.getKey()+"类上的注解@Menu的属性id与";
                String [] st=str.split(",");
                if(st.length>1){
                    error+=st[0]+"类的"+st[1]+"方法上的注解@Menu的属性id相同";
                }else{
                    error+=st[0]+"类上的注解@Menu的属性id相同";
                }
                log.error(error);
                throw new RuntimeException(error);
            }else{
                mapMap.put(menu.id(),entry.getKey());
                setMenu(voidMenu,otherMenu,menu,voidurl,otherurl,url);
                for (Method method:cls.getMethods()){
                    Menu ne=method.getAnnotation(Menu.class);
                    String ur="";
                    try {
                        if(ne!=null)
                            ur=((RequestMapping)method.getAnnotation(RequestMapping.class)).value()[0];
                        RequestMapping requestMapping= (RequestMapping) cls.getAnnotation(RequestMapping.class);
                        if(requestMapping!=null)
                            ur=requestMapping.value()[0]+ur;

                    }catch (Exception e){
                        log.error(entry.getKey()+"类"+method.getName()+"方法上缺少RequestMapping注解");
                        throw new RuntimeException(entry.getKey()+"类"+method.getName()+"方法上缺少RequestMapping注解");
                    }
                    if(ne!=null){
                        if(StringUtils.isEmpty(ne.id())){
                            log.error(entry.getKey()+"类"+method.getName()+"方法上的注解@Menu的属性id为空");
                            throw new RuntimeException(entry.getKey()+"类"+method.getName()+"方法上的注解@Menu的属性id为空");
                        }
                        if(mapMap.containsKey(ne.id())){
                            String str=mapMap.get(ne.id());
                            String error=entry.getKey()+"类的"+method.getName()+"方法的注解@Menu的属性id与";
                            String [] st=str.split(",");
                            if(st.length>1){
                                error+=st[0]+"类的"+st[1]+"方法上的注解@Menu的属性id相同";
                            }else{
                                error+=st[0]+"类上的注解@Menu的属性id相同";
                            }

                            log.error(error);
                            throw new RuntimeException(error);
                        }else{
                            mapMap.put(ne.id(),entry.getKey()+","+method.getName());
                            setMenu(voidMenu,otherMenu,ne,voidurl,otherurl,ur);
                        }
                    }
                }
            }
        }

        setMenuGoTree(voidMenu,otherMenu,voidurl,otherurl);
        sort(tree);
    }
    //parent 为void.class 先存入set 不是后存入
    private void setMenu(List<Menu> voidMenu,List<Menu> otherMenu,Menu menu,List<String> voidUrl,List<String> otherUrl,String url){
        if(menu.parent() == void.class){
            voidMenu.add(menu);
            voidUrl.add(url);
        }else{
            otherMenu.add(menu);
            otherUrl.add(url);
        }
    }

    //parent 为void.class 先插入 不是后插入
    private void setMenuGoTree(List<Menu> voidMenu,List<Menu> otherMenu,List<String> voidUrl,List<String> otherUrl){
        int i=0;
        for(Menu menu:voidMenu){
            setMenuTreeNode(menu,voidUrl.get(i));
            i++;
        }
        int j=0;
        for (Menu menu:otherMenu){
            setMenuTreeNode(menu,otherUrl.get(j));
            j++;
        }
        addNodeAgain();
    }


    private void setMenuTreeNode(Menu menu,String url){
//        已插入node
        List<MenuTree> insertedNode = new ArrayList<>();


        Class cla=menu.parent();
        MenuTree treeNode = new MenuTree();
        treeNode.setName(menu.name());
        treeNode.setOrder(menu.order());
        treeNode.setChildren(new ArrayList<MenuTree>());
        treeNode.setId(menu.id());
        treeNode.setUrl(url);
        if(cla != void.class){
            Menu MeParent= (Menu) cla.getAnnotation(Menu.class);
            MenuTree parentNode = getParentNode(tree, MeParent.id());
//            如果这个treeNode的父类还没有插入，占时放入map中
            if(parentNode == null) {
                tempNodes.put(treeNode, MeParent.id());
            } else {
                parentNode.getChildren().add(treeNode);
            }

        } else {
            //判断父Menu，如果为空为第一层子节点
            tree.getChildren().add(treeNode);
        }

    }

    private void addNodeAgain() {
        Iterator<Map.Entry<MenuTree, String>> iterator = tempNodes.entrySet().iterator();
        outer:
        for(;;) {
            if(tempNodes.size() == 0) {
                break outer;
            }
            //map遍历
            while (iterator.hasNext()) {
                Map.Entry<MenuTree,String> entry =  iterator.next();
                String parentId = entry.getValue();
                MenuTree parent = getParentNode(tree, parentId);
                if(parent != null) {
                    parent.getChildren().add(entry.getKey());
                    iterator.remove();
                }
            }
        }
    }






    //传入一个menuTreeNode 通过 递归遍历 找到该结点的父节点
    private MenuTree getParentNode(MenuTree menu, String id) {
//      进入递归先判断
        if(id.equals(menu.getId())) {
            return menu;
        }

        List<MenuTree> childs = menu.getChildren();
        if(childs != null & childs.size() > 0) {
//           下面有子结点
            for(int i = 0; i < childs.size(); i++) {
                MenuTree target =  getParentNode(childs.get(i), id);
                if(target != null) {
                    return target;
                }
            }
        }

        return null;

    }

    //按照weight进行排序
    private void sort(MenuTree menutree) {
        List<MenuTree> childs = menutree.getChildren();
        if(childs.size() > 0 && childs != null) {
            //如果有child，先排序再递归
            //排序
            Collections.sort(childs,new Comparator<MenuTree>(){
                public int compare(MenuTree arg0, MenuTree arg1) {
                    return arg0.getOrder().compareTo(arg1.getOrder());
                }
            });
//            for(int i = 0; i < childs.size(); i++) {
//                for(int j = i + 1 ; j < childs.size(); j++ ) {
//                    if(childs.get(i).getOrder() > childs.get(j).getOrder()) {
//                        MenuTree mt = childs.get(i);
//
//
//
//                        childs.get(i).setId(childs.get(j).getId());
//                        childs.get(i).setName(childs.get(j).getName());
//                        childs.get(i).setOrder(childs.get(j).getOrder());
//                        childs.get(i).setChildrens(childs.get(j).getChildrens());
//
//                        childs.get(j).setId(mt.getId());
//                        childs.get(j).setName(mt.getName());
//                        childs.get(j).setOrder(mt.getOrder());
//                        childs.get(j).setChildrens(mt.getChildrens());
//                    }
//                }
//            }

            for (int i = 0; i < childs.size(); i++) {
                sort(childs.get(i));
            }
        }
    }

    public static MenuTree getMenuTree(){
        return tree;
    }
}
