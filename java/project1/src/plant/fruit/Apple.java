package  plant.fruit; 
public class Apple{
	public String name="사과";
}



Docker로 만든 DB를 sqlplus로 접속하고싶어 


Docker desktop 로그나 inspect 전달해주면 경로, 아이디, 비밀번호, 포트 등등 이 DB를 사용하기위한 필수 정보 정리해줄 수 있어? 

Oracle이 맥 지원안한다고해서 이걸 Docker에 깐거였는데 오늘 내가 했던 것 복기중이야. 맞는지 봐줘.
[터미널]
docker pull gvenzl/oracle-xe 

docker run -d \
  -p 1521:1521 -p 5500:5500 \
  -e ORACLE_PASSWORD=Oracle1234 \
  --name oracle-xe gvenzl/oracle-xe

[DBeaver]
새 커넥션 연결(Oracle) 
Host: localhost
Port: 1521 
Database: XEPDB1
Username: system
password: Oracle1234!

Host: localhost
Port: 1521 
SID: XE
Username: system
password: Oracle1234! 

SID:XE도 되고 Database:XEPDB1 도 됨. 실무상 XEPDB1 추천한다고 함. 

[터미널 접속]
docker exec -it oracle-xe \ sqlplus 'system/"Oracle1234!"@//localhost:1521/XEPDB1'

// 자꾸 지맘대로 XE로 접속되기 때문에  
// SELECT SYS_CONTEXT('USERENV','CON_NAME') FROM DUAL;
// ALTER SESSION SET CONTAINER = XEPDB1; 해보는게 좋다 

[테이블만들기]
CREATE TABLESPACE space1112
DATAFILE '/opt/oracle/oradata/XE/space1112.dbf'

CREATE USER java IDENTIFIED BY 1234
DEFAULT TABLESPACE space1112
QUOTA UNLIMITED ON space1112;
SIZE 3M;

GRANT CREATE SESSION, CREATE TABLE, CREATE PROCEDURE, CREATE SEQUENCE, CREATE VIEW TO java;

[java 계정으로 변경]
// docker exec -it oracle-xe sqlplus 'java/"1234"@//localhost:1521/XEPDB1' 이건 최초 접속
// CONNECT system/"Oracle1234!"@//localhost:1521/XEPDB1 
CONNECT java/1234@//localhost:1521/XEPDB1


CREATE TABLE student (
    student_id NUMBER PRIMARY KEY,
    id VARCHAR2(20),
    pwd VARCHAR2(20),
    name VARCHAR2(20)
);

CREATE SEQUENCE seq_student 
INCREMENT BY 1 
START WITH 1 ; 

INSERT INTO student(student_id, id, pwd, name)
values(seq_student.nextval, 'sc','1234','ADAMS');


