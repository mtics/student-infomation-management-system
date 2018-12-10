package student.servlet;

import common.util.ExpertUtil;
import common.util.JsonUtil;
import cos.util.CosUtil;
import page.dao.PageDaoImpl;
import page.entity.Page;
import student.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class StudentListFrashServlet extends HttpServlet {

    private JsonUtil jsonUtil = new JsonUtil();

    private PageDaoImpl pageDao = new PageDaoImpl();

    private ExpertUtil expertUtil = new ExpertUtil();

    private CosUtil cosUtil = new CosUtil();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取当前是页数
        int currentPage = Integer.valueOf(request.getParameter("page_num") == null ? "1" : request.getParameter("page_num"));
        // 设置每页数据数
        int pageSize = 10;

        // 后台查询公告的链接
        String url = "http://server.aspi.tech:8080/backend/student/findall";

        String searchStudentId = request.getParameter("search_student_id");
        String searchStudentName = request.getParameter("search_student_name");
        String searchGender = request.getParameter("search_gender");
        String searchBirthday = request.getParameter("search_birthday");
        String searchPhone = request.getParameter("search_phone");
        String searchMajorId = request.getParameter("search_major_id");

        boolean isExistSearchStudentId = (searchStudentId != null && !searchStudentId.equals(""));
        boolean isExistSearchStudentName = (searchStudentName != null && !searchStudentName.equals(""));
        boolean isExistSearchGender = (searchGender != null && !searchGender.equals(""));
        boolean isExistBirthday = (searchBirthday != null && !searchBirthday.equals(""));
        boolean isExistSearchPhone = (searchPhone != null && !searchPhone.equals(""));
        boolean isExistSearchMajorId = (searchMajorId != null && !searchMajorId.equals(""));

        String tempParams = "";

        // 检查条件，存在就拼接上去
        if (isExistSearchStudentId) {
            tempParams += "studentId=" + searchStudentId + "&";
        }
        if (isExistSearchStudentName) {
            tempParams += "studentName=" + searchStudentName + "&";
        }
        if (isExistSearchGender) {
            tempParams += "gender=" + searchGender + "&";
        }
        if (isExistSearchMajorId) {
            tempParams += "majorId=" + searchMajorId + "&";
        }

        // 如果三个条件存在一个，那么就跳转到/findallbyparams里进行条件查询
        if (isExistSearchStudentId || isExistBirthday || isExistSearchPhone ||
                isExistSearchStudentName || isExistSearchGender || isExistBirthday) {

            // 因为不确定哪个条件是存在的，所以之前每个都会加"&"
            // 因此最后就需要添加一个恒成立条件"1=1"
            // 在没有条件的情况下，与"/findall"等价
            url += "?"+tempParams + "1=1";

        }

        try {

            String studentJson = jsonUtil.loadJsonFromURL(url);

            List<Student> studentList = jsonUtil.jsonToStudentList(studentJson);

            Page<Student> studentPage = new Page<Student>(currentPage, pageSize, studentList);

            studentList = pageDao.getDataListWithPage(studentPage.getCurrentPage(), studentPage);

            String handledStudentListJson = jsonUtil.studentListToJson(studentList);


            String allKeyNames = studentList.get(0).getAllKeyNames();

            String allValues = "";

            for(int i = 0; i < studentList.size(); i++){
                allValues += studentList.get(i).getAllValues()+"\r";
            }

            File file = expertUtil.getCsvFile(allKeyNames, allValues, "studentFile.csv");

            String currentPageListUrl = cosUtil.uploadFile(file);


            // 将获得的列表添加到cookie中
            request.getSession().setAttribute("students_json", handledStudentListJson);
            request.getSession().setAttribute("student_pages", studentPage.getTotalPages());
            request.getSession().setAttribute("student_current_page", studentPage.getCurrentPage()-1);
            request.getSession().setAttribute("students_current", currentPageListUrl);

            response.sendRedirect ("/user/table_student.jsp") ;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
