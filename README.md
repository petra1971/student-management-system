# student-management-system
This application is managing information about students. 
It's built by Petra Andréasson.

End point descriptions

CreateStudent:
POST http://localhost:8080/student-management-system/api/v1/students

Json body ex:
{
	"firstName" : "Förnamn",
	"lastName": "Efternamn",
	"email": "namn@email.se",
	"phoneNumber": "08-6587976"
}
Note! firstName, lastName and email are required.

GetStudents:
GET http://localhost:8080/student-management-system/api/v1/students

GetStudentById:
GET http://localhost:8080/student-management-system/api/v1/students/{id}
(replace {id} in the url with the id you are searching for)

GetStudentsByLastName: 
GET http://localhost:8080/student-management-system/api/v1/students/getbylastname?lastName={lastName}
(replace {lastName} in the url with the name you are searching for)

UpdateStudent: 
PUT http://localhost:8080/student-management-system/api/v1/students

Json body ex:
{
  "id": 1,
	"firstName" : "Förnamn",
	"lastName": "Efternamn",
	"email": "namn@email.se",
	"phoneNumber": "08-6587976"
}
Note! firstName, lastName and email are required.

ModifyStudent:
PATCH http://localhost:8080/student-management-system/api/v1/students/{id} 
(replace {id} in the url with the id you are searching for)
Note! Only the fields that are modified needs to be in the json body.

Json body ex:
{
	"email": "newemail@email.se"
}
