package com.klef.jfsd.springboot.controller;

import java.util.ArrayList;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.klef.jfsd.springboot.model.Admin;
import com.klef.jfsd.springboot.model.Faculty;
import com.klef.jfsd.springboot.model.Project;
import com.klef.jfsd.springboot.model.Review;
import com.klef.jfsd.springboot.model.Student;
import com.klef.jfsd.springboot.model.Submission;
import com.klef.jfsd.springboot.service.AdminService;
import com.klef.jfsd.springboot.service.FacultyService;
import com.klef.jfsd.springboot.service.ProjectService;
import com.klef.jfsd.springboot.service.ReviewService;
import com.klef.jfsd.springboot.service.StudentService;
import com.klef.jfsd.springboot.service.SubmissionService;

@Controller
public class ClientController 
{
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ReviewService reviewSerice;
	
	@Autowired
	SubmissionService submissionService;
	
	@GetMapping("/")
	public String mainhome()
	{
		return "index";
	}
	
	@GetMapping("/login")
	public ModelAndView logindemo()
	{
		ModelAndView mv= new ModelAndView("login");
		
		return mv;
	}
	
	 @GetMapping("/facultyreg") 
	public ModelAndView  facultyregdemo()
	  {
	    ModelAndView mv= new ModelAndView("facultyreg", "fac", new Faculty());
	    return mv;
	  }
	  @PostMapping("/addfaculty")
	public ModelAndView addfacultydemo(@ModelAttribute("fac") Faculty faculty)
	  
	  {
		  faculty.setFpassword("12345");
	    adminService.addfaculty(faculty);
	    
	    
	    ModelAndView mv= new ModelAndView();
	    mv.setViewName("facultyreg");
	    mv.addObject("msg","Faculty Registered Successfully");
	    
	    return mv;
	    
	    
	}
		@GetMapping("/adminlogin")
	public ModelAndView adminlogindemo()
		{
			ModelAndView mv= new ModelAndView("adminlogin");
			
			return mv;
		}
	
	@GetMapping("/facultylogin")
	public ModelAndView facultylogindemo()
	{
		ModelAndView mv= new ModelAndView("facultylogin");
		
		return mv;
	}
	@GetMapping("/studentlogin")
	public ModelAndView studentlogindemo()
	{
		ModelAndView mv= new ModelAndView("studentlogin");
		
		return mv;
	}
	@GetMapping("/adminhome")
	public ModelAndView adminhomedemo()
	{
		ModelAndView mv= new ModelAndView("adminhome");
		int studentcount = adminService.getStudentCount();
		System.out.println("student count" +studentcount);
		mv.addObject("projectCount", adminService.getProjectCount());
		mv.addObject("studentcount", studentcount);
		mv.addObject("facultycount", adminService.getFacultyCount());
		mv.addObject("projectscompleted", adminService.getProjectCompletedCount());
		mv.addObject("ongoingproject", adminService.getOngoingProjectCount());
		mv.addObject("topperformingprojects", adminService.getTopPerformingProjects());
		mv.addObject("allocatedfacultycount", adminService.getAllocatedFacultyCount());
		mv.addObject("nonAllocatedFaculty", adminService.getNonAllocatedFacultyCount());
		mv.addObject("projectmentorallocated", 		adminService.getProjectMentorAllocatedProjectCount());
		mv.addObject("projectmentornonallocated", 		adminService.getProjectMentorNotAllocatedProjectCount());

		return mv;
	}
	@GetMapping("/facultyhome")
	public ModelAndView facultyhomedemo()
	{
		ModelAndView mv= new ModelAndView("facultyhome");
		
		return mv;
	}
	@GetMapping("/studenthome")
	public ModelAndView studenthomedemo()
	{
		ModelAndView mv= new ModelAndView("studenthome");
		
		return mv;
	}
	
	@GetMapping("/about")
	public ModelAndView aboutdemo()
	{
		ModelAndView mv= new ModelAndView("about");
		
		return mv;
	}
	@GetMapping("/contactus")
	public ModelAndView contactusdemo()
	{
		ModelAndView mv= new ModelAndView("contactus");
		
		return mv;
	}
	@PostMapping("/checkadminlogin")
	public String checkadminlogindemo(HttpServletRequest request)
	{
		ModelAndView mv =  new ModelAndView();
		
		String auname = request.getParameter("auname");
		String apwd = request.getParameter("apwd");
		
		Admin admin = adminService.checkadminlogin(auname, apwd);
		
		if(admin!=null)
		{
			
			HttpSession session = request.getSession();
			
			session.setAttribute("auname", auname);
			
			mv.setViewName("adminhome");
			return "redirect:adminhome";
		}
		else
		{
			mv.setViewName("adminlogin");
			mv.addObject("msg", "Login Failed");
			return "redirect:adminlogin";
		}
		
		

	}
	
	 @GetMapping("/studentreg") 
	public ModelAndView  studentregdemo()
	  {
	    ModelAndView mv= new ModelAndView("studentreg", "stu", new Student());
	    
	    return mv;
	  }
	
	  @PostMapping("/addstudent")
	public String addstudentdemo(@ModelAttribute("stu") Student student)
	  
	  {
		  student.setAllocated(false);
		  student.setSpassword("12345");
	    adminService.addstudent(student);

	    return "redirect:studentlogin";
	    
	}
	  
	  @GetMapping("/projectcreation") 
	  public ModelAndView  projectcreationdemo(HttpServletRequest request)
	  {
	    ModelAndView mv= new ModelAndView("projectcreation", "proj", new Project());
	    HttpSession session = request.getSession();
		int id = (int) session.getAttribute("stuid");
	    
		if(studentService.getStudentLeadStatus(id)) {
			
			
			List<Student> ls =  studentService.getAllStudent();
			Student plead = studentService.getStudentByStudentID(id);
			
			if(plead.isAllocated()) {
				mv.setViewName("projectcreationrestricted");
				mv.addObject("msg", "You have already created a project");
				return mv;
			}
			else {
				List<Student> acls = new ArrayList<Student> () ;
				ls.remove(plead);
				for(Student s : ls) {
					System.out.println(s.isAllocated());
					if(s.isAllocated() == false) {
						acls.add(s);
					}
				}
			    mv.addObject("studentlist", acls);
			    return mv;
			}
			
			
		}
		
		else {
			mv.addObject("msg", "You are not authorised to perform this operation");
			mv.setViewName("projectcreationrestricted");
			return mv;
		}
	    
	    
	  }
	  
	  
	  @PostMapping("/addproject")
	  public ModelAndView addprojectdemo(@ModelAttribute("proj") Project project , HttpServletRequest request)
	  
	  {
		  HttpSession session = request.getSession();
		  int id = (int) session.getAttribute("stuid");
		  project.setPleadid(id);
		  int con1 = project.getContributor1();
		  int con2 = project.getContributor2();
		  ModelAndView mv =  new ModelAndView();
		  if((studentService.checkallocation(con1) || studentService.checkallocation(con2)) || con1==con2  ) 
		  {
			   mv.setViewName("projectcreation");
				mv.addObject("msg", "Creation failed");
				List<Student> ls =  studentService.getAllStudent();
				Student plead = studentService.getStudentByStudentID(id);
				List<Student> acls = new ArrayList<Student> () ;
				ls.remove(plead);
				for(Student s : ls) {
					System.out.println(s.isAllocated());
					if(s.isAllocated() == false) {
						acls.add(s);
					}
				}
			    mv.addObject("studentlist", acls);

				return mv;
		  }
		  else {
			  studentService.changeallocation(con1);
			  studentService.changeallocation(con2);
			  studentService.changeallocation(id);
			  projectService.addproject(project);
			  mv.setViewName("studenthome");
			  return mv;
			  
		  }
	    
	    

	   
	    
	}
		@PostMapping("/checkflogin")
		public ModelAndView checkfacultylogindemo(HttpServletRequest request)
		{
			ModelAndView mv =  new ModelAndView();
			
			String funame = request.getParameter("funame");
			String fpwd = request.getParameter("fpwd");
			
			
			Faculty faculty = facultyService.checkfacultylogin(funame, fpwd);
			
			if(faculty!=null)
			{
				
				HttpSession session = request.getSession();
				
				session.setAttribute("funame", funame);
				int facid= facultyService.FacultyIdByUsername(funame);
				System.out.println(facid);
				session.setAttribute("facid", facid);
				mv.setViewName("facultyhome");
			}
			else
			{
				mv.setViewName("facultylogin");
				mv.addObject("msg", "Login Failed");
			}
			
			
			return mv;
		}
		@PostMapping("/checkstudentlogin")
		public ModelAndView checkstudentlogindemo(HttpServletRequest request)
		{
			ModelAndView mv =  new ModelAndView();
			
			String suname = request.getParameter("suname");
			String spwd = request.getParameter("spwd");
			
			Student student = studentService.checkstudentlogin(suname, spwd);
			
			if(student!=null)
			{
				
				HttpSession session = request.getSession();
				
				session.setAttribute("suname", suname);
				int id = studentService.getStudentIdByUsername(suname);
				System.out.println(id);
				session.setAttribute("stuid",id);
				mv.setViewName("studenthome");
			}
			else
			{
				mv.setViewName("studentlogin");
				mv.addObject("msg", "Login Failed");
			}
			
			
			return mv;
		}
		@GetMapping("/viewallstudents")
		public ModelAndView viewallstudentsdemo()
		{
			ModelAndView mv= new ModelAndView("viewallstudents");
			
			List<Student> studentlist=adminService.viewallstudents();
			
			mv.addObject("studentlist",studentlist);
			
			return mv;
		}
		
		@GetMapping("/viewallfaculty")
		public ModelAndView viewallfacultydemo()
		{
		    ModelAndView mv= new ModelAndView("viewallfaculty");
		    
		    List<Faculty> facultylist=adminService.viewallfaculty();
		    
		    mv.addObject("facultylist",facultylist);
		    
		    return mv;
		}
	
		@GetMapping("/deletestudent")
		public String deletestudentdemo(@RequestParam("id") int sid)
		{
			adminService.deletestudent(sid);
			
			return "redirect:viewallstudents";
		}
		@GetMapping("/deletefaculty")
		public String deletefacultydemo(@RequestParam("id") int fid)
		{
			adminService.deletefaculty(fid);
			
			return "redirect:viewallfaculty";
		}
		@GetMapping("/viewstudent")
		public ModelAndView viewstudent(HttpServletRequest request)
		{
			HttpSession session = request.getSession();
			
			String suname = (String) session.getAttribute("suname");
			
			Student student =  studentService.viewstudent(suname);
			ModelAndView mv = new ModelAndView();
			mv.setViewName("viewstudent");
			mv.addObject("student",student);
			
			return mv;
		}
		@GetMapping("/viewfaculty")
		public ModelAndView viewfaculty(HttpServletRequest request)
		{
		    HttpSession session = request.getSession();
		    
		    String funame = (String) session.getAttribute("funame");
		    
		    Faculty faculty =  facultyService.viewfaculty(funame);
		    ModelAndView mv = new ModelAndView();
		    mv.setViewName("viewfaculty");
		    mv.addObject("faculty",faculty);
		    
		    return mv;
		}
		@GetMapping("/viewstudentbyid")
		public ModelAndView viewstudentbyiddemo(@RequestParam("id") int sid)
		{
			Student student = adminService.viewstudentbyid(sid);
			
			ModelAndView mv = new ModelAndView();
			
			mv.setViewName("viewstudentbyid");
			mv.addObject("student",student);
			
			return mv;
		}
		
		@GetMapping("/viewuserproject")
		public ModelAndView viewprojectbyuser(HttpServletRequest request)
		{
			HttpSession session = request.getSession();
			
			int id = (int) session.getAttribute("stuid");
			System.out.println(id);
			List<Project> projectlist = projectService.viewuserprojects(id);
			
			ModelAndView mv = new ModelAndView();
			
			mv.setViewName("viewuserproject");
			mv.addObject("projectlist",projectlist);
			
			return mv;
		}
		@GetMapping("/viewfacultyproject")
		public ModelAndView viewprojectunderfaculty(HttpServletRequest request)
		{
			HttpSession session = request.getSession();
			
			int id = (int) session.getAttribute("facid");
			System.out.println(id);
			List<Project> projectlist = projectService.viewfacultyprojects(id);
			
			ModelAndView mv = new ModelAndView();
			
			mv.setViewName("viewfacultyprojects");
			mv.addObject("projectlist",projectlist);
			
			return mv;
		}
		
		
		@GetMapping("/viewprojectbyid")
		public ModelAndView viewUserProjetid(@RequestParam("id") int pid , HttpServletRequest request)
		{
		    Project project = projectService.viewUserProjectByID(pid);
		   
		    ModelAndView mv = new ModelAndView();
		    
		    HttpSession session = request.getSession();
			
			session.setAttribute("pid",pid);
		    
		    mv.setViewName("viewuserprojectbyid");
		    mv.addObject("project",project);
		    
		    int pleadid = project.getPleadid();
		    int conid1 = project.getContributor1();
		    int conid2 = project.getContributor2();
		    int fmentorid = project.getFmentorid();
		    
		    Student plead = studentService.getStudentByStudentID(pleadid);
		    Student con1 = studentService.getStudentByStudentID(conid1);
		    Student con2 =studentService.getStudentByStudentID(conid2);
		    Faculty fmentor = facultyService.getFacultyById(fmentorid);
		    
		    mv.addObject("pleadname", plead.getSname());
		    mv.addObject("con1name", con1.getSname());
		    mv.addObject("con2name", con2.getSname());
		    mv.addObject("fmentorname", fmentor.getFname());
		    List<Review> reviewlist = reviewSerice.viewreviewsByProjectId(pid);
		    mv.addObject("reviewlist", reviewlist);
		    return mv;
		}
		
		
		@GetMapping("/viewfacultybyid")
		public ModelAndView viewfacultybyiddemo(@RequestParam("id") int fid)
		{
		    Faculty faculty = adminService.viewfacultybyid(fid);
		    System.out.println(faculty);
		    ModelAndView mv = new ModelAndView();
		    
		    mv.setViewName("viewfacultybyid");
		    mv.addObject("faculty",faculty);
		    
		    return mv;
		}
		
		@GetMapping("/viewprojectFacultybyid")
		public ModelAndView viewFacultyProjectid(@RequestParam("id") int pid,HttpServletRequest request)
		{
		    Project project = projectService.viewUserProjectByID(pid);
		    HttpSession session = request.getSession();
		    session.setAttribute("pid", pid);
		    System.out.println(project);
		    ModelAndView mv = new ModelAndView();
		    
		    mv.setViewName("viewprojectfacultybyid");
		    mv.addObject("project",project);
		    
		    int pleadid = project.getPleadid();
		    int conid1 = project.getContributor1();
		    int conid2 = project.getContributor2();
		    int fmentorid = project.getFmentorid();
		    
		    Student plead = studentService.getStudentByStudentID(pleadid);
		    Student con1 = studentService.getStudentByStudentID(conid1);
		    Student con2 =studentService.getStudentByStudentID(conid2);
		    Faculty fmentor = facultyService.getFacultyById(fmentorid);
		    
		    mv.addObject("pleadname", plead.getSname());
		    mv.addObject("con1name", con1.getSname());
		    mv.addObject("con2name", con2.getSname());
		    mv.addObject("fmentorname", fmentor.getFname());
		    
		    List<Review> reviewlist = reviewSerice.viewreviewsByProjectId(pid);
		    mv.addObject("reviewlist", reviewlist);
		    
		    DecimalFormat df=new DecimalFormat("#.##");
		    
		    double totalreviews = reviewSerice.getAllReviewsByProjectId(pid);
		    double reviewcompleted = reviewSerice.getCompletedReviewsByProjectId(pid);
		    
		    double progress = (reviewcompleted/totalreviews)*100.00;
		    
		    mv.addObject("progress", df.format(progress));
		    
		    return mv;
		}
		@GetMapping("/facultyallocationbyid")
		public ModelAndView facultyAllocationByid(@RequestParam("id") int pid , HttpServletRequest request)
		{
		    
			HttpSession session = request.getSession();
		    ModelAndView mv = new ModelAndView();
		    List<Faculty> facfull = adminService.viewallfacultysorted();
		    int studentid = (int) session.getAttribute("stuid");
		    
		    if(studentService.getStudentLeadStatus(studentid)) {

			    mv.setViewName("facultyallocation");
			    
			    session.setAttribute("pid", pid);
	           //System.out.println(projectService.checkmentorallocation(pid));
				if(projectService.checkmentorallocation(pid)>0) {
					System.out.println(projectService.checkmentorallocation(pid));
				mv.addObject("msg", "A Faculty mentor has already been alloted. ");
				mv.setViewName("facultyallocationrestricted");
					return mv;
				}
				mv.addObject("flist",facfull);
			    return mv;
		    }
		    else {
		    	mv.setViewName("facultyallocationrestricted");
		    	mv.addObject("msg", "You are not authorised to perform this operation");
		    	return mv;
		    }
		    
		}
		@PostMapping("/allocatefaculty")
		public ModelAndView allocatefaculty(HttpServletRequest request)
		{
			ModelAndView mv = new ModelAndView();			
			
			HttpSession session = request.getSession();
			int pid= (int)session.getAttribute("pid");

				mv.setViewName("facultyallocation");
				String fmentorid= request.getParameter("fmentorid");
				int fid = Integer.parseInt(fmentorid);
				projectService.allocatefaculty( fid, pid);
				
				facultyService.updateproalloted(fid);
				
			    mv.setViewName("studenthome");
				return mv;
			

		}
		
		@GetMapping("/schangepwd")
		public ModelAndView schangepwd(HttpServletRequest request)
		{
			HttpSession session = request.getSession();
			
			String suname = (String) session.getAttribute("suname");
			
			ModelAndView mv = new ModelAndView();
			mv.setViewName("schangepwd");
			mv.addObject("suname",suname);
			
			return mv;
		}
		
		
		@PostMapping("/updatespwd")
		public ModelAndView updatestudentpwddemo(HttpServletRequest request)
		{
			ModelAndView mv = new ModelAndView();
			mv.setViewName("schangepwd");
			
			HttpSession session = request.getSession();
			
			String suname = (String) session.getAttribute("suname");
			
			String soldpwd = request.getParameter("sopwd");
			String snewpwd = request.getParameter("snpwd");
			
			int n = studentService.changestudentpassword(soldpwd, snewpwd, suname);
			
			if(n > 0)
			{
				
				mv.addObject("msg","Password Updated Successfully");
			}
			else
			{
				mv.addObject("msg","Old Password is Incorrect");
			}
			
			return mv;
		}
		
		@GetMapping("/fchangepwd")
		public ModelAndView fchangepwd(HttpServletRequest request)
		{
			HttpSession session = request.getSession();
			
			String funame = (String) session.getAttribute("funame");
			
			ModelAndView mv = new ModelAndView();
			mv.setViewName("fchangepwd");
			mv.addObject("funame",funame);
			
			return mv;
		}
		@PostMapping("/updatefpwd")
		public ModelAndView updatefacultypwddemo(HttpServletRequest request)
		{
			ModelAndView mv = new ModelAndView();
			mv.setViewName("fchangepwd");
			
			HttpSession session = request.getSession();
			
			String funame = (String) session.getAttribute("funame");
			
			String foldpwd = request.getParameter("fopwd");
			String fnewpwd = request.getParameter("fnpwd");
			
			int n = facultyService.changefacultypassword(foldpwd, fnewpwd, funame);
			
			if(n > 0)
			{
				
				mv.addObject("msg","Password Updated Successfully");
			}
			else
			{
				mv.addObject("msg","Old Password is Incorrect");
			}
			
			return mv;
		}
	
		@GetMapping("/adminchangestudentlead")
	  public ModelAndView adminupdatestudentleadstatuspage() {
		  ModelAndView mv = new ModelAndView();
		  mv.setViewName("AdminChangeStudentLeadstatus");
		  return mv;
	  }
		
		@PostMapping("/updatestudentstatus")
	public ModelAndView updatestudentleadstatus(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String sid = request.getParameter("sid");
		int stuid = Integer.parseInt(sid);

		Student s = studentService.getStudentByStudentID(stuid);
		if(s==null) {
			mv.addObject("msg", "No such Student ID found");
			mv.setViewName("AdminChangeStudentLeadstatus");
			return mv;
		}
//		System.out.println( "Student ID"+s.getSid());
//		if (s.getSid()>0) {
//			mv.addObject("msg", "No such Student ID found");
//			mv.setViewName("AdminChangeStudentLeadstatus");
//			return mv;
//		}
		
		adminService.changestudentLead(stuid);
		mv.addObject("msg", "Lead status updated");
		mv.setViewName("AdminChangeStudentLeadstatus");
		return mv;
	}

	

	@PostMapping("/addreview")
	public String addreview(HttpServletRequest request){
		ModelAndView mv= new ModelAndView();
		int rid = Integer.parseInt(request.getParameter("rid"));
		String deadline = request.getParameter("deadline");
		System.out.println(deadline);
		String component = request.getParameter("component");
		HttpSession session = request.getSession();
		int pid = (int) session.getAttribute("pid");
		Review r = new Review();
		r.setPreviewid(rid);
		r.setDeadline(deadline);
		r.setComponent(component);
		r.setProjectid(pid);
	
		r.setStatus("Not Completed");
	
		 reviewSerice.addreview(r);
		
	    mv.setViewName("viewprojectfacultybyid");
	    mv.addObject("msg","Review Added !");
	   
	    return "redirect:viewprojectFacultybyid?id="+String.valueOf(pid);
		    
		    
		}
	
	@GetMapping("/addsubmissiontoreview")
	public ModelAndView addsubmissiontoreviewByid(@RequestParam("rid") int rid,HttpServletRequest request)
	{
	    HttpSession session = request.getSession();
	    session.setAttribute("rid", rid);
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("addsubmission");
	    return mv;
	}
	
	
	@PostMapping("/addsubmission")
	public String addsubmission(HttpServletRequest request){
		ModelAndView mv= new ModelAndView();
		String text = request.getParameter("text");
		String comment = request.getParameter("comment");
		HttpSession session = request.getSession();
		int pid = (int) session.getAttribute("pid");
		int rid = (int) session.getAttribute("rid");
		int sid = (int) session.getAttribute("stuid");
		Submission s = new Submission();
		s.setComment(comment);
		s.setText(text);
		s.setReviewId(rid);
		s.setProjectId(pid);
		s.setSubmittedById(sid);
		
		Date date = new Date();
	    Timestamp cdate = new Timestamp(date.getTime());
	   String cd = cdate.toString();
	   s.setSubmissiontime(cd);
	   
	   submissionService.addsubmission(s);
	   reviewSerice.editstatustosubmitted(rid);
	   
	   
	    return "redirect:viewprojectbyid?id="+String.valueOf(pid);
		    
		    
		}
	@GetMapping("/viewsubmissiontoreview")
	public ModelAndView viewsubmissiontoreviewByid(@RequestParam("rid") int rid,HttpServletRequest request)
	{
	    HttpSession session = request.getSession();
	    session.setAttribute("rid", rid);
	    //int pid = (int) session.getAttribute("pid");
	    Submission s = submissionService.getSubmissionByrid(rid);
	    int sid = s.getSubid();
	    session.setAttribute("subid", sid);
	    System.out.println(s.getText());
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("viewsubmissionbyreviewid");
	    mv.addObject("submission", s);
	    return mv;
	}
	
	@PostMapping("/editfeedbackrating")
	public String editfeedbackrating(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		int rid = (int) session.getAttribute("rid");
		int pid = (int) session.getAttribute("pid");
		int subid = (int) session.getAttribute("subid");
		String feedback = request.getParameter("feedback");
		int rating = Integer.parseInt(request.getParameter("rating"));
		submissionService.editratingfeedback(rating, feedback, subid);
		reviewSerice.editstatustocompleted(rid);
		return "redirect:viewprojectFacultybyid?id="+String.valueOf(pid);
	}
	

}
