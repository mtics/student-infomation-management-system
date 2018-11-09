-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: 10.53.54.151:16441
-- Generation Time: 2018-11-09 13:04:43
-- 服务器版本： 5.7.18-20170830-log
-- PHP Version: 5.6.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sims`
--
CREATE DATABASE IF NOT EXISTS `sims` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `sims`;

-- --------------------------------------------------------

--
-- 表的结构 `bulletin`
--

CREATE TABLE `bulletin` (
  `bull_id` int(11) NOT NULL,
  `user_id` char(13) NOT NULL,
  `bull_title` varchar(50) NOT NULL,
  `bull_published_date` datetime NOT NULL,
  `bull_context` varchar(1023) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `college`
--

CREATE TABLE `college` (
  `college_id` int(11) NOT NULL,
  `college_name` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `college`
--

INSERT INTO `college` (`college_id`, `college_name`) VALUES
(1, '政教处'),
(2, '信息工程学院');

--
-- 触发器 `college`
--
DELIMITER $$
CREATE TRIGGER `T_DeleteCollege_1` BEFORE DELETE ON `college` FOR EACH ROW DELETE FROM major WHERE college_id = old.college_id
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `T_DeleteCollege_2` BEFORE DELETE ON `college` FOR EACH ROW DELETE FROM teacher WHERE college_id = old.college_id
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `major`
--

CREATE TABLE `major` (
  `major_id` int(11) NOT NULL,
  `college_id` int(11) NOT NULL,
  `major_name` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `major`
--

INSERT INTO `major` (`major_id`, `college_id`, `major_name`) VALUES
(1, 2, '计算机科学与技术'),
(2, 2, '软件工程');

--
-- 触发器 `major`
--
DELIMITER $$
CREATE TRIGGER `T_DeleteMajor_1` BEFORE DELETE ON `major` FOR EACH ROW DELETE FROM subject WHERE major_id = old.major_id
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `T_DeleteMajor_2` BEFORE DELETE ON `major` FOR EACH ROW DELETE FROM student WHERE major_id = old.major_id
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `score`
--

CREATE TABLE `score` (
  `score_id` int(11) NOT NULL,
  `sub_id` int(11) NOT NULL,
  `stu_id` char(13) NOT NULL,
  `score_value` decimal(3,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `student`
--

CREATE TABLE `student` (
  `stu_id` char(13) NOT NULL,
  `stu_name` varchar(20) NOT NULL,
  `gender` char(5) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `phone` char(13) DEFAULT NULL,
  `portrait` varchar(1023) DEFAULT NULL,
  `major_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `student`
--

INSERT INTO `student` (`stu_id`, `stu_name`, `gender`, `birthday`, `phone`, `portrait`, `major_id`) VALUES
('20181151105', '李三', '男', '1997-01-01', '12345678910', NULL, 1);

--
-- 触发器 `student`
--
DELIMITER $$
CREATE TRIGGER `T_DeleteStudent_1` BEFORE DELETE ON `student` FOR EACH ROW DELETE FROM score WHERE stu_id = old.stu_id
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `T_DeleteStudent_2` BEFORE DELETE ON `student` FOR EACH ROW DELETE FROM user WHERE user_id = old.stu_id
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `T_InsertIntoStudent` AFTER INSERT ON `student` FOR EACH ROW INSERT INTO user VALUES(new.stu_id, '000000', 1)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `subject`
--

CREATE TABLE `subject` (
  `sub_id` int(11) NOT NULL,
  `major_id` int(11) DEFAULT NULL,
  `sub_name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 触发器 `subject`
--
DELIMITER $$
CREATE TRIGGER `T_DeleteSubject` BEFORE DELETE ON `subject` FOR EACH ROW DELETE FROM score WHERE sub_id = old.sub_id
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `teach`
--

CREATE TABLE `teach` (
  `teacher_id` char(13) NOT NULL,
  `sub_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `teacher`
--

CREATE TABLE `teacher` (
  `teacher_id` char(13) NOT NULL,
  `teacher_name` varchar(30) NOT NULL,
  `college_id` int(11) NOT NULL,
  `gender` char(5) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `portrait` varchar(1023) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `teacher`
--

INSERT INTO `teacher` (`teacher_id`, `teacher_name`, `college_id`, `gender`, `birthday`, `phone`, `portrait`) VALUES
('20181151105T', '王五', 2, '男', NULL, NULL, NULL),
('20181181108T', '赵四', 2, NULL, NULL, NULL, NULL),
('admin', 'admin', 1, NULL, NULL, NULL, NULL);

--
-- 触发器 `teacher`
--
DELIMITER $$
CREATE TRIGGER `T_DeleteTeacher_1` BEFORE DELETE ON `teacher` FOR EACH ROW DELETE FROM user WHERE user_id = old.teacher_id
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `T_DeleteTeacher_2` BEFORE DELETE ON `teacher` FOR EACH ROW DELETE FROM teach WHERE teacher_id=old.teacher_id
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `T_InsertIntoTeacher` AFTER INSERT ON `teacher` FOR EACH ROW INSERT INTO user VALUES(new.teacher_id, '000000', 2)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `user_id` char(13) NOT NULL,
  `passwd` char(20) NOT NULL,
  `user_level` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`user_id`, `passwd`, `user_level`) VALUES
('20181151105', '000000', 1),
('20181151105T', '000000', 2),
('20181181108T', '000000', 2),
('admin', 'admin', 3);

--
-- 触发器 `user`
--
DELIMITER $$
CREATE TRIGGER `T_DeleteUser_1` BEFORE DELETE ON `user` FOR EACH ROW UPDATE bulletin SET user_id = 'admin' WHERE user_id = old.user_id AND old.user_level=2
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `T_DeleteUser_2` AFTER DELETE ON `user` FOR EACH ROW DELETE FROM student WHERE student_id=old.user_id AND old.user_level=1
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `T_DeleteUser_3` AFTER DELETE ON `user` FOR EACH ROW DELETE FROM teacher WHERE teacher_id = old.user_id AND old.user_level = 2
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bulletin`
--
ALTER TABLE `bulletin`
  ADD PRIMARY KEY (`bull_id`),
  ADD KEY `FK_user_bulletin` (`user_id`);

--
-- Indexes for table `college`
--
ALTER TABLE `college`
  ADD PRIMARY KEY (`college_id`);

--
-- Indexes for table `major`
--
ALTER TABLE `major`
  ADD PRIMARY KEY (`major_id`),
  ADD KEY `FK_setup` (`college_id`);

--
-- Indexes for table `score`
--
ALTER TABLE `score`
  ADD PRIMARY KEY (`score_id`),
  ADD KEY `FK_Reference_12` (`stu_id`),
  ADD KEY `FK_subject_score2` (`sub_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`stu_id`),
  ADD KEY `FK_study_in` (`major_id`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`sub_id`),
  ADD KEY `FK_have` (`major_id`);

--
-- Indexes for table `teach`
--
ALTER TABLE `teach`
  ADD KEY `FK_teach2` (`sub_id`,`teacher_id`) USING BTREE,
  ADD KEY `FK_teacher_teach` (`teacher_id`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacher_id`),
  ADD KEY `FK_work_at` (`college_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `bulletin`
--
ALTER TABLE `bulletin`
  MODIFY `bull_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `college`
--
ALTER TABLE `college`
  MODIFY `college_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- 使用表AUTO_INCREMENT `major`
--
ALTER TABLE `major`
  MODIFY `major_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- 使用表AUTO_INCREMENT `score`
--
ALTER TABLE `score`
  MODIFY `score_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `subject`
--
ALTER TABLE `subject`
  MODIFY `sub_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 限制导出的表
--

--
-- 限制表 `bulletin`
--
ALTER TABLE `bulletin`
  ADD CONSTRAINT `FK_user_bulletin` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE;

--
-- 限制表 `major`
--
ALTER TABLE `major`
  ADD CONSTRAINT `FK_setup` FOREIGN KEY (`college_id`) REFERENCES `college` (`college_id`);

--
-- 限制表 `score`
--
ALTER TABLE `score`
  ADD CONSTRAINT `FK_Reference_12` FOREIGN KEY (`stu_id`) REFERENCES `student` (`stu_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_subject_score2` FOREIGN KEY (`sub_id`) REFERENCES `subject` (`sub_id`) ON UPDATE CASCADE;

--
-- 限制表 `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `FK_study_in` FOREIGN KEY (`major_id`) REFERENCES `major` (`major_id`);

--
-- 限制表 `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `FK_have` FOREIGN KEY (`major_id`) REFERENCES `major` (`major_id`);

--
-- 限制表 `teach`
--
ALTER TABLE `teach`
  ADD CONSTRAINT `FK_teach_subject` FOREIGN KEY (`sub_id`) REFERENCES `subject` (`sub_id`),
  ADD CONSTRAINT `FK_teacher_teach` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`);

--
-- 限制表 `teacher`
--
ALTER TABLE `teacher`
  ADD CONSTRAINT `FK_work_at` FOREIGN KEY (`college_id`) REFERENCES `college` (`college_id`);
--
-- Database: `zrla`
--
CREATE DATABASE IF NOT EXISTS `zrla` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `zrla`;

-- --------------------------------------------------------

--
-- 表的结构 `bulletin`
--

CREATE TABLE `bulletin` (
  `bull_id` int(11) NOT NULL,
  `bull_title` varchar(50) NOT NULL,
  `bull_publisher_id` char(12) NOT NULL,
  `bull_published_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `bull_tags` varchar(50) DEFAULT NULL,
  `bull_img` varchar(1024) DEFAULT NULL,
  `bull_markdown_path` varchar(1024) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `bulletin`
--

INSERT INTO `bulletin` (`bull_id`, `bull_title`, `bull_publisher_id`, `bull_published_date`, `bull_tags`, `bull_img`, `bull_markdown_path`) VALUES
(1, 'test', '20181151105', '2018-11-05 13:07:01', 'test,est,st,t', NULL, 'null'),
(2, 'lalala', '20181250125', '2018-11-06 22:14:29', 'la,a,al', NULL, ''),
(3, '6566', '00000000000', '2018-11-06 22:15:04', '666', NULL, '');

-- --------------------------------------------------------

--
-- 表的结构 `checkio`
--

CREATE TABLE `checkio` (
  `check_id` int(11) NOT NULL,
  `user_id` char(13) NOT NULL,
  `check_type` smallint(6) NOT NULL,
  `check_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `check_loc` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `lab`
--

CREATE TABLE `lab` (
  `lab_id` int(11) NOT NULL,
  `lab_name` varchar(255) DEFAULT NULL,
  `lab_manager_id` char(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `lab`
--

INSERT INTO `lab` (`lab_id`, `lab_name`, `lab_manager_id`) VALUES
(1, '双足实验室', '20181151105');

-- --------------------------------------------------------

--
-- 表的结构 `lab_bulletin`
--

CREATE TABLE `lab_bulletin` (
  `lab_id` int(11) NOT NULL,
  `bull_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `score`
--

CREATE TABLE `score` (
  `score_id` bigint(20) NOT NULL,
  `user_id` char(13) NOT NULL,
  `sub_id` bigint(20) NOT NULL,
  `score_value` decimal(3,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `student`
--

CREATE TABLE `student` (
  `stu_id` char(13) NOT NULL,
  `lab_id` int(11) NOT NULL,
  `stu_name` varchar(20) NOT NULL,
  `stu_sex` char(5) DEFAULT NULL,
  `stu_birth` date DEFAULT NULL,
  `stu_nation` varchar(60) DEFAULT NULL,
  `stu_phone` char(13) DEFAULT NULL,
  `stu_room` varchar(300) DEFAULT NULL,
  `stu_img` varchar(1024) DEFAULT NULL,
  `stu_college` varchar(1024) DEFAULT NULL,
  `stu_major` varchar(1024) DEFAULT NULL,
  `stu_email` varchar(1024) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `student`
--

INSERT INTO `student` (`stu_id`, `lab_id`, `stu_name`, `stu_sex`, `stu_birth`, `stu_nation`, `stu_phone`, `stu_room`, `stu_img`, `stu_college`, `stu_major`, `stu_email`) VALUES
('00000000000', 1, '老师', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('20181151105', 1, '李三', '男', '1997-01-01', '汉', '12345678910', '郑州大学信工科技楼111室', 'null', '信息工程学院', '计算机科学与技术', '123456789@qq.com'),
('20181250125', 1, '赵四', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

--
-- 触发器 `student`
--
DELIMITER $$
CREATE TRIGGER `T_DeleteStu` BEFORE DELETE ON `student` FOR EACH ROW DELETE FROM user WHERE user_id = old.stu_id
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `T_InsertIntoStu` AFTER INSERT ON `student` FOR EACH ROW INSERT INTO user VALUES(new.stu_id, '000000', 1)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `subject`
--

CREATE TABLE `subject` (
  `sub_id` bigint(20) NOT NULL,
  `sub_name` varchar(100) NOT NULL,
  `sub_in_time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 触发器 `subject`
--
DELIMITER $$
CREATE TRIGGER `T_DeleteSub` BEFORE DELETE ON `subject` FOR EACH ROW DELETE FROM score WHERE sub_id = old.sub_id
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `user_id` char(13) NOT NULL,
  `user_passwd` char(20) NOT NULL,
  `user_level` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`user_id`, `user_passwd`, `user_level`) VALUES
('00000000000', '000000', 1),
('20181151105', '000000', 1),
('20181250125', '000000', 1);

--
-- 触发器 `user`
--
DELIMITER $$
CREATE TRIGGER `T_DeleteCheckIOBeforeUser` BEFORE DELETE ON `user` FOR EACH ROW DELETE FROM checkio WHERE user_id = old.user_id
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `T_DeleteScoreBeforeUser` BEFORE DELETE ON `user` FOR EACH ROW DELETE FROM score WHERE user_id = old.user_id
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `T_UpdateBulletinBeforeUser` BEFORE DELETE ON `user` FOR EACH ROW UPDATE bulletin SET bull_publisher_id = '00000000000' WHERE bull_publisher_id = old.user_id
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bulletin`
--
ALTER TABLE `bulletin`
  ADD PRIMARY KEY (`bull_id`),
  ADD KEY `FK_user_bulletin` (`bull_publisher_id`);

--
-- Indexes for table `checkio`
--
ALTER TABLE `checkio`
  ADD PRIMARY KEY (`check_id`),
  ADD KEY `FK_user_check` (`user_id`);

--
-- Indexes for table `lab`
--
ALTER TABLE `lab`
  ADD PRIMARY KEY (`lab_id`);

--
-- Indexes for table `lab_bulletin`
--
ALTER TABLE `lab_bulletin`
  ADD PRIMARY KEY (`lab_id`,`bull_id`),
  ADD KEY `FK_lab_bulletin2` (`bull_id`);

--
-- Indexes for table `score`
--
ALTER TABLE `score`
  ADD PRIMARY KEY (`score_id`),
  ADD KEY `FK_subject_score2` (`sub_id`),
  ADD KEY `FK_user_score` (`user_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`stu_id`),
  ADD KEY `FK_lab_stu` (`lab_id`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`sub_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `bulletin`
--
ALTER TABLE `bulletin`
  MODIFY `bull_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- 使用表AUTO_INCREMENT `checkio`
--
ALTER TABLE `checkio`
  MODIFY `check_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `lab`
--
ALTER TABLE `lab`
  MODIFY `lab_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- 使用表AUTO_INCREMENT `score`
--
ALTER TABLE `score`
  MODIFY `score_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `subject`
--
ALTER TABLE `subject`
  MODIFY `sub_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- 限制导出的表
--

--
-- 限制表 `bulletin`
--
ALTER TABLE `bulletin`
  ADD CONSTRAINT `FK_user_bulletin` FOREIGN KEY (`bull_publisher_id`) REFERENCES `user` (`user_id`);

--
-- 限制表 `checkio`
--
ALTER TABLE `checkio`
  ADD CONSTRAINT `FK_user_check` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- 限制表 `lab_bulletin`
--
ALTER TABLE `lab_bulletin`
  ADD CONSTRAINT `FK_lab_bulletin` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`lab_id`),
  ADD CONSTRAINT `FK_lab_bulletin2` FOREIGN KEY (`bull_id`) REFERENCES `bulletin` (`bull_id`);

--
-- 限制表 `score`
--
ALTER TABLE `score`
  ADD CONSTRAINT `FK_subject_score2` FOREIGN KEY (`sub_id`) REFERENCES `subject` (`sub_id`),
  ADD CONSTRAINT `FK_user_score` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- 限制表 `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `FK_lab_stu` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`lab_id`);

--
-- 限制表 `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK_stu_user` FOREIGN KEY (`user_id`) REFERENCES `student` (`stu_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
