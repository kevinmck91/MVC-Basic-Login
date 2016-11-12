package beans;

public class User {

	private String email ; 
	private String password = "";
	private String message = "";
	
	////////Constructors /////////
	public User(){
		
	}
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	//////////Getters and setters for username + email//////////////
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
////////////Validation ///////////////
	public String getMessage() {
		return message;
	}
	
	public boolean validate(){
		/*	
			if(email == null){
				message="no email set";
				return false;
			}
			if(password == null){
				message="no PW set";
				return false;
			}
		*/
			if(!email.matches("\\w+@\\w+\\.\\w+")){
				message="invalid email set";
				return false;
			}
			
			if(password.length() < 8){
				message = "Password must be 8 chars";
				return false;
			}
			else if(password.matches("\\w*\\s+\\w*")){
				message = "PW cant have spaces";
				return false;
			}
			
			return true;
		}
}