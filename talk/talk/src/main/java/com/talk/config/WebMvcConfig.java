package com.talk.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	// WebMvcConfigurer인터페이스는
	// Spring MVC 설정을 서용자(개발자)정의한기 위해 사용한다.
	// -리소스 핸들러 설정 : 정적 리소스(css, javascript, 이미지, 파일 등)에 대한
	//  경로를 걸정하고 캐싱정책을 적용한다.
	// - CORS : coss-origin resource sharing	
	// 			다른 도메인(주소-url)에서 현재 도메인(주소-url)으로 API 요청을 허용하도록 설정
	// 뷰 컨트롤러 등록 : URL요청에 대해 뷰어름을 직업 매핑, 컨트롤러없이 뷰를 반환할  수 있음.
	// 경로 패턴 설정: URL, 경로 패턴을 설정	
	
	@Value("${uploadPath}")
	String uploadPath;
	
	//실제 업로드 경로와 웹에서 사용하는 경로(주소)를 연결,실제 업로드 경로는 웹에서
	//사용할 수 없는 경로이기 때문에 웹용으로 /files 로 쓰겠다라고 등록
	//웹에서 /files/12445.jpg 라는 이미지 요청이 들어오면 
	//실제경로는 c://talkimge/12445.jpg 의 이미지를 제공한다.
	
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/files/**")
			.addResourceLocations(uploadPath);
}

} 
