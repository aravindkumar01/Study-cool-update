package com.studycool.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studycool.config.CustomErrorType;
import com.studycool.model.Course;
import com.studycool.model.Subject;
import com.studycool.model.Sylabus;
import com.studycool.service.impl.CourseService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value="/api")
public class CourseController {

	
	public static final Logger logger = LoggerFactory.getLogger(CourseController.class);
	@Autowired
	CourseService service;
	
	//create new course

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/course") 
	public @ResponseBody String add(@RequestBody Course course)
	{
		try {
			
			return service.add(course);
		} catch (Exception e) {
			return e.toString();
			// TODO: handle exception
		}
		
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/course/{univercity_id}") 
	public @ResponseBody String addCourse(@PathVariable("univercity_id")long id,@RequestBody Course course)
	{
		try {
			
			return service.addCourse(id,course);
		} catch (Exception e) {
			return e.toString();
			// TODO: handle exception
		}
		
	}
	
	
	//delete particular course

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/course/{id}")
	public @ResponseBody String delete(@PathVariable("id") long id)
	{
		try {
			
			return service.delete(id);
		} catch (Exception e) {
			return e.toString();
			// TODO: handle exception
		}
		
	}
	
	//get all course
	@GetMapping("/course")
	public @ResponseBody ResponseEntity<List<Course>> getall()
	{
		try {
			List<Course> course =service.getAll();
	        if (course.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Course>>(course, HttpStatus.OK);
			
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
		
	}
	
	
	//get courses by univercity

	//@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/course/univercity/{id}")
	public @ResponseBody ResponseEntity<List<Course>> courseUnivercity(@PathVariable("id") long id)
	{
		try {
			
			List<Course> course =service.courseByUnivercity(id);
	        if (course.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Course>>(course, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			return null;			
		}
		
	}
	
	//get one course
	 @GetMapping("/course/{id}")
	    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
	     
		 try {
			 //logger.info("Fetching course with id {}", id);
		        Course course=service.getCourseById(id);
		        if (course == null) {
		            logger.error("course with id {} not found.", id);
		            return new ResponseEntity(new CustomErrorType("Cousre with id " + id 
		                    + " not found"), HttpStatus.NOT_FOUND);
		        }
		        return new ResponseEntity<Course>(course, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
		   
	    }
	
	
	/*****************************course end ******************************************/
	
	/********************* subject *************************************************/
	
	
	//create new subject

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/subject")
	public @ResponseBody String addSubject(@RequestBody Subject subject)
	{
		try {
			
			return service.addSubject(subject);
		} catch (Exception e) {
			return e.toString();
			// TODO: handle exception
		}
	}
	
	
	
	//delete by id

	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/subject/{id}")
	public @ResponseBody String deleteSubject(@PathVariable("id") long id)
	{
		
		try {
			
			return service.deleteSubject(id);
		} catch (Exception e) {
			return e.toString();
			// TODO: handle exception
		}
	}
	
	//get all subject

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/subject")
	public @ResponseBody ResponseEntity<List<Subject>> getallSubject()
	{
		try {
			List<Subject> subject =service.getAllSubject();
	        if (subject.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Subject>>(subject, HttpStatus.OK);
			
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
		
	}
	//single subject
	
	@GetMapping("/subject/{id}")
	public @ResponseBody ResponseEntity<?> getSubject(@PathVariable("id") long id)
	{
		try {
			Subject subject=service.getSubject(id);
	        if (subject==null) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<Subject>(subject, HttpStatus.OK);
			
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
		
	}
	
	@GetMapping("/subject/{name}/{id}")
	public @ResponseBody ResponseEntity<?> getSubjectByPagenumber(@PathVariable("id") long courseid,@PathVariable("name") String name)
	{
		try {
			Subject subject=service.getSubjectByPagenumber(courseid,name);
	        if (subject==null) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<Subject>(subject, HttpStatus.OK);
			
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
		
	}
	
	//getall subject by course
	@GetMapping("/subject/course/{id}")
	public @ResponseBody ResponseEntity<List<Subject>> subjectByCourse(@PathVariable("id") long id)
	{
		try {
			
			List<Subject> subject =service.subjectByCourse(id);
	        if (subject.isEmpty()) {
	            return new ResponseEntity(HttpStatus.NO_CONTENT);
	            // You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<Subject>>(subject, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
		
	}
	
	//getall subject by course
		@GetMapping("/subject/course/dash/{id}")
		public @ResponseBody ResponseEntity<Map<String,List<Subject>>> subjectByCourseForDash(@PathVariable("id") long id)
		{
			try {
				
				Map<String,List<Subject>> subject =service.subjectByCourseForDash(id);
		        if (subject.isEmpty()) {
		            return new ResponseEntity(HttpStatus.NO_CONTENT);
		            // You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<Map<String,List<Subject>>>(subject, HttpStatus.OK);
			} catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}
			return null;
			
		}

		@GetMapping("/subject/course/tree/{id}")
		public @ResponseBody ResponseEntity<Map<String,List<String>>> subjectByCourseForTree(@PathVariable("id") long id)
		{
			try {
				
				Map<String,List<String>> subject =service.subjectByCourseForTree(id);
		        if (subject.isEmpty()) {
		            return new ResponseEntity(HttpStatus.NO_CONTENT);
		            // You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<Map<String,List<String>>>(subject, HttpStatus.OK);
			} catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}
			return null;
			
		}

	
	
	/********************* subject *************************************************/

	/********************* Syslabus *************************************************/
	


	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/sylabus")
	public @ResponseBody String addSyslabus(@RequestBody Sylabus sylabus)
	{
		try {
			/*System.out.println(uploadfile.getOriginalFilename());
			String a=request.getParameter("name");			
			Sylabus sylabus=new ObjectMapper().readValue(a, Sylabus.class);*/
			return service.addSylabus(sylabus);
		} catch (Exception e) {
			return e.toString();
			// TODO: handle exception
		}
		
	}


	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/sylabus/{id}")
	public @ResponseBody String deleteSylabus(@PathVariable("id") long id)
	{
		try {
			
			return service.deleteSylabus(id);
		} catch (Exception e) {
			return e.toString();
			// TODO: handle exception
		}
		
	}
	//get all syslabus
		@GetMapping("/sylabus")
		public @ResponseBody ResponseEntity<List<Sylabus>> getallSylabus()
		{
			try {
				List<Sylabus> sylabus =service.getAllSylabus();
		        if (sylabus.isEmpty()) {
		            return new ResponseEntity(HttpStatus.NO_CONTENT);
		            // You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<List<Sylabus>>(sylabus, HttpStatus.OK);
				
			} catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}
			return null;
			
		}
		
		
		
		
		@GetMapping("/sylabus/{id}")
		public @ResponseBody ResponseEntity<?> getSylabus(@PathVariable("id") long id)
		{
			try {
				Sylabus sylabus=service.getSylabus(id);
		        if (sylabus==null) {
		            return new ResponseEntity(HttpStatus.NO_CONTENT);
		            // You many decide to return HttpStatus.NOT_FOUND
		        }
		        return new ResponseEntity<Sylabus>(sylabus, HttpStatus.OK);
				
			} catch (Exception e) {
				System.out.println(e);
				// TODO: handle exception
			}
			return null;
			
		}
		//get all syslabus by subject
			@GetMapping("/sylabus/subject/{id}")
			public @ResponseBody ResponseEntity<List<Sylabus>> getallSylabusBySubject(@PathVariable("id")long id)
			{
				try {
					List<Sylabus> sylabus =service.getallSylabusBySubject(id);
			        if (sylabus.isEmpty()) {
			        	System.out.println("-------------------List empty---------");
			            return new ResponseEntity(HttpStatus.NO_CONTENT);
			            // You many decide to return HttpStatus.NOT_FOUND
			        }
			        return new ResponseEntity<List<Sylabus>>(sylabus, HttpStatus.OK);
					
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e);
					// TODO: handle exception
				}
				return null;
				
			}
			
			

/********************* end Syslabus *************************************************/
	
	/********************* content *************************************************/

//create new content

	
	
	
/********************************FILE*******************/
	@PostMapping("/upload") 
	public @ResponseBody String addFile(@RequestPart("file") MultipartFile uploadfile,HttpServletRequest request)
	{
		try {
		
			System.out.println(uploadfile.getOriginalFilename());
			String a=request.getParameter("name");
			
			//Content c=new ObjectMapper().readValue(a, Content.class);
			
			return "done";
		} catch (Exception e) {      
			return e.toString();
			// TODO: handle exception
		}
		
	}

}