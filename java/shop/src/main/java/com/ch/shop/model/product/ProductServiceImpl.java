package com.ch.shop.model.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.multipart.MultipartFile;

import com.ch.shop.dto.Product;
import com.ch.shop.dto.ProductColor;
import com.ch.shop.dto.ProductImg;
import com.ch.shop.dto.ProductSize;
import com.ch.shop.dto.Size;
import com.ch.shop.exception.ProductException;
import com.ch.shop.util.FileManager;

import lombok.extern.slf4j.Slf4j;

/*
Service는 "업무 흐름(Use Case)"을 담당한다.
- Controller는 요청/응답, Service는 비즈니스 로직
- 여러 DAO 호출을 묶어 트랜잭션 commit/rollback을 결정한다.

예) 상품등록: product/img/size/color 중 하나라도 실패하면 전체 rollback(데이터 불일치 방지)
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private ProductColorDAO productColorDAO;
	@Autowired
	private ProductSizeDAO productSizeDAO;
	@Autowired
	private ProductImgDAO productImgDAO;
	@Autowired
	private FileManager fileManager;
	
	//쇼핑몰 상품 등록될 외부 저장소의 루트 경로. 앞으로 상품 등록되면 pk값따와서 디렉토리 생성하고 그 안에 파일 배치 
	private String rootDir = "/Users/rimu/shopdata/product/";

	@Override
	@Transactional
	public void regist(Product product) throws ProductException{
		log.debug("service proxy class = {}", this.getClass());
		log.debug("TX active? {}", TransactionSynchronizationManager.isActualTransactionActive());
		
		//세부업무 1 product테이블에 insert 

		log.debug("생성된 product_id = " + product.getProduct_id()); 
		productDAO.insert(product);
		log.debug("생성된 product_id = " + product.getProduct_id());  
		
		//세부업무 2 
		for(int i = 0 ; i < product.getColorList().size(); i++) {
			ProductColor productColor = new ProductColor();
			productColor.setProduct(product);
			productColor.setColor(product.getColorList().get(i));
			productColorDAO.insert(productColor);			
		}
		
		//세부업무 3향상된 포문으로하면 for(Color color : product.getColorList())
		for(Size size : product.getSizeList()) {
			ProductSize productSize = new ProductSize();
			productSize.setProduct(product);
			productSize.setSize(size);
			productSizeDAO.insert(productSize);
		}
		
		//세부업무 4 파일저장(트랜잭션 대상아니지만 크게 보면 등록업무 일부 )
		//파일 수가 여러개일 경우 롤백처리되지만 파일에 대해서는 스프링이 관여하지않음. 따라서 파일찌꺼기가 남음 
		//해결책. 개발자가 트랜잭션실패시 파일직접제거 -> 디렉토리 제거하면됨 
		String dirName = rootDir+"p"+product.getProduct_id();
		fileManager.makeDirectory(dirName);
//		
//		for(int i = 0 ; i <product.getPhoto().length ; i++) {
//			long time = System.currentTimeMillis();
//			String filename = time+"."+ fileManager.getExtend(product.getPhoto()[i].getOriginalFilename());
//			fileManager.save(product.getPhoto()[i], dirName, time+"."+extend);
//		}
		
		for(MultipartFile mf : product.getPhoto()) {
			long time = System.currentTimeMillis();
			String filename = time+"."+fileManager.getExtend(mf.getOriginalFilename());
			fileManager.save(mf, dirName, filename);
			
			ProductImg productImg = new ProductImg();
			productImg.setFilename(filename);
			productImg.setProduct(product);
			productImgDAO.insert(productImg);
		}
		
	}

	@Override
	public void cancelUpload(Product product) {
		//모든 OS에서는 디렉토리 안에 파일 존재할 경우 바로 디렉토리 삭제 금지 따라서 파일부터 지워 
		String dirName = rootDir+"p"+product.getProduct_id();
		fileManager.remove(dirName);
		
	}

	@Override
	public List<Product> getList() {
		return productDAO.selectAll();
	}

	@Override
	public List<Product> selectBySubCategoryId(int subcategory_id) {
		// TODO Auto-generated method stub
		return productDAO.selectBySubCategoryId(subcategory_id);
	}

	@Override
	public Product select(int product_id) {
		// TODO Auto-generated method stub
		return  productDAO.select(product_id);
	}


}
