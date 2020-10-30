# Student/Course/timetable records

# version 1.0:  
1.only did add/get two methods. POST/GET    
2.used jdbctemplate to access mysql database.  
3.three tables are course，student，timetable.       
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
      
5.student/course records are added and displayed by Json format.
6.mysql port:3306 user:root password:

# version 2.0: 
1.implements delete and update two more features including delete single record, delete all records,update single value,update some values. DELETE/PUT    
2.improves the MVC structure, much more reduce the code couplings and improve the code cohesions.     
3.improves data integrity by adding locks to all add,updata,delete operations.      
4.changes output to English.

# version 3.0:  
1.replaced jdbc with data jpa,which will automatically create tables,some feature used own sql sentences or query methods .    
2.improved code similicity with lombok anotations.     
3.added some atributes in some tables
4.table_student_course table's primary key now is a composite primary key with student id and course id from those tables.     



