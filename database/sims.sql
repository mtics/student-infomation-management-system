-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: 10.53.54.151:16441
-- Generation Time: 2018-12-11 16:26:03
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

-- --------------------------------------------------------

--
-- 表的结构 `bulletin`
--

CREATE TABLE `bulletin` (
  `bull_id` int(11) NOT NULL,
  `user_id` char(13) NOT NULL,
  `bull_title` varchar(66) NOT NULL,
  `bull_published_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `bull_context` varchar(1023) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `bulletin`
--

INSERT INTO `bulletin` (`bull_id`, `user_id`, `bull_title`, `bull_published_date`, `bull_context`) VALUES
(1, 'admin', 'setset', '2018-12-07 21:46:12', 'adsfasdfasd'),
(2, 'admin', 'setset', '2018-12-07 22:07:59', 'asdfasdf'),
(4, 'admin', 'asdfasd', '2018-12-09 20:43:44', 'asdfasdf'),
(8, 'admin', '我是一则公告', '2018-12-11 16:20:05', '我是这则公告的内容！！！');

-- --------------------------------------------------------

--
-- 表的结构 `college`
--

CREATE TABLE `college` (
  `college_id` int(11) NOT NULL COMMENT '学院号',
  `college_name` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `college`
--

INSERT INTO `college` (`college_id`, `college_name`) VALUES
(1, '信息工程学院'),
(2, '政教处');

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
  `major_name` varchar(150) NOT NULL COMMENT '专业全称'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `major`
--

INSERT INTO `major` (`major_id`, `college_id`, `major_name`) VALUES
(1, 1, '计算机科学与技术'),
(2, 1, '软件工程');

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
  `subject_id` int(11) NOT NULL,
  `student_id` char(13) NOT NULL,
  `score_value` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `score`
--

INSERT INTO `score` (`score_id`, `subject_id`, `student_id`, `score_value`) VALUES
(1, 3, '201677I0215', '10.00'),
(2, 4, '201677I0215', '50.00'),
(3, 9, '201677I0215', '80.00'),
(4, 6, '201677I0215', '90.00'),
(5, 1, '201677I0215', '90.00'),
(6, 5, '201677I0215', '90.00'),
(7, 7, '201677I0215', '85.00'),
(8, 2, '201677I0215', '98.00'),
(9, 8, '201677I0215', '89.00'),
(10, 1, '20161271208', '0.00'),
(11, 1, '20161271209', '0.00'),
(12, 2, '20161271208', '0.00'),
(13, 3, '20161271208', '0.00'),
(14, 4, '20161271208', '0.00'),
(15, 5, '20161271208', '0.00'),
(16, 6, '20161271208', '0.00'),
(17, 7, '20161271208', '0.00'),
(18, 8, '20161271208', '0.00'),
(19, 9, '20161271208', '0.00');

-- --------------------------------------------------------

--
-- 表的结构 `sims_user`
--

CREATE TABLE `sims_user` (
  `user_id` char(13) NOT NULL,
  `passwd` char(20) NOT NULL,
  `user_level` smallint(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `sims_user`
--

INSERT INTO `sims_user` (`user_id`, `passwd`, `user_level`) VALUES
('20161271202T', '000000', 2),
('20161271208', '000000', 1),
('20161271209', '000000', 1),
('201677I0215', '19970806123', 1),
('20181121120T', '000000', 2),
('20181151105T', '000000', 2),
('20181211201T', '000000', 2),
('20181211215', '000000', 1),
('20181211235', '000000', 1),
('20181241205', '000000', 1),
('20181261206', '000000', 1),
('20181271207', '000000', 1),
('20181291209T', '000000', 2),
('admin', 'admin', 3);

--
-- 触发器 `sims_user`
--
DELIMITER $$
CREATE TRIGGER `T_DeleteUser_1` BEFORE DELETE ON `sims_user` FOR EACH ROW UPDATE bulletin SET user_id = 'admin' WHERE user_id = old.user_id AND old.user_level=2
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `student`
--

CREATE TABLE `student` (
  `student_id` char(13) NOT NULL,
  `student_name` varchar(20) NOT NULL,
  `gender` char(5) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` char(30) DEFAULT NULL COMMENT '电子邮箱',
  `portrait` varchar(1023) DEFAULT NULL,
  `major_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `student`
--

INSERT INTO `student` (`student_id`, `student_name`, `gender`, `birthday`, `email`, `portrait`, `major_id`) VALUES
('20161271208', '李浩楠', '男', '1997-09-16', '827437709@qq.com', 'https://incentancy-1253813002.cos.ap-guangzhou.myqcloud.com/9e3a44a81a884a0582c0b0e18b98fc6b.jpg', 1),
('20161271209', '李浩楠', '女', '1997-09-16', '827437709@qq.com', 'https://incentancy-1253813002.cos.ap-guangzhou.myqcloud.com/29e1df63868b4c21ad46aef7ffa1b50b.jpg', 1),
('201677I0215', '李志伟', '男', '1997-08-06', 'vincent@aspi.tech', 'https://incentancy-1253813002.cos.ap-guangzhou.myqcloud.com/bg7r-fyrpeie1413753.jpg', 1),
('20181211215', '李', '男', '1997-09-16', '827437709@qq.com', 'https://incentancy-1253813002.cos.ap-guangzhou.myqcloud.comfd6136fe5dd64bfdbd1e013975accdbd.jpg', 1),
('20181211235', '浩楠', '男', '1997-09-16', '123@qq.com', 'https://incentancy-1253813002.cos.ap-guangzhou.myqcloud.com5e863e87f8f344e3a91d342041a3172b.jpeg', 1),
('20181241205', '李浩', '男', '1997-09-16', '123456@qq.com', 'https://incentancy-1253813002.cos.ap-guangzhou.myqcloud.com/42e9eba3cad147f4afde27980a8bd5a4.jpg', 1),
('20181261206', '李四', '男', '2000-01-01', '123456789@123.com', NULL, 2),
('20181271207', '张三', '男', '1997-09-16', '123456789@123.com', NULL, 1);

--
-- 触发器 `student`
--
DELIMITER $$
CREATE TRIGGER `T_DeleteStudent_1` BEFORE DELETE ON `student` FOR EACH ROW DELETE FROM score WHERE student_id = old.student_id
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `T_DeleteStudent_2` BEFORE DELETE ON `student` FOR EACH ROW DELETE FROM sims_user WHERE user_id = old.student_id AND user_level = 1
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `T_InsertIntoStudent` AFTER INSERT ON `student` FOR EACH ROW INSERT INTO sims_user VALUES(new.student_id, '000000', 1)
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- 表的结构 `subject`
--

CREATE TABLE `subject` (
  `subject_id` int(11) NOT NULL,
  `subject_name` varchar(100) NOT NULL,
  `major_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `subject`
--

INSERT INTO `subject` (`subject_id`, `subject_name`, `major_id`) VALUES
(1, '移动编程', 1),
(2, '计算方法', 1),
(3, '形式与政策', 1),
(4, '操作系统', 1),
(5, '编译原理', 1),
(6, '汇编语言', 1),
(7, '网络实用技术', 1),
(8, '面向对象技术', 1),
(9, '数据库设计', 1);

--
-- 触发器 `subject`
--
DELIMITER $$
CREATE TRIGGER `T_DeleteSubject` BEFORE DELETE ON `subject` FOR EACH ROW DELETE FROM score WHERE subject_id = old.subject_id
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

--
-- 转存表中的数据 `teach`
--

INSERT INTO `teach` (`teacher_id`, `sub_id`) VALUES
('20181211201T', 3),
('20181121120T', 4),
('20181121120T', 6),
('20181151105T', 9),
('20181291209T', 5),
('20181151105T', 8),
('20181291209T', 2),
('20181291209T', 1);

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
  `email` char(30) DEFAULT NULL,
  `portrait` varchar(1023) DEFAULT NULL COMMENT '头像'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `teacher`
--

INSERT INTO `teacher` (`teacher_id`, `teacher_name`, `college_id`, `gender`, `birthday`, `email`, `portrait`) VALUES
('20161271202T', 'Alan', 2, '男', '1997-09-16', 'asdfa@qq.com', 'https://incentancy-1253813002.cos.ap-guangzhou.myqcloud.com/b31c28b9b23540c788f0f0d2f15867c3.jpg'),
('20181121120T', '赵四', 1, '男', '1990-09-16', '123456789@123.com', NULL),
('20181151105T', '赵四', 1, '男', '1980-01-01', '123456789@123.com', NULL),
('20181211201T', '李四', 2, '男', '1980-02-03', '123456789@123.com', NULL),
('20181291209T', '王五', 1, '男', '1980-02-02', '123456789@123.com', NULL),
('admin', 'admin', 2, '男', '2000-01-01', 'vincent@aspi.tech', 'https://incentancy-1253813002.cos.ap-guangzhou.myqcloud.com/bg7r-fyrpeie1413753.jpg');

--
-- 触发器 `teacher`
--
DELIMITER $$
CREATE TRIGGER `T_DeleteTeacher_1` BEFORE DELETE ON `teacher` FOR EACH ROW DELETE FROM sims_user WHERE user_id = old.teacher_id AND user_level = 2
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `T_DeleteTeacher_2` BEFORE DELETE ON `teacher` FOR EACH ROW DELETE FROM teach WHERE teacher_id=old.teacher_id
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `T_InsertIntoTeacher` AFTER INSERT ON `teacher` FOR EACH ROW INSERT INTO sims_user VALUES(new.teacher_id, '000000', 2)
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
  ADD KEY `FK_Reference_12` (`student_id`),
  ADD KEY `FK_Reference_8` (`subject_id`);

--
-- Indexes for table `sims_user`
--
ALTER TABLE `sims_user`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`student_id`),
  ADD KEY `FK_study_in` (`major_id`);

--
-- Indexes for table `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`subject_id`),
  ADD KEY `FK_major_subject` (`major_id`);

--
-- Indexes for table `teach`
--
ALTER TABLE `teach`
  ADD KEY `FK_teach` (`teacher_id`),
  ADD KEY `FK_teach_subject` (`sub_id`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`teacher_id`),
  ADD KEY `FK_work_at` (`college_id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `bulletin`
--
ALTER TABLE `bulletin`
  MODIFY `bull_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- 使用表AUTO_INCREMENT `college`
--
ALTER TABLE `college`
  MODIFY `college_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '学院号', AUTO_INCREMENT=3;
--
-- 使用表AUTO_INCREMENT `major`
--
ALTER TABLE `major`
  MODIFY `major_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- 使用表AUTO_INCREMENT `score`
--
ALTER TABLE `score`
  MODIFY `score_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- 使用表AUTO_INCREMENT `subject`
--
ALTER TABLE `subject`
  MODIFY `subject_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- 限制导出的表
--

--
-- 限制表 `bulletin`
--
ALTER TABLE `bulletin`
  ADD CONSTRAINT `FK_user_bulletin` FOREIGN KEY (`user_id`) REFERENCES `sims_user` (`user_id`) ON UPDATE CASCADE;

--
-- 限制表 `major`
--
ALTER TABLE `major`
  ADD CONSTRAINT `FK_setup` FOREIGN KEY (`college_id`) REFERENCES `college` (`college_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `score`
--
ALTER TABLE `score`
  ADD CONSTRAINT `FK_Reference_12` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_Reference_8` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `FK_study_in` FOREIGN KEY (`major_id`) REFERENCES `major` (`major_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `FK_major_subject` FOREIGN KEY (`major_id`) REFERENCES `major` (`major_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `teach`
--
ALTER TABLE `teach`
  ADD CONSTRAINT `FK_teach` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_teach_subject` FOREIGN KEY (`sub_id`) REFERENCES `subject` (`subject_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- 限制表 `teacher`
--
ALTER TABLE `teacher`
  ADD CONSTRAINT `FK_work_at` FOREIGN KEY (`college_id`) REFERENCES `college` (`college_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
