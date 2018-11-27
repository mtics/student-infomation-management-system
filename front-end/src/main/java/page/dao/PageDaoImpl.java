package page.dao;

import page.entity.Page;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

        // 设置当前页数据开始的索引(相对于每一页的"0")
        int startIndex = page.getPageSize()*(currentPage-1);
        // 设置当前页数据结束的索引(相对于每一页的pageSize-1)
        int endIndex = page.getPageSize()*(currentPage) - 1;

        // 若当前页是最后一页
        if(currentPage == page.getTotalPages()){
            // 数据结束索引应该是总条数除以每页数据数的余数
            endIndex = page.getTotalCount() % page.getPageSize();
        }

        // 将当前页中的数据加入返回列表中
        for(int i = startIndex; i < endIndex; i ++){
            list.add(page.getList().get(i));
        }

        return list;
    }
}
