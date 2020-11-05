# Student/Course/timetable records
Implemented restful api for get/post/put/delete 4 methods, the passing data format is JSON. 
The user can search for a student who is taking what courses and its information by student Id, or a course is taking by whom by course id, or search for all students/courses/timetables' records.         
Add feature allows the user to add student/course/timetable records.  
Delete feature allows the user to delete single row of records or wipe out all records.     
Put feature allows the user to update records by Its relevant Id. 

# version 1.0/2.0 uses jdbctemplate can refer to branch master
https://github.com/GuoZhihong/StudentCourse/tree/master  

# version 3.0:  
1.replaced jdbc with spring data jpa,which will automatically create tables,some feature used own sql sentences or query methods .    
2.improved code similicity with lombok anotations.     
3.added some atributes in some tables.      
4.table_student_course table's primary key now is a composite primary key with student id and course id from those tables.     
5.table_student_course table now only can update time atribute because its student id and course id now is a composite primary key from table student/course.



