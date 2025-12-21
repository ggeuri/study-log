/*상품 이미지를 선택하면, 해당 상품이미지를 화면에 미리보기 기능 구현을 위한 클래스 정의*/
class PreviewImg {
	constructor(container, file, src, width, height) {
		this.container = container;
		this.file = file;
		this.src = src;
		this.width = width;
		this.height = height;

		this.wrapper = document.createElement("div");
		this.header = document.createElement("div");
		this.img = document.createElement("img");
		this.img.src = this.src;

		//style
		this.img.style.width = this.width + "px";
		this.img.style.height = this.height + "px";
		this.wrapper.style.width = (this.width + 10) + "px";
		this.wrapper.style.height = (this.height + 30) + "px";
		this.wrapper.style.display = "inline-block";
		this.wrapper.style.margin = 5 + "px";
		this.wrapper.style.border = "1px solid red";
		this.wrapper.style.borderRadius = "5px";
		this.wrapper.style.textAlign = "center";

		this.header.innerHTML = "<a href='#'>X</a>";
		this.header.style.textAlign = "right";

		//조립
		this.wrapper.appendChild(this.header);
		this.wrapper.appendChild(this.img);
		this.container.appendChild(this.wrapper);

		//x자에 이벤트 연결
		this.header.addEventListener("click", (e) => {
			console.log("지워야하는데에에에");
			//링크누를때마다 스크롤이 올라오는데 이거 a태그가 기본적으로 가진 y축 0으로 위치시키는 특징임. 해결? 그 특징 제거 
			e.preventDefault();
			this.remove();
		});
	}

	remove() {
		this.container.removeChild(this.wrapper);
		selectedFile.splice(1,selectedFile.indexOf(this.file));
		//화면제거뿐만아니라 원본배열도 지워져야된다 
	}
}
window.PreviewImg = PreviewImg;		