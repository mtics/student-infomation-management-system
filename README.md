# 学生信息管理系统SIMS

> 这是ZZU的Java Web大作业

所有API调用URL都为"http://localhost:8080/sims/类名/方法名"。

注意URL中所有字母均为小写！

## User
- save(User user):传递一个User类参数，并将其保存在数据库中。若成功，返回"SUCCESS";
    - 访问方法名"/save"，访问方式：POST
- delete(String user_id):删除指定用户id的用户。若成功，返回"SUCCESS";
    - 访问方法名"/delete"，访问方式：POST
- findALl():查询所有用户，并将其以JSON形式返回;
    - 访问方法名"/findall"，访问方式：GET
- findOne(String user_id):按user_id查询用户，并以JSON形式返回.

## Student
- save(Student student):传递一个Student类参数，并将其保存在数据库中。若成功，返回"SUCCESS";
    - 访问方法名"/save"，访问方式：POST
- delete(String stu_id):删除指定stu_id的用户。若成功，返回"SUCCESS";
    - 访问方法名"/delete"，访问方式：POST
- findALl():查询所有学生，并将其以JSON形式返回;
    - 访问方法名"/findall"，访问方式：GET
- findOne(String stu_id):按stu_id查询用户，并以JSON形式返回.
    - 访问方法名"/findbyid"，访问方式：GET

## Lab
- save(Lab lab):传递一个Lab类参数，并将其保存在数据库中。若成功，返回"SUCCESS";
    - 访问方法名"/save"，访问方式：POST
- delete(int lab_id):删除指定lab_id的实验室。若成功，返回"SUCCESS";
    - 访问方法名"/delete"，访问方式：POST
- findALl():查询所有实验室，并将其以JSON形式返回;
    - 访问方法名"/findall"，访问方式：GET
- findOne(int lab_id):按lab_id查询用户，并以JSON形式返回.
    - 访问方法名"/findbyid"，访问方式：GET

## Subject
- save(Subject subject):传递一个Subject类参数，并将其保存在数据库中。若成功，返回"SUCCESS";
    - 访问方法名"/save"，访问方式：POST
- delete(int sub_id):删除指定sub_id的考核科目。若成功，返回"SUCCESS";
    - 访问方法名"/delete"，访问方式：POST
- findALl():查询所有实验室，并将其以JSON形式返回;
    - 访问方法名"/findall"，访问方式：GET
- findOne(int sub_id):按sub_id查询考核科目，并以JSON形式返回.
    - 访问方法名"/findbyid"，访问方式：GET

## Score
- save(Score score):传递一个Score参数，并将其保存在数据库中。若成功，返回"SUCCESS";
    - 访问方法名"/save"，访问方式：POST
- delete(long score_id):删除指定score_id的科目分数。若成功，返回"SUCCESS";
    - 访问方法名"/delete"，访问方式：POST
- findALl():查询所有成绩，并将其以JSON形式返回;
    - 访问方法名"/findall"，访问方式：GET
- findOne(long score_id):按score_id查询成绩，并以JSON形式返回.
    - 访问方法名"/findbyid"，访问方式：GET

## Checkio
- save(Checkio checkio):传递一个Checkio参数，并将其保存在数据库中。若成功，返回"SUCCESS";
    - 访问方法名"/save"，访问方式：POST
- delete(long check_id):删除指定check_id的签到信息。若成功，返回"SUCCESS";
    - 访问方法名"/delete"，访问方式：POST
- findALl():查询所有签到信息，并将其以JSON形式返回;
    - 访问方法名"/findall"，访问方式：GET
- findOne(long check_id):按check_id查询签到信息，并以JSON形式返回.
    - 访问方法名"/findbyid"，访问方式：GET

## Bulletin
- save(Bulletin bulletin):传递一个Bulletin参数，并将其保存在数据库中。若成功，返回"SUCCESS";
    - 访问方法名"/save"，访问方式：POST
- delete(long bull_id):删除指定bull_id的公告。若成功，返回"SUCCESS";
    - 访问方法名"/delete"，访问方式：POST
- findALl():查询所有签到信息，并将其以JSON形式返回;
    - 访问方法名"/findall"，访问方式：GET
- findOne(long bull_id):按bull_id查询公告，并以JSON形式返回.
    - 访问方法名"/findbyid"，访问方式：GET

