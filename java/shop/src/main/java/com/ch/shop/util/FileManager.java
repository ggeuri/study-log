package com.ch.shop.util;

import java.io.File;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ch.shop.exception.DirectoryException;
import com.ch.shop.exception.UploadException;

import lombok.extern.slf4j.Slf4j;

// 파일업로드는 모델 영역의 업무. 컨트롤러가 업로드 처리하면 안됨. 떼어냄. 
// 모델영역의 객체이면서 DataBase업무 다루지 않는 객체 (DAO아님) 
@Component
@Slf4j
public class FileManager {
	
	//원하는 이름으로 디렉토리만들기 
	public void makeDirectory(String path) throws DirectoryException{
		//모든 프로그래밍 언어에서는 디렉토리도 파일 
		File file = new File(path);
		
		if(!file.mkdir()) {
			throw new DirectoryException("파일만들기 실패 !");
		};
		
	}
	//확장자 추출 메서드 
	public String getExtend(String path) {
		return path.substring(path.lastIndexOf(".")+1,path.length()); 
	}
	//원래 파일에 대한 처리는 트랜잭션 대상 아님. 하지만 우리의 경우 상품등록업무에 파일저장 포함되어있음.
//	만약파일저장 실패할 경우 Exception을 서비스로 던지면 롤백되니 이 특징 이용 
	
	public void save(MultipartFile mf, String dir, String filename) throws UploadException{
		File file = new File(dir, filename);
		//임시 디렉토리 또는 메모리상 파일정도 이용하여 실제 디스크에 저장 
		try {
			mf.transferTo(file);
			Thread.sleep(10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UploadException("파일저장실패",e);
		} 
	}
	//파일삭제 : 이 메서드 호출시 제거대상이 되는 디렉토리의 경로를 넘겨야한다 
	public void remove(String path) {
		//1_지정된 경로에 파일있는지 조사 
		File directory = new File(path);
		
		//2_디렉토리인지 판단 필요 
		if(directory.exists() && directory.isDirectory()) {
			//소속된 자식 구하기 
			File[] files = directory.listFiles(); //이 디렉토리 하위에 존재하는 디렉토리나 파일 File배열로 반환 .우리는 파일만 넣음 
			if(files!=null) {
				for(File file : files) {
					boolean result = file.delete();
					if(!result) log.debug( "파일삭제실패 : " + file.getName());
				}
			}
			
			//위에서 파일 다 지웠으니까 이제 디렉토리삭제 
			if(!directory.delete()) log.debug("디렉토리삭제실패 : " + directory.getAbsolutePath());
		}
		
	}

}
