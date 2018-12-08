package page.dao;

import page.entity.Page;

import java.util.*;

public class PageDaoImpl<T> implements PageDao<T>{

    /**
     * 将所需页面和page信息传入
     * 目前分页方法是将所有的信息都从数据库中读出，
     * 然后存入page中的list。
     * @param currentPage
     * @param page
     * @return
     */
    @Override
    public List<T> getDataListWithPage(int currentPage, Page<T> page) {

        List<T> list = null;

        // 当前页码的索引
        int currentPageIndex = currentPage-1;

        // 设置当前页数据开始的索引(相对于每一页的"0")
        int startIndex = page.getPageSize()*(currentPageIndex);
        // 设置当前页数据结束的索引(相对于每一页的pageSize-1)
        int endIndex = page.getPageSize()*(currentPageIndex+1);
        //获取page中的list
        List<T> tempList = page.getList();

        // 若当前页是最后一页
        // 因为页码索引是从0开始的，
        // 所以(总页数-1)才是最后一页的索引
        if(currentPageIndex == page.getTotalPages()-1){
            // 数据结束索引应该是总条数-1
            endIndex = page.getTotalCount()-1;
        }

        // 从List中截取出当前页面的数据
        list = tempList.subList(startIndex==0?0:startIndex-1,endIndex);

        return list;
    }
}
