package page.dao;

import page.entity.Page;

import java.util.List;

public interface PageDao<T> {

    /**
     * 通过页码查询内容
     * @param currentPage
     * @return
     */
    public List<T> getDataListWithPage(int currentPage, Page<T> page);
}
