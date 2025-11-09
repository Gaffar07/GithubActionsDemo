package api.pojo.getUser;

public class Data {
	
	  private  int id;

	  private String email;

	  
	  private  String firstName;

	  
	  private String lastName;

	  
	  private String avatar;


	    public void setId(int id) {
	        this.id = id;
	    }
	    public int getId() {
	        return id;
	    }
	    
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    public String getEmail() {
	        return email;
	    }
	    
	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }
	    public String getFirstName() {
	        return firstName;
	    }
	    
	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }
	    public String getLastName() {
	        return lastName;
	    }
	    
	    public void setAvatar(String avatar) {
	        this.avatar = avatar;
	    }
	    public String getAvatar() {
	        return avatar;
	    }


}
