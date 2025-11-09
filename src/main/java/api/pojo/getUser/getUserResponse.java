package api.pojo.getUser;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class getUserResponse {
	   int page;

	   
	   int perPage;

	   
	   int total;

	   
	   int totalPages;

	   
	   List<Data> data;

	   
	   Support support;


	    public void setPage(int page) {
	        this.page = page;
	    }
	    public int getPage() {
	        return page;
	    }
	    
	    public void setPerPage(int perPage) {
	        this.perPage = perPage;
	    }
	    public int getPerPage() {
	        return perPage;
	    }
	    
	    public void setTotal(int total) {
	        this.total = total;
	    }
	    public int getTotal() {
	        return total;
	    }
	    
	    public void setTotalPages(int totalPages) {
	        this.totalPages = totalPages;
	    }
	    public int getTotalPages() {
	        return totalPages;
	    }
	    
	    public void setData(List<Data> data) {
	        this.data = data;
	    }
	    public List<Data> getData() {
	        return data;
	    }
	    
	    public void setSupport(Support support) {
	        this.support = support;
	    }
	    public Support getSupport() {
	        return support;
	    }


}
