# 2025-11-24 리눅스 서버 환경 구축 + JS Bullet(OOP) 복습

**JavaScript Bullet 클래스(OOP 복습)**  
**Ubuntu 서버에서 JDK · Tomcat · MySQL · FTP 환경 구성**

---

## 1. JavaScript Bullet 클래스 – ES6 OOP & DOM

### 1-1. 오늘 작성한 기본 구조

```js
class Bullet {
    constructor() {
        this.x = 0;                                // 인스턴스 변수 (전역변수 대신)
        this.div = document.createElement("div");  // DOM 생성

        this.div.style.width = "30px";
        this.div.style.height = "30px";
        this.div.style.backgroundColor = "red";
        this.div.style.borderRadius = "50%";
        this.div.style.position = "absolute";
        this.div.style.left = "0px";
        this.div.style.top = "300px";

        this.stage = document.getElementById("stage");
        this.stage.appendChild(this.div);

        // 일정 시간마다 move() 반복 호출
        setInterval(() => {
            this.move();
        }, 10);
    }

    move() {
        this.x += 10;
        this.div.style.left = this.x + "px";
    }
}

// 키보드 이벤트
window.addEventListener("keydown", function (e) {
    if (e.keyCode == 32) {  // 스페이스 바
        new Bullet();
    }
});
```

1-2. 핵심 개념 정리
	1.	ES6 class / constructor / method
	•	class Bullet { constructor() {...} move() {...} }
	•	constructor() : new Bullet() 할 때 자동으로 호출되는 초기화 메서드
	•	move() : Bullet 객체의 “행동”을 정의하는 메서드
	2.	전역변수 대신 인스턴스 변수 사용
	•	this.x = 0;
	•	JS도 자바처럼, 객체가 살아 있는 동안 this.x 값이 유지된다.
	•	파일 상단에 let x 같은 전역변수를 두지 않고, 각 Bullet 인스턴스가 자신의 x를 가지도록 설계.
	3.	DOM 생성 & 스타일링
	•	document.createElement("div") → <div></div>와 1:1 대응되는 DOM 객체 생성
	•	this.stage.appendChild(this.div) → 스테이지 영역에 총알 div를 붙임
	•	CSS는 JS에서 직접 조작:
	•	부모: position: relative
	•	자식: position: absolute
	•	→ absolute인 자식의 좌표 기준이 부모가 됨
	4.	setInterval + 화살표 함수(=>) + this 바인딩
	•	setInterval(() => { this.move(); }, 10);
	•	화살표 함수는 외부의 this를 그대로 유지 → 여기서 this는 Bullet 인스턴스
	•	만약 function(){ this.move(); }를 썼다면 this가 window를 가리켜 오류 나기 쉬운 포인트
	5.	키보드 이벤트
	•	window.addEventListener("keydown", ...)
	•	e.keyCode == 32 → 스페이스바
	•	스페이스를 누를 때마다 new Bullet() → 총알이 여러 개 생성되는 구조

1-3. 놓치기 쉬운 부분 메모
	•	position: relative는 absolute 자식들의 기준점을 주기 위해 #stage에 설정.
	•	this.x가 안 사라지는 이유는 “전역변수여서”가 아니라, 객체 인스턴스가 메모리에 살아 있는 동안 인스턴스 변수로 유지되기 때문.
	•	Mac에서도 keydown은 기본적으로 동일하게 동작하지만, 브라우저 포커스가 바깥에 있으면 이벤트가 안 들어올 수 있음.

1-4. 내일 Bullet 예제 확장 TODO
	1.	총알이 화면 밖으로 나가면 제거
	•	예: this.x가 1000 이상이면 this.stage.removeChild(this.div);
	•	필요하면 clearInterval도 고려
	2.	여러 Bullet을 배열로 관리
	•	let bullets = [];
	•	new Bullet() 할 때 bullets.push(bullet);
	•	나중에 제거/충돌 판정 등을 위해 배열 관리 연습

⸻

2. Ubuntu 리눅스 기본 흐름 정리

오늘 리눅스 작업의 전체 흐름:
	1.	기본 패키지 업데이트
	2.	OpenSSH 서버 설치 + 방화벽 설정
	3.	JDK를 /opt에 직접 설치하고 환경변수 등록
	4.	Tomcat을 /opt에 설치 + 권한 변경 + 포트 오픈
	5.	MySQL을 /opt에 tar로 설치 + data 디렉토리 + libaio 설정 + 초기화
	6.	vsftpd(FTP 서버) 설치 + 설정 + FileZilla(SFTP)로 접속

2-1. 패키지 관리 & SSH & 방화벽

apt update / upgrade

sudo apt update && sudo apt upgrade -y

	•	apt update : 패키지 목록 최신화 (새 버전 정보 가져오기)
	•	apt upgrade : 설치된 패키지들을 새 버전으로 업그레이드
	•	-y : 중간 질문에 자동으로 “yes”

백엔드 서버 만들 때, OS 설치 직후 가장 먼저 하는 기본 작업.

OpenSSH 서버 설치 & 상태 확인

sudo apt install openssh-server
sudo systemctl status ssh
sudo systemctl start ssh

	•	VirtualBox 콘솔보다 Mac 터미널에서 SSH 접속하는 편이 훨씬 편함.
	•	hostname -I → 리눅스 서버 IP 확인
	•	ssh vboxuser@<서버IP> 로 접속

ufw(방화벽) 설정

sudo ufw enable
sudo ufw allow 22/tcp
sudo ufw reload
sudo ufw status

	•	enable : 방화벽 켜기
	•	allow 22/tcp : SSH 포트 허용
	•	나중에 Tomcat(8080), FTP(21)도 여기서 허용 예정

실무에서도 “새 서버 → SSH/HTTP/HTTPS 등 필요한 포트만 열고 나머지는 닫기”가 기본.

⸻

3. JDK 8 수동 설치 (/opt + 환경변수)

3-1. 왜 /opt 에 설치?
	•	/home/사용자 : 각 사용자의 개인 영역
	•	/opt : apt 같은 패키지 관리 도구가 아닌, 수동 설치 프로그램용 디렉토리
	•	여러 사용자가 공통으로 사용 가능
	•	JDK 버전 바꿀 때 관리하기 편함

현업에서도 JDK는 apt install보다 /opt에 원하는 버전 고정 설치를 많이 사용 (빌드 환경 통제용).

3-2. 설치 흐름

cd /opt
sudo wget https://download.java.net/openjdk/jdk8u44/ri/openjdk-8u44-linux-x64.tar.gz
sudo tar -xvf openjdk-8u44-linux-x64.tar.gz
sudo rm openjdk-8u44-linux-x64.tar.gz
sudo mv jdk1.8* jdk8   # 디렉토리 이름 단순화

3-3. 환경변수 등록 (/etc/profile.d/jdk8.sh)

cd /etc/profile.d
sudo vim jdk8.sh

# jdk8.sh 내용
export JAVA_HOME=/opt/jdk8
export PATH=$PATH:$JAVA_HOME/bin

	•	전체 사용자에게 공통으로 적용되는 전역 환경변수 설정 방식.
	•	수정 후:

source /etc/profile.d/jdk8.sh
java -version


⸻

4. Tomcat 9 수동 설치 & 권한

4-1. 설치

cd /opt
sudo wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.112/bin/apache-tomcat-9.0.112.tar.gz
sudo tar -xvf apache-tomcat-9.0.112.tar.gz
sudo rm apache-tomcat-9.0.112.tar.gz
sudo mv apache-tomcat-9.0.112 tomcat9

4-2. 환경변수 (/etc/profile.d/tomcat9.sh)

export CATALINA_HOME=/opt/tomcat9
export PATH=$PATH:$CATALINA_HOME/bin

4-3. 권한 설정

sudo chown -R vboxuser:vboxuser /opt/tomcat9
sudo chmod -R 755 /opt/tomcat9

	•	vboxuser 계정이 Tomcat 디렉토리 안 파일을 실행·수정할 수 있게 소유자/권한 설정.

4-4. 가동 & 방화벽

/opt/tomcat9/bin/startup.sh
sudo ufw allow 8080/tcp
sudo ufw reload

나중에는 systemd 서비스로 등록해서 부팅 시 자동으로 올라가게 만드는 게 실무 패턴.
오늘은 “스크립트 직접 실행해서 동작 구조 이해하기” 단계.

⸻

5. MySQL 8 tar 설치 + libaio 문제

5-1. 설치 & 디렉토리 구조

cd /opt
sudo wget https://dev.mysql.com/get/Downloads/MySQL-8.0/mysql-8.0.44-linux-glibc2.28-aarch64.tar.xz
sudo tar -xvf mysql-8.0.44-linux-glibc2.28-aarch64.tar.xz
sudo rm mysql-8.0.44-linux-glibc2.28-aarch64.tar.xz
sudo mv mysql-8.0.44-linux-glibc2.28-aarch64 mysql8

sudo mkdir -p /opt/mysql8/data

5-2. 환경변수 (/etc/profile.d/mysql8.sh)

export MYSQL_HOME=/opt/mysql8
export PATH=$PATH:$MYSQL_HOME/bin

5-3. libaio 관련 에러 정리
	•	MySQL 서버(mysqld)는 **libaio(비동기 I/O 라이브러리)**에 의존.
	•	ARM(Ubuntu on M1/M2) 환경이라 경로가 /lib/aarch64-linux-gnu 등에 있고,
공식 문서 예시(x86_64 기준)와 달라서 에러 발생.

해결 흐름

# 1) 현재 파일 상태 확인
ls -l /lib/aarch64-linux-gnu/libaio*
ls -l /usr/lib/aarch64-linux-gnu/libaio*

# 2) 필요한 위치에 심볼릭 링크 생성
sudo ln -s /lib/aarch64-linux-gnu/libaio.so.1t64  /lib/aarch64-linux-gnu/libaio.so.1
sudo ln -s /usr/lib/aarch64-linux-gnu/libaio.so.1t64 /usr/lib/aarch64-linux-gnu/libaio.so.1

# 3) 라이브러리 캐시 갱신
sudo ldconfig

	•	sudo apt install libaio1t64도 설치 완료한 상태.

5-4. 권한 및 data 디렉토리 재생성

필기 중 vboxuer 오타 → 실제는 vboxuser.

sudo chown -R vboxuser:vboxuser /opt/mysql8
sudo chmod -R 755 /opt/mysql8

sudo rm -rf /opt/mysql8/data
mkdir -p /opt/mysql8/data
ls -ld /opt/mysql8/data   # vboxuser vboxuser 확인

5-5. 데이터 디렉토리 초기화 (중요)

cd /opt/mysql8
./bin/mysqld --initialize \
  --basedir=/opt/mysql8 \
  --datadir=/opt/mysql8/data

	•	이 단계에서:
	•	MySQL 시스템 테이블 생성
	•	root@localhost 임시 비밀번호 생성
	•	로그 예시:

[Server] A temporary password is generated for root@localhost: hIQj1wf%%nR-

→ 이 비밀번호로 나중에 mysql -u root -p 접속.

아직 mysqld_safe나 systemd로 서비스 등록은 하지 않았고,
설치 + 초기화까지 완료된 상태.

⸻

6. vsftpd(FTP 서버) & FileZilla(SFTP)

6-1. 설치 & 백업

sudo apt install -y vsftpd
sudo cp /etc/vsftpd.conf /etc/vsftpd.conf.backup
sudo vim /etc/vsftpd.conf

6-2. 주요 설정 옵션 (주석 해제)
	1.	local_enable=YES
→ 리눅스 로컬 계정(vboxuser 등)에 FTP 로그인 허용
	2.	write_enable=YES
→ 업로드/삭제 등 쓰기 허용
	3.	chroot_local_user=YES
→ 사용자를 자기 홈 디렉토리 안에 가둬 상위 디렉토리 접근 제한

6-3. 서비스 재시작 & 부팅 자동 실행

sudo systemctl restart vsftpd
sudo systemctl enable vsftpd

6-4. 업로드 디렉토리 준비

mkdir -p /home/vboxuser/mysite
sudo chmod 755 /home/vboxuser

	•	/home/vboxuser 소유자는 이미 vboxuser이므로 따로 chown은 필요 없음.

6-5. 방화벽 & 접속

sudo ufw allow 21/tcp
sudo ufw reload
sudo ufw status

	•	FileZilla에서는 SFTP로 접속 (보안성 고려):
	•	프로토콜: SFTP
	•	호스트: 리눅스 IP
	•	포트: 22
	•	사용자: vboxuser

실무에서는 평문 FTP(21) 대신 SFTP(SSH 기반) 또는 FTPS(SSL/TLS) 사용이 거의 필수.

⸻
