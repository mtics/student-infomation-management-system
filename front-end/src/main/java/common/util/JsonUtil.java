package common.util;

import com.google.gson.*;
import bulletin.entity.Bulletin;
import student.entity.Student;
import subject.entity.Score;
import subject.entity.Subject;
import teacher.entity.Teacher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JsonUtil {

    private Gson gson = new Gson();

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");

    /**
     * JSON读取工具，返回String类型的JSON
     */
    public String loadJsonFromURL(String url) throws Exception{
        //读取URL， 返回JSON串
        String json = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(httpGet);

        if (response != null){
            HttpEntity entity =  response.getEntity();  //获取网页内容
            json = EntityUtils.toString(entity, "UTF-8");
        }

        if (response != null){
            response.close();
        }
        if (httpClient != null) {
            httpClient.close();
        }
        return json;
    }

    /**
     * 将JSON解析成List<Bulletin>
     * @param jsonStr
     * @return
     */
    public List<Bulletin> jsonToBulletinList(String jsonStr) throws ParseException {

        JSONArray jsonArray = JSONArray.fromObject(jsonStr);

        List<Bulletin> list = new ArrayList<Bulletin>();

        Bulletin bulletin = null;

        for (int i = 0; i < jsonArray.size(); i ++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            bulletin = new Bulletin(jsonObject.getInt("bulletinId"), jsonObject.getString("userId"),
                    jsonObject.getString("bulletinTitle"), sdf.parse(jsonObject.getString("publishedDate")),
                    jsonObject.getString("bulletinContext"));

            list.add(bulletin);
        }

        return list;
    }

    /**
     * 将JSON解析成List<Student>
     * @param jsonStr
     * @return
     */
    public List<Student> jsonToStudentList(String jsonStr) throws ParseException {

        JSONArray jsonArray = JSONArray.fromObject(jsonStr);

        List<Student> list = new ArrayList<Student>();

        Student student = null;

        for (int i = 0; i < jsonArray.size(); i ++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            student = new Student(jsonObject.getString("studentId"), jsonObject.getString("studentName"),
                    jsonObject.getString("gender"), sdf.parse(jsonObject.getString("birthday")),
                    jsonObject.getString("email"), jsonObject.getString("portrait"),jsonObject.getInt("majorId"));

            list.add(student);
        }

        return list;
    }

    /**
     * 将JSON解析成List<Subject>
     * @param jsonStr
     * @return
     */
    public List<Subject> jsonToSubjectList(String jsonStr) throws ParseException {

        JSONArray jsonArray = JSONArray.fromObject(jsonStr);

        List<Subject> list = new ArrayList<Subject>();

        Subject subject = null;

        for (int i = 0; i < jsonArray.size(); i ++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            subject = new Subject(jsonObject.getInt("subjectId"),
                    jsonObject.getInt("majorId"),jsonObject.getString("subjectName"));

            list.add(subject);
        }

        return list;
    }

    /**
     * 将JSON解析成List<Score>
     * @param jsonStr
     * @return
     */
    public List<Score> jsonToScoreList(String jsonStr) throws ParseException {

        JSONArray jsonArray = JSONArray.fromObject(jsonStr);

        List<Score> list = new ArrayList<Score>();

        Score score = null;

        for (int i = 0; i < jsonArray.size(); i ++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            score = new Score(jsonObject.getInt("scoreId"),
                    jsonObject.getInt("subjectId"),jsonObject.getString("studentId"),jsonObject.getDouble("scoreValue"));

            list.add(score);
        }

        return list;
    }


    /**
     * 将JSON解析成List<Student>
     * @param jsonStr
     * @return
     */
    public List<Teacher> jsonToTeacherList(String jsonStr) throws ParseException {

        JSONArray jsonArray = JSONArray.fromObject(jsonStr);

        List<Teacher> list = new ArrayList<Teacher>();

        Teacher teacher = null;

        for (int i = 0; i < jsonArray.size(); i ++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);

            teacher = new Teacher(jsonObject.getString("teacherId"), jsonObject.getString("teacherName"),
                    jsonObject.getString("gender"), sdf.parse(jsonObject.getString("birthday")),
                    jsonObject.getString("email"), jsonObject.getString("portrait"),jsonObject.getInt("collegeId"));

            list.add(teacher);
        }

        return list;
    }

    public Student jsonToStudent(String jsonStr) throws ParseException {

        JSONArray jsonArray = JSONArray.fromObject(jsonStr);
        // 因为调用该方法的只有通过id查找的，
        // 故json中最多只有一个实体，只需获取第0个即可
        JSONObject jsonObject = jsonArray.getJSONObject(0);

        String birthday = jsonObject.getString("birthday");

        Student student = new Student(jsonObject.getString("studentId"), jsonObject.getString("studentName"),
                jsonObject.getString("gender"), !birthday.equals("null") ? sdf.parse(birthday) : null,
                jsonObject.getString("email"),jsonObject.getString("portrait"), jsonObject.getInt("majorId"));


        return student;
    }

    public Teacher jsonToTeacher(String jsonStr) throws ParseException {

        JSONArray jsonArray = JSONArray.fromObject(jsonStr);
        // 因为调用该方法的只有通过id查找的，
        // 故json中最多只有一个实体，只需获取第0个即可
        JSONObject jsonObject = jsonArray.getJSONObject(0);

        String birthday = jsonObject.getString("birthday");

        Teacher teacher = new Teacher(jsonObject.getString("teacherId"), jsonObject.getString("teacherName"),
                jsonObject.getString("gender"), !birthday.equals("null")  ? sdf.parse(birthday) : null,
                    jsonObject.getString("email"),jsonObject.getString("portrait"), jsonObject.getInt("collegeId"));

        return teacher;
    }

    /**
     * 将List<Bulletin>转成Json
     * @param list
     * @return
     */
    public String bullutinListToJson(List<Bulletin> list){

        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();

        // 将List中的每个实体的属性都加入到JSONObject里
        // JSONObject添加完属性后加到JSONArray里
        for(Bulletin bulletin: list){
            jsonObject = new JSONObject();

            jsonObject.put("bulletinId", bulletin.getBulletinId());
            jsonObject.put("userId", bulletin.getUserId());
            jsonObject.put("bulletinTitle", bulletin.getBulletinTitle());
            jsonObject.put("publishedDate", sdf.format(bulletin.getPublishedDate()));
            jsonObject.put("bulletinContext", bulletin.getBulletinContext());

            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }

    /**
     * 将List<Student>转成Json
     * @param list
     * @return
     */
    public String studentListToJson(List<Student> list){

        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();

        // 将List中的每个实体的属性都加入到JSONObject里
        // JSONObject添加完属性后加到JSONArray里
        for(Student student: list){
            jsonObject = new JSONObject();

            jsonObject.put("studentId", student.getStudentId());
            jsonObject.put("studentName", student.getStudentName());
            jsonObject.put("gender", student.getGender());
            jsonObject.put("birthday", sdf.format(student.getBirthday()));
            jsonObject.put("email", student.getEmail());
            jsonObject.put("majorId", student.getMajorId());
            jsonObject.put("portrait", student.getPortrait());

            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }

    /**
     * 将List<Teacher>转成Json
     * @param list
     * @return
     */
    public String teacherListToJson(List<Teacher> list){

        JSONObject jsonObject = null;
        JSONArray jsonArray = new JSONArray();

        // 将List中的每个实体的属性都加入到JSONObject里
        // JSONObject添加完属性后加到JSONArray里
        for(Teacher teacher: list){
            jsonObject = new JSONObject();

            jsonObject.put("teacherId", teacher.getTeacherId());
            jsonObject.put("teacherName", teacher.getTeacherName());
            jsonObject.put("gender", teacher.getGender());
            jsonObject.put("birthday", sdf.format(teacher.getBirthday()));
            jsonObject.put("email", teacher.getEmail());
            jsonObject.put("collegeId", teacher.getCollegeId());
            jsonObject.put("portrait", teacher.getPortrait());

            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }

}
