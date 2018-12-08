package user.servlet;

import common.listener.UploadListener;
import common.util.ClientUtil;
import common.util.FileUtil;
import common.util.JsonUtil;
import cos.util.CosUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import page.dao.PageDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class UserInsertServlet extends HttpServlet {


    private ClientUtil clientUtil = new ClientUtil();

    private JsonUtil jsonUtil = new JsonUtil();

    private PageDaoImpl pageDao = new PageDaoImpl();

    private CosUtil cosUtil = new CosUtil();

    private FileUtil fileUtil = new FileUtil();

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    //创建工厂对象和文件上传对象
    private DiskFileItemFactory factory=new DiskFileItemFactory();
    private ServletFileUpload upload=new ServletFileUpload(factory);
    //创建上传监听器和设置监听器
    private UploadListener listener = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean isSuccess = false;

        HttpSession session=request.getSession();

        // 提交数据URL
        StringBuffer url = null;

        response.setContentType( "application/json;charset=UTF-8" );
        // 设置跨域请求头
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // 设置编码为UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        // 从网页获取用户填入的信息
        String userId = null;
        String userName = null;
        String gender = null;
        Date birthday = null;
        String email = null;
        String portraitUrl = null;
        int userLevel = -1;
        int tempMajorId = -1;

        //给上传的文件设一个最大值，这里是不得超过100MB
        int maxSize = 100*1024*1024;

        // 解析上传请求
        // 文本框叫文本域, file类型的form,一个输入框叫做一个文件域
        // 解析时,会将所有的文件选项放到 list集合里边 ; 一个输入框就是一个文件域,一个FileItem
        try {
            List<FileItem> items = upload.parseRequest(request);
            for(FileItem item: items){

                // 处理form中的文本元素
                if(item.isFormField()){
                    String fileName = item.getFieldName();
                    String value = item.getString("UTF-8");
                    // 获取值
                    if(fileName.equals("text_user_id") && !value.equals("")){
                        userId = value;
                    }else if(fileName.equals("text_user_name") && !value.equals("")){
                        userName = value;
                    }else if(fileName.equals("text_user_level") && !value.equals("")){
                        userLevel = Integer.parseInt(value);
                    }else if(fileName.equals("text_user_birthday") && !value.equals("")){
                        birthday = simpleDateFormat.parse(value);
                    }else if(fileName.equals("text_user_gender") && !value.equals("")){
                        gender = value;
                    }else if(fileName.equals("text_user_email") && !value.equals("")){
                        email = value;
                    }else if(fileName.equals("text_user_major") && !value.equals("")){
                        tempMajorId = Integer.parseInt(value);
                    }else if(fileName.equals("text_user_portrait") && !value.equals("")){
                        portraitUrl = value;
                    }
                }else{

                    String fileName = item.getName();
                    // 把后缀提取出来并保存
                    String suffix = fileName.substring(fileName.lastIndexOf("."));

                    // 随机生成一串16进制数
                    UUID uuid = UUID.randomUUID();
                    //将数字转化为字符串,并将"-"替换为""
                    String prefix = uuid.toString().replaceAll("-","");
                    // 将前缀和后缀拼接，组成要存储的文件名
                    String savedFileName = prefix+suffix;

                    // 处理form中的文件
                    // 判断是否为文件是否存在
                    if (item.getName()!=null&&!item.getName().equals("")){
                        // 获取上传文件大小和文件名称
                        long upFileSize = item.getSize();
                        String tempFileName = item.getName();
                        System.out.println("UploadFileName: " + savedFileName);

                        // 此时文件暂存在服务器的内存中，构造临时对象
                        File tempFile = new File(savedFileName);

                        fileUtil.inputStreamToFile(item.getInputStream(), tempFile);

                        portraitUrl = cosUtil.uploadFile(tempFile);

                        System.out.println("portraitUrl:"+portraitUrl);

                    }
                }
            }

            // 拼接post地址
            if(userLevel == 1){
                url = new StringBuffer("http://server.aspi.tech:8080/backend/student/save?");
                url.append("studentId="+ URLEncoder.encode(userId, "UTF-8"));
                url.append("&studentName="+URLEncoder.encode(userName, "UTF-8"));
                url.append("&majorId="+tempMajorId);
            }else if(userLevel == 2){
                url = new StringBuffer("http://server.aspi.tech:8080/backend/teacher/save?");
                url.append("teacherId="+URLEncoder.encode(userId, "UTF-8"));
                url.append("&teacherName="+URLEncoder.encode(userName, "UTF-8"));
                url.append("&collegeId="+tempMajorId);
            }

            if(!gender.equals("") && gender != null){
                url.append("&gender="+URLEncoder.encode(gender, "UTF-8"));
            }
            if(!birthday.toString().equals("") && birthday != null){
                url.append("&birthday="+URLEncoder.encode(birthday.toString(), "UTF-8"));
            }
            if(!email.equals("") && email != null){
                url.append("&email="+URLEncoder.encode(email, "UTF-8"));
            }if(!portraitUrl.equals("") && portraitUrl != null){
                url.append("&portrait="+URLEncoder.encode(portraitUrl, "UTF-8"));
            }

            // 以post方式提交，返回是否成功
            isSuccess = clientUtil.sendPost(url.toString());

            if(isSuccess){
                response.sendRedirect("/index.jsp");
            }else{
                response.sendRedirect("/user/form_user.jsp");
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
