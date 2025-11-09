package api.pojo.acmetest;

public class registerUser {
	
	 
	   private String email;
	   private String password;
	   
	   
	   public registerUser(String email, String password) {
		   this.email=email;
		   this.password=password;
	   }


	    public void setEmail(String email) {
	        this.email = email;
	    }
	    public String getEmail() {
	        return email;
	    }
	    
	    public void setPassword(String password) {
	        this.password = password;
	    }
	    public String getPassword() {
	        return password;
	    }


}
