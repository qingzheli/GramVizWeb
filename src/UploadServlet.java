import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadServlet
 */
 
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "upload";
	private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// checks if the request actually contains upload file
		 System.out.println("Parameters" + request.getQueryString());

		if (!ServletFileUpload.isMultipartContent(request)) {
		    PrintWriter writer = response.getWriter();
		    writer.println("Request does not contain upload data");
		    writer.flush();
		    return;
		}
				
		// configures upload settings
        DiskFileItemFactory diskConf = new DiskFileItemFactory();
        diskConf.setSizeThreshold(THRESHOLD_SIZE);
        diskConf.setRepository(new File(System.getProperty("java.io.tmpdir")));
         
        ServletFileUpload uploadConf = new ServletFileUpload(diskConf);
        uploadConf.setFileSizeMax(MAX_FILE_SIZE);
        uploadConf.setSizeMax(MAX_REQUEST_SIZE);
         
        // constructs the directory path to store upload file
        String uploadPath = getServletContext().getRealPath("")
            + File.separator + UPLOAD_DIRECTORY;
        System.out.println("UploadServlet: " + uploadPath);
        //System.out.println("getcontext:" +getServletContext().getRealPath(""));
        //System.out.println("UploadPath" + uploadPath);
        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        String fileName = null;
        try {
            // parses the request's content to extract file data
            List formItems = uploadConf.parseRequest(request);
            Iterator iter = formItems.iterator();
            
            // iterates over form's fields
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                System.out.println("itemName: " + item.getName());
                // processes only fields that are not form fields
                if (!item.isFormField()) {
                    fileName = new File(item.getName()).getName();
                    String filePath = uploadPath + File.separator + fileName;
                    File storeFile = new File(filePath);
                     
                    // saves the file on disk
                    item.write(storeFile);
                }
            }
            request.setAttribute("message", "Upload has been done successfully!");
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        }        
        
        // connect to database
     	
     	
     		     				  
        getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
		
	}

}
