package com.studycool.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.studycool.Repo.DBFileRepository;
import com.studycool.data.model.ExcelPOIHelper;
import com.studycool.data.model.MyCell;
import com.studycool.model.Course;
import com.studycool.model.DBFile;
import com.studycool.model.Subject;
import com.studycool.model.Sylabus;
import com.studycool.model.Univercity;


@Service
public class FileService {
	
	@Autowired
	private ExcelPOIHelper excelPOIHelper;
	
	@Autowired
	private DBFileRepository dbFileRepository;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	UnivercityService uniService;
	
	 public String storeFile(MultipartFile file,String name,String description) throws Exception {
	        // Normalize file name
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

	        try {
	            // Check if the file's name contains invalid characters
	            if(fileName.contains("..")) {
	                throw new Exception("Sorry! Filename contains invalid path sequence " + fileName);
	            }

	          //  InputStream input = new ByteArrayInputStream(file.getBytes());
	            DBFile dbFile = new DBFile();
	            		dbFile.setFileName(fileName.trim());
	            		dbFile.setFileType( file.getContentType());
	            		dbFile.setData(file.getBytes());
	            		dbFile.setOption(name.trim());
	            		dbFile.setDescription(description.trim());
	            
	            dbFileRepository.save(dbFile);

	            return "file added";
	        } catch (Exception ex) {
	            throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
	        }
	    }

	    public DBFile getFile(long fileId) {
	    	
	    	try {
	    		
	    		 return dbFileRepository.findById(fileId);
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
				return null;
			}
	      
	                
	    }
	
	    public List<DBFile>  findDbFile(){
	    	
	    	try {
				
	    		return dbFileRepository.getallFiles();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
				// TODO: handle exception
			}
	    	
	    }
	

		public boolean deleteFile(long id) {
			try {
				dbFileRepository.deleteById(id);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
				// TODO: handle exception
			}
		}
		
		public boolean startFile(long id) {
			try {
				
				startFileprocess(id);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
				// TODO: handle exception
			}
		}
	
	    
	    /*************************************function for file process*********************/
		
		
		public String startFileprocess(long id) {
			
			try {
				DBFile file=dbFileRepository.findById(id);
				addFileProcess(file);
				return "started";
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	
	public  String addFileProcess(DBFile file) {
		
		try {
			
			
			Map<Integer, List<MyCell>> data =  excelPOIHelper.readExcel(file.getData(),file.getFileName());
			boolean result=false;
			if(file.getOption().equals("Univercity")) {
				result=addUniversity(data,file);
			} else if(file.getOption().equals("Course")) {
				result=addCourse(data,file);
			} else if(file.getOption().equals("Subject")) {
				result=addSubject(data,file);
			} else if(file.getOption().equals("Sylabus")) {
				result=addSylabus(data,file);
			} 
			
			if(result) {
				dbFileRepository.updateStatus(1, file.getId()); //1 means sucess
			} else if(!result) {
				dbFileRepository.updateStatus(2, file.getId()); //2 means expection
			}
			 return "sucess";
				
			
		
			} catch (Exception e) {
				e.printStackTrace();
				return e.toString();
			}
	
	}
	
	
	public boolean addUniversity(Map<Integer, List<MyCell>> data,DBFile file) {
		
				try {
					
					//format name,location,address
					
					data.remove(0); //remove header tittiles
					for(Integer key:data.keySet()) {						
						Univercity u=new Univercity();
						int i=1;						
			 			for(MyCell d:data.get(key)) { 			 				
			       			if(d.getContent()!=null && d.getContent()!="") {
			       				System.out.println(d.getContent());			       				
			       				if(i==1) {
			       					u.setName(d.getContent());
			       				} else if(i==2) {
			       					u.setAddress(d.getContent());
			       				}else if(i==3) {
			       					u.setLocation(d.getContent());
			       				}
			       				
			       			}
			       			i++;
			       		}
			 			
			 			uniService.add(u);
			       	}
					
					
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
					// TODO: handle exception
				}
		
		}
	
	public boolean addCourse(Map<Integer, List<MyCell>> data,DBFile file) {
		
		try {
			String first=file.getFileName().replaceAll("\\.[^.]*$", "");
			String arr[]=first.split("_");
			long id=Long.valueOf(arr[1]);
			data.remove(0); //remove header tittiles
			for(Integer key:data.keySet()) {						
				Course course=new Course();
				int i=1;						
	 			for(MyCell d:data.get(key)) { 			 				
	       			if(d.getContent()!=null && d.getContent()!="") {
	       				System.out.println(d.getContent());			       				
	       				if(i==1) {
	       					course.setName(d.getContent());
	       				} else if(i==2) {
	       					String years=d.getContent().replaceAll("\\.[^.]*$", "");
	       					course.setYears((int) Integer.valueOf(years));
	       				}
	       			}
	       			i++;
	       		}
	 			course.setU_id(1);
	 			courseService.addCourse(id,course);
	       	}
			
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			// TODO: handle exception
		}

	}
	
	
		public boolean addSubject(Map<Integer, List<MyCell>> data,DBFile file) {
				
				try {
					String first=file.getFileName().replaceAll("\\.[^.]*$", "");
					String arr[]=first.split("_");
					long id=Long.valueOf(arr[1]);
					data.remove(0); //remove header tittiles
					for(Integer key:data.keySet()) {						
						Subject subject=new Subject();
						int i=1;						
			 			for(MyCell d:data.get(key)) { 			 				
			       			if(d.getContent()!=null && d.getContent()!="") {
			       				System.out.println(d.getContent());			       				
			       				if(i==1) {
			       					subject.setName(d.getContent());
			       				} else if(i==2) {
			       					String years=d.getContent().replaceAll("\\.[^.]*$", "");
			       					subject.setYear((int) Integer.valueOf(years));
			       				} else if(i==3) {
			       					String unit=d.getContent().replaceAll("\\.[^.]*$", "");
			       					subject.setUnits((int) Integer.valueOf(unit));
			       				} else if(i==4) {
			       					subject.setSemster(d.getContent());
			       				} else if(i==5) {
			       					String page=d.getContent().replaceAll("\\.[^.]*$", "");
			       					subject.setPage_number((int) Integer.valueOf(page));
			       				}
			       				
			       			}
			       			i++;
			       		}
			 			subject.setCourse_id(id);
			 			courseService.addSubject(subject);
			       	}
					
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
					// TODO: handle exception
				}
		
			}
		
		public boolean addSylabus(Map<Integer, List<MyCell>> data,DBFile file) {
			
			try {
				
				String first=file.getFileName().replaceAll("\\.[^.]*$", "");
				String arr[]=first.split("_");
				long id=Long.valueOf(arr[1]);
				data.remove(0); //remove header tittiles
				 MultipartFile multipartFile=null;
				for(Integer key:data.keySet()) {						
					Sylabus sylabus=new Sylabus();
					int i=1;	
					for(MyCell d:data.get(key)) { 			 				
		       			if(d.getContent()!=null && d.getContent()!="") {
		       				System.out.println(d.getContent());			       				
		       				if(i==1) {
		       					sylabus.setTopic(d.getContent());
		       				} else if(i==2) {
		       					String unit=d.getContent().replaceAll("\\.[^.]*$", "");
		       					sylabus.setUnit_number((int) Integer.valueOf(unit));
		       				} else if(i==3) {
		       				   // multipartFile =convertfile(d.getContent());
		       					sylabus.setContent(d.getContent());
		       				} 
		       			}
		       			i++;
		       		}
		 			sylabus.setSubject_id(id);
		 			courseService.addSylabus(sylabus);
		       	}
				
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
				// TODO: handle exception
			}

		}
		
		
		public MultipartFile convertfile(String location) {
			
			try {
				File c = new File(location);
				    FileInputStream input = new FileInputStream(c);
				    MultipartFile     multipartFile = new MockMultipartFile("file",
				            "subject", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", IOUtils.toByteArray(input));
				    	return multipartFile;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
				// TODO: handle exception
			}
			
			
		}
		


}
