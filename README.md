# HDFC Learning Academy Course Enrollment API

Spring Boot REST API for managing training courses and employee enrollments. Data is stored in memory using `Map<Integer, Course>` and `Map<Integer, Enrollment>`; there is no database, JPA, or JDBC.

## Requirements

- Java 25 LTS
- Maven 3.9+

## Run

```bash
mvn spring-boot:run
```

The API starts on `http://localhost:8080`.

## API Summary

### Courses

- `POST /courses` create a course
- `GET /courses` list courses
- `GET /courses/{id}` get a course
- `PUT /courses/{id}` update a course
- `DELETE /courses/{id}` delete a course
- `GET /courses/trainer/{trainerName}` search by trainer
- `GET /courses/fees/{amount}` find courses below a fee

Course requests require non-blank names and positive duration, capacity, and fees.

### Enrollments

- `POST /enrollments` enroll an employee
- `GET /enrollments` list enrollments
- `GET /enrollments/{id}` get an enrollment
- `PUT /enrollments/{id}/cancel` cancel an enrollment
- `PUT /enrollments/{id}/complete` mark an enrollment completed
- `GET /enrollments/status/{status}` filter by `ENROLLED`, `COMPLETED`, or `CANCELLED`
- `GET /enrollments/employee/{employeeId}` filter by employee

Enrollment creation rejects missing courses, duplicate active enrollments, and full courses.

### Analytics

- `GET /analytics/course-count`
- `GET /analytics/enrollment-count`
- `GET /analytics/most-popular-course`

Analytics are implemented with Java Streams.

## Example Requests

Create a course:

```json
{
  "courseName": "Java",
  "trainerName": "Anita",
  "durationInDays": 5,
  "maxCapacity": 3,
  "fees": 1000
}
```

Enroll an employee:

```json
{
  "employeeId": 101,
  "employeeName": "Ratan",
  "courseId": 1
}
```

## Verification

```bash
mvn clean test
mvn clean verify
```

The project includes an end-to-end MockMvc test for course creation, enrollment, duplicate and capacity rules, status filtering, and analytics.

## Package Structure

```text
com.hdfc
├── controller
├── service
├── repository
├── dto
├── entity
├── mapper
├── exception
└── CourseEnrollmentApplication
```

A ready-to-import request collection is in `postman/HDFC-Learning-Academy.postman_collection.json`.
