package com.studycool.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.studycool.Repo.CourseRepo;
import com.studycool.Repo.SubjectRepo;
import com.studycool.Repo.SylabusRepo;
import com.studycool.Repo.UnivercityRepo;
import com.studycool.config.AppConstants;
import com.studycool.model.Course;
import com.studycool.model.Subject;
import com.studycool.model.Sylabus;
import com.studycool.model.Univercity;

@Service
public class CourseService {

	
	@Autowired
	CourseRepo repo;
	
	@Autowired
	UnivercityRepo uni;
/* ******************************************************course ****************************************/

	public String addCourse(long id,Course course)
	{
		try {
			
			Univercity u=uni.findById(id);
				course.setUnivercity(u);
				course.setU_id(u.getId());
			repo.save(course);
				return "sucess";
		
			
		} catch (Exception e) {
			return e.toString();
		}
	}

	
	public String add(Course course)
	{
		try {
			
			repo.save(course);
				return "sucess";
		
			
		} catch (Exception e) {
			return e.toString();
		}
	}
	public String delete(long id) {
		
		
		try {
		
				repo.deleteById(id);
				deleteSubjectAll(id);
				return "sucess";

		} catch (Exception e) {
			return e.toString();
			// TODO: handle exception
		}
	}
	
	
	public String deleteCourseByUnivercity(long id) {
		
		
		try {
		        List<Long> list=repo.getallCourseIdByUnivericty(id);
		        
		        
		        for(long l:list)
		        {
		        	 
		        	deleteSubjectAll((long)l);
		        	
		        }	
		        repo.deleteCourseByUnivercity(id);
		       
				return "sucess";

		} catch (Exception e) {
			return e.toString();
			// TODO: handle exception
		}
	}
	public List<Course> getAll() {

		try {
		
			return	repo.findAll();
			
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
	}
	
	public List<Course> courseByUnivercity(long id) {
		try {
		
				//return repo.courseByUnivercity(id);
				return null;
		
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
	}
	
	
public Course getCourseById(long id) {
	
			try {
						return repo.findById(id);	
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
				return null;
	}
	/* ******************************************************end course ****************************************/

	/* ******************************************************subject ****************************************/
	@Autowired	
	SubjectRepo subjectRepo;
	
	public String addSubject(Subject subject) {
		
		try {
			
				
		
				subjectRepo.save(subject);
				return "sucess";
		} catch (Exception e) {
			return e.toString();
			// TODO: handle exception
		}
	}
	
	
	public String deleteSubject(long id) {

		try {
		
				subjectRepo.deleteById(id);
				deleteSylabusBySubjectSingleID(id);
				return "sucess";
		} catch (Exception e) {
			return e.toString();
			// TODO: handle exception
		}
	}
	
	public List<Subject> getAllSubject() {
		try {
	
			return subjectRepo.findAll();
			
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
	}
	
	
	public List<Subject> subjectByCourse(long id) {
		try {
			
			return subjectRepo.getAllSubject(id);
			
			
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
	}
	
	
	public Map<String,List<Subject>> subjectByCourseForDash(long id) {
		try {
			
			Map<String,List<Subject>> map=new HashMap<String, List<Subject>>();	
			Course c=repo.findById(id);
			
			int years=c.getYears();
			for(int i=1;i<=years;i++) {
				List<Subject> subject=subjectRepo.getAllSubjectDash(id,i);
				map.put(AppConstants.year[i-1], subject);
			}
			return map;
			
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Map<String,List<String>> subjectByCourseForTree(long id) {
		try {
			
			Map<String,List<String>> map=new HashMap<String, List<String>>();	
			Course c=repo.findById(id);
			
			int years=c.getYears();
			for(int i=1;i<=years;i++) {
				List<String> subject=subjectRepo.getAllSubjectTree(id,i);
				map.put(AppConstants.year[i-1], subject);
			}
			return map;
			
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return null;
	}
	
	//delete subject by course
	public String deleteSubjectAll(long course_id)
	{
		
		try {
						List<Long> list=subjectRepo.getAllSubjectId(course_id);
						subjectRepo.deleteSubjectByCourse(course_id);
						deleteSylabusBySubject(list);
						return "sucess";
		} catch (Exception e) {
			return e.toString();
			// TODO: handle exception
		}
		
		
	}
	
	
	public Subject getSubject(long id) {
	
	try {
		
		return subjectRepo.findById(id);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
	}
	
	
	public Subject getSubjectByPagenumber(long courseId,String name) {
		
		try {
			
			return subjectRepo.pagenumber(courseId,name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		}
	/* ******************************************************end subject ****************************************/
	
	
	
	/* ******************************************************syslabus ****************************************/
	
	
	@Autowired
	SylabusRepo sys;	
	
	
	public String addSylabus(Sylabus sylabus,MultipartFile uploadfile) {
		
			try {
				String filename=sylabus.getTopic().replace(" ", "")+"_"+sylabus.getSubject_id()+AppConstants.FILE_EX;
				if (!"".equalsIgnoreCase(uploadfile.getOriginalFilename())) {
                    // Handle file content - multipartFile.getInputStream()
                    uploadfile.transferTo(new File(AppConstants.HTMl_PATH +filename));
                   
                }
							sylabus.setFile_name(filename);
							sylabus.setFile_path(filename);
							sys.save(sylabus);
							return "sucess";
					
				
			
				} catch (Exception e) {
					return e.toString();
				}
		
		}
	public String deleteSylabus(long id) {

		try {
	
				sys.deleteById(id);
				return "sucess";
		} catch (Exception e) {
			return e.toString();
			// TODO: handle exception
		}
	}
	
	public List<Sylabus> getAllSylabus() {
		// TODO Auto-generated method stub
		
		try {
			return sys.findAll();
			
		} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
	}
	
	public List<Sylabus> getallSylabusBySubject(long id) {
	try {
		
			return sys.getallSylabusBySubject(id);
		
	} catch (Exception e) {
			System.out.println(e);
			// TODO: handle exception
		}
		return null;
	}
	
	//get sylabus
	
	public Sylabus getSylabus(long id) {
		try {
			
			return sys.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}


	
	
	//delete sylabus by subject
	public String deleteSylabusBySubject(List<Long> list)
	{
				
		try {
			
				List<Long> syslabus=new ArrayList<Long>();
				for(Long l:list)
				{
					syslabus.addAll(sys.getallIdSylabusBySubject(l));
					sys.deleteSylabusBySubject(l);
				}
					
				
				return "sucess";
			
			
		} catch (Exception e) {
			return e.toString();
			// TODO: handle exception
		}
		
		
		
		
	}
	
	
	
	public String deleteSylabusBySubjectSingleID(long id)
	{
		
		try {
			sys.deleteSylabusBySubject(id);
			return "sucess";
			
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
			// TODO: handle exception
		}
	}
	
	/* ******************************************************end syslabus ****************************************/
	


}