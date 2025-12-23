<%@page import="com.ch.shop.dto.Product"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ch.shop.dto.TopCategory"%>
<%
List<Product> productList=(List)request.getAttribute("productList");

%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AdminLTE 3 | Dashboard</title>

<%@ include file="../inc/head_link.jsp"%>
</head>

<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">

		<!-- Preloader -->
		<%@ include file="../inc/preloader.jsp"%>

		<!-- Navbar -->
		<%@ include file="../inc/navbar.jsp"%>

		<!-- Main Sidebar Container -->
		<%@ include file="../inc/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">

			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">

						<div class="col-sm-6">
							<h1 class="m-0">상품등록</h1>
						</div>

						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#">Home</a></li>
								<li class="breadcrumb-item active">상품관리</li>
							</ol>
						</div>

					</div>
				</div>
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">

					<!-- 메인컨텐츠 시작 -->
					<div class="row">
						<div class="col-md-12">

							<div class="card card-info">
								<div class="card-header">
									<h3 class="card-title">상품등록하기</h3>
								</div>

								<!-- form start -->
								<form id="product-form">
									<div class="card-body">

										<!-- 카테고리 -->
										<div class="form-group row">
											<div class="col-md-6">
												<select class="form-control" name="topcategory">
													<!-- JS로 채워짐 -->
												</select>
											</div>

											<div class="col-md-6">
												<!-- 강사님 기준: subcategory -->
												<select class="form-control" name="subcategory">
													<!-- JS로 채워짐 -->
												</select>
											</div>
										</div>

										<!-- 상품 기본정보 -->
										<div class="form-group">
											<input type="text" class="form-control" name="product_name" placeholder="상품명">
										</div>

										<div class="form-group">
											<input type="text" class="form-control" name="brand" placeholder="브랜드">
										</div>

										<div class="form-group">
											<input type="number" class="form-control" name="price" placeholder="가격">
										</div>

										<div class="form-group">
											<input type="number" class="form-control" name="discount" placeholder="할인가">
										</div>

										<div class="form-group">
											<div class="form-group row">
												<div class="col-md-6">
													<!-- 강사님 기준: color -->
													<select multiple class="form-control" name="color">
														<!-- JS로 채워짐 -->
													</select>
												</div>

												<div class="col-md-6">
													<!-- 강사님 기준: size -->
													<select multiple class="form-control" name="size">
														<!-- JS로 채워짐 -->
													</select>
												</div>
											</div>
										</div>

										<div class="form-group">
											<input type="text" class="form-control" name="introduce" placeholder="간단소개">
										</div>

										<!-- 상세내용 (Summernote) -->
										<div class="form-group">
											<textarea id="summernote" class="form-control" name="detail" placeholder="상품상세" style="width: 100%"></textarea>
										</div>

										<!-- 이미지 업로드 -->
										<div class="form-group">
											<div class="input-group">
												<div class="custom-file">
													<input type="file" class="custom-file-input" id="product-img" multiple>
													<!-- ✅ 라벨 for를 product-img로 정확히 -->
													<label class="custom-file-label" for="product-img">Choose file</label>
												</div>

												<div class="input-group-append">
													<span class="input-group-text">Upload</span>
												</div>
											</div>
										</div>

										<!-- 이미지 미리보기 영역 -->
										<div class="form-group row">
											<div class="col-md-12" id="product-preview"></div>
										</div>

									</div>
									<!-- /.card-body -->

									<div class="card-footer">
										<button type="button" class="btn btn-info">Submit</button>
										<button type="button" class="btn btn-info">상품 목록</button>
									</div>
								</form>
							</div>
							<!-- /.card -->

						</div>
					</div>
					<!-- /.row -->

				</div>
			</section>
			<!-- /.content -->

		</div>
		<!-- /.content-wrapper -->

		<!-- footer -->
		<%@ include file="../inc/footer.jsp"%>

		<!-- Control Sidebar -->
		<%@ include file="../inc/control_sidebar.jsp"%>

	</div>
	<!-- ./wrapper -->

	<!-- footer js -->
	<%@ include file="../inc/footer_link.jsp"%>

	<script src="/static/adminlte/custom/js/PreviewImg.js"></script>

	<script>
	let selectedFile;

	function printCategory(title, category, list){
		let tag="<option value='0'>"+title+"</option>";

		for(let i=0;i<list.length; i++){
			if(category=="topcategory"){
				tag+="<option value='"+list[i].topcategory_id+"'>"+list[i].topname+"</option>";
			}else if(category=="subcategory"){
				tag+="<option value='"+list[i].subcategory_id+"'>"+list[i].subname+"</option>";
			}else if(category=="color"){
				tag+="<option value='"+list[i].color_id+"'>"+list[i].color_name+"</option>";
			}else if(category=="size"){
				tag+="<option value='"+list[i].size_id+"'>"+list[i].size_name+"</option>";
			}
		}
		$("select[name='"+category+"']").html(tag);
	}

	function getTopCategory(){
		$.ajax({
			url:"/admin/topcategory/list",
			method:"GET",
			success:function(result){
				printCategory("상위카테고리 선택","topcategory", result);
			}
		});
	}

	function getSubCategory(){
		$.ajax({
			url:"/admin/subcategory/list?topcategory_id="+$("select[name='topcategory']").val(),
			method:"GET",
			success:function(result){
				printCategory("하위카테고리 선택","subcategory", result);
			}
		});
	}

	function preview(imgList){
		// ✅ 강사님 스타일: 바꿀 때 누적 안 되게 비우기
		$("#product-preview").empty();

		selectedFile = Array.from(imgList);

		for(let i=0;i<selectedFile.length;i++){
			let reader = new FileReader();
			reader.onload=function(e){
				new PreviewImg(
					document.getElementById("product-preview"),
					selectedFile[i],
					e.target.result,
					100,100
				);
			}
			// ✅ selectedFile 기준으로 읽기
			reader.readAsDataURL(selectedFile[i]);
		}
	}

	function getColorList(){
		$.ajax({
			url:"/admin/color/list",
			method:"GET",
			success:function(result){
				printCategory("색상 선택","color", result);
			}
		});
	}

	function getSizeList(){
		$.ajax({
			url:"/admin/size/list",
			method:"GET",
			success:function(result){
				printCategory("사이즈 선택","size", result);
			}
		});
	}

	function regist(){
		let formData = new FormData(document.getElementById("product-form"));

		formData.delete("photo");

		// 사진 안 골라도 터지지 않게
		if(selectedFile){
			for(let i=0; i<selectedFile.length;i++){
				formData.append("photo", selectedFile[i]);
			}
		}

		// ✅ 제일 중요: Product.subCategory에 하위카테고리 id를 “중첩 바인딩”으로 넣기
		formData.append("subCategory.subcategory_id", $("select[name='subcategory']").val());

		$.ajax({
			url:"/admin/product/regist",
			method:"POST",
			data: formData,
			processData:false,
			contentType:false,
			//서버응답코드가200일경우 아래 콜백함수 동작
			success:function(result,status,xhr){
				alert(result.message);
			},
			
			//서버측에서 응답한 응답 코드가 에러인 경우 (300이상 아래의 속성에 명시한 콜백함수 호출)
			error:function(xhr,status,err){
				let obj = JSON.parse(xhr.responseText);
				alert(obj.message);
			}
		});
	}

	$(function(){
		$("#summernote").summernote();

		getTopCategory();

		$("select[name='topcategory']").change(function(){
			getSubCategory();
		});

		getColorList();
		getSizeList();

		$("#product-img").change(function(e){
			preview(e.target.files);
		});

		$($(".card-footer button")[0]).click(function(){
			regist();
		});
		$($(".card-footer button")[1]).click(function(){
			location.href="/admin/product/list";
		});
	});
	</script>
</body>
</html>