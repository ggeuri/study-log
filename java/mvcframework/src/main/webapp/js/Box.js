// js의 2015년 버전인 ES6부터는 클래스 지원됨. 다이어리 이루는 셀을 클래스로 정의 + 재사용 
class Box {
    //new Box(document.querySelector(".content"),250,300,100,100,"black");
    constructor(container, x, y, width, height, bg, msg) {
        this.container = container;
        this.div = document.createElement("div");
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bg = bg;
        this.msg = msg;
        this.dd; //박스가 보유할 날짜 (printNum함수로 반복문돌때 주입받음)

        // 스타일 적용 
        this.div.style.position = "absolute";
        this.div.style.left = this.x + "px";
        this.div.style.top = this.y + "px";

        this.div.style.width = this.width + "px";
        this.div.style.height = this.height + "px";

        this.div.style.backgroundColor = this.bg;

        // this.div.style.borderRadius = "10px";
        this.div.style.border = "1px solid gray";
        this.div.style.textAlign = "center";
        this.div.style.lineHeight = "50px";
        this.div.innerText = this.msg;


        this.container.appendChild(this.div);

        //이벤트 연결 
        this.div.addEventListener("mouseout", () => {
            //화살표함수에서의 this는 상위 스코프를 말함. 객체(box임)
            this.div.style.background = "";
        });

        this.div.addEventListener("click", () => {
            alert(
                currentDate.getFullYear() + "년 " + (currentDate.getMonth() + 1) + "월" + this.dd +"일입니다."
            );
        });


    }

    setMsg(msg) {
        this.div.innerText = msg;
    }
    setDate(dd) {
        this.dd = dd;
    }

}