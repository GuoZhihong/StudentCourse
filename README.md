# demo

1.因为时间有限只做了要求的增查两种 没做改删   
2.用的是jdbctemplate来访问的mysql数据库       
3.数据库三个表分别是course，student，timetable       
4.DDL:      


      CREATE TABLE `course` (
        `cid` int(11) NOT NULL,
        `cName` varchar(45) NOT NULL,
        PRIMARY KEY (`cid`),
        UNIQUE KEY `cid_UNIQUE` (`cid`),
        UNIQUE KEY `cName_UNIQUE` (`cName`)
      ) ENGINE=MyISAM DEFAULT CHARSET=latin1

      CREATE TABLE `student` (
        `sid` int(11) NOT NULL,
        `sName` varchar(50) NOT NULL,
        `sSex` varchar(10) NOT NULL,
        `sAge` int(11) NOT NULL,
        PRIMARY KEY (`sid`),
        UNIQUE KEY `sid_UNIQUE` (`sid`)
      ) ENGINE=MyISAM DEFAULT CHARSET=latin1

      CREATE TABLE `timetable` (
        `id` int(11) NOT NULL AUTO_INCREMENT,
        `sid` int(11) NOT NULL,
        `cid` int(11) NOT NULL,
        PRIMARY KEY (`id`)
      ) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=latin1
      
5.理论上timetable的主键应该是sid 和 cid的联合键，我用的是自增长表id。 
