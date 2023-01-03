package in.co.lib.mgt.bean;

public class BookBean extends BaseBean {
	
	private long bookCode;
	private String bookName;
	private String bookUpdate;
	private String writerName;
	
	
	
	public long getBookCode() {
		return bookCode;
	}
	public void setBookCode(long bookCode) {
		this.bookCode = bookCode;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookUpdate() {
		return bookUpdate;
	}
	public void setBookUpdate(String bookUpdate) {
		this.bookUpdate = bookUpdate;
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return bookName;
	}
	
	

}
