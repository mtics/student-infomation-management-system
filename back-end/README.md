# SIMS学生信息管理系统外部接口

> 该工程下所有接口访问的前缀都是"http://server.aspi.tech/backend"。

## User
增加访问前缀"/user"。
- save(User user): 通过调用该接口传入要存入的用户信息。若用户ID已经存在，则会更新该用户ID对应的用户信息。
    - 参数：userId, passwd, userLevel
    - 访问方式: POST
    - 访问路径: "/save"
- deleteByid(String userId): 通过调用该接口删除指定ID的用户。若成功删除，则会返回"SUCCESS。
    - 参数：userId
    - 访问方式: POST
    - 访问路径: "/deletebyid"
- findAll( ): 通过调用该接口以JSON数组的形式返回所有的用户信息。
    - 访问方式: GET
    - 访问路径: "/findall"
- findById(String userId): 通过调用该接口以JSON形式返回指定ID的用户信息。
    - 访问方式: GET
    - 访问路径: "/findbyid"
- findByLevel(int userLevel): 通过调用该接口以JSON数组的形式返回指定等级的用户信息。
    - 访问方式: GET
    - 访问路径: "/findbylevel"

## Teacher
增加访问前缀"/teacher"。
- save(Teacher teacher): 通过调用该接口传入要存入的教师信息。若教师ID已经存在，则会更新该教师ID对应的教师信息。
    - 参数：teacherId, teacherName, collegeId, gender, birthday(格式:"yyyy-MM-dd"), email, portrait
    - 访问方式: POST
    - 访问路径: "/save"
- deleteByid(String teacherId): 通过调用该接口删除指定ID的教师。若成功删除，则会返回"SUCCESS。
    - 参数：teacherId
    - 访问方式: POST
    - 访问路径: "/deletebyid"
- findAll(Teacher teacher): 通过调用该接口以JSON数组的形式返回教师信息。若该请求有参数，则可以按条件查找指定的教师信息；若无参数，则返回所有的教师信息
    - 参数（可选）：teacherId, teacherName, collegeId, gender, birthday(格式:"yyyy-MM-dd")
    - 访问方式: GET
    - 访问路径: "/findall"

## Student
增加访问前缀"/student"。
- save(Student student): 通过调用该接口传入要存入的学生信息。若学生ID已经存在，则会更新该学生ID对应的学生信息。
    - 参数：studentId, studentName, studentId, gender, birthday(格式:"yyyy-MM-dd"), email, portrait
    - 访问方式: POST
    - 访问路径: "/save"
- deleteByid(String studentId): 通过调用该接口删除指定ID的学生。若成功删除，则会返回"SUCCESS。
    - 参数：studentId
    - 访问方式: POST
    - 访问路径: "/deletebyid"
- findAll(Student student): 通过调用该接口以JSON数组的形式返回学生信息。若该请求有参数，则可以按条件查找指定的学生信息；若无参数，则返回所有的学生信息
    - 参数（可选）：studentId, studentName, majorId, gender, birthday(格式:"yyyy-MM-dd")
    - 访问方式: GET
    - 访问路径: "/findall"

## Subject
增加访问前缀"/subject"。
- save(Subject subject): 通过调用该接口传入要存入的课程信息。若课程ID已经存在，则会更新该课程ID对应的课程信息。
    - 参数：subjectId, subjectName, majorId
    - 访问方式: POST
    - 访问路径: "/save"
- deleteByid(int subjectId): 通过调用该接口删除指定ID的课程。若成功删除，则会返回"SUCCESS。
    - 参数：subjectId
    - 访问方式: POST
    - 访问路径: "/deletebyid"
- findAll(Subject subject): 通过调用该接口以JSON数组的形式返回课程信息。若该请求有参数，则可以按条件查找指定的课程信息；若无参数，则返回所有的课程信息
    - 参数（可选）：subjectId, subjectName, majorId
    - 访问方式: GET
    - 访问路径: "/findall"

## Score
增加访问前缀"/score"。
- save(Score score): 通过调用该接口传入要存入的成绩信息。若成绩ID已经存在，则会更新该成绩ID对应的成绩信息。
    - 参数：scoreId, subjectId, studentId, scoreValue
    - 访问方式: POST
    - 访问路径: "/save"
- deleteByid(int scoreId): 通过调用该接口删除指定ID的成绩。若成功删除，则会返回"SUCCESS。
    - 参数：scoreId
    - 访问方式: POST
    - 访问路径: "/deletebyid"
- findAll(Score score): 通过调用该接口以JSON数组的形式返回成绩信息。若该请求有参数，则可以按条件查找指定的成绩信息；若无参数，则返回所有的成绩信息
    - 参数（可选）：scoreId, subjectId, studentId
    - 访问方式: GET
    - 访问路径: "/findall"

## Bulletin
增加访问前缀"/bulletin"。
- save(Bulletin bulletin): 通过调用该接口传入要存入的成绩信息。若成绩ID已经存在，则会更新该成绩ID对应的成绩信息。
    - 参数：bulletinId, userId, bulletinTitle, publishedDate(格式:"yyyy-MM-dd HH:mm:ss"), bulletinContext
    - 访问方式: POST
    - 访问路径: "/save"
- deleteByid(int bullId): 通过调用该接口删除指定ID的成绩。若成功删除，则会返回"SUCCESS。
    - 参数：scoreId
    - 访问方式: POST
    - 访问路径: "/deletebyid"
- findAll(Bulletin bulletin): 通过调用该接口以JSON数组的形式返回成绩信息。若该请求有参数，则可以按条件查找指定的成绩信息；若无参数，则返回所有的成绩信息
    - 参数（可选）：bulletinId, userId, bulletinTitle, bulletinContext
    - 访问方式: GET
    - 访问路径: "/findall"

## 其他

> 因其他接口在本次项目中未使用，故不再列出